package com.mola.OnlineCatalogProject.repository;

import com.mola.OnlineCatalogProject.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade,Integer> {
}
