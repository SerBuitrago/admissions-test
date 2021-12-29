package com.admissions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admissions.entity.UserAnswer;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long>{

}
