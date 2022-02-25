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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * Implementiert Methoden um Datums- und Zeitangaben zu formatieren.
 *
 * @since 0.1.0
 * @author René Majewski
 */
public class DateTimeHelper {
  /**
   * <p>formatDateTime.</p>
   *
   * @param ldt a {@link java.time.LocalDateTime} object
   * @return a {@link java.lang.String} object
   */
  public static String formatDateTime(LocalDateTime ldt) {
    if (ldt == null) {
      return "";
    }
    return ldt.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
  }

  /**
   * <p>formatDuration.</p>
   *
   * @param duration a long
   * @return a {@link java.lang.String} object
   */
  public static String formatDuration(long duration) {
    final long hours = TimeUnit.NANOSECONDS.toHours(duration);
    final long mins = TimeUnit.NANOSECONDS.toMinutes(duration);
    final long secs = TimeUnit.NANOSECONDS.toSeconds(duration);
    final long millis = TimeUnit.NANOSECONDS.toMillis(duration) - TimeUnit.SECONDS.toMillis(secs);

    return String.format("%02d:%02d:%02d.%03d", hours, mins, secs, millis);
  }

  /**
   * In einer Hilfsklasse wird kein öffentlicher Konstruktor benötigt.
   */
  private DateTimeHelper() {
    throw new IllegalStateException("Utility class");
  }
}
