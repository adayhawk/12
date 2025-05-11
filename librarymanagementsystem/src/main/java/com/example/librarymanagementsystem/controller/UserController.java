package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private BookRepository bookRepository;

    // 用户图书查询页面
    @GetMapping("/books")
    public String listBooks(
            @RequestParam(required = false) String keyword,
            Model model
    ) {
        model.addAttribute("books",
                keyword != null ?
                        bookRepository.findByTitleContainingOrAuthorContaining(keyword, keyword) :
                        bookRepository.findAll()
        );
        return "user/books"; // 返回用户专属模板
    }
}
