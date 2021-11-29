package com.zendesk.zcc.controller;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Order()
public class DefaultController {
  @RequestMapping(value = {"/{path:[^\\.]*}","/"})
  public String handleRequest() {
    return "index.jsp";
  }
}
