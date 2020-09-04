package com.example.db.impl;

import com.example.db.MongoDbService;
import com.example.model.LogEvent;
import lombok.val;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.*;

@Service
public class MongoDbServiceImpl implements MongoDbService {

  private final MongoDbCollectionSingleton dbSingleton;

  public MongoDbServiceImpl(MongoDbCollectionSingleton dbSingleton) {
    this.dbSingleton = dbSingleton;
  }

  @Override
  public String store(Document document, String logType) {
    document.append(LogEvent.APP_TYPE, logType);
    document.append(LogEvent.LOG_TIME, LocalDateTime.now());
    val result = dbSingleton.getDbAccess().insertOne(document);
    return result.getInsertedId().toString();
  }

  /*@Override
  public Optional<Document> findOne(String key, String value) {
    val result = new ArrayList<Document>();
    dbSingleton.getDbAccess().find(eq(key, value)).into(result);
    return result.size() == 1
        ? Optional.of(result.get(0))
        : Optional.empty();
  }*/

  @Override
  public Optional<Document> findById(String id) {
    val result = new ArrayList<Document>();
    dbSingleton.getDbAccess().find(eq(LogEvent.LOG_ID, new ObjectId(id))).into(result);
    return result.size() == 1
        ? Optional.of(result.get(0))
        : Optional.empty();
  }

  @Override
  public List<Document> findInTimeRange(LocalDateTime from, LocalDateTime to) {
    val result = new ArrayList<Document>();
    dbSingleton.getDbAccess().find(and(gt(LogEvent.LOG_TIME, from), lt(LogEvent.LOG_TIME, to))).into(result);
    return result;
  }

  @Override
  public List<Document> findMany(String key, String value) {
    val result = new ArrayList<Document>();
    dbSingleton.getDbAccess().find(eq(key, value)).into(result);
    return result;
  }

  @Override
  public List<Document> findMany(String key0, String value0, String key1, String value1) {
    val result = new ArrayList<Document>();
    dbSingleton.getDbAccess().find(and(eq(key0, value0), eq(key0, value0))).into(result);
    return result;
  }

  @Override
  public List<Document> findByApp(String app) {
    val result = new ArrayList<Document>();
    dbSingleton.getDbAccess().find(eq(LogEvent.APP_TYPE, app)).into(result);
    return result;
  }
}
