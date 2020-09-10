package com.mola.OnlineCatalogProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int attendanceId;

    private String attendance;
//    private Date attendanceDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private  Student student;

}
