package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GetBoardListService;
import service.LogInService;
import vo.ActionForward;
import vo.BoardVO;
import vo.MemberVO;
import vo.PageInfo;

public class GetBoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = null;
		boolean hasAttribute = false;
		MemberVO user = null;
		
		if (req.getAttribute("hasAttribute") != null) {
			hasAttribute = (boolean) req.getAttribute("hasAttribute");
		}
		
		if (hasAttribute) {
			user = (MemberVO) req.getAttribute("user");
		} else {
			String id = req.getParameter("id");
			user = LogInService.getInstance().getUserById(id);
		}
		
		GetBoardListService getBoardListService = GetBoardListService.getInstance();
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		PageInfo pageInfo = new PageInfo(); 
		int limit = 10;
		int page = 1;
		String find = "";
		String query = "";
		
		if (req.getParameter("page") != null) {
			try {
				page = Integer.parseInt(req.getParameter("page"));
			} catch (Exception e) {
				e.printStackTrace();
				page = 1;
			}
		}
		if (req.getParameter("find") != null) find = req.getParameter("find"); else find = "title";
		if (req.getParameter("query") != null) query = req.getParameter("query");
		
		boardList = getBoardListService.getBoardList(page, limit, find, query);
		
		int listCount = getBoardListService.getListCount(find, query);
		int totalPage = (int) ((double) listCount / limit + 0.95);
		int startPage = (page - 1) / 10 * 10 + 1;
		int endPage = startPage + 9;
		
		endPage = endPage > totalPage ? (totalPage > 0 ? totalPage : 1) : endPage;
		
		pageInfo.setListCount(listCount);
		pageInfo.setPage(page);
		pageInfo.setTotalPage(totalPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		
		forward = new ActionForward();
		//forward.setPath("board_list.jsp");
		forward.setPath(String.format("board_list.jsp?p=%s&f=%s&q=%s", page, find, query));
		req.setAttribute("user", user);
		req.setAttribute("boardList", boardList);
		req.setAttribute("pageInfo", pageInfo);
		
		return forward;
	}
}