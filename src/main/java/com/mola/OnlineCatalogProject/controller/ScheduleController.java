package com.mola.OnlineCatalogProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScheduleController {
    @GetMapping("schedule")
    public String showSchedule() {
        return "schedule/showschedule";
    }
}
