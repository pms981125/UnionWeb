package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AlertService;
import service.LogInService;
import service.UpdateUserService;
import vo.ActionForward;
import vo.MemberVO;

public class UpdateUserAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = null;
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String originalId = req.getParameter("originalId");
		String originalPassword = req.getParameter("originalPassword");
		String birth = req.getParameter("birth");
		String gender = req.getParameter("gender");
		UpdateUserService updateUserService = UpdateUserService.getInstance();
		LogInService logInService = LogInService.getInstance();
		MemberVO user = new MemberVO();
		boolean isPasswordCorrect = false;
		boolean isIdUnique = false;
		
		isPasswordCorrect = updateUserService.checkPassword(originalId, originalPassword);
		
		if (isPasswordCorrect) {
			isIdUnique = updateUserService.isIdUnique(id);
			
			if (isIdUnique) {
				updateUserService.updateUser(originalId, name, id, password, birth, gender);
				user = logInService.getUserById(id);
			} else {
				AlertService alertService = AlertService.getInstance();
				String message = "이미 존재하는 ID입니다.";
				
				alertService.alertAndBack(res, message);
				
				/* user = logInService.getUserById(originalId); */
			}
		} else {
			AlertService alertService = AlertService.getInstance();
			String message = "비밀번호가 일치하지 않습니다.";
			
			alertService.alertAndBack(res, message);
			/* user = logInService.getUserById(originalId); */
		}
		
		req.setAttribute("user", user);
		forward = new ActionForward();
		forward.setPath("main.jsp");
		
		return forward;
	}
}