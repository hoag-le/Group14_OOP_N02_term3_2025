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
            e.printStackTrace();
        }
        return result;
    }

    public void save(Member member) {
        String sql = "INSERT INTO members(id, name) VALUES (?, ?)";
        try (Connection conn = AivenDatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, member.getId());
            ps.setString(2, member.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}