package com.example.model;

import lombok.Data;

import java.util.Map;

@Data
public final class LogEvent {
  public static final String LOG_ID = "_id";
  public static final String LOG_TIME = "_createDate";
  public static final String APP_TYPE = "_appType";

  private final Map<String, String> logProps;

}
