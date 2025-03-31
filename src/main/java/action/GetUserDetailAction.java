package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.LogInService;
import vo.ActionForward;
import vo.MemberVO;

public class GetUserDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = null;
		LogInService logInService = LogInService.getInstance();
		String id = req.getParameter("id");
		String targetId = req.getParameter("targetId");
		MemberVO user = logInService.getUserById(id);
		MemberVO targetUser = logInService.getUserById(targetId);
		
		req.setAttribute("user", user);
		req.setAttribute("targetUser", targetUser);
		forward = new ActionForward();
		forward.setPath("member_detail.jsp");
		
		return forward;
	}
}