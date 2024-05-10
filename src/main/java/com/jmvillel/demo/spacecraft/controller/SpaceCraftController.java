package com.jmvillel.demo.spacecraft.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jmvillel.demo.spacecraft.domain.SpaceCraft;
import com.jmvillel.demo.spacecraft.service.SpaceCraftService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/spacecrafts")
@OpenAPIDefinition(info = @Info(title = "SpaceCrafts API", version = "v1"))
@Tag(name="SpaceCrafts Controller")
@SecurityRequirement(name = "basicAuth")
public class SpaceCraftController {
	
	private final SpaceCraftService spaceCraftService;
	
	SpaceCraftController(SpaceCraftService service) {
		this.spaceCraftService = service;
	}
	
	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Get a page of SpaceCrafts")
    public ResponseEntity<Page<SpaceCraft>> findPage(@RequestParam Integer page, @RequestParam Integer size)  {
        return ResponseEntity.ok(spaceCraftService.findAllPaginated(PageRequest.of(page,size)));
    }
	
	@GetMapping(path= "/filter", params = {"query"})
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Get a list of SpaceCrafts filtered by name")
	public ResponseEntity<List<SpaceCraft>> findByName(String query) {
		return ResponseEntity.ok(spaceCraftService.findAllByName(query));
	}
	
	@Operation(summary = "Get a SpaceCraft by id")
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<SpaceCraft> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(spaceCraftService.findOneById(id));
	}
	
	@Operation(summary = "Create a SpaceCraft")
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<SpaceCraft> create(@RequestBody SpaceCraft spaceCraft) {
		return ResponseEntity.ok(spaceCraftService.create(spaceCraft));
	}
	
	@Operation(summary = "Update a SpaceCraft")
	@PutMapping()
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<SpaceCraft> update(@RequestBody SpaceCraft spaceCraft) {
		return ResponseEntity.ok(spaceCraftService.update(spaceCraft));
	}
	
	@Operation(summary = "Delete a SpaceCraft")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
		
		return ResponseEntity.ok(spaceCraftService.delete(id));
	}
}
