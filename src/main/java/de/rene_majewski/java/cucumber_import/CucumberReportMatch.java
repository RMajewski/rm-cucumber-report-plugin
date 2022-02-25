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
 * Speichert von einem Schritt welche Methode aufgerufen wird und die Variablen
 * die übergeben werden.
 *
 * @author René Majewski
 */
public class CucumberReportMatch {
  /**
   * Speichert eine Liste mit den Argumenten die der Methode übergeben werden
   * sollen.
   */
  private CucumberReportMatchArgument[] arguments;

  /**
   * Speichert die Methode die bei dem Schritt aufgerufen wird.
   */
  private String location;

  /**
   * Setzt die Attribute auf einen definierten Anfangswert.
   */
  public CucumberReportMatch() {
    arguments = null;
    location = null;
  }

  /**
   * Ermittelt die Liste mit den Argumenten die der Methode übergeben werden.
   *
   * @return Liste mit den Argumenten.
   */
  public CucumberReportMatchArgument[] getArguments() {
    return arguments;
  }

  /**
   * Setzt die Liste mit den Argumenten die der Methode übergeben werden.
   *
   * @param arguments Liste mit den Argumenten.
   */
  public void setArguments(CucumberReportMatchArgument[] arguments) {
    this.arguments = arguments;
  }

  /**
   * Ermittelt die Methode die Methode die bei diesem Schritt aufgerufen wird.
   *
   * @return Methode die aufgerufen wird.
   */
  public String getLocation() {
    return location;
  }

  /**
   * Setzt die Methode die bei diesem Schritt aufgerufen wird.
   *
   * @param location Methode die aufgerufen wird.
   */
  public void setLocation(String location) {
    this.location = location;
  }

}
