package com.robinwyss.rapdemo.controller;

import com.robinwyss.rapdemo.dao.TldDao;
import com.robinwyss.rapdemo.model.TldInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class TldController {

    private TldDao tldDao;

    @Autowired
    public TldController(TldDao tldDao) {
        this.tldDao = tldDao;
    }

    @GetMapping("/tld")
    public String getCountry(Model model, @RequestParam(required = false) String tld){
        if(tld != null){
            List<TldInfo> results = tldDao.findCountryByTld(tld);
            if(results.isEmpty()){
                model.addAttribute("status", "No match found!");
            }
            model.addAttribute("results", results);
        } else {
            model.addAttribute("results", Collections.emptyList());
        }
        return "tld-lookup";
    }
}
