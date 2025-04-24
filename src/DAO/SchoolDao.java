package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;

public class SchoolDao extends DAO {

    private Connection connection;

    public SchoolDao(Connection connection) {
        this.connection = connection;
    }

    public School get(String cd) throws SQLException {
        String sql = "SELECT * FROM school WHERE cd = ?";
        School school = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cd);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    school = new School();
                    school.setCd(rs.getString("cd"));
                    school.setName(rs.getString("name"));
                }
            }
        }
        return school;
    }

    public List<School> getAll() throws SQLException {
        String sql = "SELECT * FROM school";
        List<School> schools = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                School school = new School();
                school.setCd(rs.getString("cd"));
                school.setName(rs.getString("name"));
                schools.add(school);
            }
        }
        return schools;
    }

    public boolean save(School school) throws SQLException {
        String sql = "INSERT INTO school (cd, name) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, school.getCd());
            stmt.setString(2, school.getName());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean update(School school) throws SQLException {
        String sql = "UPDATE school SET name = ? WHERE cd = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, school.getName());
            stmt.setString(2, school.getCd());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean delete(String cd) throws SQLException {
        String sql = "DELETE FROM school WHERE cd = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cd);
            return stmt.executeUpdate() > 0;
        }
    }
}
