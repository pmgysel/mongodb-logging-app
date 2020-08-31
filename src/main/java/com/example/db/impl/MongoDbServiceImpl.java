package com.example.db.impl;

import com.example.db.MongoDbService;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MongoDbServiceImpl implements MongoDbService {

  private final MongoDbCollectionSingleton dbSingleton;

  public MongoDbServiceImpl(MongoDbCollectionSingleton dbSingleton) {
    this.dbSingleton = dbSingleton;
  }

  @Override
  public Document store(Document document) {
    dbSingleton.getDbAccess().insertOne(document);
    return document; // TODO can add ID?
  }

  @Override
  public Optional<Document> findOne(String key, String value) {
    return Optional.empty();
  }

  @Override
  public List<Document> findInTimeRange(LocalDateTime from, LocalDateTime to) {
    return null;
  }

  @Override
  public List<Document> findMany(String key, String value) {
    return null;
  }

  @Override
  public List<Document> findMany(String key0, String value0, String key1, String value1) {
    return null;
  }
}
