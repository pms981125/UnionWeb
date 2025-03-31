package service;

import java.sql.Connection;

import dao.DAO;
import db.JDBCUtility;
import vo.ReservationVO;

public class GetReservationDetailService {
	private static GetReservationDetailService getReservationDetailService = null;
	private GetReservationDetailService() {}
	
	public static GetReservationDetailService getInstance() {
		if(getReservationDetailService == null) getReservationDetailService = new GetReservationDetailService();
		
		return getReservationDetailService;
	}
	
	public ReservationVO getReservation(int no) {
		ReservationVO reservation = new ReservationVO();
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		
		dao.setConnection(conn);
		reservation = dao.getReservation(no);
		JDBCUtility.close(conn, null, null);
		
		return reservation;
	}
}