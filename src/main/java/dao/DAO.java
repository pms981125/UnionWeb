package dao;

import java.awt.Point;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import db.JDBCUtility;
import vo.BoardVO;
import vo.ChattingRoomVO;
import vo.ContentChattingRoomVO;
import vo.MemberVO;
import vo.ReservationVO;

public class DAO {
	private static DAO dao = null;
	private DAO() {}
	
	public static DAO getInstance() {
		if(dao == null) dao = new DAO();
		
		return dao;
	}
	
	Connection conn = null;
	DataSource ds = null;
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	/*
	 * public boolean verifyPassword(String id, String password) { boolean
	 * isCorrectPassword = false; PreparedStatement pstmt = null; ResultSet rs =
	 * null; String sql = "select * from member where id = " + id;
	 * 
	 * try { pstmt = conn.prepareStatement(sql); rs = pstmt.executeQuery();
	 * 
	 * if (rs.next()) { isCorrectPassword =
	 * password.equals(rs.getString("password")); } } catch (Exception e) {
	 * e.printStackTrace(); } finally { JDBCUtility.close(conn, pstmt, rs); }
	 * 
	 * return isCorrectPassword; }
	 */

	public boolean logIn(String id, String password) {
		boolean isLogInSuccess = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member where id like '" + id + "'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			// System.out.println(pstmt);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				isLogInSuccess = password.equals(rs.getString("password"));
			}
		} catch (Exception e) {
			System.out.println("로그인 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(conn, pstmt, rs);
		}
		
		return isLogInSuccess;
	}

	public MemberVO getUserById(String id) {
		MemberVO user = new MemberVO();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member where id like '" + id + "'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				user.setNo(rs.getInt("no"));
				user.setName(rs.getString("name"));
				user.setId(id);
				user.setPassword(rs.getString("password"));
				user.setRegisterDate(rs.getDate("registerDate"));
				
				if (rs.getDate("birth") != null) {
					user.setBirth(rs.getDate("birth"));
					Date birth = rs.getDate("birth");
					int year = birth.getYear();
					Calendar calendar = Calendar.getInstance();
					String calendarYear = Integer.toString(calendar.get(Calendar.YEAR));
					
					if (year > Integer.parseInt(calendarYear.substring(2))) {
						year += 1900;
					}
					
					int age = calendar.get(Calendar.YEAR) - year - 1;
					
					if(calendar.get(Calendar.MONTH) > birth.getMonth()) {
						age++;
					} else if (calendar.get(Calendar.MONTH) == birth.getMonth() && calendar.get(Calendar.DATE) >= birth.getDate()) {
						age++;
					}
					
					user.setAge(age);
				} else {
					user.setAge(0);
				}
				
				if (rs.getString("gender") != null) {
					user.setGender(rs.getString("gender"));
				} else {
					user.setGender("비공개");
				}
				
				String pcrl = rs.getString("participation_chatting_room_list");
				
				if (pcrl != null) {
					String[] pcrls = pcrl.split(",");
					List<String> pcrlStrings = new ArrayList<String>();
					
					for (int i = 0; i < pcrls.length; i++) {
						pcrlStrings.add(pcrls[i]);
					}
					
					user.setParticipatingChattingRoomNo(pcrlStrings);
				}
			}
		} catch (Exception e) {
			System.out.println("Id로 User 조회 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
		
		return user;
	}
	
	public MemberVO getUserByNo(int no) {
		MemberVO user = new MemberVO();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member where no = " + no; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				user.setNo(no);
				user.setName(rs.getString("name"));
				user.setId(rs.getString("id"));
				user.setPassword(rs.getString("password"));
				user.setRegisterDate(rs.getDate("registerDate"));

				if (rs.getDate("birth") != null) {
					user.setBirth(rs.getDate("birth"));
					Date birth = rs.getDate("birth");
					int year = birth.getYear();
					Calendar calendar = Calendar.getInstance();
					String calendarYear = Integer.toString(calendar.get(Calendar.YEAR));
					
					if (year > Integer.parseInt(calendarYear.substring(2))) {
						year += 1900;
					}
					
					int age = calendar.get(Calendar.YEAR) - year - 1;
					
					if(calendar.get(Calendar.MONTH) > birth.getMonth()) {
						age++;
					} else if (calendar.get(Calendar.MONTH) == birth.getMonth() && calendar.get(Calendar.DATE) >= birth.getDate()) {
						age++;
					}
					
					user.setAge(age);
				} else {
					user.setAge(0);
				}
				
				if (rs.getString("gender") != null) {
					user.setGender(rs.getString("gender"));
				} else {
					user.setGender("비공개");
				}
				
				String pcrl = rs.getString("participation_chatting_room_list");
				
				if (pcrl != null) {
					String[] pcrls = pcrl.split(",");
					List<String> pcrlStrings = new ArrayList<String>();
					
					for (int i = 0; i < pcrls.length; i++) {
						pcrlStrings.add(pcrls[i]);
					}
					
					user.setParticipatingChattingRoomNo(pcrlStrings);
				}
			}
		} catch (Exception e) {
			System.out.println("No로 User 조회 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
		
		return user;
	}

	public String createId(int num) {
		String id = "";
		char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
		int count  = 0;
		
		while (count < num) {
			id += alphabet[(int) (Math.random() * alphabet.length)];
			count++;
		}
		
		return id;
	}

	public boolean checkUniqueTableId(String tableName, String id) {
		boolean isUnique = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from " + tableName + " where id like '" + id + "'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				isUnique = false;
			}
		} catch (Exception e) {
			System.out.println("테이블 Id 유니크 확인 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
		
		return isUnique;
	}

	public boolean createChattingRoom(String id, String name, String managerId, String participatingPeople) {
		boolean isSuccess = false;
		PreparedStatement pstmt = null;
		int n = 0;
		String sql = "insert into chatting_room(id, name, manager, participating_people) values (?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, managerId);
			pstmt.setString(4, managerId + "," + participatingPeople);
			// System.out.println(pstmt);
			n = pstmt.executeUpdate();
			
			if (n > 0) {
				isSuccess = true;
			}
		} catch (Exception e) {
			System.out.println("채팅방 생성 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, null);
		}
		
		return isSuccess;
	}

	public int getRoomNo(String id) {
		int roomNo = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from chatting_room where id like '" + id + "'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				roomNo = rs.getInt("no");
			}
		} catch (Exception e) {
			System.out.println("채팅방 번호 조회 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
		
		return roomNo;
	}

	public boolean createContentChattingRoom(int roomNo) {
		boolean isSuccess = false;
		PreparedStatement pstmt = null;
		String sql = "create table content_chatting_room"+ roomNo +" (\r\n"
				+ "     no 			int						not null\r\n"
				+ "   , message	VARCHAR(255)	not null\r\n"
				+ "   , writerid	VARCHAR(20)	not null\r\n"
				//+ "   , time			date					not null\r\n"
				+ ");\r\n";
		int n = -1;
		
		try {
			pstmt = conn.prepareStatement(sql);
			n = pstmt.executeUpdate();
			
			if (n == 0) {
				isSuccess = true;
			}
		} catch (Exception e) {
			System.out.println("채팅방 내용 테이블 생성 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, null);
		}
		
		return isSuccess;
	}

	public boolean addUserPCRL(String managerId, String participatingPeople, int roomNo) {
		boolean isSuccess = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// String sql = "update member set participation_chatting_room_list = " + roomNo + " where id like ?";
		String sql = "update member set participation_chatting_room_list = ? where id like ?";
		List<String> participatingPeoples = new ArrayList<String>();
		String[] str = null;
		MemberVO user = getUserById(managerId);
		int n = 0;
		
		if (participatingPeople.contains(",")) {
			str = participatingPeople.split(",");
			
			for (int i = 0; i < str.length; i++) {
				participatingPeoples.add(str[i]);
			}
			
			participatingPeoples.add(managerId);
		} else {
			participatingPeoples.add(participatingPeople);
			participatingPeoples.add(managerId);
		}
		
		try {
			for (int i = 0; i < participatingPeoples.size(); i++) {
				user = getUserById(participatingPeoples.get(i));
				List<String> userPCRL = user.getParticipatingChattingRoomNo();
				String pcrl = "";
				
				if (userPCRL != null) {
					for (int j = 0; j < userPCRL.size(); j++) {
						pcrl += userPCRL.get(j);
						pcrl += ",";
					}
				}
				
				pcrl += Integer.toString(roomNo);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, pcrl);
				pstmt.setString(2, user.getId());
				n += pstmt.executeUpdate();
			}
			
			if (n == participatingPeoples.size()) {
				isSuccess = true;
			}
		} catch (Exception e) {
			System.out.println("user pcrl 추가 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
		
		return isSuccess;
	}

	public ChattingRoomVO getChattingRoomByNo(int no) {
		ChattingRoomVO chattingRoom = new ChattingRoomVO();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from chatting_room where no = " + no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				chattingRoom.setNo(no);
				chattingRoom.setId(rs.getString("id"));
				chattingRoom.setName(rs.getString("name"));
				chattingRoom.setManager(rs.getString("manager"));
				String p = rs.getString("participating_people");
				
				if (p != null) {
					String[] ps = p.split(",");
					List<String> pStrings = new ArrayList<String>();
					
					for (int i = 0; i < ps.length; i++) {
						pStrings.add(ps[i]);
					}
					
					chattingRoom.setParticipatingPeople(pStrings);
				}
			}
		} catch (Exception e) {
			System.out.println("no로 채팅방 조회 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
		
		return chattingRoom;
	}

	public ContentChattingRoomVO getContentChattingRoom(int no) {
		ContentChattingRoomVO contentChattingRoom = new ContentChattingRoomVO();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from content_chatting_room"+no;
		List<Integer> n = new ArrayList<Integer>();
		List<String> s = new ArrayList<String>();
		List<String> s2 = new ArrayList<String>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				n.add(rs.getInt("no"));
				s.add(rs.getString("message"));
				s2.add(rs.getString("writerId"));
			}
			
			contentChattingRoom.setNo(n);
			contentChattingRoom.setMessage(s);
			contentChattingRoom.setWriterId(s2);
		} catch (Exception e) {
			System.out.println("채팅방 내용 조회 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
		
		return contentChattingRoom;
	}

	public void sendMessage(int no, String message, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int newNo = 0;
		String sqlSelect = "select max(no) from content_chatting_room"+no;
		String sqlInsert = "";
		
		try {
			pstmt = conn.prepareStatement(sqlSelect);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				newNo = rs.getInt(1) + 1;
				sqlInsert = "insert into content_chatting_room" + no + "(no, message, writerId) values (" + newNo + ",?,?)";
				pstmt = conn.prepareStatement(sqlInsert);
				pstmt.setString(1, message);
				pstmt.setString(2, userId);
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println("메시지 전송 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
	}

	public void addUser(String name, String id, String password, String birth, String gender) {
		PreparedStatement pstmt = null;
		String sql = "insert into member(name, id, password, registerDate, birth, gender) values (?,?,?,now(),?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, password);
			
			if (!birth.equals("")) {
				pstmt.setString(4, birth);
			} else {
				pstmt.setString(4, null);
			}
			
			if (!gender.equals("null")) {
				pstmt.setString(5, gender);
			} else {
				pstmt.setString(5, null);
			}
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("멤버 추가 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, null);
		}
	}

	public boolean checkPassword(String id, String password) {
		boolean isPasswordCorrect = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member where id like ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				isPasswordCorrect = password.equals(rs.getString("password"));
			}
		} catch (Exception e) {
			System.out.println("비밀번호 확인 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
		
		return isPasswordCorrect;
	}

	public boolean isIdUnique(String id) {
		boolean isIdUnique = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member where id like ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				isIdUnique = false;
			}
		} catch (Exception e) {
			System.out.println("Id 유니크 확인 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
		
		return isIdUnique;
	}

	public void updateUser(String originalId, String name, String id, String password, String birth, String gender) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "update member set name = ?, id = ?, password = ?, birth = ?, gender = ? where id like ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, password);
			pstmt.setString(4, birth);
			pstmt.setString(5, gender);
			pstmt.setString(6, originalId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("유저 정보 수정 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
	}

	public void updateContentChattingRoomUserId(String originalId, String id, List<String> pcrls) {
		PreparedStatement pstmt = null;
		String sql = "";
		
		try {
			for (int i = 0; i < pcrls.size(); i++) {
				int n = Integer.parseInt(pcrls.get(i));
				sql = "update content_chatting_room" + n + " set writerId = ? where writerId = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, originalId);
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println("채팅방 내용 작성자 ID 수정 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, null);
		}
	}

	/* 기존 getBoardList
	 * public List<BoardVO> getBoardList() { List<BoardVO> boardList = new
	 * ArrayList<BoardVO>(); PreparedStatement pstmt = null; ResultSet rs = null;
	 * String sql = "select * from board order by no desc";
	 * 
	 * try { pstmt = conn.prepareStatement(sql); rs = pstmt.executeQuery();
	 * 
	 * while (rs.next()) { BoardVO board = new BoardVO(); // 선언 및 초기화 위치 주의
	 * 
	 * board.setNo(rs.getInt("no")); board.setTitle(rs.getString("title"));
	 * board.setContents(rs.getString("contents"));
	 * 
	 * if (rs.getString("imageSrc") != null) {
	 * board.setImageSrc(rs.getString("imageSrc")); }
	 * 
	 * board.setReadCount(rs.getInt("readCount"));
	 * board.setLikeCount(rs.getInt("likeCount"));
	 * 
	 * if (rs.getString("likeID") != null) { List<String> list = new
	 * ArrayList<String>(); String[] likeID = rs.getString("likeID").split(",");
	 * 
	 * for (int i = 0; i < likeID.length; i++) { list.add(likeID[i]); }
	 * 
	 * board.setLikeID(list); }
	 * 
	 * board.setWriteDate(rs.getDate("writeDate"));
	 * board.setWriterName(rs.getString("writerName"));
	 * board.setWriterId(rs.getString("writerId")); boardList.add(board); } } catch
	 * (Exception e) { System.out.println("게시물 목록 조회 실패"); e.printStackTrace(); }
	 * finally { JDBCUtility.close(null, pstmt, rs); }
	 * 
	 * return boardList; }
	 */
	
	// 검색기능 추가 getBoardList
	public List<BoardVO> getBoardList(int page, int limit, String find, String query) {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		PreparedStatement pstmt = null; ResultSet rs = null;
		String sql = "select * from board where " + find + " like ? order by no desc limit ?, " + limit;
		int startRow = (page - 1) * limit;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + query + "%");
			pstmt.setInt(2, startRow);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				BoardVO board = new BoardVO(); // 선언 및 초기화 위치 주의
		
				board.setNo(rs.getInt("no")); board.setTitle(rs.getString("title"));
				board.setContents(rs.getString("contents"));
		
				if (rs.getString("imageSrc") != null) {
					board.setImageSrc(rs.getString("imageSrc")); 
				}
		
				board.setReadCount(rs.getInt("readCount"));
				board.setLikeCount(rs.getInt("likeCount"));
		 
				if (rs.getString("likeID") != null) {
					List<String> list = new ArrayList<String>();
					String[] likeID = rs.getString("likeID").split(",");
		 
					for (int i = 0; i < likeID.length; i++) { 
						list.add(likeID[i]);
					}
					
					board.setLikeID(list);
				}
		
				board.setWriteDate(rs.getDate("writeDate"));
				board.setWriterName(rs.getString("writerName"));
				board.setWriterId(rs.getString("writerId")); boardList.add(board); 
			}
		} catch (Exception e) {
			System.out.println("게시물 목록 조회 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
		
		return boardList; 
	}
	
	public int getListCount(String find, String query) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from board where " + find + " like ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + query + "%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) listCount = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("게시글 갯수 조회 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
		
		return listCount;
	}

	public void createBoard(String title, String contents, String imageSrc, String writerName, String writerId) {
		PreparedStatement pstmt = null;
		String sql = "insert into board(title, contents, imageSrc, writeDate, writerName, writerId) values (?,?,?,now(),?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setString(3, imageSrc);
			pstmt.setString(4, writerName);
			pstmt.setString(5, writerId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시글 추가 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, null);
		}
	}

	public BoardVO getBoard(int no) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from board where no = " + no;
		BoardVO board = new BoardVO();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				board.setNo(no);
				board.setTitle(rs.getString("title"));
				board.setContents(rs.getString("contents"));
				board.setImageSrc(rs.getString("imageSrc"));
				board.setReadCount(rs.getInt("readCount"));
				board.setLikeCount(rs.getInt("likeCount"));
				
				if (rs.getString("likeID") != null) {
					List<String> list = new ArrayList<String>();
					String[] likeID = rs.getString("likeID").split(",");
					
					for (int i = 0; i < likeID.length; i++) {
						list.add(likeID[i]);
					}
					
					board.setLikeID(list);
				}
				
				board.setWriteDate(rs.getDate("writeDate"));
				board.setWriterName(rs.getString("writerName"));
				board.setWriterId(rs.getString("writerId"));
			}
		} catch (Exception e) {
			System.out.println("게시글 조회 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
		
		return board;
	}
	
	public void increaseBoardReadCount(int no) {
		PreparedStatement pstmt = null;
		String sql = "update board set readCount = readCount + 1 where no = " + no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("조회수 증가 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, null);
		}
	}
	
	public void increaseLike(int no, String id, BoardVO board) {
		String str = "";
		
		if (board.getLikeCount() > 0) {
			List<String> list = board.getLikeID();
			str = String.join(",", list) + "," + id;
		} else {
			str = id;
		}
		
		PreparedStatement pstmt = null;
		String sql = "update board set likeCount = likeCount + 1, likeID = ? where no = " + no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, str);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("좋아요 증가 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, null);
		}
	}

	public void decreaseLike(int no, String id, BoardVO board) {
		List<String> list = board.getLikeID();
		
		list.remove(id);
		
		String string = String.join(",", list);
		
		PreparedStatement pstmt = null;
		String sql = "update board set likeCount = likeCount - 1, likeID = ? where no = " + no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, string);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("좋아요 감소 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, null);
		}
	}

	public void deleteBoard(int no) {
		PreparedStatement pstmt = null;
		String sql = "delete from board where no = " + no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 삭제 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, null);
		}
	}

	public void editBoard(int no, String title, String contents, String imageSrc) {
		PreparedStatement pstmt = null;
		String sql = "update board set title = ?, contents = ?, imageSrc = ? where no = " + no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setString(3, imageSrc);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시글 수정 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, null);
		}
	}

	public void updateUserPcrl(int no) {
		ChattingRoomVO chattingRoom = getChattingRoomByNo(no);
		List<String> participatingPeople = chattingRoom.getParticipatingPeople();
		List<MemberVO> users = new ArrayList<MemberVO>();
		
		for (int i = 0; i < participatingPeople.size(); i++) {
			users.add(getUserById(participatingPeople.get(i)));
		}
		
		List<List<String>> participatingChattingRoomNo = new ArrayList<List<String>>(); 

		for (int i = 0; i < users.size(); i++) {
			participatingChattingRoomNo.add(users.get(i).getParticipatingChattingRoomNo());
		}
		
		for (int i = 0; i < participatingChattingRoomNo.size(); i++) {
			for (int j = 0; j < participatingChattingRoomNo.get(i).size(); j++) {
				if (participatingChattingRoomNo.get(i).get(j).equals(Integer.valueOf(no).toString())) {
					participatingChattingRoomNo.get(i).remove(j);
					continue;
				}
				
			}
		}
		
		List<String> str = new ArrayList<String>();
		
		for (int i = 0; i < participatingChattingRoomNo.size(); i++) {
			str.add(String.join(",", participatingChattingRoomNo.get(i)));
		}
		
		for (int i = 0; i < str.size(); i++) {
			String str2 = "";
			
			if (str.get(i).indexOf(Integer.valueOf(no).toString()) + 1 < str.get(i).length()) {
				str2 = str.get(i).replace(no + ",", "");
			} else {
				str2 = str.get(i).replace("," + no, "");
			}
			
			str.set(i, str2);
		}
		
		PreparedStatement pstmt = null;
		String sql = "update member set participation_chatting_room_list = ? where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			for (int i = 0; i < str.size(); i++) {
				if (str.get(i).equals("")) {
					str.set(i, null);
				}
				
				pstmt.setString(1, str.get(i));
				pstmt.setString(2, participatingPeople.get(i));
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println("채팅방 삭제 user pcrl 업데이트 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, null);
		}
	}

	public void deleteContentChattingRoom(int no) {
		PreparedStatement pstmt = null;
		String sql = "drop table content_chatting_room" + no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("채팅방 내용 테이블 drop 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, null);
		}
	}

	public void deleteChattingRoom(int no) {
		PreparedStatement pstmt = null;
		String sql = "delete from chatting_room where no = " + no;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("채팅방 삭제 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, null);
		}
	}

	public void updateUserPcrlByExit(int no, String id) {
		MemberVO user = getUserById(id);
		List<String> pcrl = user.getParticipatingChattingRoomNo();
		
		for (int i = 0; i < pcrl.size(); i++) {
			if (pcrl.get(i).equals(Integer.toString(no))) {
				pcrl.remove(i);
			}
		}
		
		String pcrlString = String.join(",", pcrl);
		
		/*
		 * String pcrlString = String.join(",", pcrl);
		 * 
		 * if (pcrlString.indexOf(Integer.valueOf(no).toString()) + 1 <
		 * pcrlString.length()) { pcrlString = pcrlString.replace(no + ",", ""); } else
		 * { pcrlString = pcrlString.replace("," + no, "");}
		 */

		PreparedStatement pstmt = null;
		String sql = "update member set participation_chatting_room_list = ? where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pcrlString);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("채팅방 나가기 user pcrl update 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, null);
		}
	}
	
	public void exitChattingRoom(int no, String id) {
		ChattingRoomVO chattingRoom = getChattingRoomByNo(no);
		List<String> list = chattingRoom.getParticipatingPeople();
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(id)) {
				list.remove(i);
			}
		}
		
		String str = String.join(",", list);
		PreparedStatement pstmt = null;
		String sql = "update chatting_room set participating_people = ? where no = " + no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, str);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("채팅방 나가기 유저 ID 삭제 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, null);
		}
	}

	/*
	 * public List<MemberVO> getUserList(int page, int limit, String find, String
	 * query) { List<MemberVO> userList = new ArrayList<MemberVO>();
	 * PreparedStatement pstmt = null; ResultSet rs = null; String sql =
	 * "select * from member order by no desc"; // 최근 생성 순
	 * 
	 * try { pstmt = conn.prepareStatement(sql); rs = pstmt.executeQuery();
	 * 
	 * while (rs.next()) { MemberVO user = new MemberVO();
	 * user.setNo(rs.getInt("no")); user.setName(rs.getString("name"));
	 * user.setId(rs.getString("id"));
	 * user.setRegisterDate(rs.getDate("registerDate"));
	 * 
	 * if (rs.getDate("birth") != null) { Date birth = rs.getDate("birth"); int year
	 * = birth.getYear(); Calendar calendar = Calendar.getInstance(); String
	 * calendarYear = Integer.toString(calendar.get(Calendar.YEAR));
	 * 
	 * if (year > Integer.parseInt(calendarYear.substring(2))) { year += 1900; }
	 * 
	 * int age = calendar.get(Calendar.YEAR) - year - 1;
	 * 
	 * if(calendar.get(Calendar.MONTH) > birth.getMonth()) { age++; } else if
	 * (calendar.get(Calendar.MONTH) == birth.getMonth() &&
	 * calendar.get(Calendar.DATE) >= birth.getDate()) { age++; }
	 * 
	 * user.setAge(age); } else { user.setAge(0); }
	 * 
	 * if (rs.getString("gender") == null) { user.setGender("비공개"); } else {
	 * user.setGender(rs.getString("gender")); }
	 * 
	 * userList.add(user); } } catch (Exception e) {
	 * System.out.println("유저목록 조회 실패"); e.printStackTrace(); } finally {
	 * JDBCUtility.close(null, pstmt, rs); }
	 * 
	 * return userList; } }
	 */
	
	public List<MemberVO> getUserList(int page, int limit, String find, String query) {
		List<MemberVO> userList = new ArrayList<MemberVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member where " + find + " like ? order by no desc limit ?," + limit; // 최근 생성 순
		int startRow = (page - 1) * limit;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + query + "%");
			pstmt.setInt(2, startRow);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MemberVO user = new MemberVO();
				user.setNo(rs.getInt("no"));
				user.setName(rs.getString("name"));
				user.setId(rs.getString("id"));
				user.setRegisterDate(rs.getDate("registerDate"));
				
				if (rs.getDate("birth") != null) {
					user.setBirth(rs.getDate("birth"));
					Date birth = rs.getDate("birth");
					int year = birth.getYear();
					Calendar calendar = Calendar.getInstance();
					String calendarYear = Integer.toString(calendar.get(Calendar.YEAR));
					
					if (year > Integer.parseInt(calendarYear.substring(2))) {
						year += 1900;
					}
					
					int age = calendar.get(Calendar.YEAR) - year - 1;
					
					if(calendar.get(Calendar.MONTH) > birth.getMonth()) {
						age++;
					} else if (calendar.get(Calendar.MONTH) == birth.getMonth() && calendar.get(Calendar.DATE) >= birth.getDate()) {
						age++;
					}
					
					user.setAge(age);
				} else {
					user.setAge(0);
				}
				
				if (rs.getString("gender") == null) {
					user.setGender("비공개");
				} else {
					user.setGender(rs.getString("gender"));
				}
				
				userList.add(user);
			}
		} catch (Exception e) {
			System.out.println("유저목록 조회 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
		
		return userList;
	}

	public int getMemberListCount(String find, String query) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from member where " + find + " like ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + query + "%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) listCount = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("회원 수 조회 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
		
		return listCount;
	}

	public int isExistRoom(String name, String participatingPeople) {
		int roomNo = 0;
		String[] strings = participatingPeople.split(",");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from chatting_room where name like ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String p = rs.getString("participating_people");
				String[] ps = p.split(",");
				List<String> pStrings = new ArrayList<String>();
					
				for (int i = 0; i < ps.length; i++) {
					pStrings.add(ps[i]);
				}
				
				for (int i = 0; i < strings.length; i++) {
					pStrings.remove(strings[i]);
				}
				
				if (pStrings.size() > 0) {
					roomNo = rs.getInt("no");
				}
			}
		} catch (Exception e) {
			System.out.println("채팅방 존재여부 확인 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
		
		return roomNo;
	}

	public void deleteMember(String id) {
		PreparedStatement pstmt = null;
		String sql = "delete from member where id like ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("유저 삭제 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, null);
		}
	}

	public List<Integer> getChattingRoomNoList(String id) {
		List<Integer> chattingRoomNo = new ArrayList<Integer>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from chatting_room where manager = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				chattingRoomNo.add(rs.getInt("no"));
			}
		} catch (Exception e) {
			System.out.println("유저가 매니저로 있는 채팅방 번호 조회 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
		
		return chattingRoomNo;
	}

	public List<Integer> getParticipatingChattingRoomNo(String id) {
		List<ChattingRoomVO> chattingRoomList = new ArrayList<ChattingRoomVO>(); 
		List<Integer> participatingChattingRoomNo = new ArrayList<Integer>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from chatting_room";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				chattingRoomList.add(getChattingRoomByNo(rs.getInt("no")));
			}
		} catch (Exception e) {
			System.out.println("채팅룸 목록 조회 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
		
		for (int i = 0; i < chattingRoomList.size(); i++) {
			if (chattingRoomList.get(i).getParticipatingPeople().contains(id)) {
				participatingChattingRoomNo.add(chattingRoomList.get(i).getNo());
			}
		}
		
		return participatingChattingRoomNo;
	}

	public void deleteMessageWithdrawalMember(Integer no, String id) {
		PreparedStatement pstmt = null;
		String sql = "delete from content_chatting_room" + no + " where writerId = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("채팅방 내용 테이블에서 탈퇴한 유저 메세지 삭제 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, null);
		}
	}

	public void updateChattingRoomParticipatingPeople(Integer no, String id) {
		ChattingRoomVO chattingRoom = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from chatting_room where no = " + no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				chattingRoom = getChattingRoomByNo(rs.getInt("no"));
			}
		} catch (Exception e) {
			System.out.println("채팅룸 목록 조회 실패");
			e.printStackTrace();
		}

		chattingRoom.getParticipatingPeople().remove(id);
		String string = String.join(",", chattingRoom.getParticipatingPeople());
		
		sql = "update chatting_room set participating_people = ? where no = " + no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, string);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("계정 탈퇴 - 채팅방 participating_people 업데이트 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
	}

	public int getContentCount(int no) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(no) from content_chatting_room" + no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("채팅방 메시지 갯수 조회 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
		
		return count;
	}

	public List<Integer> getLikedBoardNoList(String id) {
		List<Integer> likedBoardNoList = new ArrayList<Integer>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from board where likeCount > 0";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BoardVO board = getBoard(rs.getInt("no"));
				
				if (board.getLikeID().contains(id)) {
					likedBoardNoList.add(rs.getInt("no"));
				}
			}
		} catch (Exception e) {
			System.out.println("좋아요 수가 0보다 큰 게시물 목록 조회 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
		
		return likedBoardNoList;
	}

	public void makeReservation(String name, String id, String reservationPersonName, int reservationPeopleNum,
			LocalDateTime reservationDate, String reservationLocationName, Point reservationLocation) {
		PreparedStatement pstmt = null;
		String sql = "insert into reservation(name, reservation_person_id, reservation_person_name, reservation_people_num, reservation_date, reservation_location_name, reservation_location)"
				+ " values (?,?,?,?,?,?,?)";
		reservationLocation.setLocation(makeRandomLocation()); // 더미데이터
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, reservationPersonName);
			pstmt.setInt(4, reservationPeopleNum);
			pstmt.setTimestamp(5, Timestamp.valueOf(reservationDate));
			pstmt.setString(6, reservationLocationName);
			// pstmt.setObject(7, reservationLocation);
			pstmt.setObject(7, null);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예약 일정 추가 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, null);
		}
	}

	private Point makeRandomLocation() {
		Point point = new Point();
		double latitude = (Math.random() * 3) + 35;// 35 ~ 38
		double longitude = (Math.random() * 3.5) + 123;// 126 ~ 129.5
		
		point.setLocation(latitude, longitude);
		
		return point;
	}
	
	public ReservationVO getReservation(int no) {
		ReservationVO reservation = new ReservationVO();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from reservation where no = " + no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				reservation.setNo(no);
				reservation.setName(rs.getString("name"));
				reservation.setReservationPersonId(rs.getString("reservation_person_id"));
				reservation.setReservationPersonName(rs.getString("reservation_person_name"));
				reservation.setReservationPeopleNum(rs.getInt("reservation_people_num"));
				reservation.setReservationDate(rs.getTimestamp("reservation_date").toLocalDateTime());
				reservation.setReservationLocationName(rs.getString("reservation_location_name"));
				
				if (rs.getObject("reservation_location") != null) {
					reservation.setReservationLocation((Point) rs.getObject("reservation_location"));
				}
			}
		} catch (Exception e) {
			System.out.println("예약 일정 조회 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
		
		return reservation;
	}

	public List<ReservationVO> getReservationList(String id, LocalDateTime now) {
		List<ReservationVO> reservationList = new ArrayList<ReservationVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from reservation where reservation_person_id like ? and reservation_date > ? order by reservation_date";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setTimestamp(2, Timestamp.valueOf(now));
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				reservationList.add(getReservation(rs.getInt("no")));
			}
		} catch (Exception e) {
			System.out.println(id + "의 예약 일정 목록 조회 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
		
		return reservationList;
	}

	public void modifyReservation(int no, String name, int reservationPeopleNum, LocalDateTime reservationDate,
			String reservationLocationName, Point reservationLocation) {
		PreparedStatement pstmt = null;
		String sql = "update reservation set name = ?, reservation_people_num = ?, reservation_date = ?, reservation_location_name = ?, reservation_location = ? where no = " + no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, reservationPeopleNum);
			pstmt.setTimestamp(3, Timestamp.valueOf(reservationDate));
			pstmt.setString(4, reservationLocationName);
			// pstmt.setObject(5, reservationLocation);
			pstmt.setObject(5, null);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예약 일정 변경 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, null);
		}
	}

	public void deleteReservation(int no) {
		PreparedStatement pstmt = null;
		String sql = "delete from reservation where no = " + no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("예약 일정 삭제 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, null);
		}
	}

	public List<Integer> getReservationListOfDeleteMember(String id) {
		List<Integer> reservationList = new ArrayList<Integer>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from reservation where reservation_person_id like ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				reservationList.add(rs.getInt("no"));
			}
		} catch (Exception e) {
			System.out.println("삭제할 유저의 예약 일정 번호 목록 조회 실패");
			e.printStackTrace();
		} finally {
			JDBCUtility.close(null, pstmt, rs);
		}
		
		return reservationList;
	}
}