package com.javaMentor.SpringBoot.Security.service;

import com.javaMentor.SpringBoot.Security.model.Role;

import java.util.List;

public interface RoleService {
    Role getRoleByName(String name);

    Role getRoleById(Integer id);

    List<Role> allRoles();
}
