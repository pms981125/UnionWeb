package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GetChattingRoomService;
import service.LogInService;
import vo.ActionForward;
import vo.ChattingRoomVO;
import vo.ContentChattingRoomVO;
import vo.MemberVO;
import vo.PageInfo;

public class GetChattingRoomListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		ActionForward forward = null;
		boolean hasAttribute = false;
		boolean hasAttribute2 = false;
		MemberVO user = null;
		GetChattingRoomService getChattingRoomService = GetChattingRoomService.getInstance();
		List<String> pcrl = new ArrayList<String>();
		List<ChattingRoomVO> chattingRoomList = new ArrayList<>();
		List<ContentChattingRoomVO> contentChattingRoomList = new ArrayList<>();
		int roomNum = 0;
		int roomNo = 0;
		String iValue = null;
		
		if (req.getAttribute("hasAttribute") != null) {
			hasAttribute = (boolean) req.getAttribute("hasAttribute");
		}
		
		if (req.getAttribute("hasAttribute2") != null) {
			hasAttribute2 = (boolean) req.getAttribute("hasAttribute2");
		}
		
		if (hasAttribute2) {
			user = (MemberVO) req.getAttribute("user");
			iValue = (String) req.getAttribute("iValue");
			roomNo = Integer.parseInt((String) req.getAttribute("roomNo"));
		} else if (hasAttribute) {
			user = (MemberVO) req.getAttribute("user");
		} else {
			String id = req.getParameter("id");
			user = LogInService.getInstance().getUserById(id);
		}
		
		try {
			roomNum = user.getParticipatingChattingRoomNo().size();
			pcrl = user.getParticipatingChattingRoomNo();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < roomNum; i++) {
			chattingRoomList.add(getChattingRoomService.getChattingRoomByNo(Integer.parseInt(pcrl.get(i))));
		}
		
		for(int i = 0; i < roomNum; i++) {
			contentChattingRoomList.add(getChattingRoomService.getContentChattingRoom(Integer.parseInt(pcrl.get(i))));
		}
		
		req.setAttribute("roomNo", Integer.toString(roomNo));
		req.setAttribute("iValue", iValue);
		req.setAttribute("user", user);
		req.setAttribute("pcrl", pcrl);
		req.setAttribute("chattingRoomList", chattingRoomList);
		req.setAttribute("contentChattingRoomList", contentChattingRoomList);
		req.setAttribute("roomNum", roomNum);
		forward = new ActionForward();
		forward.setPath("chatting_room_list.jsp");
		
		return forward;
	}
}