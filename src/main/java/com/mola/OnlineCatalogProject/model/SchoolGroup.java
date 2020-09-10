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
public class SchoolGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int groupId;

    private String groupName;
    private Date groupYear;

    @ManyToOne(fetch = FetchType.LAZY)
    private SchoolUnit schoolUnit;

    @OneToMany(
            mappedBy = "schoolGroup",
            cascade = CascadeType.ALL
    )
    private List<Student> students;

    @ManyToMany
    @JoinTable(name = "group_professors", joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id"))
    private List<Professor> professors;

    @ManyToMany
    @JoinTable(name = "group_discipline", joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "discipline_name"))
    private List<Discipline> disciplines;

}