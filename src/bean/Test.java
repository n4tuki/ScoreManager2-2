package bean;

import java.io.Serializable;

public class Test implements Serializable {
    /**
     * 学生番号:String
     */
    private String studentNo;
    /**
     * 科目コード:String
     */
    private String subjectCode;
    /**
     * 学校コード:String
     */
    private String schoolCode;
    /**
     * テスト番号または識別ID:String
     */
    private String no;
    /**
     * 点数:int
     */
    private int point;
    /**
     * クラス番号:String
     */
    private String classNum;

    // ゲッター・セッター
    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getSubjectCd() {
        return subjectCode;
    }

    public void setSubjectCd(String subjectCd) {
        this.subjectCode = subjectCd;
    }

    public String getSchoolCd() {
        return schoolCode;
    }

    public void setSchoolCd(String schoolCd) {
        this.schoolCode = schoolCd;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }
}
