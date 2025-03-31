package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GetBoardDetailService;
import service.LogInService;
import vo.ActionForward;
import vo.BoardVO;
import vo.MemberVO;

public class GetBoardDetailAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = null;
		boolean hasAttribute = false;
		MemberVO user = null;
		BoardVO board = null;
		
		if (req.getAttribute("hasAttribute") != null) {
			hasAttribute = (boolean) req.getAttribute("hasAttribute");
		}
		
		if (hasAttribute) {
			user = (MemberVO) req.getAttribute("user");
			board = (BoardVO) req.getAttribute("board");
		} else {
			String id = req.getParameter("id");
			int no = Integer.parseInt(req.getParameter("no"));
			user = LogInService.getInstance().getUserById(id);
			GetBoardDetailService getBoardDetailService = GetBoardDetailService.getInstance();
			board = getBoardDetailService.getBoardDetail(no);
		}
		
		forward = new ActionForward();
		forward.setPath("board_detail.jsp");
		req.setAttribute("user", user);
		req.setAttribute("board", board);
		
		return forward;
	}
}