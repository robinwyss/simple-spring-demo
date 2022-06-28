package com.robinwyss.rapdemo.controller;

import com.robinwyss.rapdemo.dao.TldDao;
import com.robinwyss.rapdemo.service.LookupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LookupController {

    private static final Logger logger = LoggerFactory.getLogger(LookupController.class);

    public LookupService lookupService;

    @Autowired
    public LookupController(LookupService lookupService) {
        this.lookupService = lookupService;
    }

    @GetMapping("/domain")
    public String lookup(Model model, @RequestParam(required = false) String domain){
        if(domain != null){
            logger.info("Lookup for domain {}", domain);
            String result = lookupService.lookupDomainName(domain);
            model.addAttribute("result", result);
        }
        return "domain-lookup";
    }
}
