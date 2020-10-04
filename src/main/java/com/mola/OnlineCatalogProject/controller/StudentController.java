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

    @GetMapping("/{id}/addstudent")//ruta care trebuie sa se regasesca in html
    public String addStudent(Model model, @PathVariable Integer id) {
        Student student = new Student();
        model.addAttribute( "schoolgroup",
                schoolGroupService.findById( id ));
        student.setSchoolGroup( schoolGroupService.findById( id ) );
        model.addAttribute( "student", student );
        return "student/addstudent";
    }

    @PostMapping("/{id}/addstudent")
    public String addStudent(@ModelAttribute Student student, @PathVariable Integer id) {
        student.setSchoolGroup( schoolGroupService.findById( id ) );
        studentService.save( student);
        return "redirect:/group/"+id+"/students";
    }

    @GetMapping("/editstudent/{id}")
    public String editStudent(Model model, @PathVariable Integer id) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student); // initial bind with the form, to say to the webpage
        return "student/editstudent";
    }

    @PostMapping("/editstudent/{id}")
    public String editStudent(@ModelAttribute Student student, @PathVariable Integer id) {
        Student databaseStudent = studentService.findById( id );
        databaseStudent.setFirstName( student.getFirstName() );
        databaseStudent.setLastName( student.getLastName() );
        studentService.save(databaseStudent);
        return "redirect:/group/"+databaseStudent.getSchoolGroup().getGroupId()+"/students";

    }

    @GetMapping("/deletestudent/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        int groupId= studentService.findById( id ).getSchoolGroup().getGroupId();
        studentService.deleteById(id);
        return "redirect:/group/"+groupId+"/students";
    }
}