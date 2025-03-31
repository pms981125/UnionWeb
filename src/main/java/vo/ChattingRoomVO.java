package vo;

import java.util.List;

public class ChattingRoomVO {
	private int no;
	private String id;
	private String name;
	private String manager;
	private List<String> participatingPeople;
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getManager() {
		return manager;
	}
	
	public void setManager(String manager) {
		this.manager = manager;
	}
	
	public List<String> getParticipatingPeople() {
		return participatingPeople;
	}
	
	public void setParticipatingPeople(List<String> participatingPeople) {
		this.participatingPeople = participatingPeople;
	}

	@Override
	public String toString() {
		return "ChattingRoomVO [no=" + no + ", id=" + id + ", name=" + name + ", manager=" + manager
				+ ", participatingPeople=" + participatingPeople + "]";
	}
}