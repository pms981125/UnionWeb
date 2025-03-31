package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GetChattingRoomService;
import service.LogInService;
import vo.ActionForward;
import vo.ChattingRoomVO;
import vo.ContentChattingRoomVO;
import vo.MemberVO;

public class GetChattingRoomAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = null;
		int roomNo = 0;
		GetChattingRoomService getChattingRoomService = GetChattingRoomService.getInstance();
		ChattingRoomVO chattingRoom = new ChattingRoomVO();
		ContentChattingRoomVO contentChattingRoom = new ContentChattingRoomVO();
		MemberVO user = null; 
		boolean hasAttribute = false;

		if (req.getAttribute("hasAttribute") != null) {
			hasAttribute = (boolean) req.getAttribute("hasAttribute");
		}
		
		if (hasAttribute) {
			user = (MemberVO) req.getAttribute("user");
			roomNo = (int) req.getAttribute("roomNo");
			chattingRoom = getChattingRoomService.getChattingRoomByNo(roomNo);
			contentChattingRoom = getChattingRoomService.getContentChattingRoom(roomNo);
		} else {
			String userId = req.getParameter("userId");
			user = LogInService.getInstance().getUserById(userId);
			roomNo = Integer.parseInt(req.getParameter("roomNo"));
			chattingRoom = getChattingRoomService.getChattingRoomByNo(roomNo);
			contentChattingRoom = getChattingRoomService.getContentChattingRoom(roomNo);
		}
		
		req.setAttribute("chattingRoom", chattingRoom);
		req.setAttribute("contentChattingRoom", contentChattingRoom);
		req.setAttribute("hasAttribute", true);
		req.setAttribute("user", user);
		forward = new ActionForward();
		// forward.setPath("/chatting_room.jsp");
		// forward.setPath("/chatting_room2.jsp");
		forward.setPath("getChattingRoomList.do");
		
		return forward;
	}
}