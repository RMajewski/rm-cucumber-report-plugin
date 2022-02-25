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
package de.rene_majewski.java.helper;

/**
 * Hilfsklasse die Konstanten definiert die in mehreren Klassen genutzt werden.
 *
 * @author René Majewski
 * @since 0.1.0
 */
public class ConstHelper {
  /**
   * Konstante die angibt was im Status steht, wenn die Ausführung erfolgreich
   * war.
   */
  public static final String PASSED = "passed";

  /**
   * Gibt an was im Status steht, wenn die Ausführung übersprungen wurde.
   */
  public static final String SKIP = "skipped";

  /**
   * Gibt an was im Status steht, wenn noch keine Methode zu einem Schritt
   * definiert wurde.
   */
  public static final String UNDEFINED = "undefined";

  /**
   * In einer Hilfsklasse wird kein öffentlicher Konstruktor benötigt.
   */
  private ConstHelper() {
    throw new IllegalStateException("Utility class");
  }
}
