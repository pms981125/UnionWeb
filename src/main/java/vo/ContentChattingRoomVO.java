package vo;

import java.util.List;

public class ContentChattingRoomVO {
	private List<Integer> no;
	private List<String> message;
	private List<String> writerId;
	
	public List<Integer> getNo() {
		return no;
	}
	
	public void setNo(List<Integer> no) {
		this.no = no;
	}
	
	public List<String> getMessage() {
		return message;
	}
	
	public void setMessage(List<String> message) {
		this.message = message;
	}
	
	public List<String> getWriterId() {
		return writerId;
	}
	
	public void setWriterId(List<String> writerId) {
		this.writerId = writerId;
	}

	@Override
	public String toString() {
		return "ContentChattingRoomVO [no=" + no + ", message=" + message + ", writerId=" + writerId + "]";
	}
}