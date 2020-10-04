package com.mola.OnlineCatalogProject.service;

import com.mola.OnlineCatalogProject.model.Grade;
import com.mola.OnlineCatalogProject.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("IGradeService")
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public List<Grade> findAll() {
        return gradeRepository.findAll();
    }

    public void save(Grade grade) {
        gradeRepository.save(grade);
    }

    public Grade findById(Integer id) {
        Optional<Grade> grade = gradeRepository.findById(id);
        if (grade.isPresent()) {
            return grade.get();
        }

        return null;
    }

    public void deleteById(Integer id) {
        gradeRepository.deleteById(id);
    }

    public List<Grade> findByGradeId(Integer gradeId){return gradeRepository.findGradesForStudentId( gradeId );}
}
