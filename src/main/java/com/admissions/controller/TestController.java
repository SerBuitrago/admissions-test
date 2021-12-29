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

import com.admissions.entity.Test;
import com.admissions.service.TestService;

@RestController
@RequestMapping("/api/test")
public class TestController {
	
	@Autowired
	TestService testService;
	
	@GetMapping(value = { "/find/id/{id}", "/{id}" })
	public ResponseEntity<Test> findById(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(testService.findById(id));
	}

	@GetMapping(value = { "/find/name/{name}" })
	public ResponseEntity<Test> findByName(@PathVariable("name") String name) {
		return ResponseEntity.status(HttpStatus.OK).body(testService.findByName(name));
	}

	@GetMapping(value = {"", "/all"})
	public ResponseEntity<List<Test>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(testService.findAll());
	}

	@PostMapping
	public ResponseEntity<Test> save(@RequestBody Test test) {
		return ResponseEntity.status(HttpStatus.OK).body(testService.save(test, 1));
	}

	@PutMapping
	public ResponseEntity<Test> update(@RequestBody Test test) {
		return ResponseEntity.status(HttpStatus.OK).body(testService.save(test, 2));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(testService.delete(id));
	}
}
