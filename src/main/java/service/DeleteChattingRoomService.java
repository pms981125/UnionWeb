package service;

import java.sql.Connection;

import dao.DAO;
import db.JDBCUtility;

public class DeleteChattingRoomService {
	private static DeleteChattingRoomService deleteChattingRoomService = null;
	private DeleteChattingRoomService() {}
	
	public static DeleteChattingRoomService getInstance() {
		if(deleteChattingRoomService == null) deleteChattingRoomService = new DeleteChattingRoomService();
		
		return deleteChattingRoomService;
	}

	public void deleteChattingRoom(String id, int no) {
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		
		dao.setConnection(conn);
		dao.updateUserPcrl(no);
		dao.deleteContentChattingRoom(no);
		dao.deleteChattingRoom(no);
		JDBCUtility.commit(conn);
		JDBCUtility.close(conn, null, null);
	}
}