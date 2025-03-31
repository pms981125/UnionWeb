package service;

import java.sql.Connection;

import dao.DAO;
import db.JDBCUtility;
import vo.BoardVO;

public class LikeService {
	private static LikeService likeService = null;
	private LikeService() {}
	
	public static LikeService getInstance() {
		if(likeService == null) likeService = new LikeService();
		
		return likeService;
	}

	public void decreaseLike(int no, String id) {
		BoardVO board = GetBoardDetailService.getInstance().getBoard(no);
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		
		dao.setConnection(conn);
		dao.decreaseLike(no, id, board);
		JDBCUtility.commit(conn);
		JDBCUtility.close(conn, null, null);
	}

	public void increaseLike(int no, String id) {
		BoardVO board = GetBoardDetailService.getInstance().getBoard(no);
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		
		dao.setConnection(conn);
		dao.increaseLike(no, id, board);
		JDBCUtility.commit(conn);
		JDBCUtility.close(conn, null, null);
	}
}