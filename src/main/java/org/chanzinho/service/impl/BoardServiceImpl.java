package org.chanzinho.service.impl;

import org.chanzinho.model.Board;
import org.chanzinho.repository.BoardRepository;
import org.chanzinho.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardRepository boardRepository;
	
	public Board findById(Integer id) {
		return boardRepository.findById(id);
	}
}
