package com.mola.OnlineCatalogProject.repository;

import com.mola.OnlineCatalogProject.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepository extends JpaRepository<Discipline,String> {
}
