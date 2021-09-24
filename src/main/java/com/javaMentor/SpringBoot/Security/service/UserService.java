package com.javaMentor.SpringBoot.Security.service;

import com.javaMentor.SpringBoot.Security.model.User;
import java.util.List;

public interface UserService {
    List<User> showAllUsers();

    User showUser(Integer id);

    void createUser(User user, String[] roles);

    void updateUser(User user, String[] roles);

    void deleteUser(Integer id);

    User findUserByName(String name);

    String getCurrentUsername();

    public void createUserOnStartup(User user);
}
