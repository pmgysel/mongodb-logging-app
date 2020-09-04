package com.example.service.impl;

import com.example.model.LogEvent;
import com.example.service.JsonToLogMapper;
import com.google.gson.Gson;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class JsonToLogMapperImpl implements JsonToLogMapper {
  // TODO: test for case where string is empty; json has more than one level
  @Override
  public Optional<LogEvent> fromJson(String json) {
    return Try.of(() -> new Gson().fromJson(json, Map.class))
        .map(LogEvent::new)
        .toJavaOptional();
  }
}
