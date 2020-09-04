package com.example.service.impl;

import com.example.model.LogEvent;
import com.example.model.LogType;
import com.example.service.RandomLog;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class RandomLogImpl implements RandomLog {

  private static String RANDOM_MSG = "xyz";
  private static String SEVERITY = "severity";
  private static String MESSAGE = "message";
  private static String CHECKOUT_STATE = "checkoutState";
  private static String BASKET_SIZE = "basketSize";
  private static String USER_ID = "userId";
  private static String PAY_AMOUNT = "paymentAmount";
  private static String CREDIT_CARD = "creditCardProvider";
  private static String RETRIES = "loginCount";

  @Override
  public LogEvent randomLog(LogType logType) {
    switch (logType) {
      case BASKET:
        return basketLog();
      case LOGIN:
        return loginLog();
      case PAYMENT:
      default:
        return paymentLog();
    }
  }

  private LogEvent loginLog() {
    Map<String, String> map = basicLog();
    map.put(RETRIES, RANDOM_MSG);
    map.put(USER_ID, RANDOM_MSG);
    return new LogEvent(map);
  }

  private LogEvent basketLog() {
    Map<String, String> map = basicLog();
    map.put(CHECKOUT_STATE, RANDOM_MSG);
    map.put(BASKET_SIZE, RANDOM_MSG);
    map.put(USER_ID, RANDOM_MSG);
    return new LogEvent(map);
  }

  private LogEvent paymentLog() {
    Map<String, String> map = basicLog();
    map.put(PAY_AMOUNT, RANDOM_MSG);
    map.put(CREDIT_CARD, RANDOM_MSG);
    return new LogEvent(map);
  }

  private Map<String, String> basicLog() {
    Map<String, String> map = new HashMap<>();
    map.put(LogEvent.LOG_TIME, LocalDateTime.now().toString());
    map.put(SEVERITY, RANDOM_MSG);
    map.put(MESSAGE, RANDOM_MSG);
    return map;
  }
}
