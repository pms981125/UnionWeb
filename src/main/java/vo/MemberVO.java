package vo;

import java.util.Date;
import java.util.List;

public class MemberVO {
	private int no;
	private String name;
	private String id;
	private String password;
	private Date registerDate;
	private Date birth;
	private int age;
	private String gender;
	private List<String> participatingChattingRoomNo;
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<String> getParticipatingChattingRoomNo() {
		return participatingChattingRoomNo;
	}
	
	public void setParticipatingChattingRoomNo(List<String> participatingChattingRoomNo) {
		this.participatingChattingRoomNo = participatingChattingRoomNo;

	}

	@Override
	public String toString() {
		return "MemberVO [no=" + no + ", name=" + name + ", id=" + id + ", password=" + password + ", registerDate="
				+ registerDate + ", birth=" + birth + ", age=" + age + ", gender=" + gender
				+ ", participatingChattingRoomNo=" + participatingChattingRoomNo + "]";
	}
}