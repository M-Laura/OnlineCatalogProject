package com.mola.OnlineCatalogProject.controller;

import com.mola.OnlineCatalogProject.model.SchoolUnit;
import com.mola.OnlineCatalogProject.service.SchoolGroupService;
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
public class SchoolUnitController {

    @Autowired
    private SchoolUnitService schoolUnitService;

    @Autowired
    private SchoolGroupService schoolGroupService;

    @Autowired
    private StudentService studentService;

    @GetMapping("allschoolunits")
    public String showAllGroups(Model model) {
        List<SchoolUnit> schoolUnits = schoolUnitService.findAll();
        model.addAttribute( "schoolunits", schoolUnits );
        return "schoolunit/showallschoolunits";
    }

    @GetMapping("/addschoolunit")
    public String addSchoolUnit(Model model) {
        model.addAttribute( "schoolunit", new SchoolUnit() ); // initial bind with the form, to say to the webpage
        return "schoolunit/addschoolunit";
    }

    @PostMapping("/addschoolunit")
    public String addSchoolUnit(@ModelAttribute SchoolUnit schoolUnit) {
        schoolUnitService.save( schoolUnit );
        return "redirect:/allschoolunits";

    }

    @GetMapping("/viewschoolunit/{id}")
    public String showSchoolUnit(Model model, @PathVariable Integer id) {
        SchoolUnit schoolUnit = schoolUnitService.findById( id );
        model.addAttribute( "schoolunit", schoolUnit );
        model.addAttribute( "schoolgroups",
                schoolGroupService.findById( id ) );
        return "schoolunit/viewschoolunit";
    }


    @GetMapping("/editschoolunit/{id}")
    public String editSchoolUnit(Model model, @PathVariable Integer id) {
        SchoolUnit schoolUnit = schoolUnitService.findById(id);
        model.addAttribute("schoolunit", schoolUnit);
        return "schoolunit/editschoolunit";
    }

    @PostMapping("/editschoolunit/{id}")
    public String editSchoolUnit(@ModelAttribute SchoolUnit schoolUnit, @PathVariable Integer id) {
        schoolUnitService.save(schoolUnit);
        return "redirect:/allschoolunits";
    }

    @GetMapping("/deleteschoolunit/{id}")
    public String deleteSchoolUnit(@PathVariable Integer id) {
        schoolUnitService.deleteById(id);
        return "redirect:/allschoolunits"; // forward
    }


}

