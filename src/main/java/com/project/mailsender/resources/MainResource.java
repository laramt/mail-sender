package com.project.mailsender.resources;

import com.project.mailsender.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainResource {

    @Autowired
    EmailService service;

    @GetMapping("/home")
    public ModelAndView home( ){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @GetMapping("/about")
    public ModelAndView about() {
        ModelAndView mv = new ModelAndView("about");
        return mv;
    }
}
