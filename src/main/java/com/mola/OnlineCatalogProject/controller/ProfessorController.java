package com.mola.OnlineCatalogProject.controller;

import com.mola.OnlineCatalogProject.model.Professor;
import com.mola.OnlineCatalogProject.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping("allprofessors")
    public String showAllProfessors(Model model) {

        List<Professor> professors = professorService.findAll();
        model.addAttribute("professors", professors);

        return "professor/showallprofessors";
    }

    @GetMapping("/addprofessor")
    public String addProfessor(Model model) {
        model.addAttribute("professor", new Professor()); // initial bind with the form, to say to the webpage
        // what is the type of student th:object

        return "professor/addprofessor";
    }

    @PostMapping("/addprofessor")
    public String addProfessor(@ModelAttribute Professor professor) {
//        System.out.println(student);
        professorService.save(professor);
        return "redirect:/allprofessors";
        //TODO: show in same page on the left all students, on the right add a new student
    }

    @GetMapping("/editprofessor/{id}")
    public String editProfessor(Model model, @PathVariable Integer id) {
        Professor professor = professorService.findById(id);

        model.addAttribute("professor", professor); // initial bind with the form, to say to the webpage
        // what is the type of student th:object

        return "professor/editprofessor";
    }

    @PostMapping("/editprofessor/{id}")
    public String editProfessor(@ModelAttribute Professor professor, @PathVariable Integer id) {

        professorService.save(professor); // save it again. SAVE acts as UPDATE
//        return "redirect:/editstudent/"+id;
        return "redirect:/allprofessors";
        //TODO: show in same page on the left all students, on the right add a new student
    }

    @GetMapping("/deleteprofessor/{id}")
    public String deleteProfessor(@PathVariable Integer id) {
        professorService.deleteById(id);

        return "redirect:/allprofessors"; // forward
    }
}