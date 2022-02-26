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
 *
 */
public class CucumberReportStepRow {
  /**
   * Speichert die Liste mit den Zellen.
   */
  private List<String> cells;

  public CucumberReportStepRow() {
    cells = new ArrayList<>();
  }

  /**
   * Gibt die gespeicherten Zellen zurück.
   *
   * @return Liste mit den Inhalt der Zellen.
   */
  public List<String> getCells() {
    return cells;
  }

  /**
   * Setzt die Liste mit den Inhalt der Zellen.
   *
   * @param cells Liste mit den Inhalt der Zellen.
   */
  public void setCells(List<String> cells) {
    this.cells = cells;
  }
}
