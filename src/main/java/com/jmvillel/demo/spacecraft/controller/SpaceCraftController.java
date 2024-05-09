package com.jmvillel.demo.spacecraft.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jmvillel.demo.spacecraft.domain.SpaceCraft;
import com.jmvillel.demo.spacecraft.service.SpaceCraftService;

@RestController
public class SpaceCraftController {
	
	private final SpaceCraftService spaceCraftService;
	
	SpaceCraftController(SpaceCraftService service) {
		this.spaceCraftService = service;
	}
	
	/**
	 * Find the page number @page with size @size
	 * @param pageable
	 * @return
	 */
	@GetMapping(path = "/spacecrafts", params = { "page", "size" })
    public ResponseEntity<Page<SpaceCraft>> findPage(final Pageable pageable) {
        return ResponseEntity.ok(spaceCraftService.findAllPaginated(pageable));
    }
	
	@GetMapping(path="/spacecrafts", params = {"query"})
	public ResponseEntity<List<SpaceCraft>> findByName(String query) {
		return ResponseEntity.ok(spaceCraftService.findAllByName(query));
	}
	
	@GetMapping("/spacecrafts/{id}")
	public ResponseEntity<SpaceCraft> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(spaceCraftService.findOneById(id));
	}
	
	@PostMapping("/spacecrafts")
	public ResponseEntity<SpaceCraft> create(@RequestBody SpaceCraft spaceCraft) {
		return ResponseEntity.ok(spaceCraftService.create(spaceCraft));
	}
	
	@PutMapping("/spacecrafts")
	public ResponseEntity<SpaceCraft> update(@RequestBody SpaceCraft spaceCraft) {
		return ResponseEntity.ok(spaceCraftService.update(spaceCraft));
	}
	
	@DeleteMapping("/spacecrafts/{id}")
	public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
		
		return ResponseEntity.ok(spaceCraftService.delete(id));
	}
}
