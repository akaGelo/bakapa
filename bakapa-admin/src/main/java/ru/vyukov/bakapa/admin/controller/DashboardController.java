package ru.vyukov.bakapa.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @RequestMapping("/")
    public String dashboard(){
        return "dashboard";
    }
}
