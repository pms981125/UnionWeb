package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AlertService;
import service.CreateChattingRoomService;
import service.LogInService;
import vo.ActionForward;
import vo.MemberVO;

public class CreateChattingRoomAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = null;
		String name = req.getParameter("name");
		String managerId = req.getParameter("managerId");
		String participatingPeople = req.getParameter("invitePeopleId");
		int roomNo = 0; 
		CreateChattingRoomService createChattingRoomService = CreateChattingRoomService.getInstance();

		if (managerId.equals(participatingPeople)) {
			MemberVO user = LogInService.getInstance().getUserById(managerId);
			req.setAttribute("hasAttribute", true);
			req.setAttribute("user", user);
			forward = new ActionForward();
			forward.setPath("getChattingRoomList.do");
			
			return forward;
		}
		
		roomNo = createChattingRoomService.createChattingRoom(name, managerId, participatingPeople);
 		
 		if (roomNo != 0) {
 			MemberVO user = LogInService.getInstance().getUserById(managerId);
 			req.setAttribute("hasAttribute", true);
 			req.setAttribute("user", user);
 			req.setAttribute("roomNo", roomNo);
 			forward = new ActionForward();
 			// forward.setPath("getChattingRoom.do");
 			forward.setPath("getChattingRoomList.do");
		} else {
			MemberVO user = LogInService.getInstance().getUserById(managerId);
			AlertService alertService = AlertService.getInstance();
			
			alertService.alertAndBack(res, "채팅방 생성 실패");
			req.setAttribute("user", user);
			forward = new ActionForward();
			forward.setPath("getChattingRoomList.do");
			System.out.println("채팅방 생성 실패");
		}
		
		return forward;
	}
}