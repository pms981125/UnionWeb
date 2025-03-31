package vo;

import java.util.Date;
import java.util.List;

public class BoardVO {
	private int no;
	private String title;
	private String contents;
	private String imageSrc;
	private int readCount;
	private int likeCount;
	private List<String> likeID;
	private Date writeDate;
	private String writerName;
	private String writerId;
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContents() {
		return contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public int getReadCount() {
		return readCount;
	}
	
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	
	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public List<String> getLikeID() {
		return likeID;
	}

	public void setLikeID(List<String> likeID) {
		this.likeID = likeID;
	}

	public Date getWriteDate() {
		return writeDate;
	}
	
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	
	public String getWriterName() {
		return writerName;
	}
	
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	
	public String getWriterId() {
		return writerId;
	}
	
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	@Override
	public String toString() {
		return "BoardVO [no=" + no + ", title=" + title + ", contents=" + contents + ", imageSrc=" + imageSrc
				+ ", readCount=" + readCount + ", likeCount=" + likeCount + ", likeID=" + likeID + ", writeDate="
				+ writeDate + ", writerName=" + writerName + ", writerId=" + writerId + "]";
	}
}