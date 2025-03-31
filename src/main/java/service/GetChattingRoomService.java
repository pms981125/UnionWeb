package service;

import java.sql.Connection;

import dao.DAO;
import db.JDBCUtility;
import vo.ChattingRoomVO;
import vo.ContentChattingRoomVO;

public class GetChattingRoomService {
	private static GetChattingRoomService getChattingRoomService = null;
	private GetChattingRoomService() {}
	
	public static GetChattingRoomService getInstance() {
		if(getChattingRoomService == null) getChattingRoomService = new GetChattingRoomService();
		
		return getChattingRoomService;
	}

	public ChattingRoomVO getChattingRoomByNo(int no) {
		ChattingRoomVO chattingRoom = new ChattingRoomVO();
		DAO dao = DAO.getInstance();
		Connection conn = JDBCUtility.getConnection();
		
		dao.setConnection(conn);
		chattingRoom = dao.getChattingRoomByNo(no);
		JDBCUtility.close(conn, null, null);
		
		return chattingRoom;
	}

	public ContentChattingRoomVO getContentChattingRoom(int no) {
		ContentChattingRoomVO contentChattingRoom = new ContentChattingRoomVO();
		DAO dao = DAO.getInstance();
		Connection conn = JDBCUtility.getConnection();
		
		dao.setConnection(conn);
		contentChattingRoom = dao.getContentChattingRoom(no);
		JDBCUtility.close(conn, null, null);
		
		return contentChattingRoom;
	}
	
	public int getContentCount(int no) {
		int count = 0;
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		
		dao.setConnection(conn);
		count = dao.getContentCount(no);
		JDBCUtility.close(conn, null, null);
		
		return count;
	}
}