package com.blissstock.mappingSite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CompleteTest {
    @RequestMapping("/test")
    public String test()
    {
        return "CompleteTest";
    }
}
