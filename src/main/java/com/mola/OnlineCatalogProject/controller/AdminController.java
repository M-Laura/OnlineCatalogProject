package com.mola.OnlineCatalogProject.controller;

import com.mola.OnlineCatalogProject.model.User;
import com.mola.OnlineCatalogProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin")
    public String showAllStudents(Model model) {

        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "showallusers";
    }
}