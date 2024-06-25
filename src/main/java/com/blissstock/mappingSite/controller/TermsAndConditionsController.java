package com.blissstock.mappingSite.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TermsAndConditionsController {
    
    private static final Logger logger = LoggerFactory.getLogger(TermsAndConditionsController.class);

    @GetMapping("/guest/terms_and_conditions")
    private String TermsConditions(){

        logger.info("GET request");

        return "Terms and Conditions";
    }
}
