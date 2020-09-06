package com.example.service;

import com.example.model.LogEvent;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class DocumentToLogMapper {

  public Document toDocument(LogEvent log) {
    Document doc = new Document();
    log.getLogEvent()
        .forEach(doc::append);
    return doc;
  }

  public LogEvent toLog(Document document) {
    Map<String, String> map = new HashMap<>();
    document.forEach((key, val) -> map.put(key, toStringMapper(val)));
    return new LogEvent(map);
  }

  private String toStringMapper(Object val) {
    return val instanceof Date
        ? toLocalDateTime((Date)val).toString()
        : val.toString();
  }

  private LocalDateTime toLocalDateTime(Date date) {
    return date.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();
  }
}
