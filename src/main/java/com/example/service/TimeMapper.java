package com.example.service;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TimeMapper {
  public LocalDateTime toTime(String time);
}
