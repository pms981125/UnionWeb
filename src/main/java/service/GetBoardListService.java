package service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.DAO;
import db.JDBCUtility;
import vo.BoardVO;

public class GetBoardListService {
	private static GetBoardListService getBoardListService = null;
	private GetBoardListService() {}
	
	public static GetBoardListService getInstance() {
		if(getBoardListService == null) getBoardListService = new GetBoardListService();
		
		return getBoardListService;
	}
	
	public int getListCount(String find, String query) {
		int listCount = 0;
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		
		dao.setConnection(conn);
		listCount = dao.getListCount(find, query);
		JDBCUtility.close(conn, null, null);
		
		return listCount;
	}

	public List<BoardVO> getBoardList(int page, int limit, String find, String query) {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		
		dao.setConnection(conn);
		boardList = dao.getBoardList(page, limit, find, query);
		JDBCUtility.close(conn, null, null);
		
		return boardList;
	}
}