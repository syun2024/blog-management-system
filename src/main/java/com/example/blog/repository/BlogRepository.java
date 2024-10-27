package com.example.blog.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.blog.entity.Blog;

@Repository
public interface BlogRepository {
    List<Blog> findAll();
    void save(Blog blog);
    Blog findById(UUID id);
    void update(Blog blog);
    void delete(UUID id);
}
