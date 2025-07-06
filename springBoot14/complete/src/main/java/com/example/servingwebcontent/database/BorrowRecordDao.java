package com.example.servingwebcontent.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.servingwebcontent.models.Book;
import com.example.servingwebcontent.models.BorrowRecord;
import com.example.servingwebcontent.models.Member;

@Component
public class BorrowRecordDao implements CrudRepository<BorrowRecord> {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private MemberDao memberDao;

    public List<BorrowRecord> findAll() {
        List<BorrowRecord> result = new ArrayList<>();
        String sql = "SELECT id, book_id, member_id, borrow_date, due_date, return_date, returned FROM borrow_records";
        try (Connection conn = AivenDatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Book book = bookDao.read(rs.getInt("book_id"));
                Member member = memberDao.read(rs.getInt("member_id"));
                Date borrowDate = rs.getTimestamp("borrow_date");
                Date dueDate = rs.getTimestamp("due_date");
                BorrowRecord record = new BorrowRecord(book, member, borrowDate, dueDate);
                record.setId(rs.getInt("id"));
                if (rs.getBoolean("returned")) {
                    Date ret = rs.getTimestamp("return_date");
                    if (ret != null) {
                        record.setReturned(ret);
                    } else {
                        record.setReturned(new Date());
                    }
                }
                result.add(record);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
        return result;
    }

    @Override
    public List<BorrowRecord> listAll() {
        return findAll();
    }

    public void save(BorrowRecord record) {
        String sql = "INSERT INTO borrow_records(book_id, member_id, borrow_date, due_date, returned) VALUES (?,?,?,?,?)";
        try (Connection conn = AivenDatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, record.getBook().getId());
            ps.setInt(2, record.getMember().getId());
            ps.setTimestamp(3, new Timestamp(record.getBorrowDate().getTime()));
            ps.setTimestamp(4, new Timestamp(record.getDueDate().getTime()));
            ps.setBoolean(5, record.isReturned());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    record.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }

    @Override
    public void create(BorrowRecord obj) {
        save(obj);
    }

    @Override
    public BorrowRecord read(int id) {
        String sql = "SELECT id, book_id, member_id, borrow_date, due_date, return_date, returned FROM borrow_records WHERE id = ?";
        try (Connection conn = AivenDatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Book book = bookDao.read(rs.getInt("book_id"));
                    Member member = memberDao.read(rs.getInt("member_id"));
                    Date borrowDate = rs.getTimestamp("borrow_date");
                    Date dueDate = rs.getTimestamp("due_date");
                    BorrowRecord record = new BorrowRecord(book, member, borrowDate, dueDate);
                    record.setId(rs.getInt("id"));
                    if (rs.getBoolean("returned")) {
                        Date ret = rs.getTimestamp("return_date");
                        if (ret != null) {
                            record.setReturned(ret);
                        } else {
                            record.setReturned(new Date());
                        }
                    }
                    return record;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
        return null;
    }

    @Override
    public void update(BorrowRecord record) {
        String sql = "UPDATE borrow_records SET return_date = ?, returned = ? WHERE id = ?";
        try (Connection conn = AivenDatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            if (record.getReturnDate() != null) {
                ps.setTimestamp(1, new Timestamp(record.getReturnDate().getTime()));
            } else {
                ps.setTimestamp(1, null);
            }
            ps.setBoolean(2, record.isReturned());
            ps.setInt(3, record.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM borrow_records WHERE id = ?";
        try (Connection conn = AivenDatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }
}