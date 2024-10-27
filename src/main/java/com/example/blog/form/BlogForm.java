package com.example.blog.form;

import java.time.LocalDateTime;
import java.util.UUID;

public class BlogForm {
    private UUID id;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;
    
    public BlogForm() {
        
    }
    
    public BlogForm(UUID id,String title,String content,LocalDateTime createAt,LocalDateTime updateAt,LocalDateTime deleteAt) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.createAt = createAt;
            this.updateAt = updateAt;
            this.deleteAt = deleteAt;
        }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public UUID getId() {
        return id;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
    
    public LocalDateTime getCreateAt() {
        return createAt;
    }
    
    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
    
    public LocalDateTime getUpdateAt() {
        return updateAt;
    }
    
    public void setDeleteAt(LocalDateTime deleteAt) {
        this.deleteAt = deleteAt;
    }
    
    public LocalDateTime getDeleteAt() {
        return deleteAt;
    }

}
