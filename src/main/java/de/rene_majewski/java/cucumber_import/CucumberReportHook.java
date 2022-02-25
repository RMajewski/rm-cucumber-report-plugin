package de.rene_majewski.java.cucumber_import;

/**
 * Speichert die Daten des eines aufgerufen Hooks aus den Cucumber-Daten.
 *
 * @author René Majewski
 * @since 0.1.0
 */
public class CucumberReportHook {
  /**
   * Speichert die Daten des Ergebnisses.
   */
  private CucumberReportResult result;

  /**
   * Speichert die Daten des Resultats.
   */
  private CucumberReportMatch match;

  public CucumberReportHook() {
    result = null;
    match = null;
  }

  /**
   * Ermittelt die Daten des Ergebnisses.
   *
   * @return Daten des Ergebnisses
   */
  public CucumberReportResult getResult() {
    return result;
  }

  /**
   * Setzt die Daten des Ergebnisses.
   *
   * @param result Daten des Ergebnisses.
   */
  public void setResult(CucumberReportResult result) {
    this.result = result;
  }

  /**
   * Ermittelt die Daten des Resultats.
   *
   * @return Daten des Resultats.
   */
  public CucumberReportMatch getMatch() {
    return match;
  }

  /**
   * Setzt die Daten des Resultats.
   *
   * @param match Daten des Resultats.
   */
  public void setMatch(CucumberReportMatch match) {
    this.match = match;
  }


}
