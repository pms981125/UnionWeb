package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GetReservationDetailService;
import service.LogInService;
import vo.ActionForward;
import vo.MemberVO;
import vo.ReservationVO;

public class GetReservationDetailAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = null;
		String id = req.getParameter("id");
		int no = Integer.parseInt(req.getParameter("no"));
		GetReservationDetailService getReservationDetailService = GetReservationDetailService.getInstance();
		ReservationVO reservation = null;
		MemberVO user = LogInService.getInstance().getUserById(id);
		
		reservation = getReservationDetailService.getReservation(no);
		forward = new ActionForward();
		forward.setPath("reservation_detail.jsp");
		req.setAttribute("user", user);
		req.setAttribute("reservation", reservation);
		
		return forward;
	}
}