package com.javaMentor.SpringBoot.Security.repositories;

import com.javaMentor.SpringBoot.Security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("select r from Role r where r.role = :role")
    Role getRoleByName(String role);
}
