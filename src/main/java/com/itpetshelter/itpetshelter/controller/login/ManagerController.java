package com.itpetshelter.itpetshelter.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagerController {

    @GetMapping("/manager/login")
    public String showAdminLoginForm() {
        return "manager-login";
    }
}
