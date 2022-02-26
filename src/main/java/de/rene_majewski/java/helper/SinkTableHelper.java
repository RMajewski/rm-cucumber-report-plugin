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

import static org.apache.maven.doxia.sink.SinkEventAttributes.STYLE;
import java.util.ArrayList;
import java.util.List;
import org.apache.maven.doxia.markup.HtmlMarkup;
import org.apache.maven.doxia.sink.Sink;
import org.apache.maven.doxia.sink.SinkEventAttributes;
import org.apache.maven.doxia.sink.impl.SinkEventAttributeSet;
import de.rene_majewski.java.report.StepArgument;

/**
 * Hilfsklasse die Zeilen in eine Tabelle im {@link Sink}-Objekt schreibt.
 *
 * @author René Majewski
 */
public class SinkTableHelper {
  /**
   * Schreibt die Daten einer Tabellen-Zeile mit 2 Spalten in das
   * {@link Sink}-Objekt. In der ersten Spalte wird der Text fett dargestellt.
   *
   * @param sink Das {@link org.apache.maven.doxia.sink.Sink}-Objekt in das die
   *             Zeile geschrieben werden soll.
   *
   * @param cell1 Text der in die 1. Spalte geschrieben werden soll.
   *
   * @param cell2 Text der in die 2. Spalte geschrieben werden soll.
   */
  public static void writeRow2ColsFirstBold(Sink sink, String cell1, String cell2) {
    sink.tableRow();
    sink.tableCell();
    sink.bold();
    sink.text(cell1);
    sink.bold_();
    sink.tableCell_();
    sink.tableCell();
    sink.text(cell2);
    sink.tableCell_();
    sink.tableRow_();
  }

  /**
   * Schreibt die Daten einer Tabellen-Zeile mit 2 Spalten in das
   * {@link Sink}-Objekt. In der ersten Spalte wird der Text fett dargestellt.
   * In der zweiten Spalte wird eine Fortschrittsanzeige eingefügt.
   *
   * @param sink Das {@link org.apache.maven.doxia.sink.Sink}-Objekt in das die
   *             Zeile geschrieben werden soll.
   *
   * @param cell1 Text der in die 1. Spalte geschrieben werden soll.
   *
   * @param value Wert des Fortschritts.
   *
   * @param max Maximal Wert des Fortschritts.
   *
   * @param label Text der in die Fortschrittsanzeige angezeigt werden soll.
   */
  public static void writeRow2ColsFirstBoldSecondProgress(Sink sink, String cell1, final int value, final int max, final String label) {
    sink.tableRow();
    sink.tableCell();
    sink.bold();
    sink.text(cell1);
    sink.bold_();
    sink.tableCell_();
    sink.tableCell();
    SinkHelper.writeProgress(sink, value, max, label);
    sink.tableCell_();
    sink.tableRow_();
  }

  /**
   * Schreibt die Daten eines Schrittes in eine Tabellen-Zeile in das
   * {@link Sink}-Objekt.
   *
   * @param sink Das {@link org.apache.maven.doxia.sink.Sink}-Objekt in das die
   *             Zeile geschrieben werden soll.
   *
   * @param keyword Schlüsselwort das mit dem Schritt verbunden ist.
   *
   * @param name Text des Schrittes.
   *
   * @param status Ergebnis der Ausführung des Schrittes.
   *
   * @param duration Dauer der Ausführung des Schrittes.
   *
   * @param method Name der Methode die bei dem Schritt aufgerufen wurde.
   *
   * @param pass Wurde der Schritt erfolgreich ausgeführt?
   *
   * @param failure Wurde der Schritt fehlerhaft ausgeführt wurden?
   *
   * @param error Wurde der Schritt mit einem Fehler beendet?
   *
   * @param skip Wurde der Schritt ausgelassen?
   */
  @Deprecated
  public static void writeScenarioRow(Sink sink, String keyword, String name, String status, long duration, String method, boolean pass, boolean failure, boolean error, boolean skip) {
    writeScenarioRow(sink, keyword, name, status, duration, method, pass, failure, error, skip, false);
  }

