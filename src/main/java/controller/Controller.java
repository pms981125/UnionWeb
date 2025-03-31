package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.*;
import vo.ActionForward;

@WebServlet("*.do")
public class Controller extends HttpServlet{
	private static final long serialVersionUID = 1L; // 임시
	
	Action action = null;
	ActionForward forward = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		process(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		process(req, res);
	}
	
	private void process(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = requestURI.substring(contextPath.length() + 1, requestURI.length() - 3);
		RequestDispatcher dispatcher = null;
		
		switch (command) {
		case "logIn": {
			action = new LogInAction();
			forward = action.execute(req, res);
			break;
		}
		case "createChattingRoom": {
			action = new CreateChattingRoomAction();
			forward = action.execute(req, res);
			break;
		}
		case "getChattingRoomList": {
			action = new GetChattingRoomListAction();
			forward = action.execute(req, res);
			break;
		}
		case "getChattingRoom": {
			action = new GetChattingRoomAction();
			forward = action.execute(req, res);
			break;
		}
		case "sendMessage": {
			action = new SendMessageAction();
			forward = action.execute(req, res);
			break;
		}
		case "register": {
			action = new AddUserAction();
			forward = action.execute(req, res);
			break;
		}
		case "goSettingPage": {
			action = new GoSettingPageAction();
			forward = action.execute(req, res);
			break;
		}
		case "updateUser": {
			action = new UpdateUserAction();
			forward = action.execute(req, res);
			break;
		}
		case "getBoardList": {
			action = new GetBoardListAction();
			forward = action.execute(req, res);
			break;
		}
		case "createBoardForm": {
			action = new createBoardFormAction();
			forward = action.execute(req, res);
			break;
		}
		case "createBoard": {
			action = new createBoardAction();
			forward = action.execute(req, res);
			break;
		}
		case "getBoardDetail": {
			action = new GetBoardDetailAction();
			forward = action.execute(req, res);
			break;
		}
		case "goEditBoardForm": {
			action = new GoEditBoardFormAction();
			forward = action.execute(req, res);
			break;
		}
		case "deleteBoard": {
			action = new DeleteBoardAction();
			forward = action.execute(req, res);
			break;
		}
		case "editBoard": {
			action = new EditBoardAction();
			forward = action.execute(req, res);
			break;
		}
		case "deleteChattingRoom": {
			action = new DeleteChattingRoomAction();
			forward = action.execute(req, res);
			break;
		}
		case "exitChattingRoom": {
			action = new ExitChattingRoomAction();
			forward = action.execute(req, res);
			break;
		}
		case "getMemberList": {
			action = new GetMemberListAction();
			forward = action.execute(req, res);
			break;
		}
		case "getUserDetail": {
			action = new GetUserDetailAction();
			forward = action.execute(req, res);
			break;
		}
		case "like": {
			action = new LikeAction();
			forward = action.execute(req, res);
			break;
		}
		case "withdraw": {
			action = new DeleteMemberAction();
			forward = action.execute(req, res);
			break;
		}
		case "getReservationList": {
			action = new GetReservationListAction();
			forward = action.execute(req, res);
			break;
		}
		case "goReservationForm": {
			action = new GoReservationFormAction();
			forward = action.execute(req, res);
			break;
		}
		case "makeReservation": {
			action = new MakeReservationAction();
			forward = action.execute(req, res);
			break;
		}
		case "getReservationDetail": {
			action = new GetReservationDetailAction();
			forward = action.execute(req, res);
			break;
		}
		case "modifyReservationForm": {
			action = new ModifyReservationFormAction();
			forward = action.execute(req, res);
			break;
		}
		case "modifyReservation": {
			action = new ModifyReservationAction();
			forward = action.execute(req, res);
			break;
		}
		case "deleteReservation": {
			action = new DeleteReservationAction();
			forward = action.execute(req, res);
			break;
		}
		case "logOut": {
			action = new LogOutAction();
			forward = action.execute(req, res);
			break;
		}
		}
		
		if (forward != null) {
			if (forward.isRedirect()) {
				res.sendRedirect(forward.getPath());
			} else {
				dispatcher = req.getRequestDispatcher(forward.getPath());
				dispatcher.forward(req, res);
			}
		}
	}
}