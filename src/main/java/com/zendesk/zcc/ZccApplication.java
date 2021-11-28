package com.zendesk.zcc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.zendesk.zcc.Constants.ZCC_PASS;
import static com.zendesk.zcc.Constants.ZCC_SUB_DOMAIN;
import static com.zendesk.zcc.Constants.ZCC_USER;

@SpringBootApplication
public class ZccApplication implements ApplicationRunner {
  @Value("${user}")
  String username;
  @Value("${pass}")
  String password;
  @Value("${sub_domain}")
  String sub_domain;

  public static void main(String[] args) {
    SpringApplication.run(ZccApplication.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    System.setProperty(ZCC_USER, username);
    System.setProperty(ZCC_PASS, password);
    System.setProperty(ZCC_SUB_DOMAIN, sub_domain);
  }
}
