package com.mola.OnlineCatalogProject.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // start from 1  on each table
    private Integer userId;

    private String username; //nickname
    private String emailAddress;
    private String password;
    private boolean enabled;
    private boolean tokenExpired;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Role role;

    @OneToOne(mappedBy = "user")
    private PendingUser pendingUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private List<Role> roles;
}
