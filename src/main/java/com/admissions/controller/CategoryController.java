package com.admissions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admissions.entity.Category;
import com.admissions.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping(value = { "/find/id/{id}", "/{id}" })
	public ResponseEntity<Category> findById(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.findById(id));
	}

	@GetMapping(value = { "/find/name/{name}" })
	public ResponseEntity<Category> findByName(@PathVariable("name") String name) {
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.findByName(name));
	}

	@GetMapping(value = {"", "/all"})
	public ResponseEntity<List<Category>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
	}

	@PostMapping
	public ResponseEntity<Category> save(@RequestBody Category category) {
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.save(category, 1));
	}

	@PutMapping
	public ResponseEntity<Category> update(@RequestBody Category category) {
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.save(category, 2));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.delete(id));
	}
}
