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

/**
 * Bereitet die Daten für ein Szenario auf und zeigt diese an.
 *
 * @author René Majewski
 */
public class Scenario extends Parent {
  /**
   * Speichert eine Liste mit den Schritten die zu diesem Szenario gehören.
   */
  private List<Step> steps;

  /**
   * Speichert eine Liste mit dem Hooks die aufgerufen wurden um das Szenario
   * vorzubereiten.
   */
  private List<Hook> before;

  /**
   * Speichert eine Liste mit den Hooks die aufgerufen wurden um das Szenario
   * abzuschließen.
   */
  private List<Hook> after;

  /**
   * Initialisiert die Klasse.
   *
   * Importiert die Daten aus dem {@link de.rene_majewski.java.cucumber_import.CucumberReportElement} und bereitet
   * sie zum anzeigen vor.
   *
   * @param element Szenario dessen Daten aufbereitet werden sollen.
   */
  public Scenario(CucumberReportElement element) {
    super();
    steps = new ArrayList<>();
    before = generateHookList(element.getBefore());
    after = generateHookList(element.getAfter());

    name = element.getName();
    keyword = element.getKeyword();
    description = element.getDescription();
    startedAt = LocalDateTime.parse(element.getStartTimestamp(), DateTimeFormatter.ISO_DATE_TIME);

    for (CucumberReportStep crs : element.getSteps()) {
      Step step = new Step(crs);
      duration += step.getDuration();
      failure = failure || step.isFailure();
      error = error || step.isError();
      undefined = undefined || step.isUndefined();
      skip = skip || step.isSkip();
      steps.add(step);
    }

    pass = !failure && !error && !undefined && !skip;
  }

  /**
   * Ermittelt die Anzahl an Schritten die zu diesem Szenario gehören.
   *
   * @return Anzahl an Schritten die zu diesen Szenario gehören.
   */
  public int getStepsCount() {
    return steps.size();
  }

  /**
   * Ermittelt die Anzahl an Schritten die richtig ausgeführt wurden.
   *
   * @return Anzahl an Schritten die richtig ausgeführt wurden.
   */
  public int getStepsPass() {
    int result = 0;
    for (Step step : steps) {
      result += step.isPass() ? 1 : 0;
    }
    return result;
  }

  /**
   * Ermittelt die Anzahl an fehlgeschlagenen Schritten.
   *
   * @return Anzahl an fehlgeschlagenen Schritten.
   */
  public int getStepsFailure() {
    int result = 0;
    for (Step step : steps) {
      result += step.isFailure() ? 1 : 0;
    }
    return result;
  }

  /**
   * Ermittelt die Anzahl an übersprungenen Schritten.
   *
   * @return Anzahl an übersprungenen Schritten.
   */
  public int getStepsSkip() {
    int result = 0;
    for (Step step : steps) {
      result += step.isSkip() ? 1 : 0;
    }
    return result;
  }

  /**
   * Ermittelt die Anzahl an undefinierten Schritten.
   *
   * @return Anzahl an undefinierten Schritten.
   */
  public int getStepsUndefined() {
    int result = 0;
    for (Step step : steps) {
      result += step.isUndefined() ? 1 : 0;
    }
    return result;
  }

  /**
   * Anzahl an Schritten die einen Fehler aufweisen.
   *
   * @return Anzahl an Schritten die einen Fehler aufweisen.
   */
  public int getStepsError() {
    int result = 0;
    for (Step step : steps) {
      result += step.isError() ? 1 : 0;
    }
    return result;
  }

  /** {@inheritDoc} */
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
    SinkTableHelper.writeRow2ColsFirstBoldSecondProgress(sink, "Anzahl bestandener Schritte", getStepsPass(), getStepsCount(), String.valueOf(getStepsPass()));
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Anzahl nicht bestandener Schritte", String.valueOf(getStepsFailure()));
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Anzahl fehlerhafter Schritte", String.valueOf(getStepsError()));
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Anzahl übersprungener Schritte", String.valueOf(getStepsSkip()));
    SinkTableHelper.writeRow2ColsFirstBold(sink, "Anzahl undefinierter Schritte", String.valueOf(getStepsUndefined()));
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

  /**
   * Erstellt eine Liste mit den ausgeführten Hooks.
   *
   * @param hooks Array von {@link de.rene_majewski.java.cucumber_import.CucumberReportHook}.
   * @return Erstellte Liste mit {@link de.rene_majewski.java.report.Hook}s.
   */
  private List<Hook> generateHookList(CucumberReportHook[] hooks) {
    List<Hook> result = new ArrayList<>();

    for (CucumberReportHook crh : hooks) {
      Hook hook = new Hook(crh);
      duration += hook.getDuration();
      result.add(hook);
    }

    return result;
  }

  /**
   * Schreibt die Spaltenüberschriften für das Szenario in zu erstellende
   * Report-Datei.
   *
   * @param sink {@link org.apache.maven.doxia.sink.Sink}-Objekt das genutzt
   *             werden soll.
   */
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

  /**
   * Ermittelt den Status des Szenarios.
   *
   * @return {@link java.lang.String}-Objekt das den Status des Szenarios
   *         beinhaltet. Mögliche Status-Eintragungen:
   *         <ul>
   *           <li>Fehler</li>
   *           <li>Fehlerfrei</li>
   *           <li>Nicht bestanden</li>
   *           <li>Nicht definiert</li>
   *           <li>Übersprungen</li>
   *         </ul>
   */
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

    if (isUndefined()) {
      return "Nicht definiert";
    }

    return "Fehler";
  }

  /**
   * Schreibt den Hook in den Bericht.
   *
   * @param sink {@link org.apache.maven.doxia.sink.Sink}-Objekt das zum
   *             erstellen der HTML-Seite genutzt wird.
   * @param hooks Liste mit {@link de.rene_majewski.java.report.Hook}-Einträgen die in den Bericht
   *              geschrieben werden soll.
   */
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
