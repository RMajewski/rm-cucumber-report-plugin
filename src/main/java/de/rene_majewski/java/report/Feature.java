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

import java.util.ArrayList;
import java.util.List;
import org.apache.maven.doxia.sink.Sink;
import de.rene_majewski.java.cucumber_import.CucumberReport;
import de.rene_majewski.java.cucumber_import.CucumberReportElement;
import de.rene_majewski.java.helper.DateTimeHelper;
import de.rene_majewski.java.helper.SinkTableHelper;

/**
 * Bereitet die Daten für eine Feature-Datei auf und schreibt diese in den
 * Bericht.
 *
 * @author René Majewski
 * @since 0.1.0
 */
public class Feature extends Parent{
  /**
   * Speichert eine Liste mit den Szenarios die in dieser Feature-Datei
   * definiert sind.
   */
  private List<Scenario> scenarios;

  /**
   * Speichert den Namen der Feature-Datei.
   */
  private String featureFile;

  /**
   * Initialisiert die Klasse.
   *
   * Importiert die Daten aus dem {@link de.rene_majewski.java.cucumber_import.CucumberReport}
   * und bereitet sie zum anzeigen vor.
   *
   * @param reports {@link de.rene_majewski.java.cucumber_import.CucumberReport}-Objekt
   *                dessen Daten aufbereite und in den Bericht geschrieben
   *                werden sollen.
   */
  public Feature(CucumberReport reports) {
    super();
    scenarios = new ArrayList<>();

    featureFile = reports.getUri().substring(reports.getUri().indexOf(":") + 1);
    description = reports.getDescription();

    for (CucumberReportElement element : reports.getElements()) {
      Scenario scenario = new Scenario(element);
      duration += scenario.getDuration();
      scenarios.add(scenario);
    }
  }

  /**
   * Ermittelt die Anzahl der Szenarios die in der Feature-Datei definiert
   * wurden.
   *
   * @return Anzahl der definierten Szenarios in der Feature-Datei.
   */
  public int getScenarioCount() {
    return scenarios.size();
  }

  /**
   * Ermittelt die Anzahl der Szenarios die erfolgreich abgeschlossen wurden.
   *
   * @return Anzahl der erfolgreich abgeschlossen Szenarios.
   */
  public int getScenarioPassCount() {
    int result = 0;
    for (Scenario scenario : scenarios) {
      result += scenario.isPass() ? 1 : 0;
    }
    return result;
  }

  /**
   * Ermittelt die Anzahl der Szenarios die mit einem Fehler beendet wurden.
   *
   * @return Anzahl der Szenarios die mit einem Fehler beendet wurden.
   */
  public int getScenarioErrorCount() {
    int result = 0;
    for (Scenario scenario : scenarios) {
      result += scenario.isError() ? 1 : 0;
    }
    return result;
  }

  /**
   * Ermittelt die Anzahl der Szenarios die übersprungen wurden.
   *
   * @return Anzahl der Szenarios die übersprungen wurden.
   */
  public int getScenarioSkipCount() {
    int result = 0;
    for (Scenario scenario : scenarios) {
      result += scenario.isSkip() ? 1 : 0;
    }
    return result;
  }

  /**
   * Ermittelt die Anzahl der Szenarios die nicht erfolgreich waren.
   *
   * @return Anzahl der nicht erfolgreichen Szenarios.
   */
  public int getScenarioFailureCount() {
    int result = 0;
    for (Scenario scenario : scenarios) {
      result += scenario.isFailure() ? 1 : 0;
    }
    return result;
  }

  /** {@inheritDoc} */
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
    SinkTableHelper.writeRow2ColsFirstBoldSecondProgress(sink, "Erfolgreich", getScenarioPassCount(), getScenarioCount(), String.valueOf(getScenarioPassCount()));
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
