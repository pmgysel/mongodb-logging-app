package com.example.model;

import java.util.Map;

public final class LogEvent {
  public static final String LOG_ID = "_id";
  public static final String LOG_TIME = "createDate";
  public static final String APP_TYPE = "appType";

  private Map<String, String> logProps;

  public LogEvent(Map<String, String> logProps) {
    this.logProps = logProps;
  }

  public Map<String, String> getLogProps() {
    return logProps;
  }

  public void setLogProps(Map<String, String> logProps) {
    this.logProps = logProps;
  }
}
