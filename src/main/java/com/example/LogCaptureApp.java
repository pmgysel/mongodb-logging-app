package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogCaptureApp {
  public static void main(String[] args) {
    new SpringApplication(LogCaptureApp.class).run();
  }
}
