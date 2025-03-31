package service;

import java.sql.Connection;

import dao.DAO;
import db.JDBCUtility;

public class AddUserService {
	private static AddUserService addUserService = null;
	private AddUserService() {}
	
	public static AddUserService getInstance() {
		if(addUserService == null) addUserService = new AddUserService();
		
		return addUserService;
	}

	public void addUser(String name, String id, String password, String birth, String gender) {
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		
		dao.setConnection(conn);
		dao.addUser(name, id, password, birth, gender);
		
		JDBCUtility.commit(conn);
		JDBCUtility.close(conn, null, null);
	}
}