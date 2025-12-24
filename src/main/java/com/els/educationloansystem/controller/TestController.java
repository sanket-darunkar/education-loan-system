package com.els.educationloansystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/run")
    public String test() {
        return "Controller working";
    }
}
