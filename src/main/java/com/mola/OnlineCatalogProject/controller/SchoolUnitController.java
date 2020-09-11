package com.mola.OnlineCatalogProject.controller;

import com.mola.OnlineCatalogProject.model.SchoolUnit;
import com.mola.OnlineCatalogProject.service.SchoolUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SchoolUnitController {

    @Autowired
    private SchoolUnitService schoolUnitService;

    @GetMapping("allschoolunits")
    public String showAllGroups(Model model) {
        List<SchoolUnit> schoolUnits = schoolUnitService.findAll();
        model.addAttribute("schoolunits", schoolUnits);
        return "schoolunit/showallschoolunits";
    }

}

