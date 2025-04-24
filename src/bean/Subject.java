package bean;

import java.io.Serializable;

public class Subject implements Serializable {
    /**
     * 科目コード:String
     */
    private String code;
    /**
     * 科目名:String
     */
    private String name;
    /**
     * 学校コード:String
     */
    private String schoolCode;

    // ゲッター・セッター
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }
}
