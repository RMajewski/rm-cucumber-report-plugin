package de.rene_majewski.java.helper;

import static java.util.stream.Collectors.joining;
import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.Map;
import io.cucumber.core.backend.Snippet;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;

public class JavaSnippet implements Snippet {
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

  @Override
  public String arguments(Map<String, Type> arguments) {
    return arguments.entrySet()
      .stream()
      .map(argType -> getArgType(argType.getValue()) + " " + argType.getKey())
      .collect(joining(", "));
  }

  @Override
  public String escapePattern(String pattern) {
    return pattern.replace("\\", "\\\\").replace("\"", "\\\"");
  }

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
