package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AlertService;
import service.LogInService;
import vo.ActionForward;
import vo.MemberVO;

public class LogInAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		String id = req.getParameter("id"); 
		String password = req.getParameter("password"); 
		ActionForward forward = new ActionForward();
		/* System.out.println(id + "32" +password); */
		//boolean isCorrectPassword = false;
		boolean isLogInSuccess = false;
		LogInService logInService = LogInService.getInstance();
		//isCorrectPassword = logInService.verifyPassword(id, password);
		
		isLogInSuccess = logInService.logIn(id, password);
		
		if (isLogInSuccess) {
			MemberVO user = logInService.getUserById(id);
			req.setAttribute("user", user);
			forward.setPath("main.jsp");
		} else {
			AlertService alertService = AlertService.getInstance();
			String message = "로그인 실패 - 아이디 혹은 비밀번호가 잘못되었습니다.";
			
			alertService.alertAndGo(res, message, "logIn.jsp");
		}
		
		return forward;
	}
}