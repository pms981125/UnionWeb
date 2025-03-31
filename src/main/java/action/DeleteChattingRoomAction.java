package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.DeleteChattingRoomService;
import service.LogInService;
import vo.ActionForward;
import vo.MemberVO;

public class DeleteChattingRoomAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = null;
		String id = req.getParameter("id");
		int no = Integer.parseInt(req.getParameter("no"));
		DeleteChattingRoomService deleteChattingRoomService = DeleteChattingRoomService.getInstance();
		MemberVO user = LogInService.getInstance().getUserById(id);
		
		deleteChattingRoomService.deleteChattingRoom(id, no);
		
		req.setAttribute("user", user);
		// req.setAttribute("hasAttribute", true);
		forward = new ActionForward();
		forward.setPath("getChattingRoomList.do"); // hasAttriute 없어도 되는지 확인 왜 안 버그
		
		return forward;
	}
}