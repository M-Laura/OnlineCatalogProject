package com.mola.OnlineCatalogProject.controller;

import com.mola.OnlineCatalogProject.service.SchoolGroupService;
import com.mola.OnlineCatalogProject.model.SchoolGroup;
import com.mola.OnlineCatalogProject.service.SchoolUnitService;
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
public class SchoolGroupController {

    @Autowired
    private SchoolGroupService schoolGroupService;

    @Autowired
    private SchoolUnitService schoolUnitService;

    @Autowired
    private StudentService studentService;

    @GetMapping("allschoolgroups")
    public String showAllGroups(Model model) {
        List<SchoolGroup> schoolGroups = schoolGroupService.findAll();
        model.addAttribute( "schoolgroups", schoolGroups );
        return "schoolgroup/showallschoolgroups";
    }

    @GetMapping("/{id}/addschoolgroup")
    public String addSchoolGroup(Model model, @PathVariable Integer id) {
        SchoolGroup schoolGroup = new SchoolGroup();
        schoolGroup.setSchoolUnit( schoolUnitService.findById( id ) );
        model.addAttribute( "schoolgroup", schoolGroup ); // initial bind with the form, to say to the webpage
        return "schoolgroup/addschoolgroup";
    }

    @PostMapping("/{id}/addschoolgroup")
    public String addSchoolGroup(@ModelAttribute SchoolGroup schoolGroup, @PathVariable Integer id) {
        schoolGroup.setSchoolUnit( schoolUnitService.findById( id ) );
        schoolGroupService.save( schoolGroup );
        return "redirect:/viewschoolunit/" + id;
    }

    @GetMapping("/group/{id}/students")
    public String viewStudentsInGroup(Model model, @PathVariable Integer id) {
        model.addAttribute( "schoolgroup",
                schoolGroupService.findById( id ) );
        model.addAttribute( "students",//cum se regaseste in html atribut
                studentService.findById( id ) );
        return "schoolgroup/viewstudents";
    }

    @GetMapping("/editschoolgroup/{id}")
    public String editSchoolGroup(Model model, @PathVariable Integer id) {
        SchoolGroup schoolGroup = schoolGroupService.findById( id );
        model.addAttribute( "schoolgroup", schoolGroup );
        return "schoolgroup/editschoolgroup";
    }

    @PostMapping("/editschoolgroup/{id}")
    public String editSchoolGroup(@ModelAttribute SchoolGroup schoolGroup, @PathVariable Integer id) {
        SchoolGroup databaseSchoolGroup = schoolGroupService.findById( id );
        databaseSchoolGroup.setGroupName( schoolGroup.getGroupName());
        schoolGroupService.save( databaseSchoolGroup );
        return "redirect:/viewschoolunit/" + databaseSchoolGroup.getSchoolUnit().getUnitId();
    }

    @GetMapping("/deleteschoolgroup/{id}")
    public String deleteSchoolGroup(@PathVariable Integer id) {
        int unitId = schoolGroupService.findById( id ).getSchoolUnit().getUnitId();
        schoolGroupService.deleteById( id );
        return "redirect:/viewschoolunit/" + unitId; // forward
    }
}