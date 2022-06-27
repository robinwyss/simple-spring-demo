package com.robinwyss.rapdemo.controller;

import com.robinwyss.rapdemo.service.LookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LookupController {

    public LookupService lookupService;

    @Autowired
    public LookupController(LookupService lookupService) {
        this.lookupService = lookupService;
    }

    @GetMapping("/domain")
    public String lookup(Model model, @RequestParam(required = false) String domain){
        if(domain != null){
            String result = lookupService.lookupDomainName(domain);
            model.addAttribute("result", result);
        }
        return "domain-lookup";
    }
}
