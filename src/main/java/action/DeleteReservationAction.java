package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.DeleteReservationService;
import service.LogInService;
import vo.ActionForward;
import vo.MemberVO;

public class DeleteReservationAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = null;
		String id = req.getParameter("id");
		int no = Integer.parseInt(req.getParameter("no"));
		DeleteReservationService deleteReservationService = DeleteReservationService.getInstance();
		MemberVO user = LogInService.getInstance().getUserById(id);
		
		deleteReservationService.deleteReservation(no);
		
		forward = new ActionForward();
		forward.setPath("getReservationList.do");
		req.setAttribute("hasAttribute", true);
		req.setAttribute("user", user);
		
		return forward;
	}
}