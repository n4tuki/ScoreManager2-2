package DAO;

import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao {
    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) {
        List<Student> list = new ArrayList<>();

        if (school == null || school.getCd() == null) {
            throw new IllegalArgumentException("School information is missing!");
        }

        // **DBから適切に学生情報を取得**
        System.out.println("Fetching students for school: " + school.getCd());

        return list;
    }

    public List<Student> filter(School school, boolean isAttend) {
        return filter(school, 2025, "A", isAttend);
    }

    public List<Student> Afilter(School school, boolean defaultCondition) {
        return filter(school, 2025, "A", true);
    }
}