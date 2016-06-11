package org.chanzinho.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.chanzinho.config.TemplateConfig;
import org.chanzinho.model.Teste;
import org.chanzinho.model.Teste2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@RestController
@EnableAutoConfiguration
public class TestController {
	
	@Autowired
	TemplateConfig templateConfig;
	
	@RequestMapping("/")
	public String indexAction() {
		try {
			Configuration cfg = templateConfig.getConfig();
			
			Map<String, Object> map = new HashMap<String, Object>();
			Teste teste = new Teste();
			Teste2 teste2 = new Teste2();
			teste2.setTeste2("teste2");
			teste.setUser("fulano");
			teste.setUrl("url");
			teste.setName("name");
			teste.setTeste2(teste2);
			map.put("teste", teste);
			
			Template tmp = cfg.getTemplate("fragments/global_board_header.ftlh");
			
			OutputStream stream = new FileOutputStream("resources/test.html", false);
			Writer out = new OutputStreamWriter(stream);
			
			tmp.process(map, out);
			out.close();
		} catch(IOException e) {
			System.out.println("Se fudeu");
			return "se fudeu";
		} catch(TemplateException e) {
			System.out.println("se fudeu");
			return "se fudeu";
		}
		return "deu certo";
	}
}
