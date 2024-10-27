package com.example.blog.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Table(name = "blog")
@Entity
@Getter
@Setter
public class Blog {
    @Id
    private UUID id;
    
    private String title;
    
    private String content;
    
    private LocalDateTime createAt;
    
    private LocalDateTime updateAt;
    
    private LocalDateTime deleteAt;

}
