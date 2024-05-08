package com.jmvillel.demo.spacecraft.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jmvillel.demo.spacecraft.domain.SpaceCraft;
import com.jmvillel.demo.spacecraft.repository.SpaceCraftRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

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
		
		if(id != null 
				&& spaceCraftRepository.existsById(id)) {
			return spaceCraftRepository.findById(id).get();
		} else {
			throw new EntityNotFoundException("SpaceCraft with id "+id+" not exists.");
		}
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

	public SpaceCraft update(SpaceCraft spaceCraft) {
		
		if(spaceCraft.getId() != null 
				&& spaceCraftRepository.existsById(spaceCraft.getId())) {
			return spaceCraftRepository.save(spaceCraft);
		} else {
			throw new EntityNotFoundException("SpaceCraft with id "+spaceCraft.getId()+" not exists.");
		}

	}

	public Long delete(Long id) {
		if(id != null 
				&& spaceCraftRepository.existsById(id)) {
			spaceCraftRepository.deleteById(id);
			return id;
		} else {
			throw new EntityNotFoundException("SpaceCraft with id "+id+" not exists.");
		}
	}

}
