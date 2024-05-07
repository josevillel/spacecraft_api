package com.jmvillel.demo.spacecraft.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jmvillel.demo.spacecraft.domain.SpaceCraft;
import com.jmvillel.demo.spacecraft.repository.SpaceCraftRepository;

import jakarta.persistence.EntityExistsException;

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

	public List<SpaceCraft> findAllByName(String name) {
		return spaceCraftRepository.findAllByNameContainingIgnoreCase(name);
	}

	public SpaceCraft create(SpaceCraft spaceCraft) {
		
		if(spaceCraft.getId() != null 
				&& spaceCraftRepository.existsById(spaceCraft.getId())) {
			throw new EntityExistsException("SpaceCraft with id "+spaceCraft.getId()+" already exists.");
		} else {
			return spaceCraftRepository.save(spaceCraft);
		}
	}

}
