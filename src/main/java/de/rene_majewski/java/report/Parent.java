package de.rene_majewski.java.report;

import java.time.LocalDateTime;
import org.apache.maven.doxia.sink.Sink;

public abstract class Parent {
  protected boolean pass;
  protected boolean error;
  protected boolean failure;
  protected boolean pending;
  protected boolean undefined;
  protected boolean skip;
  protected String description;
  protected LocalDateTime startedAt;
  protected String name;
  protected String keyword;
  protected long duration;

  public Parent() {
    pass = false;
    error = false;
    failure = false;
    pending = false;
    skip = false;
    undefined = false;
    startedAt = null;
    description = "";
    name = "";
    keyword = "";
    duration = 0;
  }

  /**
   * @return the pass
   */
  public boolean isPass() {
    return pass;
  }

  /**
   * @param pass the pass to set
   */
  public void setPass(boolean pass) {
    this.pass = pass;
  }

  /**
   * @return the error
   */
  public boolean isError() {
    return error;
  }

  /**
   * @param error the error to set
   */
  public void setError(boolean error) {
    this.error = error;
  }

  /**
   * @return the failure
   */
  public boolean isFailure() {
    return failure;
  }

  /**
   * @param failure the failure to set
   */
  public void setFailure(boolean failure) {
    this.failure = failure;
  }

  /**
   * @return the pending
   */
  public boolean isPending() {
    return pending;
  }

  /**
   * @param pending the pending to set
   */
  public void setPending(boolean pending) {
    this.pending = pending;
  }

  /**
   * @return the skip
   */
  public boolean isSkip() {
    return skip;
  }

  /**
   * @param skip the skip to set
   */
  public void setSkip(boolean skip) {
    this.skip = skip;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return the startedAt
   */
  public LocalDateTime getStartedAt() {
    return startedAt;
  }

  /**
   * @param startetAt the startetAt to set
   */
  public void setStartedAt(LocalDateTime startetAt) {
    this.startedAt = startetAt;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the keyword
   */
  public String getKeyword() {
    return keyword;
  }

  /**
   * @param keyword the keyword to set
   */
  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  /**
   * @return the duration
   */
  public long getDuration() {
    return duration;
  }

  /**
   * @param duration the duration to set
   */
  public void setDuration(long duration) {
    this.duration = duration;
  }

  /**
   * @return the undefined
   */
  public boolean isUndefined() {
    return undefined;
  }

  /**
   * @param undefined the undefined to set
   */
  public void setUndefined(boolean undefined) {
    this.undefined = undefined;
  }

  public abstract void writeToReport(Sink sink);
}
