package com.brunocesar.certification_nlw.models.certifications.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunocesar.certification_nlw.models.students.entities.CertificationStudentEntity;
import com.brunocesar.certification_nlw.models.students.repositories.CertificationStudentRepository;

@Service
public class Top10RankingUseCase {

  @Autowired
  private CertificationStudentRepository certificationStudentRepository;
  
  public List<CertificationStudentEntity> execute() {
    var result = this.certificationStudentRepository.findTop10ByOrderByGradeDesc();
    return result;
  }
}
