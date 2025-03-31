package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GetBoardDetailService;
import service.LikeService;
import service.LogInService;
import vo.ActionForward;
import vo.BoardVO;
import vo.MemberVO;

public class LikeAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = null;
	 	String id = req.getParameter("id");
	 	int no = Integer.parseInt(req.getParameter("no"));
	 	MemberVO user = LogInService.getInstance().getUserById(id);
	 	BoardVO board = GetBoardDetailService.getInstance().getBoard(no);
	 	LikeService likeService = LikeService.getInstance();
	 	
	 	if (board.getLikeID() != null && board.getLikeID().contains(id)) {
	 		likeService.decreaseLike(no, id);
		} else {
			likeService.increaseLike(no, id);
		}
	 	
	 	forward = new ActionForward();
	 	forward.setPath("board_detail.jsp");
	 	req.setAttribute("user", user);
	 	board = GetBoardDetailService.getInstance().getBoard(no);
	 	req.setAttribute("board", board);
		
		return forward;
	}
}