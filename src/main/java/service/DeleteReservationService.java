package service;

import java.sql.Connection;

import dao.DAO;
import db.JDBCUtility;

public class DeleteReservationService {
	private static DeleteReservationService deleteReservationService = null;
	private DeleteReservationService() {}
	
	public static DeleteReservationService getInstance() {
		if(deleteReservationService == null) deleteReservationService = new DeleteReservationService();
		
		return deleteReservationService;
	}

	public void deleteReservation(int no) {
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		
		dao.setConnection(conn);
		dao.deleteReservation(no);
		JDBCUtility.commit(conn);
		JDBCUtility.close(conn, null, null);
	}
}