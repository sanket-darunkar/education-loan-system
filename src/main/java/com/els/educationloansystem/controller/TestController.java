package com.els.educationloansystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class TestController {

    @GetMapping("/run")
    public String test() {
        return "Controller working";
    }
    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    public void check()
    {
    	
    	
    }
}
