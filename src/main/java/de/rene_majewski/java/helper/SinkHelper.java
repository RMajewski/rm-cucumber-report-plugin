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

import org.apache.maven.doxia.markup.HtmlMarkup;
import org.apache.maven.doxia.sink.Sink;
import org.apache.maven.doxia.sink.SinkEventAttributes;
import org.apache.maven.doxia.sink.impl.SinkEventAttributeSet;

/**
 * Hilfsklasse die Methoden implementiert um Objekte ins {@link Sink}-Objekt zu
 * schreiben. Diese Methode werden in verschiedenen Report-Klasse genutzt.
 *
 * @since 0.1.0
 * @author René Majewski
 */
public class SinkHelper {
  /**
   * Erstellt aus den Daten eine Fortschrittsanzeige und schreibt diese in den
   * Bericht.
   *
   * @param sink Das {@link org.apache.maven.doxia.sink.Sink}-Objekt in das die
   *             Zeile geschrieben werden soll.
   *
   * @param value Wert des Fortschritts.
   *
   * @param max Maximal Wert des Fortschritts.
   *
   * @param label Text der in die Fortschrittsanzeige angezeigt werden soll.
   */
  public static void writeProgress(Sink sink, final int value, final int max, final String label) {
    final float percent = ((float) value / (float) max) * 100f;

    final Object[] open = new Object[] {Integer.valueOf(HtmlMarkup.TAG_TYPE_START)};
    final Object[] close = new Object[] {Integer.valueOf(HtmlMarkup.TAG_TYPE_END)};

    sink.text(label);

    SinkEventAttributeSet atts = new SinkEventAttributeSet();
    atts.addAttribute(SinkEventAttributes.CLASS, SinkCssHelper.METER_CLASS);

    sink.unknown("div", open, atts);

    atts = new SinkEventAttributeSet();
    atts.addAttribute(SinkEventAttributes.STYLE, "width: " + percent + "%;");

    sink.unknown("span", open, atts);
    sink.unknown("span", close, null);

    sink.unknown("div", close, null);
  }

  /**
   * In einer Hilfsklasse wird kein öffentlicher Konstruktor benötigt.
   */
  private SinkHelper() {
    throw new IllegalStateException("Utility class");
  }
}
