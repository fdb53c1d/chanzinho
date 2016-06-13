package org.chanzinho.controller;

import org.chanzinho.config.TemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class TestController {
	
	@Autowired
	TemplateConfig templateConfig;
	
	@RequestMapping("/")
	public String indexAction() {
		return "deu certo";
	}
}
