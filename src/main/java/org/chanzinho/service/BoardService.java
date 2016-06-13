package org.chanzinho.service;

import java.util.List;
import java.util.Map;

import org.chanzinho.model.Board;
import org.chanzinho.model.Section;

public interface BoardService {
	
	Board findById(Integer id);
	
	Map<Section, List<Board>> findBoardList();

}
