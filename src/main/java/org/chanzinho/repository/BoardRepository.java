package org.chanzinho.repository;

import org.chanzinho.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	
	Board findById(Integer id);

}
