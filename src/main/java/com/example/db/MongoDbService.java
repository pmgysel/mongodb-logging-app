package com.example.db;

import org.bson.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MongoDbService {
  public Document store(Document document);
  public Optional<Document> findOne(String key, String value);
  public List<Document> findInTimeRange(LocalDateTime from, LocalDateTime to);
  public List<Document> findMany(String key, String value);
  public List<Document> findMany(String key0, String value0, String key1, String value1);
}
