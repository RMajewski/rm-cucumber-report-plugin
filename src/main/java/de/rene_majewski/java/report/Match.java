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

import de.rene_majewski.java.cucumber_import.CucumberReportMatch;
import de.rene_majewski.java.cucumber_import.CucumberReportResult;

/**
 * Bereitet die Daten für die Ausführung eines Schrittes oder Szenarios auf.
 *
 * @author René Majewski
 * @since 0.1.0
 */
public class Match {
  /**
   * Speichert den Namen der Methode die aufgerufen wurde.
   */
  private String location;

  /**
   * Speichert die Ausführungsdauer.
   */
  private long duration;

  /**
   * Speichert wie die Methode beendet wurde.
   */
  private String status;

  /**
   * Initialisiert die Klasse.
   *
   * Importiert die Daten aus dem {@link de.rene_majewski.java.cucumber_import.CucumberReportResult}-Objekt
   * und {@link de.rene_majewski.java.cucumber_import.CucumberReportMatch}-Objekt,
   * verknüpft diese und Bereitet sie zur Ausgabe im Bericht vor.
   *
   * @param result {@link de.rene_majewski.java.cucumber_import.CucumberReportResult}-Objekt
   *               dessen Daten aufbereitet und mit den Daten aus {@code match}
   *               verknüpft werden sollen.
   *
   * @param match {@link de.rene_majewski.java.cucumber_import.CucumberReportMatch}-Objekt
   *              dessen Daten aufbereitet und mit den Daten aus {@code result}
   *              verknüpft werden sollen.
   */
  public Match(CucumberReportResult result, CucumberReportMatch match) {
    location = match.getLocation();
    duration = result.getDuration();
    status = result.getStatus();
  }

  /**
   * Ermittelt wie die aufgerufene Methode beendet wurde.
   *
   * @return Wie wurde die aufgerufene Methode beendet?
   */
  public String getStatus() {
    return status;
  }

  /**
   * Ermittelt welche Methode aufgerufen wurde.
   *
   * @return Name der aufgerufenen Methode.
   */
  public String getLocation() {
    return location;
  }

  /**
   * Ermittelt wie lange die Ausführungsdauer war.
   *
   * @return Dauer der Ausführung der aufgerufen Methode.
   */
  public long getDuration() {
    return duration;
  }
}
