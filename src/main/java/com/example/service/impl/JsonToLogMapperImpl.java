package com.example.service.impl;

import com.example.model.LogEvent;
import com.example.service.JsonToLogMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JsonToLogMapperImpl implements JsonToLogMapper {
  @Override
  public Optional<LogEvent> fromJson(String json) {
    return Optional.empty();
  }
}
