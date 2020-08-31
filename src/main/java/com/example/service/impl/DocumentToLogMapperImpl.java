package com.example.service.impl;

import com.example.model.LogEvent;
import com.example.service.DocumentToLogMapper;
import org.bson.Document;
import org.springframework.stereotype.Service;

@Service
public class DocumentToLogMapperImpl implements DocumentToLogMapper {

  @Override
  public Document toDocument(LogEvent log) {
    return null;
  }

  @Override
  public LogEvent toLog(Document document) {
    return null;
  }
}
