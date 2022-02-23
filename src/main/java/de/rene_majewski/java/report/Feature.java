package de.rene_majewski.java.report;

import java.util.ArrayList;
import java.util.List;
import org.apache.maven.doxia.sink.Sink;
import de.rene_majewski.java.cucumber_import.CucumberReport;
import de.rene_majewski.java.cucumber_import.CucumberReportElement;
import de.rene_majewski.java.helper.DateTimeHelper;
import de.rene_majewski.java.helper.SinkTableHelper;

public class Feature extends Parent{
  private List<Scenario> scenarios;
  private String featureFile;

  public Feature(CucumberReport reports) {
    super();
    scenarios = new ArrayList<>();

    featureFile = reports.uri.substring(reports.uri.indexOf(":") + 1);
    description = reports.description;

    for (CucumberReportElement element : reports.elements) {
      Scenario scenario = new Scenario(element);
      duration += scenario.getDuration();
      scenarios.add(scenario);
    }
  }

  public int getScenarioCount() {
    return scenarios.size();
  }

  public int getScenarioPassCount() {
    int result = 0;
    for (Scenario scenario : scenarios) {
      result += scenario.isPass() ? 1 : 0;
    }
    return result;
  }

  public int getScenarioErrorCount() {
    int result = 0;
    for (Scenario scenario : scenarios) {
      result += scenario.isError() ? 1 : 0;
    }
    return result;
  }

  public int getScenarioSkipCount() {
    int result = 0;
    for (Scenario scenario : scenarios) {
      result += scenario.isSkip() ? 1 : 0;
    }
    return result;
  }

  public int getScenarioFailureCount() {
    int result = 0;
    for (Scenario scenario : scenarios) {
      result += scenario.isFailure() ? 1 : 0;
    }
    return result;
  }

  @Override
  public void writeToReport(Sink sink) {
    sink.section(2, null);
    sink.sectionTitle(2, null);
    sink.text("Feature: " + featureFile);
    sink.sectionTitle_(2);

    // Allgemeine Informationen über das Feature
    sink.table();

    // Informationen über die Datei
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Feature-Datei", featureFile);
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Beschreibung", description);
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Ausführungsdauer", DateTimeHelper.formatDuration(duration));
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Anzahl Szenarien", String.valueOf(getScenarioCount()));
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Erfolgreich", String.valueOf(getScenarioPassCount()));
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Fehlgeschlagen", String.valueOf(getScenarioFailureCount()));
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Ausgelassen", String.valueOf(getScenarioSkipCount()));
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Aufgetretene Fehler", String.valueOf(getScenarioErrorCount()));

    // Tabelle und Abschnitt beenden
    sink.table_();
    sink.section_(2);

    for (Scenario scenario : scenarios) {
      scenario.writeToReport(sink);
    }
  }
}
