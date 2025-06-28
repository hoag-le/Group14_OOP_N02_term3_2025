package com.example.servingwebcontent.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.servingwebcontent.models.Book;

import org.springframework.stereotype.Component;

@Component
public class BookDao {

    public List<Book> findAll() {
        List<Book> result = new ArrayList<>();
        String sql = "SELECT id, title, author FROM books";
        try (Connection conn = AivenDatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Book book = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"));
                result.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void save(Book book) {
        String sql = "INSERT INTO books(id, title, author) VALUES (?, ?, ?)";
        try (Connection conn = AivenDatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, book.getId());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}