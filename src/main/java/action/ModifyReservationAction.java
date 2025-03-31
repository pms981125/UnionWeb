package action;

import java.awt.Point;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GetReservationDetailService;
import service.LogInService;
import service.ModifyReservationService;
import vo.ActionForward;
import vo.MemberVO;
import vo.ReservationVO;

public class ModifyReservationAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = null;
		int no = Integer.parseInt(req.getParameter("no"));
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		LocalDateTime reservationDate = LocalDateTime.parse(req.getParameter("reservationDate"));
		int reservationPeopleNum = Integer.parseInt(req.getParameter("reservationPeopleNum"));
		String reservationLocationName = req.getParameter("reservationLocationName");
		Point reservationLocation = new Point();
		MemberVO user = LogInService.getInstance().getUserById(id);
		ModifyReservationService modifyReservationService = ModifyReservationService.getInstance();
		GetReservationDetailService getReservationDetailService = GetReservationDetailService.getInstance();
		ReservationVO reservation = new ReservationVO();
		
		modifyReservationService.modifyReservation(no, name, reservationPeopleNum, reservationDate, reservationLocationName, reservationLocation);
		
		reservation = getReservationDetailService.getReservation(no);
		forward = new ActionForward();
		forward.setPath("reservation_detail.jsp");
		req.setAttribute("user", user);
		req.setAttribute("reservation", reservation);
		
		return forward;
	}
}