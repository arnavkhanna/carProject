package com.car.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebController {

    private final Logger logger = LogManager.getLogger(getClass());

    @GetMapping("/")
    public String index(Model model) {

        logger.info("request to index page");

        model.addAttribute("message", "Welcome to Cars.");
        return "cars";
    }
}
