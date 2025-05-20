package bean;

import java.io.Serializable;

public class Subject implements Serializable {
    /**
     * 科目コード:String
     */
    private String school_cd;
    /**
     * 科目名:String
     */
    private String cd;
    /**
     * 学校コード:String
     */
    private String name;

    // ゲッター・セッター
    public String getCode() {
        return cd;
    }

    public void setCode(String cd) {
        this.cd = cd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolCode() {
        return school_cd;
    }

    public void setSchoolCode(String schoolCode) {
        this.school_cd = school_cd;
    }

	public int getId() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}
}
