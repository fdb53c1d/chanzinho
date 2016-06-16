package org.chanzinho.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class TestController {

  @RequestMapping("/")
  public String indexAction() {
    return "deu certo";
  }
}