  /**
   * Schreibt die Daten eines Schrittes in eine Tabellen-Zeile in das
   * {@link Sink}-Objekt.
   *
   * @param sink Das {@link org.apache.maven.doxia.sink.Sink}-Objekt in das die
   *             Zeile geschrieben werden soll.
   *
   * @param keyword Schlüsselwort das mit dem Schritt verbunden ist.
   *
   * @param name Text des Schrittes.
   *
   * @param status Ergebnis der Ausführung des Schrittes.
   *
   * @param duration Dauer der Ausführung des Schrittes.
   *
   * @param method Name der Methode die bei dem Schritt aufgerufen wurde.
   *
   * @param pass Wurde der Schritt erfolgreich ausgeführt?
   *
   * @param failure Wurde der Schritt fehlerhaft ausgeführt wurden?
   *
   * @param error Wurde der Schritt mit einem Fehler beendet?
   *
   * @param skip Wurde der Schritt ausgelassen?
   *
   * @param undefined Wurde für die Schritt noch keine Methode definiert?
   */
  @Deprecated
  public static void writeScenarioRow(Sink sink, String keyword, String name, String status, long duration, String method, boolean pass, boolean failure, boolean error, boolean skip, boolean undefined) {
    writeScenarioRow(sink, keyword, name, new ArrayList<>(), status, duration, method, pass, failure, error, skip, undefined);
  }

  /**
   * Schreibt die Daten eines Schrittes in eine Tabellen-Zeile in das
   * {@link Sink}-Objekt.
   *
   * @param sink Das {@link org.apache.maven.doxia.sink.Sink}-Objekt in das die
   *             Zeile geschrieben werden soll.
   *
   * @param keyword Schlüsselwort das mit dem Schritt verbunden ist.
   *
   * @param name Text des Schrittes.
   *
   * @param arguments Liste mit den Argumenten die an die ausgeführte Methode
   *                  übergeben wurde.
   *
   * @param status Ergebnis der Ausführung des Schrittes.
   *
   * @param duration Dauer der Ausführung des Schrittes.
   *
   * @param method Name der Methode die bei dem Schritt aufgerufen wurde.
   *
   * @param pass Wurde der Schritt erfolgreich ausgeführt?
   *
   * @param failure Wurde der Schritt fehlerhaft ausgeführt wurden?
   *
   * @param error Wurde der Schritt mit einem Fehler beendet?
   *
   * @param skip Wurde der Schritt ausgelassen?
   *
   * @param undefined Wurde für die Schritt noch keine Methode definiert?
   */
  @Deprecated
  public static void writeScenarioRow(Sink sink, String keyword, String name,
      List<StepArgument> arguments, String status, long duration, String method, boolean pass,
      boolean failure, boolean error, boolean skip, boolean undefined) {
    writeScenarioRow(sink, keyword, name, arguments, status, duration, method, pass, failure, error, skip, undefined, null, false);
  }

