package com.mola.OnlineCatalogProject.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    private String roleName;
    private String role = "ROLE_STUDENT";
    private String role2 = "ROLE_PROFESSOR";
    private String role3 = "ROLE_ADMIN";
   

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<User> users;


}
