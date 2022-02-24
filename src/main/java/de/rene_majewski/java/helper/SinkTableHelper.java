package de.rene_majewski.java.helper;

import java.util.ArrayList;
import java.util.List;
import org.apache.maven.doxia.sink.Sink;
import org.apache.maven.doxia.sink.impl.SinkEventAttributeSet;
import de.rene_majewski.java.report.StepArgument;

public class SinkTableHelper {
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

  @Deprecated
  public static void writeScenarioRow(Sink sink, String keyword, String name, String status, long duration, String method, boolean pass, boolean failure, boolean error, boolean skip) {
    writeScenarioRow(sink, keyword, name, status, duration, method, pass, failure, error, skip, false);
  }

  @Deprecated
  public static void writeScenarioRow(Sink sink, String keyword, String name, String status, long duration, String method, boolean pass, boolean failure, boolean error, boolean skip, boolean undefined) {
    writeScenarioRow(sink, keyword, name, new ArrayList<>(), status, duration, method, pass, failure, error, skip, undefined);
  }

  public static void writeScenarioRow(Sink sink, String keyword, String name,
      List<StepArgument> arguments, String status, long duration, String method, boolean pass,
      boolean failure, boolean error, boolean skip, boolean undefined) {
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

    if (skip) {
      style = "color: #3498db;";
    }

    if (undefined) {
      style = "color: #d4ac0d; font-weight: bolder;";
    }

    SinkEventAttributeSet atts = new SinkEventAttributeSet();
    atts.addAttribute(SinkEventAttributeSet.STYLE, style);

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
      if (method.indexOf("\n") > 0) {
        sink.verbatim(null);
        sink.text(method);
        sink.verbatim_();
      } else {
        sink.text(method);
      }
    }
    sink.tableCell_();

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
    sink.tableRow_();
  }

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
}
