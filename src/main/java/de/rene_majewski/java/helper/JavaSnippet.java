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

import static java.util.stream.Collectors.joining;
import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.Map;
import io.cucumber.core.backend.Snippet;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;

/**
 * Generiert Java-Snippets von Schritten, die noch nicht definiert wurden.
 *
 * Diese Klasse ist aus dem Projekt
 * <a href="https://github.com/cucumber/cucumber-jvm/tree/main/java">cucumber-java</a>
 * Projekt entnommen.
 *
 * @since 0.1.0
 * @author René Majewski
 */
public class JavaSnippet implements Snippet {
  /** {@inheritDoc} */
  @Override
  public MessageFormat template() {
    return new MessageFormat("" +
      "@{0}(\"{1}\")\n" +
      "public void {2}({3}) '{'\n" +
      "    // {4}\n" +
      "{5}    throw new " + PendingException.class.getName() + "();\n" +
      "'}'"
    );
  }

  /** {@inheritDoc} */
  @Override
  public String tableHint() {
    return "" +
      "    // For automatic transformation, change DataTable to one of\n" +
      "    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or\n" +
      "    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,\n" +
      "    // Double, Byte, Short, Long, BigInteger or BigDecimal.\n" +
      "    //\n" +
      "    // For other transformations you can register a DataTableType.\n";
  }

  /** {@inheritDoc} */
  @Override
  public String arguments(Map<String, Type> arguments) {
    return arguments.entrySet()
      .stream()
      .map(argType -> getArgType(argType.getValue()) + " " + argType.getKey())
      .collect(joining(", "));
  }

  /** {@inheritDoc} */
  @Override
  public String escapePattern(String pattern) {
    return pattern.replace("\\", "\\\\").replace("\"", "\\\"");
  }

  /**
   * <p>getArgType.</p>
   *
   * @param argType a {@link java.lang.reflect.Type} object
   * @return a {@link java.lang.String} object
   */
  private String getArgType(Type argType) {
    if (argType instanceof Class) {
      Class<?> cType = (Class<?>) argType;
      if (cType.equals(DataTable.class)) {
          return cType.getName();
      }
      return cType.getSimpleName();
    }

    // Got a better idea? Send a PR.
    return argType.toString();
  }
}
