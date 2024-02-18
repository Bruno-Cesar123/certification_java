package com.brunocesar.certification_nlw.models.questions.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brunocesar.certification_nlw.models.questions.dto.AlternativesResultDTO;
import com.brunocesar.certification_nlw.models.questions.dto.QuestionResultDTO;
import com.brunocesar.certification_nlw.models.questions.entities.AlternativesEntity;
import com.brunocesar.certification_nlw.models.questions.entities.QuestionEntity;
import com.brunocesar.certification_nlw.models.questions.repositories.QuestionRepository;

@RestController
@RequestMapping("/questions")
public class QuestionController {

  @Autowired
  private QuestionRepository questionRepository;
  
  @GetMapping("/technology/{technology}")
  public List<QuestionResultDTO> findByTechnology(@PathVariable String technology) {
    var result = this.questionRepository.findByTechnology(technology);

    var toMap = result.stream().map(question -> mapQuestionToDTO(question))
    .collect(Collectors.toList());
    return toMap;
  }

  static QuestionResultDTO mapQuestionToDTO(QuestionEntity question) {
    var questionResultDTO = QuestionResultDTO.builder()
    .id(question.getId())
    .technology(question.getTechnology())
    .description(question.getDescription()).build();
    
    List<AlternativesResultDTO> alternativesResultDTOs =
    question.getAlternatives()
    .stream().map(alternative -> mapAlternativeDTO(alternative))
    .collect(Collectors.toList());

    questionResultDTO.setAlternatives(alternativesResultDTOs);
    return questionResultDTO;
  }

  static AlternativesResultDTO mapAlternativeDTO(AlternativesEntity alternativesResultDTO) {

    return AlternativesResultDTO.builder()
    .id(alternativesResultDTO.getId())
    .description(alternativesResultDTO.getDescription()).build();

  }
}
