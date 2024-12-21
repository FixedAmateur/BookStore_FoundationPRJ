package com.foundationProject.BookStore.controller.views;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view/auth")
@Tag(name = "Authentication View", description = "authentication view")
public class AuthenticationViewController {
    @RequestMapping("/login")
    public String login() {
        return "customer-template/login";
    }
}
