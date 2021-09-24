package com.javaMentor.SpringBoot.Security.controller;

import com.javaMentor.SpringBoot.Security.model.Role;
import com.javaMentor.SpringBoot.Security.model.User;
import com.javaMentor.SpringBoot.Security.service.RoleService;
import com.javaMentor.SpringBoot.Security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class MainController {

    private final RoleService roleService;

    private final UserService userService;

    public MainController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @GetMapping("/admin")
    public String getMain(Model model){
        model.addAttribute("users", userService.showAllUsers());
        return "mainPage";
    }

    @GetMapping("/user")
    public String getUserPage(Model model){
        model.addAttribute("user",  userService.findUserByName(userService.getCurrentUsername()));
        return "user";
    }

    @GetMapping("/admin/{id}")
    public String showUser(@PathVariable("id") Integer id, Model model){
        model.addAttribute("user", userService.showUser(id));
        return "showUser";
    }

    @GetMapping("/admin/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.allRoles());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user, @RequestParam(value = "roles", required = false) String[] roles){
        userService.createUser(user, roles);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/edit")
    public String updateUser(Model model, @PathVariable("id") Integer id){
        model.addAttribute("user", userService.showUser(id));
        model.addAttribute("allRoles", roleService.allRoles());
        return "editUser";
    }

    @PatchMapping("/admin/{id}")
    public String update(@ModelAttribute("user") User user, @RequestParam(value = "roles", required = false) String[] roles){
        userService.updateUser(user, roles);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") Integer id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
