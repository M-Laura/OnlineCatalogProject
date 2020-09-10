package com.mola.OnlineCatalogProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int studentId;

    private String firstName;
    private String lastName;


    //    @ManyToMany
//    @JoinTable(name = "student_grades", joinColumns = @JoinColumn(name = "student_id"),
//            inverseJoinColumns = @JoinColumn(name = "grade_id"))
//    private List<Grade> grades;
    @ManyToOne(fetch = FetchType.LAZY)
    private SchoolGroup schoolGroup;

    @ManyToMany
    @JoinTable(name = "student_discipline", joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "discipline_id"))
    private List<Discipline> disciplines;

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL
    )
    private List<Attendance> attendances;

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL
    )
    private List<Grade> grades;

}
