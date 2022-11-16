package com.example.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@Table(name = "Users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    //Had to change to fetchtype eager cuz bad design
    //Almost no loss in performance because there are 3 roles by default
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "RoleId", nullable = false)
    @ToString.Exclude
    private Role role;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TeamId", nullable = false)
    @ToString.Exclude
    private Team team;
    @Column(name = "isLeadDev")
    private boolean leadDev;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Leave> studentList;

    public User(String username, String password, String firstName, String lastName, Role role, Team team, boolean leadDev) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.team = team;
        this.leadDev = leadDev;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
