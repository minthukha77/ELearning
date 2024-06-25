package com.blissstock.mappingSite.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutManagementCompanyController {

    private static final Logger logger = LoggerFactory.getLogger(AboutManagementCompanyController.class);

    @GetMapping("/guest/about_management_company")
    private String AboutManagement() {

        logger.info("GET reques t");

        return "About Management Company";
    }
}
