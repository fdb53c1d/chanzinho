package org.chanzinho.service.impl;

import org.chanzinho.model.BanList;
import org.chanzinho.repository.BanListRepository;
import org.chanzinho.service.BanListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BanServiceImpl implements BanListService {

	@Autowired
	BanListRepository banListRepository;	
	
	public BanList findById(Integer id) {
		return banListRepository.findById(id);
	}
	
}
