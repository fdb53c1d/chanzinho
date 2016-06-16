package org.chanzinho.repository;

import java.util.List;

import org.chanzinho.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Integer> {

  Board findById(Integer id);

  @Query("select b from Board b where b.section = :id order by b.order")
  List<Board> findBoardsBySection(@Param("id") Integer id);

  Board findByName(String name);
}
