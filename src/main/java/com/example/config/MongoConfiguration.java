package com.example.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfiguration {

  @Bean
  public MongoCollection<Document> getMongoCollection(
      @Value("${mongo.database}") String database,
      @Value("${mongo.collection}") String collection) {
    MongoClient mongoClient = MongoClients.create();
    MongoDatabase db = mongoClient.getDatabase(database);
    return db.getCollection(collection);
  }
}
