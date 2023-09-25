package com.example.exploration.controller;

import com.example.exploration.dto.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

  private List<Book> queryBooks() {
    return Arrays.asList(
        new Book("111", "Clean code", "Robert"),
        new Book("222", "Head First Design Patterns", "Freeman"),
        new Book("333", "Refactor", "Martin"));
  }

  @GetMapping("/")
  public ResponseEntity<List<Book>> getBooks() {
    return ResponseEntity.ok(queryBooks());
  }

  @GetMapping("/view-books")
  public String viewBooks(Model model) {
    model.addAttribute("books", queryBooks());
    return "view-books";
  }
}
