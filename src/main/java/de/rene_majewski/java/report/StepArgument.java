package de.rene_majewski.java.report;

public class StepArgument {
  private String value;
  private int offset;

  public StepArgument(String value, int offset) {
    this.value = value;
    this.offset = offset;
  }

  /**
   * @return the value
   */
  public String getValue() {
    return value;
  }

  /**
   * @param value the value to set
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * @return the offset
   */
  public int getOffset() {
    return offset;
  }

  /**
   * @param offset the offset to set
   */
  public void setOffset(int offset) {
    this.offset = offset;
  }


}
