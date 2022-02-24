package de.rene_majewski.java.report;

import static de.rene_majewski.java.helper.ConstHelper.PASSED;
import static de.rene_majewski.java.helper.ConstHelper.SKIP;
import static de.rene_majewski.java.helper.ConstHelper.UNDEFINED;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.apache.maven.doxia.sink.Sink;
import de.rene_majewski.java.cucumber_import.CucumberReportMatchArgument;
import de.rene_majewski.java.cucumber_import.CucumberReportStep;
import de.rene_majewski.java.helper.JavaSnippet;
import de.rene_majewski.java.helper.SinkTableHelper;
import io.cucumber.core.feature.FeatureParser;
import io.cucumber.core.resource.Resource;
import io.cucumber.core.snippets.SnippetGenerator;
import io.cucumber.core.snippets.SnippetType;
import io.cucumber.cucumberexpressions.ParameterTypeRegistry;

public class Step extends Parent {
  private String status;
  private String method;
  private List<StepArgument> arguments;

  public Step(CucumberReportStep step) {
    super();
    arguments = new ArrayList<>();

    duration = step.result.duration;
    keyword = step.keyword;
    name = step.name;
    method = step.match.location;
    pass = step.result.status.equalsIgnoreCase(PASSED);
    skip = step.result.status.equalsIgnoreCase(SKIP);
    undefined = step.result.status.equals(UNDEFINED);
    status = step.result.status;

    if (step.match != null && step.match.arguments != null && step.match.arguments.length > 0) {
      for (CucumberReportMatchArgument arg : step.match.arguments) {
        StepArgument stepArgument = new StepArgument(arg.val, arg.offset);
        arguments.add(stepArgument);
      }
    }

    // Snippet generieren wenn es undefiniert ist.
    if (method == null || method.isEmpty()) {
      undefined = true;
      final String  source = "" +
        "# language: de\n" +
        "\n" +
        "Funktionalität: Generate Feature\n" +
        "  Szenario: Generate Step\n" +
        "    " + keyword + " " + name + "\n";
      io.cucumber.core.gherkin.Feature fp = new FeatureParser(UUID::randomUUID).parseResource(new Resource() {
        @Override
        public URI getUri() {
          return URI.create("/generate.feature");
        }

        @Override
        public InputStream getInputStream() {
          return new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8));
        }
      }).orElse(null);

      if (fp.getPickles().size() > 0) {
        if (fp.getPickles().get(0).getSteps().size() > 0) {
          io.cucumber.core.gherkin.Step newStep = fp.getPickles().get(0).getSteps().get(0);
          List<String> snippet = new SnippetGenerator(
            new JavaSnippet(),
            new ParameterTypeRegistry(Locale.GERMAN)
          ).getSnippet(newStep, SnippetType.CAMELCASE);
          method = String.join("\n", snippet);
        } else {
          method = "Keine Steps bei Generierung";
        }
      } else {
        method = "Keine Pickles bei Generierung";
      }
    }
  }

  @Override
  public void writeToReport(Sink sink) {
    SinkTableHelper.writeScenarioRow(
      sink,
      keyword,
      name,
      arguments,
      status,
      duration,
      method,
      pass,
      failure,
      error,
      skip,
      undefined
    );
  }
}
