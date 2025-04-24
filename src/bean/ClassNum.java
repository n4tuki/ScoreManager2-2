package bean;

import java.io.Serializable;

public class ClassNum implements Serializable {
    /**
     * クラス名:class_num
     */
    private String class_num;

    /**
     * 学校:School
     */
    private String school_cd;

    /**
     * ゲッター・セッター
     */

    public String getSchool_cd() {
        return school_cd;
    }

    public void setSchool_cd(String school_cd) {
        this.school_cd = school_cd;
    }

    public String getClass_num() {
        return class_num;
    }

    public void setClass_num(String class_num) {
        this.class_num = class_num;
    }

}