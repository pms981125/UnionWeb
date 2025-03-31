package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.LogInService;
import vo.ActionForward;
import vo.MemberVO;

public class createBoardFormAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = null;
		String id = req.getParameter("id");
		MemberVO user = LogInService.getInstance().getUserById(id);
		
		forward = new ActionForward();
		forward.setPath("board_form.jsp");
		req.setAttribute("user", user);
		
		return forward;
	}
}