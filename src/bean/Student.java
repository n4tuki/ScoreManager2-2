package bean;
public class Student implements java.io.Serializable {
//生徒情報のゲッター、セッターを作成
	private int id;
	private String name;
	private int course_id;
	private String courseName;

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getCourse_id() {
		return course_id;
	}
	public String getCourseName(){
		return courseName;
	}


	public void setId(int id) {
		this.id=id;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setCourse_id(int course_id) {
			this.course_id=course_id;
	}
	public void setCourseName(String courseName) {
		this.courseName=courseName;
}
}