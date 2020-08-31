package com.example.controller;

import com.example.db.MongoDbService;
import com.example.model.LogEvent;
import com.example.model.LogType;
import com.example.service.DocumentToLogMapper;
import com.example.service.JsonToLogMapper;
import com.example.service.RandomLog;
import com.example.service.TimeMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@RestController
public class LogRestController {

  private final MongoDbService mongoDbService;
  private final DocumentToLogMapper documentToLogMapper;
  private final JsonToLogMapper jsonToLogMapper;
  private final RandomLog randomLog;
  private final TimeMapper timeMapper;

  public LogRestController(MongoDbService mongoDbService, DocumentToLogMapper documentToLogMapper, JsonToLogMapper jsonToLogMapper, RandomLog randomLog, TimeMapper timeMapper) {
    this.mongoDbService = mongoDbService;
    this.documentToLogMapper = documentToLogMapper;
    this.jsonToLogMapper = jsonToLogMapper;
    this.randomLog = randomLog;
    this.timeMapper = timeMapper;
  }

  @RequestMapping(method = RequestMethod.GET, path = "/api/log/{id}")
  public ResponseEntity<LogEvent> getLogById(@PathVariable(value = "id") String id) {
    return mongoDbService.findOne(LogEvent.LOG_ID, id)
        .map(documentToLogMapper::toLog)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @RequestMapping(method = RequestMethod.POST, path = "/api/log/fromJson")
  public ResponseEntity<LogEvent> createLogFromJson(@RequestBody String logJson) {
    return jsonToLogMapper.fromJson(logJson)
        .map(documentToLogMapper::toDocument)
        .map(mongoDbService::store)
        .map(documentToLogMapper::toLog)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.badRequest().build());
  }

  @RequestMapping(method = RequestMethod.GET, path = "/api/log/dateRange")
  public ResponseEntity<List<LogEvent>> getLogByDateRange(
      @RequestParam(value = "from") String from,
      @RequestParam(value = "to") String to
  ) {
    LocalDateTime fromTime = timeMapper.toTime(from);
    LocalDateTime toTime = timeMapper.toTime(to);
    List<LogEvent> results = mongoDbService.findInTimeRange(fromTime, toTime)
        .stream()
        .map(documentToLogMapper::toLog)
        .collect(Collectors.toList());
    return ResponseEntity.ok(results);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/api/log/search1")
  public ResponseEntity<List<LogEvent>> getLogByOneField(
      @RequestParam(value = "key") String key,
      @RequestParam(value = "value") String value
  ) {
    List<LogEvent> results = mongoDbService.findMany(key, value)
        .stream()
        .map(documentToLogMapper::toLog)
        .collect(Collectors.toList());
    return ResponseEntity.ok(results);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/api/log/search2")
  public ResponseEntity<List<LogEvent>> getLogByTwoFields(
      @RequestParam(value = "key0") String key0,
      @RequestParam(value = "value0") String value0,
      @RequestParam(value = "key1") String key1,
      @RequestParam(value = "value1") String value1
  ) {
    List<LogEvent> results = mongoDbService.findMany(key0, value0, key1, value1)
        .stream()
        .map(documentToLogMapper::toLog)
        .collect(Collectors.toList());
    return ResponseEntity.ok(results);  }

  @RequestMapping(method = RequestMethod.POST, path = "/api/log/random")
  public ResponseEntity<LogEvent> createLogRandom(@RequestParam LogType appType) {
    return ResponseEntity.ok(randomLog.randomLog(appType));
  }

}
