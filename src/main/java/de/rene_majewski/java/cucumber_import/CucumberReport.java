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
 * Speichert die Daten für ein Cucumber-Report.
 *
 * @author René Majewski
 */
public class CucumberReport extends CucumberReportGeneral {
  /**
   * Speichert die einzelnen Elemente der Feature-Datei.
   */
  private CucumberReportElement[] elements;

  /**
   * Gibt an für welche Feature-Datei der Report gedacht ist.
   */
  private String uri;

  /**
   * Speichert die einzelnen Tags der Feature-Datei.
   */
  private String[] tags;

  /**
   * Setzt die Attribute auf einen definierten Anfangswert.
   */
  public CucumberReport() {
    elements = null;
    uri = null;
    tags = null;
  }

  /**
   * Gibt das Array mit den einzelnen Elementen zurück.
   *
   * @return Array mit den einzelnen Elementen.
   */
  public CucumberReportElement[] getElements() {
    return elements;
  }

  /**
   * Setzt das Array mit den einzelnen Elementen.
   *
   * @param elements Array mit den einzelnen Elementen was gespeichert werden
   *                 soll.
   */
  public void setElements(CucumberReportElement[] elements) {
    this.elements = elements;
  }

  /**
   * Gibt die Datei zurück für die der Report gedacht ist.
   *
   * @return Datei für die der Report gedacht ist.
   */
  public String getUri() {
    return uri;
  }

  /**
   * Setzt die Datei für die der Report gedacht ist.
   *
   * @param uri Datei für die der Report gedacht ist.
   */
  public void setUri(String uri) {
    this.uri = uri;
  }

  /**
   * Gibt das Array mit den gesetzten Tags zurück.
   *
   * @return Array mit den gesetzten Tags.
   */
  public String[] getTags() {
    return tags;
  }

  /**
   * Setzt die Tags.
   *
   * @param tags Array mit den einzelnen Tags.
   */
  public void setTags(String[] tags) {
    this.tags = tags;
  }
}
