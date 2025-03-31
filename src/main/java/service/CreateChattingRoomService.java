package service;

import java.sql.Connection;

import dao.DAO;
import db.JDBCUtility;

public class CreateChattingRoomService {
	private static CreateChattingRoomService creatingChattingRoomService = null;
	private CreateChattingRoomService() {}
	
	public static CreateChattingRoomService getInstance() {
		if(creatingChattingRoomService == null) creatingChattingRoomService = new CreateChattingRoomService();
		
		return creatingChattingRoomService;
	}

	public int createChattingRoom(String name, String managerId, String participatingPeople) {
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		boolean isUnique = false;
		boolean isSuccess = false;
		int roomNo = 0;
		String id = "";
		
		dao.setConnection(conn);
		
		if ((roomNo = dao.isExistRoom(name, participatingPeople)) != 0) {
			JDBCUtility.close(conn, null, null);
			return roomNo;
		}
		
		while (!isUnique) {
			id = dao.createId(20);
			isUnique = dao.checkUniqueTableId("chatting_room" , id);
		}
		
		isSuccess = dao.createChattingRoom(id, name, managerId, participatingPeople);
		
		if (isSuccess) {
			roomNo = dao.getRoomNo(id);
			
			isSuccess = false;
			isSuccess = dao.createContentChattingRoom(roomNo);
			
			if (isSuccess) {
				isSuccess = false;
				isSuccess = dao.addUserPCRL(managerId, participatingPeople, roomNo);
				
				if (isSuccess) {
					JDBCUtility.commit(conn);
					JDBCUtility.close(conn, null, null);
				} else {
					JDBCUtility.rollback(conn);
					JDBCUtility.close(conn, null, null);
				}
			} else {
				JDBCUtility.rollback(conn);
				JDBCUtility.close(conn, null, null);
			}
		} else {
			JDBCUtility.rollback(conn);
			JDBCUtility.close(conn, null, null);
		}
		
		return roomNo;
	}
}