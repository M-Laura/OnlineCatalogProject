package com.mola.OnlineCatalogProject.repository;

import com.mola.OnlineCatalogProject.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, String> {
    @Query("FROM Attendance s WHERE s.attendance.attendanceId= :attendanceId")
    List<Attendance> findAllByStudentId(@Param("attendanceId")Integer studentId);
}

