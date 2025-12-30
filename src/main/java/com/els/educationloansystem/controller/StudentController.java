package com.els.educationloansystem.controller;

import com.els.educationloansystem.dto.StudentLoginDTO;
import com.els.educationloansystem.dto.StudentRegisterDTO;
import com.els.educationloansystem.entity.Student;
import com.els.educationloansystem.service.StudentService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /* ===============================
       Registration
       =============================== */

    @GetMapping("/register")
    public String showRegisterPage() {
        return "Register";
    }

    @PostMapping("/register")
    public String registerStudent(
            @ModelAttribute StudentRegisterDTO registerDTO,
            Model model) {

        studentService.registerStudent(registerDTO);
        model.addAttribute("msg", "Registration successful! Please login.");
        return "Login";
    }

    /* ===============================
       Login
       =============================== */

    @GetMapping("/login")
    public String showLoginPage() {
        return "Login";
    }

    @PostMapping("/login")
    public String loginStudent(
            @ModelAttribute StudentLoginDTO loginDTO,
            Model model,
            HttpSession session) {

        Student student = studentService.loginStudent(loginDTO);

        if (student != null) {
        	System.out.println("LOGIN SUCCESS");
            session.setAttribute("loggedInStudent", student);
            return "StudentDashboard";
        } else {
        	 System.out.println("LOGIN FAILED");
            model.addAttribute("error", "Invalid email or password");
            return "Login";
        }
    }

    /* ===============================
       Dashboard
       =============================== */

    @GetMapping("/dashboard")
    public String studentDashboard(HttpSession session) {

        if (session.getAttribute("loggedInStudent") == null) {
            return "redirect:/student/login";
        }
        return "StudentDashboard";
    }

    /* ===============================
       Logout
       =============================== */

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/student/login";
    }
}
