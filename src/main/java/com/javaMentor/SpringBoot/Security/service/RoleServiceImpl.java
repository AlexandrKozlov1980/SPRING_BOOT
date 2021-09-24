package com.javaMentor.SpringBoot.Security.service;

import com.javaMentor.SpringBoot.Security.model.Role;
import com.javaMentor.SpringBoot.Security.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name);
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public List<Role> allRoles() {
        return roleRepository.findAll();
    }
}
