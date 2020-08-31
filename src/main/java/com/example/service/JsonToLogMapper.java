package com.example.service;

import com.example.model.LogEvent;

import java.util.Optional;

public interface JsonToLogMapper {
  public Optional<LogEvent> fromJson(String json);
}
