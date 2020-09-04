package com.example.db;

import org.bson.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MongoDbService {
  public String store(Document document, String app);
  public Optional<Document> findById(String id);
  //public Optional<Document> findOne(String key, String value);
  public List<Document> findInTimeRange(LocalDateTime from, LocalDateTime to);
  public List<Document> findMany(String key, String value);
  public List<Document> findMany(String key0, String value0, String key1, String value1);
  public List<Document> findByApp(String app);
}