  /**
   * Schreibt die Daten eines Schrittes in eine Tabellen-Zeile in das
   * {@link Sink}-Objekt.
   *
   * @param sink Das {@link org.apache.maven.doxia.sink.Sink}-Objekt in das die
   *             Zeile geschrieben werden soll.
   *
   * @param keyword Schlüsselwort das mit dem Schritt verbunden ist.
   *
   * @param name Text des Schrittes.
   *
   * @param arguments Liste mit den Argumenten die an die ausgeführte Methode
   *                  übergeben wurde.
   *
   * @param status Ergebnis der Ausführung des Schrittes.
   *
   * @param duration Dauer der Ausführung des Schrittes.
   *
   * @param method Name der Methode die bei dem Schritt aufgerufen wurde.
   *
   * @param pass Wurde der Schritt erfolgreich ausgeführt?
   *
   * @param failure Wurde der Schritt fehlerhaft ausgeführt wurden?
   *
   * @param error Wurde der Schritt mit einem Fehler beendet?
   *
   * @param skip Wurde der Schritt ausgelassen?
   *
   * @param undefined Wurde für die Schritt noch keine Methode definiert?
   *
   * @param table Optional. Angaben einer Tabelle die an den Schritt übergeben wurde.
   *
   * @param displayDebugStepColumns Gibt an ob die Spalten für den
   *                                Debug-Prozess im Bericht angezeigt werden
   *                                sollen {@code true} oder nicht {@code false}.
   */
  public static void writeScenarioRow(Sink sink, String keyword, String name,
      List<StepArgument> arguments, String status, long duration, String method, boolean pass,
      boolean failure, boolean error, boolean skip, boolean undefined, String table,
      final boolean displayDebugStepColumns) {
    String style = "";
    if (pass) {
      style = "color: #16a085;";
    }

    if (failure) {
      style = "color: #e67e22;";
    }

    if (error) {
      style = "color: #e74c3c;";
    }

    if (skip && status.equals(ConstHelper.SKIP)) {
      style = "color: #3498db;";
    }

    if (undefined && status.equals(ConstHelper.UNDEFINED)) {
      style = "color: #d4ac0d; font-weight: bolder;";
    }

    SinkEventAttributeSet atts = new SinkEventAttributeSet();
    if (!style.isEmpty()) {
      atts.addAttribute(STYLE, style);
    }

    sink.tableRow();
    sink.tableCell();
    if (status != null && !status.isEmpty()) {
      sink.figure();

      if (pass) {
        sink.figureGraphics("images/icon_success_sml.gif");
      }

      else if (undefined) {
        sink.figureGraphics("images/icon_warning_sml.gif");
      }

      else if (skip) {
        sink.figureGraphics("images/icon_info_sml.gif");
      }

      sink.figure_();
    }
    sink.tableCell_();

    sink.tableCell();
    if (keyword != null && !keyword.isEmpty()) {
      sink.text(keyword);
    }
    sink.tableCell_();

    sink.tableCell();
    if (name != null && !name.isEmpty()) {
      writeHighlightedName(sink, name, arguments);

      if (table != null && !table.isEmpty()) {
        SinkEventAttributeSet tableAtts = new SinkEventAttributeSet();
        tableAtts.addAttribute(SinkEventAttributes.STYLE, "color: #297bde;");

        sink.verbatim(tableAtts);
        sink.text(table);
        sink.verbatim_();
      }
    }
    sink.tableCell_();

    sink.tableCell(atts);
    if (status != null && !status.isEmpty()) {
      sink.text(status);
    }
    sink.tableCell_();

    sink.tableCell();
    sink.text(DateTimeHelper.formatDuration(duration));
    sink.tableCell_();

    sink.tableCell();
    if (method != null && !method.isEmpty()) {
      if (method.indexOf("\n") > -1) {
        SinkEventAttributeSet codeAtts = new SinkEventAttributeSet();
        sink.unknown("code", new Object[] { Integer.valueOf(HtmlMarkup.TAG_TYPE_START)}, codeAtts);
        sink.verbatim(null);
        sink.text(method);
        sink.verbatim_();
        sink.unknown("code", new Object[] { Integer.valueOf(HtmlMarkup.TAG_TYPE_END)}, null);
      } else {
        sink.text(method);
      }
    }
    sink.tableCell_();

    if (displayDebugStepColumns) {
      sink.tableCell();
      if (pass) {
        sink.text("x");
      }
      sink.tableCell_();

      sink.tableCell();
      if (failure) {
        sink.text("x");
      }
      sink.tableCell_();

      sink.tableCell();
      if (error) {
        sink.text("x");
      }
      sink.tableCell_();

      sink.tableCell();
      if (skip) {
        sink.text("x");
      }
      sink.tableCell_();

      sink.tableCell();
      if (undefined) {
        sink.text("x");
      }
      sink.tableCell_();
    }

    sink.tableRow_();
  }

  /**
   * Hebt die Argumente im Text des Schrittes hervor.
   *
   * @param sink Das {@link org.apache.maven.doxia.sink.Sink}-Objekt in das die
   *             Zeile geschrieben werden soll.
   *
   * @param name Text des Schrittes.
   *
   * @param arguments Liste mit den Argumenten die im Text hervorgehoben werden
   *                  soll. Wenn keine Argumente hervorgerufen werden sollen,
   *                  dann {@code null} übergeben.
   */
  private static void writeHighlightedName(Sink sink, String name, List<StepArgument> arguments) {
    if (arguments == null || arguments.isEmpty()) {
      sink.text(name);
    } else {
      int startOffset = 0;
      int endOffset = 0;

      for (StepArgument arg : arguments) {
        endOffset = arg.getOffset();
        sink.text(name.substring(startOffset, endOffset));

        startOffset = endOffset;
        endOffset += arg.getValue().length();
        sink.bold();
        sink.text(name.substring(startOffset, endOffset));
        sink.bold_();

        startOffset = endOffset;
      }

      if (startOffset < name.length()) {
        sink.text(name.substring(startOffset));
      }
    }
  }

  /**
   * In einer Hilfsklasse wird kein öffentlicher Konstruktor benötigt.
   */
  private SinkTableHelper() {
    throw new IllegalStateException("Utility class");
  }
}
