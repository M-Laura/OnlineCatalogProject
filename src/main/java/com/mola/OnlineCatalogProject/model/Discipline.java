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
public class Discipline {

    @Id
    private String disciplineName;

    @OneToMany(
            mappedBy = "discipline",
            cascade = CascadeType.ALL)
    private List<Professor> professors;

    @ManyToMany(mappedBy = "disciplines")
    private List<Student> students;

    @ManyToMany(
            mappedBy = "disciplines",
            fetch = FetchType.LAZY)
    private List<SchoolGroup> schoolGroups;

    @ManyToMany
    @JoinTable(name = "discipline_professors", joinColumns = @JoinColumn(name = "discipline_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id"))
    private List<Professor> professorList;

    @OneToMany(
            mappedBy = "discipline",
            cascade = CascadeType.ALL)
    private List<Grade> grades;
}