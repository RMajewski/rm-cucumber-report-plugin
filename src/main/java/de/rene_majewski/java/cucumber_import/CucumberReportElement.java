package de.rene_majewski.java.cucumber_import;

import com.google.gson.annotations.SerializedName;

/**
 * Speichert die Daten zu einem Cucumber-Szenario.
 *
 * @author René Majewski
 * @since 0.1.0
 */
public class CucumberReportElement extends CucumberReportGeneral {
  /**
   * Speichert den Zeitpunkt wann das Szenario ausgeführt wurde.
   */
  @SerializedName(value = "start_timestamp")
  private String startTimestamp;

  /**
   * Speichert die Liste mit Methoden die aufgerufen wurde um das Szenario zu
   * initialisieren.
   */
  private CucumberReportHook[] before;

  /**
   * Speichert die Liste mit Methoden die aufgerufen wurde, um das Szenario zu
   * beenden.
   */
  private CucumberReportHook[] after;

  /**
   * Speichert den Type des Szenarios.
   */
  private String type;

  /**
   * Speicher eine Liste mit den einzelnen Schritten des Szenarios.
   */
  private CucumberReportStep[] steps;

  /**
   * Initialisiert das Szenario.
   */
  public CucumberReportElement() {
    startTimestamp = null;
    before = null;
    after = null;
    type = null;
    steps = null;
  }

  /**
   * Ermittelt den Zeitpunkt wann das Szenario ausgeführt wurde.
   *
   * @return Zeitpunkt wann das Szenario ausgeführt wurde.
   */
  public String getStartTimestamp() {
    return startTimestamp;
  }

  /**
   * Setzt den Zeitpunkt wann das Szenario ausgeführt wurde.
   *
   * @param startTimestamp Zeitpunkt was das Szenario ausgeführt wurde.
   */
  public void setStartTimestamp(String startTimestamp) {
    this.startTimestamp = startTimestamp;
  }

  /**
   * Ermittelt die Liste mit den Methoden-Aufrufen die zum initialisieren des
   * Szenarios aufgerufen wurden.
   *
   * @return Liste mit Methoden-Aufrufen die zum initialisieren des Szenarios
   *         aufgerufen wurden.
   */
  public CucumberReportHook[] getBefore() {
    return before;
  }

  /**
   * Setzt die Liste mit den Methoden-Aufrufen, die zum initialisieren des
   * Szenarios aufgerufen wurden.
   *
   * @param before Liste mit Methoden-Aufrufen die zum initialisieren des
   *               Szenarios aufgerufen wurden.
   */
  public void setBefore(CucumberReportHook[] before) {
    this.before = before;
  }

  /**
   * Ermittelt die Liste mit den Methoden-Aufrufen die zum Beenden des
   * Szenarios aufgerufen wurden.
   *
   * @return Liste mit den Methoden-Aufrufen die zum Beenden des Szenarios
   *         aufgerufen wurden.
   */
  public CucumberReportHook[] getAfter() {
    return after;
  }

  /**
   * Setzt die Liste mit den Methoden-Aufrufen die zum Beenden des Szenarios
   * aufgerufen wurden.
   *
   * @param after Liste mit den Methoden-Aufrufen die zum Beenden des Szenarios
   *              aufgerufen wurden.
   */
  public void setAfter(CucumberReportHook[] after) {
    this.after = after;
  }

  /**
   * Ermittelt den Typ des Szenarios.
   *
   * @return Typ des Szenarios.
   */
  public String getType() {
    return type;
  }

  /**
   * Setzt den Typ des Szenarios.
   *
   * @param type Typ des Szenarios.
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Ermittelt die Liste mit den einzelnen Schritten des Szenarios.
   *
   * @return Liste mit den einzelnen Schritten des Szenarios.
   */
  public CucumberReportStep[] getSteps() {
    return steps;
  }

  /**
   * Setzt die Liste mit den einzelnen Schritten des Szenarios.
   *
   * @param steps Liste mit den einzelnen Schritten des Szenarios.
   */
  public void setSteps(CucumberReportStep[] steps) {
    this.steps = steps;
  }

}
