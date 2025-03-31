package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GetReservationListService;
import service.LogInService;
import vo.ActionForward;
import vo.MemberVO;
import vo.ReservationVO;

public class GetReservationListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = null;
		boolean hasAttribute = false;
		MemberVO user = null; 
		GetReservationListService getReservationListService = GetReservationListService.getInstance();
		List<ReservationVO> reservationList = new ArrayList<ReservationVO>();
		
		if (req.getAttribute("hasAttribute") != null) {
			hasAttribute = (boolean) req.getAttribute("hasAttribute");
		}
		
		if (hasAttribute) {
			user = (MemberVO) req.getAttribute("user");
		} else {
			String id = req.getParameter("id");
			user = LogInService.getInstance().getUserById(id);
		}
		
		reservationList = getReservationListService.getReservationList(user.getId());
		forward = new ActionForward();
		forward.setPath("reservation_list.jsp");
		req.setAttribute("user", user);
		req.setAttribute("reservationList", reservationList);
		
		return forward;
	}
}