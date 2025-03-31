package service;

import java.sql.Connection;

import dao.DAO;
import db.JDBCUtility;

public class CreateBoardService {
	private static CreateBoardService createBoardService = null;
	private CreateBoardService() {}
	
	public static CreateBoardService getInstance() {
		if (createBoardService == null) createBoardService = new CreateBoardService();
		
		return createBoardService;
	}

	public void createBoard(String title, String contents, String imageSrc, String writerName, String writerId) {
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		
		dao.setConnection(conn);
		dao.createBoard(title, contents, imageSrc, writerName, writerId);
		JDBCUtility.commit(conn);
		JDBCUtility.close(conn, null, null);
	}
}