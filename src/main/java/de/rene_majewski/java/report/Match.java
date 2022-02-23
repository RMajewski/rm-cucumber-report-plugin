package de.rene_majewski.java.report;

import de.rene_majewski.java.cucumber_import.CucumberReportMatch;
import de.rene_majewski.java.cucumber_import.CucumberReportResult;

public class Match {
  private String location;
  private long duration;
  private String status;

  public Match(CucumberReportResult result, CucumberReportMatch match) {
    location = match.location;
    duration = result.duration;
    status = result.status;
  }

  public String getStatus() {
    return status;
  }

  public String getLocation() {
    return location;
  }

  public long getDuration() {
    return duration;
  }
}
