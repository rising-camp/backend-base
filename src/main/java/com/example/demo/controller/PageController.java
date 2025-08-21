package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class PageController {

    @GetMapping("")
    public String index() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(" - Logged User : " + authentication);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(" - Anonymous User : " + authentication);
        return "login";
    }
}
