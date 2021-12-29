package com.admissions.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admissions.entity.Question;
import com.admissions.entity.validate.QuestionValidate;
import com.admissions.repository.QuestionRepository;
import com.admissions.service.CategoryService;
import com.admissions.service.QuestionService;
import com.admissions.service.TestService;
import com.admissions.util.Admissions;
import com.admissions.util.exception.AdmissionsException;

@Service
public class QuestionServiceImpl implements QuestionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(QuestionServiceImpl.class);

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	CategoryService categoryService;

	@Autowired
	TestService testService;

	@Override
	public Question findById(Long id) {
		if (!Admissions.isLong(id))
			throw new AdmissionsException("El id de la pregunta no es valido.");
		Optional<Question> optional = questionRepository.findById(id);
		if (!optional.isPresent())
			throw new AdmissionsException("No se ha encontrado ninguna pregunta con el id " + id + ".");
		return optional.get();
	}

	@Override
	public List<Question> findAll() {
		return questionRepository.findAll();
	}

	@Override
	public List<Question> findByTestAll(Long test) {
		if (!Admissions.isLong(test))
			throw new AdmissionsException("El id de la prueba no es valido.");
		return questionRepository.findByIdTest(test);
	}

	@Override
	public List<Question> findByCategoryAll(Long category) {
		if (!Admissions.isLong(category))
			throw new AdmissionsException("El id de la categoria no es valido.");
		return questionRepository.findByCategoryAll(category);
	}

	@Override
	public Question save(Question question, int type) {
		QuestionValidate.validate(question);
		String message = Admissions.message(type, false);
		question = !Admissions.isLong(question.getId()) ? saveToSave(question, type) : saveToUpdate(question, type);
		question = questionRepository.save(question);
		if (question == null)
			throw new AdmissionsException("No se ha podido " + message + " la pregunta.");
		return question;
	}

	@Override
	public boolean delete(Long id) {
		findById(id);
		questionRepository.deleteById(id);
		try {
			findById(id);
		} catch (AdmissionsException e) {
			LOGGER.error("delete(Long id)", e);
			return true;
		}
		throw new AdmissionsException("No se ha eliminado la pregunta con el id " + id + ".");
	}

	private Question saveToSave(Question question, int type) {
		question.setId(0L);
		question.setCategory(categoryService.findById(question.getCategory().getId()));
		testService.findById(question.getIdTest());
		return question;
	}

	private Question saveToUpdate(Question question, int type) {
		Question aux = null;
		if (type != 3) {
			aux = findById(question.getId());
			if (question.getCategory().getId() != aux.getCategory().getId())
				question.setCategory(categoryService.findById(question.getCategory().getId()));
			if (question.getIdTest() != aux.getIdTest())
				testService.findById(question.getIdTest());
		}
		return question;
	}
}
