package com.iozturk.service_monitor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MonitorController {

    private final MonitorService monitorService;

    public MonitorController(MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("results", monitorService.getResults());
        return "dashboard";
    }
}