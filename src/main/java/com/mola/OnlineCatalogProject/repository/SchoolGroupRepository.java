package com.mola.OnlineCatalogProject.repository;

import com.mola.OnlineCatalogProject.model.SchoolGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SchoolGroupRepository extends JpaRepository<SchoolGroup,Integer> {
    @Query("FROM SchoolGroup s WHERE s.schoolUnit.unitId= :unitId")
    List<SchoolGroup> findAllByUnitId(@Param("unitId")Integer unitId);
}
