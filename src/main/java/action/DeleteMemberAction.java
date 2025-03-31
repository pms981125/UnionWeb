package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AlertService;
import service.DeleteMemberService;
import service.LogInService;
import vo.ActionForward;
import vo.MemberVO;

public class DeleteMemberAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = null;
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		DeleteMemberService deleteMemberService = DeleteMemberService.getInstance();
		boolean isPasswordCorrect = false;
		
		isPasswordCorrect = deleteMemberService.checkPassword(id, password);
		
		if (isPasswordCorrect) {
			deleteMemberService.deleteMember(id);
			forward = new ActionForward();
			forward.setPath("logIn.jsp");
			
			return forward;
		} else {
			AlertService alertService = AlertService.getInstance();
			String message = "비밀번호가 일치하지 않습니다.";
			
			alertService.alertAndBack(res, message);
			/*
			 * System.out.println("계정 탈퇴 실패 - 비밀번호 불일치"); LogInService logInService =
			 * LogInService.getInstance(); MemberVO user = logInService.getUserById(id);
			 * 
			 * forward = new ActionForward(); forward.setPath("setting.jsp");
			 * req.setAttribute("user", user);
			 */
			
			return forward;
		}
	}
}