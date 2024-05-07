package com.jmvillel.demo.spacecraft.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmvillel.demo.spacecraft.domain.SpaceCraft;
import com.jmvillel.demo.spacecraft.repository.SpaceCraftRepository;

@RestController
public class SpaceCraftController {
	
	private final SpaceCraftRepository spaceCraftRepository;
	
	SpaceCraftController(SpaceCraftRepository repository) {
		this.spaceCraftRepository = repository;
	}
	
	/**
	 * Find the page number @page with size @size
	 * @param pageable
	 * @return
	 */
	@GetMapping(path = "/spacecrafts", params = { "page", "size" })
    public ResponseEntity<Page<SpaceCraft>> findPage(final Pageable pageable) {
        return ResponseEntity.ok(spaceCraftRepository.findAll(pageable));
    }	
	
}
