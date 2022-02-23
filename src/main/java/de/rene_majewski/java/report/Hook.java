package de.rene_majewski.java.report;

import de.rene_majewski.java.cucumber_import.CucumberReportHook;

public class Hook {
  private String location;
  private long duration;
  private String status;

  public Hook(CucumberReportHook crh) {
    location = crh.match.location;
    duration = crh.result.duration;
    status = crh.result.status;
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
