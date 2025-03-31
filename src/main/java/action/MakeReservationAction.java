package action;

import java.awt.Point;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.LogInService;
import service.MakeReservationService;
import vo.ActionForward;
import vo.MemberVO;

public class MakeReservationAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = null;
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		LocalDateTime reservationDate = LocalDateTime.parse(req.getParameter("reservationDate"));
		String reservationPersonName = req.getParameter("reservationPersonName");
		int reservationPeopleNum = Integer.parseInt(req.getParameter("reservationPeopleNum"));
		String reservationLocationName = req.getParameter("reservationLocationName");
		Point reservationLocation = new Point(); // 좌표
		MemberVO user = LogInService.getInstance().getUserById(id);
		MakeReservationService makeReservationService = MakeReservationService.getInstance();
		
		makeReservationService.makeReservation(name, id, reservationPersonName, reservationPeopleNum, reservationDate, reservationLocationName, reservationLocation);
		
		forward = new ActionForward();
		forward.setPath("getReservationList.do");
		req.setAttribute("hasAttribute", true);
		req.setAttribute("user", user);
		
		return forward;
	}
}