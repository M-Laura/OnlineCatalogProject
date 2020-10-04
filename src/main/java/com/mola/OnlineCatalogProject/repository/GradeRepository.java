package com.mola.OnlineCatalogProject.repository;

import com.mola.OnlineCatalogProject.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade,Integer> {
    @Query("FROM Grade s WHERE s.grade.gradeId= :gradeId")
    List<Grade> findGradesForStudentId(@Param("gradeId")Integer studentId);
}
