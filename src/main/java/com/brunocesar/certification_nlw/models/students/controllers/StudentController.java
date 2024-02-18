package com.brunocesar.certification_nlw.models.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brunocesar.certification_nlw.models.students.dto.StudentCertificationAnswerDTO;
import com.brunocesar.certification_nlw.models.students.dto.VerifyHasCertificationDTO;
import com.brunocesar.certification_nlw.models.students.entities.CertificationStudentEntity;
import com.brunocesar.certification_nlw.models.students.useCases.StudentCertificationAnswersUseCase;
import com.brunocesar.certification_nlw.models.students.useCases.VerifyIfHasCertificationUseCase;

@RestController
@RequestMapping("/students")
public class StudentController {

  @Autowired
  private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

  @Autowired
  private StudentCertificationAnswersUseCase studentCertificationAnswersUseCase;
  
  @PostMapping("/verifyIfHasCertification")
  public String verifyIfHasCertification(@RequestBody VerifyHasCertificationDTO verifyHasCertificationDTO) {

    var result = this.verifyIfHasCertificationUseCase.execute(verifyHasCertificationDTO);
    if (result) {
      return "Usuario ja fez a prova";
    }

    return "Usuario pode fazer a prova";
  }

  @PostMapping("/certification/answer")
  public ResponseEntity<Object>  certificationAnswer(
    @RequestBody StudentCertificationAnswerDTO studentCertificationAnswerDTO) throws Exception {

      try {
        var result = studentCertificationAnswersUseCase.execute(studentCertificationAnswerDTO);
        return ResponseEntity.ok().body(result);

      } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
      }
    
  }

}
