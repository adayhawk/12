package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.model.User;
import com.example.librarymanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 根路径重定向到登录页
    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }

    // 登录页面
    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String error, Model model) {
        if (error != null) model.addAttribute("error", "用户名或密码错误");
        return "login";
    }

    // 注册页面
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    // 注册处理（强制设为普通用户）
    @PostMapping("/register")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ) {
        if (userRepository.findByUsername(username) != null) {
            model.addAttribute("error", "用户名已存在");
            return "register";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER"); // 强制设为普通用户
        userRepository.save(user);
        return "redirect:/login?registered";
    }
}