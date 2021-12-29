package com.admissions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admissions.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{

}
