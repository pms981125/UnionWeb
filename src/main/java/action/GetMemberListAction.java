package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GetMemberListService;
import service.LogInService;
import vo.ActionForward;
import vo.MemberVO;
import vo.PageInfo;

public class GetMemberListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = null;
		MemberVO user = null; 
		GetMemberListService getMemberListService = GetMemberListService.getInstance();
		List<MemberVO> userList = new ArrayList<MemberVO>();
		PageInfo pageInfo = new PageInfo(); 
		int limit = 10;
		int page = 1;
		String find = "name";
		String query = "";
		
		if (req.getParameter("page") != null) {
			try {
				page = Integer.parseInt(req.getParameter("page"));
			} catch (Exception e) {
				e.printStackTrace();
				page = 1;
			}
		}
		if (req.getParameter("query") != null) query = req.getParameter("query");
		
		user = LogInService.getInstance().getUserById(req.getParameter("id"));
		userList = getMemberListService.getUserList(page, limit, find, query);
		
		int listCount = getMemberListService.getListCount(find, query);
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
		//forward.setPath("member_list.jsp");
		forward.setPath(String.format("member_list.jsp?p=%s&f=%s&q=%s", page, find, query));
		req.setAttribute("user", user);
		req.setAttribute("userList", userList);
		req.setAttribute("pageInfo", pageInfo);
		
		return forward;
	}
}