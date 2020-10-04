package com.mola.OnlineCatalogProject.controller;

import com.mola.OnlineCatalogProject.model.Grade;
import com.mola.OnlineCatalogProject.service.GradeService;
import com.mola.OnlineCatalogProject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @Autowired
    private StudentService studentService;

    @GetMapping("allgrades")
    public String showAllGrades(Model model) {
        List<Grade> gradeList = gradeService.findAll();
        model.addAttribute("grade", gradeList);
        return "grade/viewallgrades";
    }
}