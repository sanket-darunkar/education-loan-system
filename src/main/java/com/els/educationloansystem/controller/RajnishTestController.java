package com.els.educationloansystem.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rajnish")
public class RajnishTestController {
    
    @PostMapping("/tester")
    public String testing(){
            return "Testing Done";
    }


}
