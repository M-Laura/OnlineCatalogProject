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
public class SchoolUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int unitId;

    private String unitName;

    @ManyToMany
    @JoinTable(name = "unit_professors", joinColumns = @JoinColumn(name = "unit_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id"))
    private List<Professor> professors;

    @OneToMany(
            mappedBy = "schoolUnit",
            cascade = CascadeType.ALL
    )
    private List<SchoolGroup> schoolGroups;
}
