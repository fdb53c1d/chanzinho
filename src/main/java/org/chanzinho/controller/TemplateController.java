package org.chanzinho.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.chanzinho.exception.ChanzinhoException;
import org.chanzinho.model.Boards;
import org.chanzinho.service.TemplateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

@RestController
@EnableAutoConfiguration
public class TemplateController {
	
	@Autowired
	TemplateProcessor processor;
	
	@RequestMapping(value="/template.php", method=RequestMethod.GET)
	public String processTemplate() {
		Map<String, Object> root = new HashMap<String, Object>();
		
		Boards board = new Boards();
		board.setName("b");
		board.setDesc("aleatorio");
		board.setImage("");
		board.setIncludeHeader("");
		board.setEnableCaptcha(0);
		List<String> styles = new ArrayList<String>();
		styles.add("style1");
		styles.add("lena");
		styles.add("burichan");
		List<List<Boards>> boardlist = new ArrayList<List<Boards>>();
		List<Boards> section = new ArrayList<Boards>();
		section.add(board);
		boardlist.add(section);
		
		root.put("board", board);
		root.put("cwebpath", "http://localhost:12345");
		root.put("CHARSET", "UTF-8");
		root.put("BOARDSPATH", "http://localhost:12345");
		root.put("styles", styles);
		root.put("is_file", new IsFile());
		root.put("defaultStyle", "lena");
		root.put("CGIPATH", "http://localhost:12345");
		root.put("replyThread", 0);
		root.put("STYLESWITCHER", true);
		root.put("DROPSWITCHER", true);
		root.put("WATCHTHREADS", false);
		root.put("GENERATEBOARDLIST", true);
		root.put("BOARDSFOLDER", "/");
		root.put("WEBPATH", "/");
		root.put("boardlist", boardlist);
		root.put("HEADERURL", "");
		root.put("DIRTITLE", true);
		
		try {
			processor.processTemplateFile("fragments/img_header.ftl", new File("resources/test.html"), root);
		} catch(ChanzinhoException e) {
			return e.getMessage();
		}
			
		return "";
	}
}

class IsFile implements TemplateMethodModelEx {
	
	@Override
	public Object exec(List args) throws TemplateModelException {
		if (((ArrayList)args).size() != 1) {
			throw new TemplateModelException("Wrong arguments");
		}
		if(new File((String) ((ArrayList)args).get(0)).exists()) {
			return true;
		}
		return false;
	}
}
