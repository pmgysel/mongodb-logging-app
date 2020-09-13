package com.example.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public final class LogEvent {
  public static final String LOG_ID = "_id";
  public static final String LOG_TIME = "_createDate";
  public static final String APP_TYPE = "_appType";

  @ApiModelProperty(example = "{" +
      "\"severiy\": \"ERROR\"," +
      "\"creditCardProvider\": \"VISA\"," +
      "\"message\": \"Unable to process payment\"," +
      "\"paymentAmount\": \"9.90\"" +
    "}")
  private Map<String, String> logEvent;

  // explicit constructor for json serialization
  public LogEvent() {
    this.logEvent = new HashMap<>();
  }

}
