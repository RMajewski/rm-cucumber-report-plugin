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

import de.rene_majewski.java.cucumber_import.CucumberReportHook;

/**
 * Bereitet die Daten für einen Hook auf und schreibt diese in den Bericht.
 *
 * @author René Majewski
 * @since 0.1.0
 */
public class Hook {
  /**
   * Speichert die Methode die aufgerufen wurde.
   */
  private String location;

  /**
   * Speichert die Dauer der Ausführung.
   */
  private long duration;

  /**
   * Speichert wie der Hook beendet wurde.
   */
  private String status;

  /**
   * Initialisiert die Klasse.
   *
   * Importiert die Daten aus dem {@link de.rene_majewski.java.cucumber_import.CucumberReportHook}-Objekt
   * und bereitet die Daten auf.
   *
   * @param crh {@link de.rene_majewski.java.cucumber_import.CucumberReportHook}-Objekt
   *            dessen Daten aufbereitet werden sollen.
   */
  public Hook(CucumberReportHook crh) {
    location = crh.getMatch().getLocation();
    duration = crh.getResult().getDuration();
    status = crh.getResult().getStatus();
  }

  /**
   * Gibt den Wert zurück wie der Hook beendet wurde.
   *
   * @return Wert der angibt wie der Hook beendet wurde.
   */
  public String getStatus() {
    return status;
  }

  /**
   * Ermittelt die Methode die bei diesem Hook aufgerufen wurde.
   *
   * @return Methode die aufgerufen wurde.
   */
  public String getLocation() {
    return location;
  }

  /**
   * Ermittelt die Ausführungsdauer der Methode.
   *
   * @return Dauer der Ausführung.
   */
  public long getDuration() {
    return duration;
  }
}
