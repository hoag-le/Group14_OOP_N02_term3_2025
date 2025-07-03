package com.example.servingwebcontent.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.servingwebcontent.models.Member;

import org.springframework.stereotype.Component;

@Component
public class MemberDao {

    public List<Member> findAll() {
        List<Member> result = new ArrayList<>();
        String sql = "SELECT id, name FROM members";
        try (Connection conn = AivenDatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Member member = new Member(rs.getInt("id"), rs.getString("name"));
                result.add(member);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
        return result;
    }

    public void save(Member member) {
        String checkSql = "SELECT id FROM members WHERE id = ?";
        String sql = "INSERT INTO members(id, name) VALUES (?, ?)";
        try (Connection conn = AivenDatabaseConfig.getConnection();
             PreparedStatement check = conn.prepareStatement(checkSql)) {
            check.setInt(1, member.getId());
            try (ResultSet rs = check.executeQuery()) {
                if (rs.next()) {
                    throw new IllegalArgumentException("Member ID already exists");
                }
            }
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, member.getId());
                ps.setString(2, member.getName());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }

    public boolean update(Member member) {
        String sql = "UPDATE members SET name = ? WHERE id = ?";
        try (Connection conn = AivenDatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, member.getName());
            ps.setInt(2, member.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM members WHERE id = ?";
        try (Connection conn = AivenDatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }
}