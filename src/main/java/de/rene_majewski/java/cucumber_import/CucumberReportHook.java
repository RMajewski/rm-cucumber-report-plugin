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
 * Speichert die Daten des eines aufgerufen Hooks aus den Cucumber-Daten.
 *
 * @author René Majewski
 * @since 0.1.0
 */
public class CucumberReportHook {
  /**
   * Speichert die Daten des Ergebnisses.
   */
  private CucumberReportResult result;

  /**
   * Speichert die Daten des Resultats.
   */
  private CucumberReportMatch match;

  public CucumberReportHook() {
    result = null;
    match = null;
  }

  /**
   * Ermittelt die Daten des Ergebnisses.
   *
   * @return Daten des Ergebnisses
   */
  public CucumberReportResult getResult() {
    return result;
  }

  /**
   * Setzt die Daten des Ergebnisses.
   *
   * @param result Daten des Ergebnisses.
   */
  public void setResult(CucumberReportResult result) {
    this.result = result;
  }

  /**
   * Ermittelt die Daten des Resultats.
   *
   * @return Daten des Resultats.
   */
  public CucumberReportMatch getMatch() {
    return match;
  }

  /**
   * Setzt die Daten des Resultats.
   *
   * @param match Daten des Resultats.
   */
  public void setMatch(CucumberReportMatch match) {
    this.match = match;
  }


}
