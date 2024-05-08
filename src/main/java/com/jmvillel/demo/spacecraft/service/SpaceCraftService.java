package com.jmvillel.demo.spacecraft.service;

import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jmvillel.demo.spacecraft.domain.SpaceCraft;
import com.jmvillel.demo.spacecraft.repository.SpaceCraftRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SpaceCraftService {
	
    
	private static final Locale locale = LocaleContextHolder.getLocale();
	
	private final SpaceCraftRepository spaceCraftRepository;
	private final MessageSource messageSource;
	
	SpaceCraftService(SpaceCraftRepository repository, MessageSource messageSource ) {
		this.spaceCraftRepository = repository;
		this.messageSource = messageSource;
	}

	public Page<SpaceCraft> findAllPaginated(Pageable pageable) {
		return spaceCraftRepository.findAll(pageable);
	}

	public SpaceCraft findOneById(Long id) {
		
		if(id != null 
				&& spaceCraftRepository.existsById(id)) {
			return spaceCraftRepository.findById(id).get();
		} else {
			throw new EntityNotFoundException(messageSource.getMessage("error.entity.notFound", new Object[]{"Spacecraft",id}, locale));
		}
	}

	public List<SpaceCraft> findAllByName(String name) {
		return spaceCraftRepository.findAllByNameContainingIgnoreCase(name);
	}

	public SpaceCraft create(SpaceCraft spaceCraft) {
		
		if(spaceCraft.getId() != null 
				&& spaceCraftRepository.existsById(spaceCraft.getId())) {
			throw new EntityExistsException(messageSource.getMessage("error.entity.exists", new Object[]{"Spacecraft",spaceCraft.getId()}, locale));
		} else {
			return spaceCraftRepository.save(spaceCraft);
		}
	}

	public SpaceCraft update(SpaceCraft spaceCraft) {
		
		if(spaceCraft.getId() != null 
				&& spaceCraftRepository.existsById(spaceCraft.getId())) {
			return spaceCraftRepository.save(spaceCraft);
		} else {
			throw new EntityNotFoundException(messageSource.getMessage("error.entity.notFound", new Object[]{"Spacecraft",spaceCraft.getId()}, locale));
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
