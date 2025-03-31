package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.LogInService;
import vo.ActionForward;
import vo.MemberVO;

public class GoSettingPageAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = null;
		String id = req.getParameter("id");
		MemberVO user = LogInService.getInstance().getUserById(id);
		
		req.setAttribute("user", user);
		forward = new ActionForward();
		forward.setPath("setting.jsp");
		
		return forward;
	}
}