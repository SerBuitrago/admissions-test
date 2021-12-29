package com.admissions.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admissions.entity.Option;
import com.admissions.entity.validate.OptionValidate;
import com.admissions.repository.CategoryRepository;
import com.admissions.repository.OptionRepository;
import com.admissions.service.OptionService;
import com.admissions.util.Admissions;
import com.admissions.util.exception.AdmissionsException;

@Service
public class OptionServiceImpl implements OptionService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OptionServiceImpl.class);
	
	@Autowired
	OptionRepository optionRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Option findById(Long id) {
		if (!Admissions.isLong(id))
			throw new AdmissionsException("El id de la opcion no es valida.");
		Optional<Option> optional = optionRepository.findById(id);
		if (!optional.isPresent())
			throw new AdmissionsException("No se ha encontrado ninguna opcion con el id " + id + ".");
		return optional.get();
	}

	@Override
	public Option findByIsCorrectQuestion(Long idQuestion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Option> findAll() {
		return optionRepository.findAll();
	}

	@Override
	public List<Option> findByQuestionAll(Long idQuestion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Option save(Option option, int type) {
		OptionValidate.validate(option);
		String message = Admissions.message(type, false);
		//option = !Admissions.isLong(option.getId()) ? saveToSave(option, type) : saveToUpdate(option, type);
		option = optionRepository.save(option);
		if (option == null)
			throw new AdmissionsException("No se ha podido " + message + " la opcion.");
		return option;
	}

	@Override
	public boolean delete(Long id) {
		findById(id);
		optionRepository.deleteById(id);
		try {
			findById(id);
		} catch (AdmissionsException e) {
			LOGGER.error("delete(Long id)", e);
			return true;
		}
		throw new AdmissionsException("No se ha eliminado la opcion con el id " + id + ".");
	}
}
