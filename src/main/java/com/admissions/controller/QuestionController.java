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

import com.admissions.entity.Question;
import com.admissions.service.QuestionService;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

	@Autowired
	QuestionService questionService;
	
	@GetMapping(value = { "/find/id/{id}", "/{id}" })
	public ResponseEntity<Question> findById(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(questionService.findById(id));
	}

	@GetMapping(value = {"", "/all"})
	public ResponseEntity<List<Question>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(questionService.findAll());
	}
	
	@GetMapping(value = {"/all/find/test/{idTest}"})
	public ResponseEntity<List<Question>> findByTestAll(@PathVariable("idTest") Long idTest) {
		return ResponseEntity.status(HttpStatus.OK).body(questionService.findByTestAll(idTest));
	}
	
	@GetMapping(value = {"/all/find/category/{idCategory}"})
	public ResponseEntity<List<Question>> findByCategoryAll(@PathVariable("idCategory") Long idCategory) {
		return ResponseEntity.status(HttpStatus.OK).body(questionService.findByCategoryAll(idCategory));
	}

	@PostMapping
	public ResponseEntity<Question> save(@RequestBody Question question) {
		return ResponseEntity.status(HttpStatus.OK).body(questionService.save(question, 1));
	}

	@PutMapping
	public ResponseEntity<Question> update(@RequestBody Question question) {
		return ResponseEntity.status(HttpStatus.OK).body(questionService.save(question, 2));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(questionService.delete(id));
	}
}
