package org.chanzinho.service;

import org.chanzinho.model.BanList;
import org.chanzinho.repository.BanListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BanService {

	@Autowired
	BanListRepository banListRepository;	
	
	public BanList findById(Integer id) {
		return banListRepository.findById(id);
	}
	
}
