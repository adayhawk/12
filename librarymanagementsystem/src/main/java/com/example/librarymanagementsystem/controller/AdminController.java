// AdminController.java (管理员功能)
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

    // 管理员图书列表
    @GetMapping("/books")
    public String listBooks(Model model, @RequestParam(required = false) String keyword) {
        model.addAttribute("books", 
            keyword != null ? 
            bookRepository.findByTitleContainingOrAuthorContaining(keyword, keyword) : 
            bookRepository.findAll()
        );
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("totalBooks", bookRepository.count());
        return "admin/books";
    }

    // 添加图书
    @PostMapping("/books/add")
    public String addBook(Book book) {
        bookRepository.save(book);
        return "redirect:/admin/books";
    }

    // 删除图书
    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Integer id) {
        bookRepository.deleteById(id);
        return "redirect:/admin/books";
    }
}