package com.mola.OnlineCatalogProject.repository;

import com.mola.OnlineCatalogProject.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, String> {
}

