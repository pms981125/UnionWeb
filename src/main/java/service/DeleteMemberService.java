package service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.DAO;
import db.JDBCUtility;
import vo.BoardVO;

public class DeleteMemberService {
	private static DeleteMemberService deleteMemberService = null;
	private DeleteMemberService() {}
	
	public static DeleteMemberService getInstance() {
		if(deleteMemberService == null) deleteMemberService = new DeleteMemberService();
		
		return deleteMemberService;
	}
	
	public boolean checkPassword(String id, String password) {
		boolean isPasswordCorrect = false;
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		
		dao.setConnection(conn);
		isPasswordCorrect = dao.checkPassword(id, password);
		JDBCUtility.close(conn, null, null);
		
		return isPasswordCorrect;
	}

	public void deleteMember(String id) {
		Connection conn = JDBCUtility.getConnection();
		DAO dao = DAO.getInstance();
		List<Integer> chattingRoomNo = new ArrayList<Integer>();
		
		dao.setConnection(conn);
		// 유저가 매니저인 채팅방 삭제
		chattingRoomNo = dao.getChattingRoomNoList(id); // 유저가 매니저인 채팅방들 번호 얻기
		
		for (int i = 0; i < chattingRoomNo.size(); i++) {
			dao.updateUserPcrl(chattingRoomNo.get(i)); // 채팅방 삭제시 해당 채팅방에 참여하던 유저들의 pcrl 업데이트
		}
		
		for (int i = 0; i < chattingRoomNo.size(); i++) {
			dao.deleteContentChattingRoom(chattingRoomNo.get(i)); // 유저가 매니저인 채팅방의 내용 테이블 드랍
		}
		
		for (int i = 0; i < chattingRoomNo.size(); i++) {
			dao.deleteChattingRoom(chattingRoomNo.get(i)); // 유저가 매니저인 채팅방 삭제
		}
		
		// 유저가 참가하고 있는 채팅방에서 메시지 삭제
		List<Integer> participatingChattingRoomNo = new ArrayList<Integer>();
		participatingChattingRoomNo = dao.getParticipatingChattingRoomNo(id); // 유저가 참가한 채팅방 번호 얻기
		
		for (int i = 0; i < participatingChattingRoomNo.size(); i++) {
			dao.deleteMessageWithdrawalMember(participatingChattingRoomNo.get(i), id); // 유저가 참여한 채팅방 내용 테이블에서 유저의 메시지 삭제
		}
		
		for (int i = 0; i < participatingChattingRoomNo.size(); i++) {
			dao.updateChattingRoomParticipatingPeople(participatingChattingRoomNo.get(i), id); // 유저가 참여한 채팅방에서 유저 id 제거
		}
		
		// 유저가 한 좋아요 취소
		// 유저가 좋아요 누른 게시물 번호 리스트 얻기
		List<Integer> likedBoardNoList = new ArrayList<Integer>();
		likedBoardNoList = dao.getLikedBoardNoList(id);
		
		// 해당 번호와 일치하는 게시물들 decreaseLike 호출
		for (int i = 0; i < likedBoardNoList.size(); i++) {
			GetBoardDetailService getBoardDetailService = GetBoardDetailService.getInstance();
			BoardVO board = getBoardDetailService.getBoard(likedBoardNoList.get(i));
			conn = JDBCUtility.getConnection();

			dao.setConnection(conn);
			dao.decreaseLike(likedBoardNoList.get(i), id, board);
		}
		
		// 유저의 예약 일정 삭제
		// 유저가 생성한 예약 일정 번호 리스트 얻기
		List<Integer> reservationList = new ArrayList<Integer>();
		reservationList = dao.getReservationListOfDeleteMember(id);
		
		// 해당 번호와 일치하는 예약 일정 삭제
		for (int i = 0; i < reservationList.size(); i++) {
			dao.deleteReservation(reservationList.get(i));
		}
		
		// 유저 삭제
		dao.deleteMember(id);
		JDBCUtility.commit(conn);
		JDBCUtility.close(conn, null, null);
	}
}