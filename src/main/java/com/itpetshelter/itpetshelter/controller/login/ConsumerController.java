package com.itpetshelter.itpetshelter.controller.login;

import com.itpetshelter.itpetshelter.domain.Consumer;
import com.itpetshelter.itpetshelter.service.login.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("consumer", new Consumer());
        return "register";
    }

    @PostMapping("/register")
    public String registerConsumer(Consumer consumer) {
        consumerService.saveConsumer(consumer);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "user-login";
    }
}
