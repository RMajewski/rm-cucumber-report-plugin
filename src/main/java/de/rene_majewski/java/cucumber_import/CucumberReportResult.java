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
package de.rene_majewski.java.cucumber_import;

/**
 * Speichert wie lange die Ausführung gedauert hat und welches Ergebnis die
 * Ausführung hat.
 *
 * @author René Majewski
 * @since 0.1.0
 */
public class CucumberReportResult {
  private long duration;
  private String status;

  /**
   * Setzt die Attribute auf einen definierten Anfangswert.
   */
  public CucumberReportResult() {
    duration = 0;
    status = null;
  }

  /**
   * Ermittelt die Dauer der Ausführung.
   *
   * @return Dauer der Ausführung.
   */
  public long getDuration() {
    return duration;
  }

  /**
   * Setzt die Dauer der Ausführung.
   *
   * @param duration Dauer der Ausführung.
   */
  public void setDuration(long duration) {
    this.duration = duration;
  }

  /**
   * Ermittelt das Ergebnis der Ausführung.
   *
   * @return Ergebnis der Ausführung.
   */
  public String getStatus() {
    return status;
  }

  /**
   * Setzt das Ergebnis der Ausführung.
   *
   * @param status Ergebnis der Ausführung.
   */
  public void setStatus(String status) {
    this.status = status;
  }


}
