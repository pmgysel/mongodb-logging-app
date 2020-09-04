package com.example.service.impl;

import com.example.service.TimeMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimeMapperImpl implements TimeMapper {

  @Override
  public LocalDateTime toTime(String time) {
    return LocalDateTime.parse(time);
  }
}
