package com.mola.OnlineCatalogProject.repository;

import com.mola.OnlineCatalogProject.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {

}
