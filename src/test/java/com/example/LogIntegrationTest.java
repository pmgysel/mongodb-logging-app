package com.example;

import com.example.db.MongoDbService;
import com.example.model.LogEvent;
import com.example.model.LogType;
import com.example.service.RandomLog;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LogIntegrationTest {

  @Autowired
  private MockMvc mvc;
  @Autowired
  private RandomLog randomLog;
  @Autowired
  private MongoDbService mongoDbService;

  private LogEvent log;
  private LogType logType;

  @BeforeEach
  public void before() {
    logType = LogType.LOGIN;
    log = randomLog.randomLog(logType);
    mongoDbService.removeAll();
  }

  /**
   * Create a log entry from JSON (LogRestController#createLog), then search for the log by _appType
   * (LogRestController#getLogByOneField).
   */
  @Test
  public void createAndSearchLog() throws Exception {
    mvc.perform(
        post("/api/log")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(log))
            .param("app", logType.toString()))
        .andExpect(status().isOk());

    String json = mvc.perform(
        get("/api/log/search1")
            .param("key", LogEvent.APP_TYPE)
            .param("value", logType.toString()))
        .andReturn().getResponse().getContentAsString();

    List<LogEvent> events = fromJsonString(json);

    assertEquals(1, events.size());
    assertEquals(logType.toString(), events.get(0).getLogEvent().get(LogEvent.APP_TYPE));
  }

  public static String asJsonString(Object obj) {
    return Try.of(() -> new ObjectMapper().writeValueAsString(obj))
        .onFailure(err -> System.out.println("Something went wrong during JSON serialization of " + obj))
        .get();
  }

  public static List<LogEvent> fromJsonString(String logs) {
    return Try.of(() -> new ObjectMapper().readValue(logs, new TypeReference<List<LogEvent>>(){}))
        .onFailure(err -> System.out.println("Can't read json data: " + logs))
        .get();
  }
}
