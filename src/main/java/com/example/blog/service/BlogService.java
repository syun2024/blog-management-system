package com.example.blog.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.entity.Blog;
import com.example.blog.form.BlogForm;
import com.example.blog.repository.BlogRepository;

@Service
public class BlogService {

        
        @Autowired
        private final BlogRepository blogRepository;
        
        public BlogService(BlogRepository blogRepository) {
            this.blogRepository = blogRepository;
        }
        
        public List<Blog> list() {
            return blogRepository.findAll();
        }
        
        public void create(BlogForm blogForm) {
            Blog blog = new Blog();
            blog.setId(blogForm.getId());
            blog.setTitle(blogForm.getTitle());
            blog.setContent(blogForm.getContent());
//            blog.setCreateAt(blogForm.getCreateAt());
//            blog.setUpdateAt(blogForm.getUpdateAt());
            blogRepository.save(blog);
        }
        
        public Blog detail(UUID id) {
            return blogRepository.findById(id);
        }
        
        public void update(BlogForm blogForm) {
            Blog blog = null;
            if (blogForm.getId() == null) {
                blog = new Blog();
            } else {
                blog = blogRepository.findById(blogForm.getId());
                if(blog == null) {
                    throw new RuntimeException("Blog not found:"+blogForm.getId());
                }
            }
            blog.setTitle(blogForm.getTitle());
            blog.setContent(blogForm.getContent());
            blog.setUpdateAt(LocalDateTime.now());
            blogRepository.update(blog);
        }
        
        public void delete(UUID id) {
            blogRepository.delete(id);
        }
     
}
