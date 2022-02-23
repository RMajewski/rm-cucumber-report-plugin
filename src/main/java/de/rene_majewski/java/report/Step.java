package de.rene_majewski.java.report;

import static de.rene_majewski.java.helper.ConstHelper.PASSED;
import org.apache.maven.doxia.sink.Sink;
import de.rene_majewski.java.cucumber_import.CucumberReportStep;
import de.rene_majewski.java.helper.SinkTableHelper;

public class Step extends Parent {
  private String status;
  private String method;

  public Step(CucumberReportStep step) {
    super();
    duration = step.result.duration;
    keyword = step.keyword;
    name = step.name;
    method = step.match.location;
    pass = step.result.status.equalsIgnoreCase(PASSED);
    status = step.result.status;
  }

  @Override
  public void writeToReport(Sink sink) {
    SinkTableHelper.writeScenarioRow(
      sink,
      keyword,
      name,
      status,
      duration,
      method,
      pass,
      failure,
      error,
      skip
    );
  }
}
