package com.mola.OnlineCatalogProject.repository;

import com.mola.OnlineCatalogProject.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor,Integer> {
}
