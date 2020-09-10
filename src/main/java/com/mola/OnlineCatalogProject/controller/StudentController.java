package com.mola.OnlineCatalogProject.controller;

import com.mola.OnlineCatalogProject.model.Student;

import com.mola.OnlineCatalogProject.service.SchoolGroupService;
import com.mola.OnlineCatalogProject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SchoolGroupService schoolGroupService;

    @GetMapping("allstudents")
    public String showAllStudents(Model model) {

        List<Student> studentList = studentService.findAll();
        model.addAttribute("students", studentList);

        return "student/showallstudents";
    }

    @GetMapping("/addstudent")
    public String addStudent(Model model) {
        model.addAttribute("schoolgroups", schoolGroupService.findAll());
        model.addAttribute("student", new Student()); // initial bind with the form, to say to the webpage
        // what is the type of student th:object

        return "student/addstudent";
    }

    @PostMapping("/addstudent")
    public String addStudent(@ModelAttribute Student student) {
//        System.out.println(student);
        studentService.save(student);
        return "redirect:/allstudents";
        //TODO: show in same page on the left all students, on the right add a new student
    }

    @GetMapping("/editstudent/{id}")
    public String editStudent(Model model, @PathVariable Integer id) {
        Student student = studentService.findById(id);

        model.addAttribute("student", student); // initial bind with the form, to say to the webpage
        model.addAttribute("schoolgroups", schoolGroupService.findAll());
        // what is the type of student th:object

        return "student/editstudent";
    }

    @PostMapping("/editstudent/{id}")
    public String editStudent(@ModelAttribute Student student, @PathVariable Integer id) {
        System.out.println(student);

        studentService.save(student); // save it again. SAVE acts as UPDATE
//        return "redirect:/editstudent/"+id;
        return "redirect:/allstudents";
        //TODO: show in same page on the left all students, on the right add a new student
    }

    @GetMapping("/deletestudent/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        studentService.deleteById(id);

        return "redirect:/allstudents"; // forward
    }
}