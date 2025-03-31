package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AlertService;
import service.GetBoardDetailService;
import service.LogInService;
import vo.ActionForward;
import vo.BoardVO;
import vo.MemberVO;

public class GoEditBoardFormAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = null;
		String id = req.getParameter("id");
		int no = Integer.parseInt(req.getParameter("no"));
		MemberVO user  = LogInService.getInstance().getUserById(id);
		BoardVO board = GetBoardDetailService.getInstance().getBoard(no);
		forward = new ActionForward();
		
		if (id.equals(board.getWriterId())) {
			forward.setPath("board_edit_form.jsp");
			req.setAttribute("user", user);
			req.setAttribute("board", board);
		} else {
			AlertService alertService = AlertService.getInstance();
			String message = "게시글 수정 실패 - 작성자가 아닙니다.";
			/*
			 * forward.setPath("getBoardDetail.do"); req.setAttribute("hasAttribute", true);
			 * req.setAttribute("user", user); req.setAttribute("board", board);
			 */
			alertService.alertAndBack(res, message);
		}
		
		return forward;
	}
}