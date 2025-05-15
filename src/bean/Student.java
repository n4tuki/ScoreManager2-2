package bean;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String no;
    private String name;
    private int entYear;
    private String classNum;
    private boolean isAttend;
    private School school;

    public Student() {}

    public Student(String no, String name, int entYear, String classNum, boolean isAttend, School school) {
        this.no = no;
        this.name = name;
        this.entYear = entYear;
        this.classNum = classNum;
        this.isAttend = isAttend;
        this.school = school;
    }

    public String getNo() { return no; }
    public void setNo(String no) { this.no = no; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getEntYear() { return entYear; }
    public void setEntYear(int entYear) { this.entYear = entYear; }

    public String getClassNum() { return classNum; }
    public void setClassNum(String classNum) { this.classNum = classNum; }

    public boolean isAttend() { return isAttend; }
    public void setAttend(boolean isAttend) { this.isAttend = isAttend; }

    public School getSchool() { return school; }
    public void setSchool(School school) { this.school = school; }

    // **追加: 学校コードを取得**
    public String getSchoolCd() {
        return school != null ? school.getCd() : null;
    }

    @Override
    public String toString() {
        return "Student{no='" + no + "', name='" + name + "', entYear=" + entYear +
                ", classNum='" + classNum + "', isAttend=" + isAttend + ", school=" + school + "}";
    }

    public void setSchoolCd(String cd) {
        if (this.school == null) {
            this.school = new School();  // 新しい School インスタンスを作成
        }
        this.school.setCd(cd);
    }
}