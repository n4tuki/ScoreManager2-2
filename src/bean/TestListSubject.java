package bean;

import java.io.Serializable;
import java.util.Objects;

public class TestListSubject implements Serializable {
    private int id;
    private String name;

    // デフォルトコンストラクタ
    public TestListSubject() {}

    // パラメータ付きコンストラクタ
    public TestListSubject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // ゲッターとセッター
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public int getId1() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

    // toString メソッドのオーバーライド
    @Override
    public String toString() {
        return "TestListSubject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    // equals メソッドのオーバーライド
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestListSubject that = (TestListSubject) o;
        return id == that.id && name.equals(that.name);
    }

    // hashCode メソッドのオーバーライド
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}