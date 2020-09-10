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
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String firstName;
    private String lastName;

    @ManyToMany(
            mappedBy = "professors" ,
            fetch = FetchType.LAZY)
    private List<SchoolUnit> schoolUnits;

    @ManyToMany(
            mappedBy = "professors",
            fetch = FetchType.LAZY)
    private List<SchoolGroup> schoolGroups;

    @ManyToOne(fetch = FetchType.LAZY)
    private Discipline discipline;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Discipline> disciplines;


}
