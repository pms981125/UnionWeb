package service;

import java.sql.Connection;

import dao.DAO;
import db.JDBCUtility;
import vo.BoardVO;

public class GetBoardDetailService {
	private static GetBoardDetailService getBoardDetailService = null;
	private GetBoardDetailService() {}
	
	public static GetBoardDetailService getInstance() {
		if(getBoardDetailService == null) getBoardDetailService = new GetBoardDetailService();
		
		return getBoardDetailService;
	}

	public BoardVO getBoard(int no) { // 조회수 증가 X
		BoardVO board = null;
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		
		dao.setConnection(conn);
		board = dao.getBoard(no);
		JDBCUtility.close(conn, null, null);
		
		return board;
	}
	
	public BoardVO getBoardDetail(int no) { // 조회수 증가
		BoardVO board = null;
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		
		dao.setConnection(conn);
		dao.increaseBoardReadCount(no);
		board = dao.getBoard(no);
		JDBCUtility.commit(conn);
		JDBCUtility.close(conn, null, null);
		
		return board;
	}
}