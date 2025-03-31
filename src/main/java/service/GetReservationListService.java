package service;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dao.DAO;
import db.JDBCUtility;
import vo.ReservationVO;

public class GetReservationListService {
	private static GetReservationListService getReservationListService = null;
	private GetReservationListService() {}
	
	public static GetReservationListService getInstance() {
		if (getReservationListService == null) getReservationListService = new GetReservationListService();
		
		return getReservationListService;
	}
	
	public List<ReservationVO> getReservationList(String id) {
		List<ReservationVO> reservationList = new ArrayList<ReservationVO>();
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		LocalDateTime now = LocalDateTime.now();
		
		dao.setConnection(conn);
		reservationList = dao.getReservationList(id, now);
		JDBCUtility.close(conn, null, null);
		
		return reservationList;
	}
}