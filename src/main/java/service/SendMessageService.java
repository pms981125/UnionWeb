package service;

import java.sql.Connection;

import dao.DAO;
import db.JDBCUtility;

public class SendMessageService {
	private static SendMessageService sendMessageService = null;
	private SendMessageService() {}
	
	public static SendMessageService getInstance() {
		if(sendMessageService == null) sendMessageService = new SendMessageService();
		
		return sendMessageService;
	}

	public void sendMessage(int no, String message, String userId) {
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		
		dao.setConnection(conn);
		dao.sendMessage(no, message, userId);
		JDBCUtility.commit(conn);
		JDBCUtility.close(conn, null, null);
	}
}