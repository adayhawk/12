// UserController.java (用户功能)
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

    // 用户图书列表
    @GetMapping("/books")
    public String listBooks(Model model, @RequestParam(required = false) String keyword) {
        model.addAttribute("books",
                keyword != null ?
                        bookRepository.findByTitleContainingOrAuthorContaining(keyword, keyword) :
                        bookRepository.findAll()
        );
        return "user/books";
    }
}