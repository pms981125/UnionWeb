package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GetChattingRoomService;
import service.LogInService;
import service.SendMessageService;
import vo.ActionForward;
import vo.ChattingRoomVO;
import vo.ContentChattingRoomVO;
import vo.MemberVO;

public class SendMessageAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = null;
		int no = Integer.parseInt(req.getParameter("chattingRoomNo"));
		String userId = req.getParameter("userId");
		String message = req.getParameter("message");
		String iValue = req.getParameter("i");
		SendMessageService sendMessageService = SendMessageService.getInstance();
		GetChattingRoomService getChattingRoomService = GetChattingRoomService.getInstance();
		MemberVO user = LogInService.getInstance().getUserById(userId);
		
		sendMessageService.sendMessage(no, message, userId);
		
		ChattingRoomVO chattingRoom = getChattingRoomService.getChattingRoomByNo(no);
		ContentChattingRoomVO contentChattingRoom = getChattingRoomService.getContentChattingRoom(no);
		
		/*
		 * req.setAttribute("chattingRoom", chattingRoom);
		 * req.setAttribute("contentChattingRoom", contentChattingRoom);
		 */
		
		req.setAttribute("user", user);
		req.setAttribute("iValue", iValue);
		req.setAttribute("hasAttribute2", true);
		req.setAttribute("roomNo", Integer.toString(no));
		forward = new ActionForward();
		//forward.setPath("/chatting_room.jsp");
		//forward.setPath("/chatting_room2.jsp");
		forward.setPath("getChattingRoomList.do");
		
		return forward;
	}
}