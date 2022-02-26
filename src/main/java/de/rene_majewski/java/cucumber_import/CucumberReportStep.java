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

import java.util.ArrayList;
import java.util.List;

/**
 * Speichert die Daten zu einem Schritt.
 *
 * @author René Majewski
 * @since 0.1.0
 */
public class CucumberReportStep {
  /**
   * Speichert das Ergebnis der Ausführung des Schritts.
   */
  private CucumberReportResult result;

  /**
   * Speichert die Zeile in der der Schritt definiert ist.
   */
  private int line;

  /**
   * Speichert den Text des Schrittes.
   */
  private String name;

  /**
   * Speichert welche Methode der Schritt aufruft und die Daten zu Argumenten.
   */
  private CucumberReportMatch match;

  /**
   * Speichert das Schlüsselwort das mit dem Schritt verbunden ist.
   */
  private String keyword;

  /**
   * Speichert die Liste mit den Zeilen einer angehängten Tabellen.
   */
  private List<CucumberReportStepRow> rows;

  /**
   * Setzt die Attribute auf einen definierten Anfangswert.
   */
  public CucumberReportStep() {
    result = null;
    line = -1;
    name = null;
    match = null;
    keyword = null;
    rows = new ArrayList<>();
  }

  /**
   * Ermittelt das Ergebnis der Ausführung.
   *
   * @return Ergebnis der Ausführung.
   */
  public CucumberReportResult getResult() {
    return result;
  }

  /**
   * Setzt das Ergebnis der Ausführung.
   *
   * @param result Ergebnis der Ausführung.
   */
  public void setResult(CucumberReportResult result) {
    this.result = result;
  }

  /**
   * Ermittelt die Zeilennummer in der der Schritt definiert ist.
   *
   * @return Zeilennummer in der der Schritt definiert ist.
   */
  public int getLine() {
    return line;
  }

  /**
   * Setzt die Zeilennummer in der der Schritt definiert ist.
   *
   * @param line Zeilennummer in der der Schritt definiert ist.
   */
  public void setLine(int line) {
    this.line = line;
  }

  /**
   * Ermittelt den Text des Schrittes.
   *
   * @return Text des Schrittes.
   */
  public String getName() {
    return name;
  }

  /**
   * Setzt den Text des Schrittes.
   *
   * @param name Text des Schrittes.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Ermittelt die Daten zur aufzurufenden Methode und zu den Argumenten.
   *
   * @return Daten der aufzurufenden Methode und zu den Argumenten.
   */
  public CucumberReportMatch getMatch() {
    return match;
  }

  /**
   * Setzt die Daten zur aufzurufenden Method end zu den Argumenten.
   *
   * @param match Daten der aufzurufenden Methode und zu den Argumenten.
   */
  public void setMatch(CucumberReportMatch match) {
    this.match = match;
  }

  /**
   * Ermittelt das Schlüsselwort das mit dem Schritt verbunden ist.
   *
   * @return Schlüsselwort das mit dem Schritt verbunden ist.
   */
  public String getKeyword() {
    return keyword;
  }

  /**
   * Setzt das Schlüsselwort das mit dem Schritt verbunden ist.
   *
   * @param keyword Schlüsselwort das mit dem Schritt verbunden ist.
   */
  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  /**
   * Ermittelt die Zeilen einer möglichen Tabelle.
   *
   * @return Zeilen einer möglichen Tabelle.
   */
  public List<CucumberReportStepRow> getRows() {
    return rows;
  }

  /**
   * Setzt die Zeilen einer möglichen Tabelle.
   *
   * @param rows Zeilen einer möglichen Tabelle.
   */
  public void setRows(List<CucumberReportStepRow> rows) {
    this.rows = rows;
  }


}
