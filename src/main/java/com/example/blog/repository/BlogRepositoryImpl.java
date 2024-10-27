package com.example.blog.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.blog.entity.Blog;

@Repository
public class BlogRepositoryImpl implements BlogRepository {
private static final String POSTGRES_DRIVER = "org.postgresql.Driver";
    
    private static final String JDBC_CONNECTION = "jdbc:postgresql://localhost:5432/blog-system";
    
    private static final String USER = "postgres";
    
    private static final String PASS = "postgres";
    
    @Override
    public void save(Blog blog) {
        String SQL = "INSERT INTO Blog (id,title,content) VALUES(?,?,?)";
        try {
            Class.forName(POSTGRES_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_CONNECTION,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            
            blog.setId(UUID.randomUUID());
            
            preparedStatement.setObject(1, blog.getId());
            preparedStatement.setString(2, blog.getTitle());
            preparedStatement.setString(3, blog.getContent());
//            preparedStatement.setTimestamp(4, java.sql.Timestamp.valueOf(blog.getCreateAt()));
//            preparedStatement.setTimestamp(5, java.sql.Timestamp.valueOf(blog.getUpdateAt()));
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    
    @Override
    public Blog findById(UUID id) {
        String SQL = "SELECT * FROM Blog WHERE id = ? AND delete_at IS NULL";
        Blog blog = null;
        try {
            Class.forName(POSTGRES_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_CONNECTION,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                blog = new Blog();
                blog.setId(resultSet.getObject("id",UUID.class));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setCreateAt(resultSet.getTimestamp("create_at").toLocalDateTime());
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return blog;
    }
    
    @Override
    public void update(Blog blog) {

        String SQL = "UPDATE Blog SET title=?,content=?,update_at = NOW() WHERE id = ?";
        try {
            Class.forName(POSTGRES_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_CONNECTION,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            
            preparedStatement.setString(1, blog.getTitle());
            preparedStatement.setString(2, blog.getContent());
            preparedStatement.setObject(3, blog.getId());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }



    public void delete(UUID id) {
        String SQL = "UPDATE Blog SET delete_at = NOW() WHERE id = ?";
        try {
            Class.forName(POSTGRES_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_CONNECTION,USER,PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    @Override
    public List<Blog> findAll() {
        // TODO 自動生成されたメソッド・スタブ
        List<Blog> blogs = new ArrayList<>();
        
        String SQL = "SELECT * FROM Blog WHERE delete_at IS NULL ORDER BY id DESC";
        try {
            Class.forName(POSTGRES_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_CONNECTION,USER,PASS);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Blog blog = new Blog();
                blog.setId(resultSet.getObject("id",UUID.class));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setCreateAt(resultSet.getTimestamp("create_at").toLocalDateTime());
                blogs.add(blog);
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return blogs;
    }




}


