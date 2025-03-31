package service;

import java.sql.Connection;

import dao.DAO;
import db.JDBCUtility;

public class EditBoardService {
	private static EditBoardService editBoardService;
	private EditBoardService() {}
	
	public static EditBoardService getInstance() {
		if(editBoardService == null) editBoardService = new EditBoardService();
		
		return editBoardService;
	}

	public void editBoard(int no, String title, String contents, String imageSrc) {
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		
		dao.setConnection(conn);
		dao.editBoard(no, title, contents, imageSrc);
		JDBCUtility.commit(conn);
		JDBCUtility.close(conn, null, null);
	}
}