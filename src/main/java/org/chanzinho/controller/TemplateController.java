package org.chanzinho.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.chanzinho.exception.ChanzinhoException;
import org.chanzinho.model.Boards;
import org.chanzinho.service.TemplateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class TemplateController {
	
	@Autowired
	TemplateProcessor processor;
	
	@RequestMapping(value="/template.php", method=RequestMethod.GET)
	public String processTemplate() {
		Map<String, Object> root = new HashMap<String, Object>();
		
		Boards board = new Boards();
		board.setName("Aleatorio");
		
		root.put("board", board);
		root.put("cwebpath", "http://localhost:12345");
		root.put("CHARSET", "UTF-8");
		
		try {
			processor.processTemplateFile("fragments/global_board_header.ftlh", new File("resources/test.html"), root);
		} catch(ChanzinhoException e) {
			return e.getMessage();
		}
			
		return "";
	}
}
