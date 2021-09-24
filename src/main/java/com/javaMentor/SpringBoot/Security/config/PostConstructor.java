package com.javaMentor.SpringBoot.Security.config;

import com.javaMentor.SpringBoot.Security.model.Role;
import com.javaMentor.SpringBoot.Security.model.User;
import com.javaMentor.SpringBoot.Security.service.UserService;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class PostConstructor {

    private final UserService userService;

    public PostConstructor(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void createUsers(){
        Set<Role> roleAdmin= new HashSet<>();
        roleAdmin.add(new Role("ROLE_ADMIN"));
        Set<Role> roleUser = new HashSet<>();
        roleUser.add(new Role("ROLE_USER"));

        userService.createUserOnStartup(new User("ADMIN", "0", roleAdmin));
        userService.createUserOnStartup(new User("USER","1", roleUser));
    }
}
