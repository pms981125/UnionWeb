package service;

import java.sql.Connection;

import dao.DAO;
import db.JDBCUtility;
import vo.MemberVO;

public class LogInService {
	private static LogInService logInService = null;
	private LogInService () {}
	
	public static LogInService getInstance() {
		if(logInService == null) logInService = new LogInService();
		
		return  logInService;
	}

	/* 패스워드 확인 필요 X?
	 * public boolean verifyPassword(String id, String password) { boolean
	 * isCorrectPassword = false; Connection conn = JDBCUtility.getConnection(); DAO
	 * dao = DAO.getInstance();
	 * 
	 * dao.setConnection(conn);
	 * isCorrectPassword = dao.verifyPassword(id, password);
	 * 
	 * return isCorrectPassword; }
	 */
	
	// 로그인
	public boolean logIn(String id, String password) {
		boolean isLogInSuccess = false;
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		
		dao.setConnection(conn);
		isLogInSuccess = dao.logIn(id, password);
		
		return isLogInSuccess;
	}
	
	public MemberVO getUserById(String id) {
		MemberVO user = new MemberVO();
		Connection conn = JDBCUtility.getConnection(); // 메인 게시물과 일정?
		DAO dao = DAO.getInstance();
		
		dao.setConnection(conn);
		user = dao.getUserById(id);
		JDBCUtility.close(conn, null, null);
		
		return user;
	}
}