package bean;

import java.io.Serializable;

public class School implements Serializable {
    private static final long serialVersionUID = 1L;
    private String cd;
    private String name;

    public School() {}

    public School(String cd, String name) {
        this.cd = cd;
        this.name = name;
    }

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

    @Override
    public String toString() {
        return "School{cd='" + cd + "', name='" + name + "'}";
    }
}