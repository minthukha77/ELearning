package com.blissstock.mappingSite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class newTestController {
    @GetMapping("/newTest")
    public String completebox(){
        return "newTest";
    }

    
}
