package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import service.EditBoardService;
import service.GetBoardDetailService;
import service.LogInService;
import vo.ActionForward;
import vo.BoardVO;
import vo.MemberVO;

public class EditBoardAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = null;
		String title = "";
		String contents = "";
		String imageSrc = null;
		String id = "";
		int no = 0;
		String saveFolder = "D:/pms/05.JSP/project1/WebContent/upload";
		int fileSize = 1024 * 1024 * 10;
		MemberVO user = null;
		BoardVO board = null;
		MultipartRequest multi = null;
		EditBoardService editBoardService = EditBoardService.getInstance();
		
		try {
			multi = new MultipartRequest(req, saveFolder, fileSize, "utf-8", new DefaultFileRenamePolicy());
			no = Integer.parseInt(multi.getParameter("no"));
			title = multi.getParameter("title");
			
			if (!title.contains("(수정)")) {
				title = "(수정) " + title; 
			}
			
			contents = multi.getParameter("contents");
			imageSrc = multi.getOriginalFileName((String) multi.getFileNames().nextElement());
			id = multi.getParameter("id");
			user = LogInService.getInstance().getUserById(id);
			editBoardService.editBoard(no, title, contents, imageSrc);
			board = GetBoardDetailService.getInstance().getBoard(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		forward = new ActionForward();
		forward.setPath("getBoardDetail.do");
		req.setAttribute("hasAttribute", true);
		req.setAttribute("user", user);
		req.setAttribute("board", board);
		
		return forward;
	}
}