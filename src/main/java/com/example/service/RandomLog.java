package com.example.service;

import com.example.model.LogEvent;
import com.example.model.LogType;

public interface RandomLog {
  public LogEvent randomLog(LogType logType);
}
