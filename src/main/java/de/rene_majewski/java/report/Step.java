/**
 * Copyright (C) 2022 René Majewski
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
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

/**
 * Bereit die Daten für einen Schritt auf und schreibt diese in den Bericht.
 *
 * @author René Majewski
 * @since 0.1.0
 */
public class Step extends Parent {
  /**
   * Speichert wie der Schritt beendet wurde.
   */
  private String status;

  /**
   * Speichert die Methode die bei diesem Schritt aufgerufen wurde.
   */
  private String method;

  /**
   * Speichert die Liste mit Argumenten die an die Methode übergeben wurden.
   */
  private List<StepArgument> arguments;

  /**
   * Initialisiert diese Klasse.
   *
   * Importiert die Daten aus {@link de.rene_majewski.java.cucumber_import.CucumberReportStep}-Objekt
   * und bereitet diese zum anzeigen auf.
   *
   * @param step a {@link de.rene_majewski.java.cucumber_import.CucumberReportStep} object
   */
  public Step(CucumberReportStep step) {
    super();
    arguments = new ArrayList<>();

    duration = step.getResult().getDuration();
    keyword = step.getKeyword();
    name = step.getName();
    method = step.getMatch().getLocation();
    pass = step.getResult().getStatus().equalsIgnoreCase(PASSED);
    skip = step.getResult().getStatus().equalsIgnoreCase(SKIP);
    undefined = step.getResult().getStatus().equals(UNDEFINED);
    status = step.getResult().getStatus();

    if (step.getMatch() != null && step.getMatch().getArguments() != null && step.getMatch().getArguments().length > 0) {
      for (CucumberReportMatchArgument arg : step.getMatch().getArguments()) {
        StepArgument stepArgument = new StepArgument(arg.getVal(), arg.getOffset());
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

      if (fp != null && !fp.getPickles().isEmpty()) {
        if (!fp.getPickles().get(0).getSteps().isEmpty()) {
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

  /** {@inheritDoc} */
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
