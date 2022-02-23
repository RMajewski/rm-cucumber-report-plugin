package de.rene_majewski.java.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class DateTimeHelper {
  public static String formatDateTime(LocalDateTime ldt) {
    if (ldt == null) {
      return "";
    }
    return ldt.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
  }

  public static String formatDuration(long duration) {
    final long hours = TimeUnit.NANOSECONDS.toHours(duration);
    final long mins = TimeUnit.NANOSECONDS.toMinutes(duration);
    final long secs = TimeUnit.NANOSECONDS.toSeconds(duration);
    final long millis = TimeUnit.NANOSECONDS.toMillis(duration) - TimeUnit.SECONDS.toMillis(secs);

    return String.format("%02d:%02d:%02d.%03d", hours, mins, secs, millis);
  }
}
