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

import java.time.LocalDateTime;
import org.apache.maven.doxia.sink.Sink;

/**
 * Stellt die Attribute bereit die in den einzelnen Report-Klassen gebraucht
 * werden.
 *
 * @author René Majewski
 */
public abstract class Parent {
  /**
   * Wurde der Abschnitt erfolgreich abgeschlossen? {@code true} bei
   * erfolgreichen Abschluss. {@code false} wenn nicht erfolgreich
   * abgeschlossen wurde.
   *
   * Bei nicht erfolgreichen Abschluss wird durch die Attribute {@link #error},
   * {@link #failure}, {@link #pending}, {@link #undefined} und {@link #skip}
   * näher beschrieben warum der Abschluss nicht erfolgreich war.
   */
  protected boolean pass;

  /**
   * Wurde der Abschnitt mit einem Fehler beendet? {@code true} es ist ein
   * Fehler aufgetreten. Bei {@code false} nicht.
   */
  protected boolean error;

  /**
   * Wurde der Abschnitt nicht erfolgreich beendet? {@code true} der Abschnitt
   * ist fehlerhaft. {@code false} Der Abschnitt ist nicht fehlerhaft.
   */
  protected boolean failure;

  @Deprecated
  protected boolean pending;

  /**
   * Wurde noch keine Methode für den Abschnitt definiert? {@code true} es
   * wurde noch keine Methode definiert. {@code false} es wurde bereits eine
   * Methode definiert.
   */
  protected boolean undefined;

  /**
   * Wurde der Abschnitt übersprungen? {@code true} der Abschnitt wurde
   * übersprungen. {@code false} der Abschnitt wurde nicht übersprungen.
   */
  protected boolean skip;

  /**
   * Speichert die Beschreibung zu einem Abschnitt.
   */
  protected String description;

  /**
   * Speichert das Datum und die Uhrzeit wann der Abschnitt ausgeführt wurde.
   */
  protected LocalDateTime startedAt;

  /**
   * Speichert den Namen des Abschnittes.
   */
  protected String name;

  /**
   * Speichert das Schlüsselwort das mit dem Abschnitt verbunden ist.
   */
  protected String keyword;

  /**
   * Speichert die Dauer der Ausführung des Abschnitts.
   */
  protected long duration;

  /**
   * Setzt die Attribute auf einen definierten Anfangswert.
   */
  public Parent() {
    pass = false;
    error = false;
    failure = false;
    pending = false;
    skip = false;
    undefined = false;
    startedAt = null;
    description = "";
    name = "";
    keyword = "";
    duration = 0;
  }

  /**
   * Ermittelt ob der Abschnitt erfolgreich beendet wurde.
   *
   * @return Wurde der Abschnitt erfolgreich beendet?
   *
   * @see #pass
   */
  public boolean isPass() {
    return pass;
  }

  /**
   * Setzt ob der Abschnitt erfolgreich beendet wurde.
   *
   * @param pass Wurde der Abschnitt erfolgreich beendet?
   *
   * @see #pass
   */
  public void setPass(boolean pass) {
    this.pass = pass;
  }

  /**
   * Ermittelt ob ein Fehler im Abschnitt aufgetreten ist.
   *
   * @return Ist ein Fehler im Abschnitt aufgetreten?
   *
   * @see #error
   */
  public boolean isError() {
    return error;
  }

  /**
   * Setzt ob ein Fehler im Abschnitt aufgetreten ist.
   *
   * @param error Ist ein Fehler im Abschnitt aufgetreten?
   *
   * @see #error
   */
  public void setError(boolean error) {
    this.error = error;
  }

  /**
   * Ermittelt ob der Abschnitt fehlerhaft beendet wurde.
   *
   * @return Ist der Abschnitt fehlerhaft beendet wurden?
   *
   * @see #failure
   */
  public boolean isFailure() {
    return failure;
  }

  /**
   * Setzt ob der Abschnitt fehlerhaft beendet wurde.
   *
   * @param failure Ist der Abschnitt fehlerhaft beendet wurden?
   *
   * @see #failure
   */
  public void setFailure(boolean failure) {
    this.failure = failure;
  }

