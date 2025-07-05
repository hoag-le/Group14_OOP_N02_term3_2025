package com.example.servingwebcontent.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.servingwebcontent.models.Book;
import org.springframework.stereotype.Component;

@Component
public class BookDao implements CrudRepository<Book> {

    public List<Book> findAll() {
        List<Book> result = new ArrayList<>();
        String sql = "SELECT id, title, author, available FROM books";
        try (Connection conn = AivenDatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Book book = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available"), java.time.LocalDate.now());
                result.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
        return result;
    }

    @Override
    public List<Book> listAll() {
        return findAll();
    }

    public void save(Book book) {
        String checkSql = "SELECT id FROM books WHERE id = ?";
        String sql = "INSERT INTO books(id, title, author, available) VALUES (?, ?, ?, ?)";
        try (Connection conn = AivenDatabaseConfig.getConnection();
             PreparedStatement check = conn.prepareStatement(checkSql)) {
            check.setInt(1, book.getId());
            try (ResultSet rs = check.executeQuery()) {
                if (rs.next()) {
                    throw new IllegalArgumentException("Book ID already exists");
                }
            }
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, book.getId());
                ps.setString(2, book.getTitle());
                ps.setString(3, book.getAuthor());
                ps.setBoolean(4, book.isAvailable());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }

    @Override
    public void create(Book obj) {
        save(obj);
    }

    @Override
    public Book read(int id) {
        String sql = "SELECT id, title, author, available FROM books WHERE id = ?";
        try (Connection conn = AivenDatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available"), java.time.LocalDate.now());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
        return null;
    }

    @Override
    public void update(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, available = ? WHERE id = ?";
        try (Connection conn = AivenDatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setBoolean(3, book.isAvailable());
            ps.setInt(4, book.getId());
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new IllegalArgumentException("Book not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }


    @Override
    public void delete(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection conn = AivenDatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new IllegalArgumentException("Book not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }


    /**
     * Simple search by title or author containing the provided keyword.
     */
    public List<Book> search(String keyword) {
        List<Book> result = new ArrayList<>();
        String sql = "SELECT id, title, author, available FROM books WHERE LOWER(title) LIKE ? OR LOWER(author) LIKE ?";
        try (Connection conn = AivenDatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String pattern = "%" + keyword.toLowerCase() + "%";
            ps.setString(1, pattern);
            ps.setString(2, pattern);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available"), java.time.LocalDate.now());
                    result.add(book);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
        return result;
    }

    /**
     * Update the availability status of a book.
     */
    public void updateAvailability(int id, boolean available) {
        String sql = "UPDATE books SET available = ? WHERE id = ?";
        try (Connection conn = AivenDatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, available);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }
}