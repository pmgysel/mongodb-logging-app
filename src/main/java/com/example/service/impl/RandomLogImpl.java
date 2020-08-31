package com.example.service.impl;

import com.example.model.LogEvent;
import com.example.model.LogType;
import com.example.service.RandomLog;
import org.springframework.stereotype.Service;

@Service
public class RandomLogImpl implements RandomLog {

  @Override
  public LogEvent randomLog(LogType logType) {
    return null;
  }
}
