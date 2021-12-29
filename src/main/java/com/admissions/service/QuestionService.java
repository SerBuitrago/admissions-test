package com.admissions.service;

import java.util.List;

import com.admissions.entity.Question;

public interface QuestionService {
	
	Question findById(Long id);
	
	List<Question> findAll();
	
	List<Question> findByTestAll(Long test);
	
	List<Question> findByCategoryAll(Long category);
	
	Question save(Question question, int type);
	
	boolean delete(Long id);
}
