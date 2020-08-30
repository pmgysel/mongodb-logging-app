package com.example;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
public class LogRestController {

  @RequestMapping(method = RequestMethod.GET, path = "/api/log/{id}")
  public ResponseEntity<Log> getLogById(@PathVariable(value = "id") String id) {
    return ResponseEntity.ok(new Log().setName("company 0"));
  }

  @RequestMapping(method = RequestMethod.POST, path = "/api/log/fromJson")
  public ResponseEntity<Log> createLogFromJson(@RequestBody String logJson) {
    return ResponseEntity.ok(new Log().setName("company 0"));
  }

  @RequestMapping(method = RequestMethod.GET, path = "/api/log/dateRange")
  public ResponseEntity<List<Log>> getLogByDateRange(
      @RequestParam(value = "from") String from,
      @RequestParam(value = "to") String to
  ) {
    return ResponseEntity.ok(Arrays.asList(new Log().setName("company 0")));
  }

  @RequestMapping(method = RequestMethod.GET, path = "/api/log/search1")
  public ResponseEntity<List<Log>> getLogByOneField(
      @RequestParam(value = "key") String key,
      @RequestParam(value = "value") String value
  ) {
    return ResponseEntity.ok(Arrays.asList(new Log().setName("company 0")));
  }

  @RequestMapping(method = RequestMethod.GET, path = "/api/log/search2")
  public ResponseEntity<List<Log>> getLogByTwoFields(
      @RequestParam(value = "key0") String key0,
      @RequestParam(value = "value0") String value0,
      @RequestParam(value = "key1") String key1,
      @RequestParam(value = "value1") String value1
  ) {
    return ResponseEntity.ok(Arrays.asList(
        new Log().setName("company 0"),
        new Log().setName("company 1")
    ));
  }

  @RequestMapping(method = RequestMethod.POST, path = "/api/log/random")
  public ResponseEntity<Log> createLogRandom(@RequestParam LogType appType) {
    return ResponseEntity.ok(new Log().setName(appType.toString()));
  }

}
