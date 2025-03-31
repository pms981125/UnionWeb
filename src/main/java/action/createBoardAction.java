package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import service.CreateBoardService;
import service.LogInService;
import vo.ActionForward;
import vo.MemberVO;

public class createBoardAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = null;
		String title = "";
		String contents = "";
		String imageSrc = null;
		String id = "";
		String saveFolder = "D:/pms/05.JSP/project1/WebContent/upload";
		int fileSize = 1024 * 1024 * 10;
		MemberVO user = null;
		CreateBoardService createBoardService = CreateBoardService.getInstance();
		MultipartRequest multi = null;
		
		try {
			multi = new MultipartRequest(req, saveFolder, fileSize, "utf-8", new DefaultFileRenamePolicy());
			title = multi.getParameter("title");
			contents = multi.getParameter("contents");
			imageSrc = multi.getOriginalFileName((String) multi.getFileNames().nextElement());
			id = multi.getParameter("id");
			user = LogInService.getInstance().getUserById(id);
			
			createBoardService.createBoard(title, contents, imageSrc, user.getName(), id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		forward = new ActionForward();
		forward.setPath("getBoardList.do");
		req.setAttribute("hasAttribute", true);
		req.setAttribute("user", user);
		
		return forward;
	}
}