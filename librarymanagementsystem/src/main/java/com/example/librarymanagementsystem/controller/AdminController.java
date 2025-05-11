package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // 管理员图书管理页面
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
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("totalBooks", bookRepository.count());
        return "admin/books"; // 返回管理员专属模板
    }

    // 添加、删除、编辑方法保持不变
}