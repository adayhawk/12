// Book.java
package com.example.librarymanagementsystem.model;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private Integer quantity;

    // Getters and Setters
}