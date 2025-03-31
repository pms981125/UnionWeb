package service;

import java.sql.Connection;
import java.util.List;

import dao.DAO;
import db.JDBCUtility;
import vo.MemberVO;

public class UpdateUserService {
	private static UpdateUserService updateUserService = null;
	private UpdateUserService() {}
	
	public static UpdateUserService getInstance() {
		if(updateUserService == null) updateUserService = new UpdateUserService();
		
		return updateUserService;
	}

	public boolean checkPassword(String originalId, String originalPassword) {
		boolean isPasswordCorrect = false;
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		
		dao.setConnection(conn);
		isPasswordCorrect = dao.checkPassword(originalId, originalPassword);
		JDBCUtility.close(conn, null, null);
		
		return isPasswordCorrect;
	}

	public boolean isIdUnique(String id) {
		boolean isIdUnique = false;
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		
		dao.setConnection(conn);
		isIdUnique = dao.isIdUnique(id);
		JDBCUtility.close(conn, null, null);
		
		return isIdUnique;
	}

	public void updateUser(String originalId, String name, String id, String password, String birth, String gender) {
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		MemberVO user = LogInService.getInstance().getUserById(originalId);
		List<String> pcrls = user.getParticipatingChattingRoomNo();
		
		dao.setConnection(conn);
		dao.updateUser(originalId, name, id, password, birth, gender);
		dao.updateContentChattingRoomUserId(originalId, id, pcrls);
		JDBCUtility.commit(conn);
		JDBCUtility.close(conn, null, null);
	}
}