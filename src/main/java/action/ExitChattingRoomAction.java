package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ExitChattingRoomService;
import service.LogInService;
import vo.ActionForward;
import vo.MemberVO;

public class ExitChattingRoomAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = null;
		String id = req.getParameter("id");
		int no = Integer.parseInt(req.getParameter("no"));
		ExitChattingRoomService exitChattingRoomService = ExitChattingRoomService.getInstance();
		MemberVO user = LogInService.getInstance().getUserById(id);
		
		exitChattingRoomService.exitChattingRoom(no, id);
		
		req.setAttribute("user", user);
		// req.setAttribute("hasAttribute", true);
		forward = new ActionForward();
		forward.setPath("getChattingRoomList.do");
		
		return forward;
	}
}