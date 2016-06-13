package org.chanzinho.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.chanzinho.exception.ChanzinhoException;
import org.chanzinho.model.Board;
import org.chanzinho.model.Post;
import org.chanzinho.model.Section;
import org.chanzinho.service.BoardService;
import org.chanzinho.service.PostService;
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
	@Autowired
	BoardService boardService;
	@Autowired
	PostService postService;
	
	@RequestMapping(value="/template.php", method=RequestMethod.GET)
	public String processTemplate() {
		Map<String, Object> root = new HashMap<String, Object>();
		
		Board board = boardService.findById(1);
		Map<Section, List<Board>> boardList = boardService.findBoardList();
		List<Post> opss = postService.findOpsByBoardIdAndPageIndex(1, 0);
		Map<Post, List<Post>> ops = postService.findPostsByBoardIdAndPageIndex(3, 1, 0);
		
		root.put("board", board);
		root.put("cwebpath", "http://localhost:12345/");
		root.put("charset", "UTF-8");
		root.put("boardList", boardList);
		root.put("ops", ops);
		
		try {
			processor.processTemplateFile("img_index.ftl", new File("resources/index.html"), root);
		} catch(ChanzinhoException e) {
			return e.getMessage();
		}
			
		return "";
	}
}
