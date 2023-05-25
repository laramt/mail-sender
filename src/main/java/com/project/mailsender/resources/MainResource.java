package com.project.mailsender.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainResource {
    
    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @GetMapping("/email")
    public String emailForm() {
        return "email";
    }

    @GetMapping("/template")
    public String emailTemplateForm() {
        return "template";
    }
}
