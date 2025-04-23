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
    private String school;

    /**
     * ゲッター・セッター
     */

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getClass_num() {
        return class_num;
    }

    public void setClass_num(String class_num) {
        this.class_num = class_num;
    }

}