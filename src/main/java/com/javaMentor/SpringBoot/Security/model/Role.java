package com.javaMentor.SpringBoot.Security.model;

import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role")
    private String role;

    @ManyToMany
    @JoinTable(name="user_role",
            joinColumns=@JoinColumn(name="role_id"),
            inverseJoinColumns=@JoinColumn(name="user_id"))

    private List<User> users;

    public Role(){

    }

    public Role(String role) {

        this.role = role;
    }

    public Role(String role, List<User> users) {
        this.role = role;
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
