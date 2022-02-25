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

/**
 * Bereitet die Daten für ein Argument, dass in die aufzurufende Methode bei
 * einem Schritt übergeben wurde, auf und schreibt diese in den Bericht.
 *
 * @author René Majewski
 * @since 0.1.0
 */
public class StepArgument {
  /**
   * Speichert den Wert des Arguments.
   */
  private String value;

  /**
   * Speichert die Stelle an der das Argument im Text des Schritts beginnt.
   */
  private int offset;

  /**
   * Initialisiert die Klasse.
   *
   * @param value Wert des Arguments.
   *
   * @param offset Stelle an der das Argument im text des Schrittes beginnt.
   */
  public StepArgument(String value, int offset) {
    this.value = value;
    this.offset = offset;
  }

  /**
   * Ermittelt den Wert des Arguments.
   *
   * @return Wert des Arguments.
   */
  public String getValue() {
    return value;
  }

  /**
   * Setzt den Wert des Arguments.
   *
   * @param value Wert des Arguments.
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * Ermittelt die Stelle an der das Argument im Text des Schritts beginnt.
   *
   * @return Stelle an der das Argument im Text des Schritts beginnt.
   */
  public int getOffset() {
    return offset;
  }

  /**
   * Setzt die Stelle an der das Argument im Text des Schritts beginnt.
   *
   * @param offset Stellt an der das Argument im Text Schritts beginnt.
   */
  public void setOffset(int offset) {
    this.offset = offset;
  }
}
