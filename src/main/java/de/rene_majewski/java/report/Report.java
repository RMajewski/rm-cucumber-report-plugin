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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.maven.doxia.sink.Sink;
import de.rene_majewski.java.cucumber_import.CucumberReport;
import de.rene_majewski.java.helper.DateTimeHelper;
import de.rene_majewski.java.helper.SinkTableHelper;

/**
 * Bereitet die Daten des Cucumber-Reports und schreibt diese in den Bericht.
 *
 * @author René Majewski
 * @since 0.1.0
 */
public class Report extends Parent {
  /**
   * Speichert eine Liste mit den einzelnen Feature-Dateien.
   */
  private List<Feature> features;

  /**
   * Initialisiert die Klasse.
   *
   * Importiert die Daten aus dem
   * {@link de.rene_majewski.java.cucumber_import.CucumberReport}-Array und
   * bereitet diese zur Ausgabe im Bericht auf.
   *
   * @param data {@link de.rene_majewski.java.cucumber_import.CucumberReport}-Array
   *             deren Daten importiert und aufbereitet werden soll.
   */
  public Report(CucumberReport[] data) {
    super();
    features = new ArrayList<>();

    startedAt = LocalDateTime.now();

    for (CucumberReport cr : data) {
      Feature feature = new Feature(cr);
      duration += feature.getDuration();
      features.add(feature);
    }
  }

  /**
   * Fügt eine Feature-Datei zur Liste hinzu
   *
   * @param feature Das {@link de.rene_majewski.java.report.Feature}-Datei die
   *                zur Liste hinzugefügt werden soll.
   */
  public void addFeature(Feature feature) {
    features.add(feature);
  }

  /**
   * Ermittelt die Anzahl an importierten Feature-Dateien.
   *
   * @return Anzahl an importierten Feature-Dateien.
   */
  public int getFeaturesCount() {
    return features.size();
  }

  @Deprecated
  public int getScenarioCount() {
    int result = 0;
    for (Feature feature : features) {
      result += feature.getScenarioCount();
    }
    return result;
  }

  /**
   * Ermittelt die Anzahl an erfolgreich abgeschlossen Feature-Dateien.
   *
   * @return Anzahl an erfolgreich abgeschlossen Feature-Dateien.
   */
  public int getFeaturesPass() {
    int result = 0;
    for (Feature feature : features) {
      result += feature.isPass() ? 1 : 0;
    }
    return result;
  }

  /**
   * Ermittelt die Anzahl an Feature-Dateien die mit einem Fehler abgeschlossen
   * wurden.
   *
   * @return Anzahl an Feature-Dateien die mit einem Fehler beendet wurden.
   */
  public int getFeaturesError() {
    int result = 0;
    for (Feature feature : features) {
      result += feature.isError() ? 1 : 0;
    }
    return result;
  }

  /**
   * Ermittelt die Anzahl an Feature-Dateien die übersprungen wurden.
   *
   * @return Anzahl Feature-Dateien die übersprungen wurden.
   */
  public int getFeaturesSkip() {
    int result = 0;
    for (Feature feature : features) {
      result += feature.isSkip() ? 1 : 0;
    }
    return result;
  }

  @Deprecated
  public int getFeaturesPending() {
    int result = 0;
    for (Feature feature : features) {
      result += feature.isPending() ? 1 : 0;
    }
    return result;
  }

  /**
   * Ermittelt die Anzahl Feature-Dateien die fehlerhaft beendet wurden.
   *
   * @return Anzahl an Feature-Dateien die fehlerhaft sind.
   */
  public int getFeaturesFailure() {
    int result = 0;
    for (Feature feature : features) {
      result += feature.isFailure() ? 1 : 0;
    }
    return result;
  }

  /**
   * Ermittelt die Anzahl der Szenarios die in allen Feature-Dateien definiert
   * wurden.
   *
   * @return Anzahl der Szenarios in allen Feature-Dateien.
   */
  public int getAllScenarioCount() {
    int result = 0;
    for (Feature feature : features) {
      result += feature.getScenarioCount();
    }
    return result;
  }

  /**
   * Ermittelt die Anzahl der erfolgreich abgeschlossen Szenarien aus allen
   * Feature-Dateien.
   *
   * @return Anzahl an erfolgreich abgeschlossen Szenarien.
   */
  public int getAllScenarioPassCount() {
    int result = 0;
    for (Feature feature : features) {
      result += feature.getScenarioPassCount();
    }
    return result;
  }

  /**
   * Ermittelt die Anzahl an nicht erfolgreich abgeschlossenen Szenarien aus
   * allen Feature-Dateien.
   *
   * @return Anzahl an fehlerhaften Szenarien.
   */
  public int getAllScenarioFailure() {
    int result = 0;
    for (Feature feature : features) {
      result += feature.getScenarioFailureCount();
    }
    return result;
  }

  /**
   * Ermittelt die Anzahl an Szenarien die mit einem Fehler beendet wurden aus
   * allen Feature-Dateien.
   *
   * @return Anzahl an Szenarien die mit einem Fehler beendet wurden.
   */
  public int getAllScenarioError() {
    int result = 0;
    for (Feature feature : features) {
      result += feature.getScenarioErrorCount();
    }
    return result;
  }

  /**
   * Ermittelt die Anzahl an Szenarien die übersprungen wurden aus allen
   * Feature-Dateien.
   *
   * @return Anzahl an Szenarien die übersprungen wurden.
   */
  public int getAllScenariosSkip() {
    int result = 0;
    for (Feature feature : features) {
      result += feature.getScenarioSkipCount();
    }
    return result;
  }

  /** {@inheritDoc} */
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
    SinkTableHelper.writeRow2ColsFirstBoldSecondProgress(sink, "Erfolgreich abgeschlossene Szenarios", getAllScenarioPassCount(), getAllScenarioCount(), String.valueOf(getAllScenarioPassCount()));
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
