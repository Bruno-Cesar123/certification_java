package com.brunocesar.certification_nlw.models.questions.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunocesar.certification_nlw.models.questions.entities.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, UUID> {
  
  List<QuestionEntity> findByTechnology(String technology);
}
