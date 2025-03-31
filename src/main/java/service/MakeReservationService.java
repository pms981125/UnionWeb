package service;

import java.awt.Point;
import java.sql.Connection;
import java.time.LocalDateTime;

import dao.DAO;
import db.JDBCUtility;

public class MakeReservationService {
	private static MakeReservationService makeReservationService = null;
	private MakeReservationService() {}
	
	public static MakeReservationService getInstance() {
		if(makeReservationService == null) makeReservationService = new MakeReservationService();
		
		return makeReservationService;
	}

	public void makeReservation(String name, String id, String reservationPersonName, int reservationPeopleNum,
			LocalDateTime reservationDate, String reservationLocationName, Point reservationLocation) {
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		
		dao.setConnection(conn);
		dao.makeReservation(name, id, reservationPersonName, reservationPeopleNum, reservationDate, reservationLocationName, reservationLocation);
		JDBCUtility.commit(conn);
		JDBCUtility.close(conn, null, null);
	}
}