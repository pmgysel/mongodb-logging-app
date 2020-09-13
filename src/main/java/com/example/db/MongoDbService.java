package com.example.db;

import com.example.model.LogEvent;
import com.example.model.LogId;
import com.example.model.LogType;
import com.mongodb.client.MongoCollection;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class MongoDbService {

  private final MongoCollection<Document> mongoCollection;

  public LogId store(Document document, LogType logType) {
    document.append(LogEvent.APP_TYPE, logType.toString());
    document.append(LogEvent.LOG_TIME, LocalDateTime.now());
    val result = mongoCollection.insertOne(document);
    return new LogId(result.getInsertedId().asObjectId().getValue().toString());
  }

  public Optional<Document> findById(String id) {
    return Try.of(() -> new ObjectId(id)).toOption().toJavaOptional()
        .flatMap(this::findById);
  }

  private Optional<Document> findById(ObjectId id) {
    return Optional.ofNullable(mongoCollection.find(eq(LogEvent.LOG_ID, id)).first());
  }

  public List<Document> findInTimeRange(LocalDateTime from, LocalDateTime to) {
    val result = new ArrayList<Document>();
    mongoCollection.find(and(gt(LogEvent.LOG_TIME, from), lt(LogEvent.LOG_TIME, to))).into(result);
    return result;
  }

  public List<Document> findMany(String key, String value) {
    val result = new ArrayList<Document>();
    mongoCollection.find(eq(key, value)).into(result);
    return result;
  }

  public List<Document> findMany(String key0, String value0, String key1, String value1) {
    val result = new ArrayList<Document>();
    mongoCollection.find(and(eq(key0, value0), eq(key0, value0))).into(result);
    return result;
  }

  public List<Document> findByApp(LogType app) {
    val result = new ArrayList<Document>();
    mongoCollection.find(eq(LogEvent.APP_TYPE, app.toString())).into(result);
    return result;
  }
}
