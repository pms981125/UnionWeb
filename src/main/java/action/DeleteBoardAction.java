package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AlertService;
import service.DeleteBoardService;
import service.GetBoardDetailService;
import service.LogInService;
import vo.ActionForward;
import vo.BoardVO;
import vo.MemberVO;

public class DeleteBoardAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = null;
		String id = req.getParameter("id");
		int no = Integer.parseInt(req.getParameter("no"));
		MemberVO user = LogInService.getInstance().getUserById(id);
		BoardVO board = GetBoardDetailService.getInstance().getBoard(no);
		DeleteBoardService deleteBoardService = DeleteBoardService.getInstance();
		
		forward = new ActionForward();
		
		if (id.equals(board.getWriterId())) {
			deleteBoardService.deleteBoard(no);
			forward.setPath("getBoardList.do");
			req.setAttribute("hasAttribute", true);
			req.setAttribute("user", user);
		} else {
			AlertService alertService = AlertService.getInstance();
			String message = "게시글 삭제 실패 - 작성자가 아닙니다.";
			
			alertService.alertAndBack(res, message);
			/*
			 * forward.setPath("getBoardDetail.do"); req.setAttribute("hasAttribute", true);
			 * req.setAttribute("user", user); req.setAttribute("board", board);
			 */
		}
		
		return forward;
	}
}