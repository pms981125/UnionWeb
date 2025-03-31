package service;

import java.awt.Point;
import java.sql.Connection;
import java.time.LocalDateTime;

import dao.DAO;
import db.JDBCUtility;

public class ModifyReservationService {
	private static ModifyReservationService modifyReservationService = null;
	private ModifyReservationService() {}
	
	public static ModifyReservationService getInstance() {
		if(modifyReservationService == null) modifyReservationService = new ModifyReservationService();
		
		return modifyReservationService;
	}

	public void modifyReservation(int no, String name, int reservationPeopleNum, LocalDateTime reservationDate,
			String reservationLocationName, Point reservationLocation) {
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		
		dao.setConnection(conn);
		dao.modifyReservation(no, name, reservationPeopleNum, reservationDate, reservationLocationName, reservationLocation);
		JDBCUtility.commit(conn);
		JDBCUtility.close(conn, null, null);
	}
}