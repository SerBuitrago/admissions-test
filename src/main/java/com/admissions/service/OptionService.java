package com.admissions.service;

import java.util.List;

import com.admissions.entity.Option;

public interface OptionService {

	Option findById(Long id);
	
	Option findByIsCorrectQuestion(Long idQuestion);
	
	List<Option> findAll();
	
	List<Option> findByQuestionAll(Long idQuestion);
	
	Option save(Option option, int type);
	
	boolean delete(Long id);
}
