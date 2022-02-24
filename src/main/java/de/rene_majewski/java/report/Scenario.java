package de.rene_majewski.java.report;

import static de.rene_majewski.java.helper.ConstHelper.PASSED;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.maven.doxia.sink.Sink;
import de.rene_majewski.java.cucumber_import.CucumberReportElement;
import de.rene_majewski.java.cucumber_import.CucumberReportHook;
import de.rene_majewski.java.cucumber_import.CucumberReportStep;
import de.rene_majewski.java.helper.DateTimeHelper;
import de.rene_majewski.java.helper.SinkTableHelper;

public class Scenario extends Parent {
  private List<Step> steps;
  private List<Hook> before;
  private List<Hook> after;

  public Scenario(CucumberReportElement element) {
    super();
    steps = new ArrayList<>();
    before = generateHookList(element.before);
    after = generateHookList(element.after);

    name = element.name;
    keyword = element.keyword;
    description = element.description;
    startedAt = LocalDateTime.parse(element.start_timestamp, DateTimeFormatter.ISO_DATE_TIME);

    for (CucumberReportStep crs : element.steps) {
      Step step = new Step(crs);
      duration += step.getDuration();
      failure = failure || step.isFailure();
      error = error || step.isError();
      steps.add(step);
    }

    pass = !failure && !error;
  }

  public int getStepsCount() {
    return steps.size();
  }

  public int getStepsPass() {
    int result = 0;
    for (Step step : steps) {
      result += step.isPass() ? 1 : 0;
    }
    return result;
  }

  public int getStepsFailure() {
    int result = 0;
    for (Step step : steps) {
      result += step.isFailure() ? 1 : 0;
    }
    return result;
  }

  public int getStepsSkip() {
    int result = 0;
    for (Step step : steps) {
      result += step.isSkip() ? 1 : 0;
    }
    return result;
  }

  public int getStepsError() {
    int result = 0;
    for (Step step : steps) {
      result += step.isError() ? 1 : 0;
    }
    return result;
  }

  @Override
  public void writeToReport(Sink sink) {
    sink.section(3, null);
    sink.sectionTitle(2, null);
    sink.text(keyword + ": " + name);
    sink.sectionTitle_(3);

    sink.table();
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Ausführung gestartet am/um", DateTimeHelper.formatDateTime(startedAt));
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Dauer der Ausführung", DateTimeHelper.formatDuration(duration));
    if (description != null && !description.isEmpty()) {
      SinkTableHelper.writeRow2ColsFirstBold(sink, "Beschreibung", description);
    }
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Status des Szenario", getStatus());
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Anzahl ausgeführter Schritte", String.valueOf(getStepsCount()));
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Anzahl nicht bestandener Schritte", String.valueOf(getStepsFailure()));
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Anzahl fehlerhafter Schritte", String.valueOf(getStepsError()));
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Anzahl übersprungener Schritte", String.valueOf(getStepsSkip()));
    sink.table_();

    sink.table();
    sink.tableRows(
      new int[] {
        Sink.JUSTIFY_LEFT,
        Sink.JUSTIFY_LEFT,
        Sink.JUSTIFY_LEFT,
        Sink.JUSTIFY_LEFT,
        Sink.JUSTIFY_RIGHT,
        Sink.JUSTIFY_LEFT,
        Sink.JUSTIFY_CENTER,
        Sink.JUSTIFY_CENTER,
        Sink.JUSTIFY_CENTER,
        Sink.JUSTIFY_CENTER,
        Sink.JUSTIFY_CENTER
      },
      false
    );
    writeScenarioRowHeader(sink);
    writeHook(sink, before);

    for (Step step : steps) {
      step.writeToReport(sink);
    }

    writeHook(sink, after);

    sink.table_();

    sink.section_(3);
  }

  private List<Hook> generateHookList(CucumberReportHook[] hooks) {
    List<Hook> result = new ArrayList<>();

    for (CucumberReportHook crh : hooks) {
      Hook hook = new Hook(crh);
      duration += hook.getDuration();
      result.add(hook);
    }

    return result;
  }

  private void writeScenarioRowHeader(Sink sink) {
    sink.tableRow();
    sink.tableHeaderCell();
    sink.tableHeaderCell_();

    sink.tableHeaderCell();
    sink.text("Schlüsselwort");
    sink.tableHeaderCell_();

    sink.tableHeaderCell();
    sink.text("Name");
    sink.tableHeaderCell_();

    sink.tableHeaderCell();
    sink.text("Status");
    sink.tableHeaderCell_();

    sink.tableHeaderCell();
    sink.text("Ausführungsdauer");
    sink.tableHeaderCell_();

    sink.tableHeaderCell();
    sink.text("Funktionsaufruf");
    sink.tableHeaderCell_();

    sink.tableHeaderCell();
    sink.text("Bestanden");
    sink.tableHeaderCell_();

    sink.tableHeaderCell();
    sink.text("Nicht bestanden");
    sink.tableHeaderCell_();

    sink.tableHeaderCell();
    sink.text("Fehler");
    sink.tableHeaderCell_();

    sink.tableHeaderCell();
    sink.text("Übersprungen");
    sink.tableHeaderCell_();

    sink.tableHeaderCell();
    sink.text("Step fehlt");
    sink.tableHeaderCell_();
    sink.tableRow_();
  }

  private String getStatus() {
    if (isPass()) {
      return "Fehlerfrei";
    }

    if (isFailure()) {
      return "Nicht bestanden";
    }

    if (isSkip()) {
      return "Übersprungen";
    }

    return "Fehler";
  }

  private void writeHook(Sink sink, List<Hook> hooks) {
    for (Hook hook : hooks) {
      SinkTableHelper.writeScenarioRow(
        sink,
        null,
        null,
        hook.getStatus(),
        hook.getDuration(),
        hook.getLocation(),
        hook.getStatus().equalsIgnoreCase(PASSED),
        false,
        false,
        false
      );
    }
  }
}
