package DAO;
//dao
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

import bean.School;

public class TestListSubjectDao {
    private Connection connection;
    private String baseSql = "SELECT * FROM test_list_subject";

    public TestListSubjectDao(Connection connection) {
        this.connection = connection;
    }

    public List<TestListSubject> postFilter(ResultSet resultSet) throws SQLException {
        List<TestListSubject> filteredList = new ArrayList<>();
        while (resultSet.next()) {
            // ResultSetからデータを取得し、TestListSubjectオブジェクトを作成してリストに追加します
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            filteredList.add(new TestListSubject(id, name));
        }
        return filteredList;
    }

    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws SQLException {
        String query = baseSql + " WHERE ent_year = ? AND class_num = ? AND subject_id = ? AND school_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entYear);
            statement.setString(2, classNum);
            statement.setInt(3, subject.getId());
            statement.setInt(4, school.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                return postFilter(resultSet);
            }
        }
    }
}

