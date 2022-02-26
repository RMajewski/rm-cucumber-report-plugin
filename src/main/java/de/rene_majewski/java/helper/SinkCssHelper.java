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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.apache.maven.doxia.markup.HtmlMarkup;
import org.apache.maven.doxia.sink.Sink;
import org.apache.maven.doxia.sink.SinkEventAttributes;
import org.apache.maven.doxia.sink.impl.SinkEventAttributeSet;
import de.rene_majewski.java.RmCucumberReportMojo;

/**
 * Implementiert Methoden um Css in das {@link Sink}-Objekt zu schreiben.
 *
 * @since 0.1.0
 * @author René Majewski
 */
public class SinkCssHelper {
  /**
   * Definiert den Namen der Klasse für das Div-Objekt dass die Fortschrittsanzeige beinhaltet.
   */
  public static final String METER_CLASS = "rm-cucumber-report-meter";

  /**
   * Fügt das erforderliche CSS in den Header-Bereich des {@link Sink}-Objektes
   * ein und kopiert die entsprechenden Dateien.
   *
   * @param sink {@link Sink}-Objekt in dass das CSS geschrieben werden soll.
   *
   * @throws IOException Kann auftreten wenn die CSS-Datei kopiert wird.
   */
  public static void writeCssToHeader(Sink sink, final String outputDir) throws IOException {
    // CSS-Datei kopieren
    final String fileName = "rm-cucumber-report.css";
    final URL resUrl = RmCucumberReportMojo.class.getClassLoader().getResource("css/" + fileName);
    File destFile = new File(outputDir + "/css", fileName);
    FileUtils.copyURLToFile(resUrl, destFile);

    // Link-Einfügen
    SinkEventAttributeSet atts = new SinkEventAttributeSet();
    atts.addAttribute(SinkEventAttributes.REL, "stylesheet");
    atts.addAttribute(SinkEventAttributes.HREF, "./css/rm-cucumber-report.css");

    sink.unknown("link", new Object[] {HtmlMarkup.TAG_TYPE_START} , atts);
  }

  /**
   * In einer Hilfsklasse wird kein öffentlicher Konstruktor benötigt.
   */
  private SinkCssHelper() {
    throw new IllegalStateException("Utility class");
  }
}
