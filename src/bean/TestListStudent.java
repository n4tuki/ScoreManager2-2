package bean;

import java.io.Serializable;
import java.util.Objects;

public class TestListStudent implements Serializable {
    private int studentId;
    private double testScore;

    // デフォルトコンストラクタ
    public TestListStudent() {}

    // パラメータ付きコンストラクタ
    public TestListStudent(int studentId, double testScore) {
        this.studentId = studentId;
        this.testScore = testScore;
    }

    // ゲッターとセッター
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public double getTestScore() {
        return testScore;
    }

    public void setTestScore(double testScore) {
        this.testScore = testScore;
    }

	public int getId() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

    // toString メソッドのオーバーライド
    @Override
    public String toString() {
        return "TestListStudent{" +
                "studentId=" + studentId +
                ", testScore=" + testScore +
                '}';
    }

    // equals メソッドのオーバーライド
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestListStudent that = (TestListStudent) o;
        return studentId == that.studentId && Double.compare(that.testScore, testScore) == 0;
    }

    // hashCode メソッドのオーバーライド
    @Override
    public int hashCode() {
        return Objects.hash(studentId, testScore);
    }
}