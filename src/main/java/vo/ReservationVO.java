package vo;

import java.awt.Point;
import java.time.LocalDateTime;

public class ReservationVO {
	private int no;
	private String name;
	private String reservationPersonId;
	private String reservationPersonName;
	private int reservationPeopleNum;
	private LocalDateTime reservationDate;
	private String reservationLocationName;
	private Point reservationLocation;
	
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
	
	public String getReservationPersonId() {
		return reservationPersonId;
	}
	
	public void setReservationPersonId(String reservationPersonId) {
		this.reservationPersonId = reservationPersonId;
	}
	
	public String getReservationPersonName() {
		return reservationPersonName;
	}
	
	public void setReservationPersonName(String reservationPersonName) {
		this.reservationPersonName = reservationPersonName;
	}
	
	public int getReservationPeopleNum() {
		return reservationPeopleNum;
	}
	
	public void setReservationPeopleNum(int reservationPeopleNum) {
		this.reservationPeopleNum = reservationPeopleNum;
	}
	public LocalDateTime getReservationDate() {
		return reservationDate;
	}
	
	public void setReservationDate(LocalDateTime reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getReservationLocationName() {
		return reservationLocationName;
	}

	public void setReservationLocationName(String reservationLocationName) {
		this.reservationLocationName = reservationLocationName;
	}

	public Point getReservationLocation() {
		return reservationLocation;
	}

	public void setReservationLocation(Point reservationLocation) {
		this.reservationLocation = reservationLocation;
	}

	@Override
	public String toString() {
		return "ReservationVO [no=" + no + ", name=" + name + ", reservationPersonId=" + reservationPersonId
				+ ", reservationPersonName=" + reservationPersonName + ", reservationPeopleNum=" + reservationPeopleNum
				+ ", reservationDate=" + reservationDate + ", reservationLocationName=" + reservationLocationName
				+ ", reservationLocation=" + reservationLocation + "]";
	}
}