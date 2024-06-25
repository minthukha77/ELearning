package com.blissstock.mappingSite.controller;

import com.blissstock.mappingSite.repository.LeaveInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AbsentListController {

    @Autowired
    private LeaveInfoRepository leaveInfoRepo;
    
    @GetMapping("/admin/absent-list")
    private String absentList(Model model){

        model.addAttribute("absentList", leaveInfoRepo.findAll());
        
        return "AD0004_AbsentList";
    }


}
