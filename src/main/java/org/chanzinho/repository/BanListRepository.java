package org.chanzinho.repository;

import org.chanzinho.model.BanList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanListRepository extends JpaRepository<BanList, Integer> {

  BanList findById(Integer id);

}
