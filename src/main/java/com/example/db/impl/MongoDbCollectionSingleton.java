package com.example.db.impl;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Service;

@Service
public final class MongoDbCollectionSingleton {
  private MongoDbCollectionSingleton(){}
  private static MongoCollection<Document> dbAccess;

  static {
    MongoClient mongoClient = MongoClients.create();
    MongoDatabase database = mongoClient.getDatabase("mydb");
    dbAccess = database.getCollection("logEvents");
  }

  public MongoCollection<Document> getDbAccess() {
    return dbAccess;
  }
}
