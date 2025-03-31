package service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.DAO;
import db.JDBCUtility;
import vo.MemberVO;

public class GetMemberListService {
	private static GetMemberListService getMemberListService = null;
	private GetMemberListService() {}
	
	public static GetMemberListService getInstance() {
		if(getMemberListService == null) getMemberListService = new GetMemberListService();
		
		return getMemberListService;
	}
	
	public int getListCount(String find, String query) {
		int listCount = 0;
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		
		dao.setConnection(conn);
		listCount = dao.getMemberListCount(find, query);
		JDBCUtility.close(conn, null, null);
		
		return listCount;
	}

	public List<MemberVO> getUserList(int page, int limit, String find, String query) {
		List<MemberVO> userList = new ArrayList<MemberVO>();
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		
		dao.setConnection(conn);
		userList = dao.getUserList(page, limit, find, query);
		JDBCUtility.close(conn, null, null);
		
		return userList;
	}
}