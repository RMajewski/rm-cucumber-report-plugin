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
 * Speichert die Daten zu einem Argument das im Schritt der auszuführenden
 * Methode übergeben wird.
 *
 * @author René Majewski
 * @since 0.1.0
 */
public class CucumberReportMatchArgument {
  /**
   * Speichert den Wert des Arguments dass der Methode übergeben wird.
   */
  private String val;

  /**
   * Speichert an welcher Stelle das übergebene Argument in der Zeichenkette
   * steht, die den Text des Schrittes enthält.
   */
  private int offset;

  /**
   * Setzt die Attribute auf einen definierten Anfangswert.
   */
  public CucumberReportMatchArgument() {
    val = null;
    offset = -1;
  }

  /**
   * Ermittelt den Wert des Arguments.
   *
   * @return Wert des Arguments.
   */
  public String getVal() {
    return val;
  }

  /**
   * Setzt den Wert des Arguments.
   *
   * @param val Wert des Arguments.
   */
  public void setVal(String val) {
    this.val = val;
  }

  /**
   * Ermittelt die Stelle in der der Wert in der Zeichenkette mit dem Text des
   * Schrittes steht.
   *
   * @return Stelle an der der Wert des Argumentes beginnt.
   */
  public int getOffset() {
    return offset;
  }

  /**
   * Setzt die Stelle in der der Wert in der Zeichenkette mit dem Text des
   * Schrittes beginnt.
   *
   * @param offset Stelle an der der Wert des Argumentes beginnt.
   */
  public void setOffset(int offset) {
    this.offset = offset;
  }
}
