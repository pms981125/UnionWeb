package service;

import java.sql.Connection;

import dao.DAO;
import db.JDBCUtility;

public class ExitChattingRoomService {
	private static ExitChattingRoomService exitChattingRoomService = null;
	private ExitChattingRoomService() {}
	
	public static ExitChattingRoomService getInstance() {
		if(exitChattingRoomService == null) exitChattingRoomService = new ExitChattingRoomService();
		
		return exitChattingRoomService;
	}

	public void exitChattingRoom(int no, String id) {
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		
		dao.setConnection(conn);
		dao.updateUserPcrlByExit(no, id);
		dao.exitChattingRoom(no, id);
		// contentChattingRoom에서 나간사람 표시?
		JDBCUtility.commit(conn);
		JDBCUtility.close(conn, null, null);
	}
}