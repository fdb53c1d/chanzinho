package org.chanzinho.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.chanzinho.model.Board;
import org.chanzinho.model.Section;
import org.chanzinho.repository.BoardRepository;
import org.chanzinho.repository.SectionRepository;
import org.chanzinho.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardRepository boardRepository;
	@Autowired
	SectionRepository sectionRepository;
	
	public Board findById(Integer id) {
		return boardRepository.findById(id);
	}
	
	public Board findByName(String name) {
		return boardRepository.findByName(name);
	}
	
	public Map<Section, List<Board>> findBoardList() {
		
		List<Section> sections = sectionRepository.findNotHiddenSections();
		Map<Section, List<Board>> boardList = new LinkedHashMap<Section, List<Board>>();
		
		for(Section section : sections) {
			List<Board> boards = boardRepository.findBoardsBySection(section.getId());
			boardList.put(section, boards);
		}
		
		return boardList;
	}
}
