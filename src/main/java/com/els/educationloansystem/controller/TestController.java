package com.els.educationloansystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TestController {

    @GetMapping("/run")
    public String test() {
        return "Controller working";
    }
}
