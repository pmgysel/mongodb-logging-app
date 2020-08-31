package com.example.service;

import com.example.model.LogEvent;
import org.bson.Document;

public interface DocumentToLogMapper {
  public Document toDocument(LogEvent log);
  public LogEvent toLog(Document document);
}
