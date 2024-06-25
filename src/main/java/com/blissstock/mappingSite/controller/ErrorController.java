package com.blissstock.mappingSite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
    @GetMapping("/404")
    public String notFound(Model model) {
        return "error/404";
    }
    
    @GetMapping("/500")
    public String internal(Model model) {
        return "error/500";
    }

    @GetMapping("/403")
    public String accessForbidden(Model model) {
        return "error/403";
    }
}