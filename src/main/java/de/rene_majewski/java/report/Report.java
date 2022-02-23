package de.rene_majewski.java.report;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.maven.doxia.sink.Sink;
import de.rene_majewski.java.cucumber_import.CucumberReport;
import de.rene_majewski.java.helper.DateTimeHelper;
import de.rene_majewski.java.helper.SinkTableHelper;

public class Report extends Parent {
  private List<Feature> features;

  public Report(CucumberReport data[]) {
    super();
    features = new ArrayList<>();

    startedAt = LocalDateTime.now();

    for (CucumberReport cr : data) {
      Feature feature = new Feature(cr);
      duration += feature.getDuration();
      features.add(feature);
    }
  }

  public void addFeature(Feature feature) {
    features.add(feature);
  }

  public int getFeaturesCount() {
    return features.size();
  }

  public int getScenarioCount() {
    int result = 0;
    for (Feature feature : features) {
      result += feature.getScenarioCount();
    }
    return result;
  }

  public int getFeaturesPass() {
    int result = 0;
    for (Feature feature : features) {
      result += feature.isPass() ? 1 : 0;
    }
    return result;
  }

  public int getFeaturesError() {
    int result = 0;
    for (Feature feature : features) {
      result += feature.isError() ? 1 : 0;
    }
    return result;
  }

  public int getFeaturesSkip() {
    int result = 0;
    for (Feature feature : features) {
      result += feature.isSkip() ? 1 : 0;
    }
    return result;
  }

  public int getFeaturesPending() {
    int result = 0;
    for (Feature feature : features) {
      result += feature.isPending() ? 1 : 0;
    }
    return result;
  }

  public int getFeaturesFailure() {
    int result = 0;
    for (Feature feature : features) {
      result += feature.isFailure() ? 1 : 0;
    }
    return result;
  }

  public int getAllScenarioCount() {
    int result = 0;
    for (Feature feature : features) {
      result += feature.getScenarioCount();
    }
    return result;
  }

  public int getAllScenarioPassCount() {
    int result = 0;
    for (Feature feature : features) {
      result += feature.getScenarioPassCount();
    }
    return result;
  }

  public int getAllScenarioFailure() {
    int result = 0;
    for (Feature feature : features) {
      result += feature.getScenarioFailureCount();
    }
    return result;
  }

  public int getAllScenarioError() {
    int result = 0;
    for (Feature feature : features) {
      result += feature.getScenarioErrorCount();
    }
    return result;
  }

  public int getAllScenariosSkip() {
    int result = 0;
    for (Feature feature : features) {
      result += feature.getScenarioSkipCount();
    }
    return result;
  }

  @Override
  public void writeToReport(Sink sink) {
    // Allgemeine Informationen
    sink.section(1, null);
    sink.sectionTitle(1, null);
    sink.text("Cucumber-Report");
    sink.sectionTitle_(1);
    sink.table();
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Report erstellt", DateTimeHelper.formatDateTime(startedAt));
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Hinweis", "Fehlschläge werden erwartet und durch Behauptungen überprüft während Fehler unerwartet sind.");
    sink.table_();
    sink.section_(1);


    // Übersicht Feature-Dateien
    sink.section(2, null);
    sink.sectionTitle(2, null);
    sink.text("Übersicht über Features");
    sink.sectionTitle_(2);
    sink.table();
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Ausführungsdauer gesamt", DateTimeHelper.formatDuration(duration));
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Anzahl Feature-Dateien", String.valueOf(getFeaturesCount()));
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Erfolgreich abgeschlossene Feature-Dateien", String.valueOf(getFeaturesPass()));
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Fehlgeschlagene Feature-Dateien", String.valueOf(getFeaturesFailure()));
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Abgebrochene Feature-Dateien", String.valueOf(getFeaturesSkip()));
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Anzahl aufgetretene Fehler in Feature-Dateien", String.valueOf(getFeaturesError()));
    sink.table_();
    sink.section_(2);

    // Übersicht Szenarios
    sink.section(2, null);
    sink.sectionTitle(2, null);
    sink.text("Übersicht über Szenarios");
    sink.sectionTitle_(2);
    sink.table();
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Anzahl Szenarios", String.valueOf(getAllScenarioCount()));
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Erfolgreich abgeschlossene Szenarios", String.valueOf(getAllScenarioPassCount()));
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Fehlgeschlagene Szenarios", String.valueOf(getAllScenarioFailure()));
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Abgebrochene Szenarios", String.valueOf(getAllScenariosSkip()));
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Anzahl aufgetretene Fehler in Szenarios", String.valueOf(getAllScenarioError()));
    sink.table_();
    sink.section_(2);

    // Einzelne Reports für Feature-Dateien
    for (Feature feature : features) {
      feature.writeToReport(sink);
    }
  }
}
