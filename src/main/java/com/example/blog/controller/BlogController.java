package com.example.blog.controller;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.blog.entity.Blog;
import com.example.blog.form.BlogForm;
import com.example.blog.service.BlogService;

@Controller
public class BlogController {
    @Autowired
    private final BlogService blogService;
    
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }
    
    @GetMapping("/blogs")
    public String list(Model model) {
        List<Blog> blogs = blogService.list();
        model.addAttribute("blogs",blogs);
        return "blog/list";
    }
    
    @GetMapping("blogs/new")
    public String create(Model model) {
        model.addAttribute("blog",new BlogForm());
        return "blog/form";
    }
    
    @PostMapping("/blogs")
    public String create(@ModelAttribute BlogForm blogForm) {
        //TODO: process POST request
        blogService.create(blogForm);
        return "redirect:/blogs";
    }
    
    @GetMapping("/blogs/{id}")
    public String detail(@PathVariable UUID id,Model model) {
        Blog blog = blogService.detail(id);
        model.addAttribute("blog",blog);
        return "blog/detail";
    }
    
    @GetMapping("/blogs/{id}/edit")
    public String edit(@PathVariable UUID id,Model model) {
        Blog blog = blogService.detail(id);
        model.addAttribute("blog",blog);
        return "blog/form";
    }
    
    @PostMapping("/blogs/{id}/edit")
    public String update(@PathVariable UUID id,@Valid @ModelAttribute BlogForm blogForm) {
        //TODO: process POST request
        blogService.update(blogForm);
        return "redirect:/blogs";
    }
    
    @PostMapping("blogs/{id}/delete")
    public String delete(@PathVariable UUID id) {
        //TODO: process POST request
        blogService.delete(id);
        return "redirect:/blogs";
    }
    
      

}
