package service;

import java.sql.Connection;

import dao.DAO;
import db.JDBCUtility;

public class DeleteBoardService {
	private static DeleteBoardService  deleteBoardService = null;
	private DeleteBoardService() {}
	
	public static DeleteBoardService getInstance() {
		if(deleteBoardService == null) deleteBoardService = new DeleteBoardService();
		
		return deleteBoardService;
	}

	public void deleteBoard(int no) {
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		
		dao.setConnection(conn);
		dao.deleteBoard(no);
		JDBCUtility.commit(conn);
		JDBCUtility.close(conn, null, null);
	}
}