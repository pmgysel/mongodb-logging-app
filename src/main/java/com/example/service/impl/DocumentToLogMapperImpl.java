package com.example.service.impl;

import com.example.model.LogEvent;
import com.example.service.DocumentToLogMapper;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DocumentToLogMapperImpl implements DocumentToLogMapper {

  @Override
  public Document toDocument(LogEvent log) {
    Document doc = new Document();
    log.getLogProps()
        .forEach(doc::append);
    return doc;
  }

  // todo test case where document has more than one level
  // todo: can just do JsonToLogMapper.fromJson(document.toString()) ??
  @Override
  public LogEvent toLog(Document document) {
    Map<String, String> map = new HashMap<>();
    document.forEach((key, val) -> map.put(key, val.toString()));
    return new LogEvent(map);
  }
}
