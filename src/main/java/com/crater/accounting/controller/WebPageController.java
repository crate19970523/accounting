package com.crater.accounting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebPageController {

    @RequestMapping("/home")
    public String home() {
        return "index.html";
    }
}
