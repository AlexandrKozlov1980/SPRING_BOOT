package com.javaMentor.SpringBoot.Security.service;

import com.javaMentor.SpringBoot.Security.model.Role;
import com.javaMentor.SpringBoot.Security.model.User;
import com.javaMentor.SpringBoot.Security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public List<User> showAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User showUser(Integer id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    @Override
    public void createUserOnStartup(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void createUser(User user, String[] roles) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> rolesSet = new HashSet<>();
        for (String s: roles){
            rolesSet.add(roleService.getRoleByName(s));
        }

        user.setRole(rolesSet);
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user, String[] roles) {
        User updatedUser = userRepository.findById(user.getId()).get();
        Set<Role> rolesSet = new HashSet<>();

        for (String s: roles){
            rolesSet.add(roleService.getRoleByName(s));
        }

        updatedUser.setName(user.getName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setAge(user.getAge());
        updatedUser.setEMail(user.getEMail());

        if (user.getPassword() != ""){
            updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        updatedUser.setRole(rolesSet);
        userRepository.save(updatedUser);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
