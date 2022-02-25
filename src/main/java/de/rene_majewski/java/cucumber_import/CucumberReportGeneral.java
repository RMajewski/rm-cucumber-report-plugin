package de.rene_majewski.java.cucumber_import;

/**
 * Implementiert die Felder die in allen Cucumber-Report-Klassen genutzt
 * werden.
 *
 * @author René Majewski
 * @since 0.1.0
 */
public class CucumberReportGeneral {
  /**
   * Speichert die Zeilennummer.
   */
  private int line;

  /**
   * Speichert den Namen der Zeile.
   */
  private String name;

  /**
   * Speichert die Beschreibung.
   */
  private String description;

  /**
   * Speichert die ID der Zeile.
   */
  private String id;

  /**
   * Speichert das Schlüsselwort das mit der Zeile verknüpft ist.
   */
  private String keyword;

  /**
   * Setzt die Felder auf einen definierten Anfangs-Wert.
   */
  public CucumberReportGeneral() {
    line = -1;
    name = null;
    description = null;
    id = null;
    keyword = null;
  }

  /**
   * Gibt die Zeilennummer zurück.
   *
   * @return Nummer der Zeile.
   */
  public int getLine() {
    return line;
  }

  /**
   * Setzt die Zeilennummer.
   *
   * @param line Die Nummer der Zeile die gespeichert werden soll.
   */
  public void setLine(int line) {
    this.line = line;
  }

  /**
   * Gibt den Namen zurück.
   *
   * @return Name
   */
  public String getName() {
    return name;
  }

  /**
   * Speichert den Namen.
   *
   * @param name Name der gespeichert werden soll.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gibt die Beschreibung zurück.
   *
   * @return Beschreibung
   */
  public String getDescription() {
    return description;
  }

  /**
   * Speichert die Beschreibung.
   *
   * @param description Beschreibung die gespeichert werden soll.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gibt die ID der Zeile zurück.
   *
   * @return ID der Zeile.
   */
  public String getId() {
    return id;
  }

  /**
   * Setzt die ID der Zeile.
   *
   * @param id ID der Zeile die gespeichert werden soll.
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Gibt das Schlüsselwort zurück.
   *
   * @return Schlüsselwort das mit dem Datensatz verknüpft ist.
   */
  public String getKeyword() {
    return keyword;
  }

  /**
   * Speichert das Schlüsselwort.
   *
   * @param keyword Schlüsselwort das gespeichert werden soll.
   */
  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

}
