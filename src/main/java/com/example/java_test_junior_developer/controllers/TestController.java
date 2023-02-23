package com.example.java_test_junior_developer.controllers;

import ch.qos.logback.core.model.Model;
import com.example.java_test_junior_developer.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TestController {


    private  final UserRepository userRepository;

    @GetMapping("/1_question")
    public String tesQuestion1(Model model) {
        return "1_question";
    }

    @GetMapping("/2_question")
    public String testQuestion2(Model model) {
        return "2_question";
    }
}
