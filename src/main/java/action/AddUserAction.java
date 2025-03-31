package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AddUserService;
import service.AlertService;
import service.UpdateUserService;
import vo.ActionForward;

public class AddUserAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = null;
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String birth = req.getParameter("birth");
		String gender = req.getParameter("gender");
		AddUserService addUserService = AddUserService.getInstance();
		UpdateUserService updateUserService = UpdateUserService.getInstance();
		boolean isIdUnique = false;
		
		isIdUnique = updateUserService.isIdUnique(id);
		
		if (isIdUnique) {
			addUserService.addUser(name, id, password, birth, gender);
			forward = new ActionForward();
			forward.setPath("/logIn.jsp");
		} else {
			AlertService alertService = AlertService.getInstance();
			String message = "이미 존재하는 ID입니다.";
			
			alertService.alertAndBack(res, message);
		}
		
		
		return forward;
	}
}