  @Deprecated
  public boolean isPending() {
    return pending;
  }

  @Deprecated
  public void setPending(boolean pending) {
    this.pending = pending;
  }

  /**
   * Ermittelt ob der Abschnitt übersprungen wurde.
   *
   * @return Wurde der Abschnitt übersprungen?
   *
   * @see #skip
   */
  public boolean isSkip() {
    return skip;
  }

  /**
   * Setzt ob der Abschnitt übersprungen wurde.
   *
   * @param skip Wurde der Abschnitt übersprungen?
   *
   * @see #skip
   */
  public void setSkip(boolean skip) {
    this.skip = skip;
  }

  /**
   * Ermittelt die Beschreibung des Abschnitts.
   *
   * @return Beschreibung des Abschnitts. {@code null} wenn keine Beschreibung
   *         zu dem Abschnitt angegeben wurde.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Setzt die Beschreibung des Abschnitts.
   *
   * @param description Beschreibung des Abschnitts. {@code null} wenn keine
   *                    Beschreibung zu dem Abschnitt angegeben wurde.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Ermittelt das Datum und die Uhrzeit wann die Ausführung des Abschnitts
   * begonnen hat.
   *
   * @return Datum und Uhrzeit des Zeitpunkt zudem die Ausführung begonnen hat.
   */
  public LocalDateTime getStartedAt() {
    return startedAt;
  }

  /**
   * Setzt das Datum und die Uhrzeit wan die Ausführung des Abschnitts
   * begonnen hat.
   *
   * @param startetAt Datum und Uhrzeit des Zeitpunkt zudem die Ausführung
   *                  begonnen hat.
   */
  public void setStartedAt(LocalDateTime startetAt) {
    this.startedAt = startetAt;
  }

  /**
   * Ermittelt den Namen des Abschnitts.
   *
   * @return Name des Abschnitts. {@code null} wenn kein Name definiert wurde.
   */
  public String getName() {
    return name;
  }

  /**
   * Setzt den Namen des Abschnitts.
   *
   * @param name Name des Abschnitts. {@code null} wenn kein Name definiert
   *             wurde.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Ermittelt das Schlüsselwort das mit dem Abschnitt verbunden ist.
   *
   * @return Schlüsselwort das mit dem Abschnitt verbunden ist.
   */
  public String getKeyword() {
    return keyword;
  }

  /**
   * Setzt das Schlüsselwort das mit dem Abschnitt verbunden ist.
   *
   * @param keyword Schlüsselwort das mit dem Abschnitt verbunden ist.
   */
  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  /**
   * Ermittelt die Dauer der Ausführung des Abschnitts.
   *
   * @return Ausführungsdauer des Abschnitts.
   */
  public long getDuration() {
    return duration;
  }

  /**
   * Setzt die Dauer der Ausführung des Abschnitts.
   *
   * @param duration Ausführungsdauer des Abschnitts.
   */
  public void setDuration(long duration) {
    this.duration = duration;
  }

  /**
   * Ermittelt ob noch keine Methode zum Abschnitt definiert wurde.
   *
   * @return Wurde noch keine Methode zum Abschnitt definiert?
   *
   * @see #undefined
   */
  public boolean isUndefined() {
    return undefined;
  }

  /**
   * Setzt ob noch keine Methode zum Abschnitt definiert wurde.
   *
   * @param undefined Wurde noch keine Methode zum Abschnitt definiert?
   *
   * @see #undefined
   */
  public void setUndefined(boolean undefined) {
    this.undefined = undefined;
  }

  /**
   * Schreibt die Daten Abschnitts in den Bericht.
   *
   * @param sink {@link org.apache.maven.doxia.sink.Sink}-Objekt dass zum
   *             erstellen des Berichts benutzt werden soll.
   *
   * @param displayDebugStepColumns Gibt an ob die Spalten für den
   *                                Debug-Prozess im Bericht angezeigt werden
   *                                sollen {@code true} oder nicht {@code false}.
   */
  public abstract void writeToReport(Sink sink, final boolean displayDebugStepColumns);
}
