package com.jmvillel.demo.spacecraft.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jmvillel.demo.spacecraft.domain.SpaceCraft;
import com.jmvillel.demo.spacecraft.repository.SpaceCraftRepository;

@Service
public class SpaceCraftService {
	
	private final SpaceCraftRepository spaceCraftRepository;
	
	SpaceCraftService(SpaceCraftRepository repository) {
		this.spaceCraftRepository = repository;
	}

	public Page<SpaceCraft> findAllPaginated(Pageable pageable) {
		return spaceCraftRepository.findAll(pageable);
	}

	public SpaceCraft findOneById(Long id) {
		return spaceCraftRepository.findById(id).orElse(null);
	}
	
	

}
