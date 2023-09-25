package com.example.exploration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AjaxTableController {
  @GetMapping("ajax-table")
  public String ajaxTable() {
    return "ajax-table";
  }
}
