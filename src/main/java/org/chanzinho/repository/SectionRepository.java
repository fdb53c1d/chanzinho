package org.chanzinho.repository;

import java.util.List;

import org.chanzinho.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SectionRepository extends JpaRepository<Section, Integer> {

  @Query("select s from Section s where s.hidden = 0 order by s.order")
  List<Section> findNotHiddenSections();

}
