package de.rene_majewski.java.cucumber_import;

public class CucumberReportElement extends CucumberReportGeneral {
  public String start_timestamp = null;
  public CucumberReportHook[] before = null;
  public CucumberReportHook[] after = null;
  public String type = null;
  public CucumberReportStep[] steps = null;
}
