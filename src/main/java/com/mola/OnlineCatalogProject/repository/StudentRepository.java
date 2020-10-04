package com.mola.OnlineCatalogProject.repository;

import com.mola.OnlineCatalogProject.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query("FROM Student s WHERE s.schoolGroup.groupId= :groupId")
    List<Student> findAllByGroupId(@Param("groupId")Integer groupId);

}
