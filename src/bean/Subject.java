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
    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolCd() {
        return school_cd;
    }

    public void setSchoolCd(String school_cd) {
        this.school_cd = school_cd;
    }

	public int getId() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}
}
