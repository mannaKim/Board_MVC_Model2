package com.ezen.board.controller;

import com.ezen.board.controller.action.Action;
import com.ezen.board.controller.action.AddReplyAction;
import com.ezen.board.controller.action.BoardCheckPassAction;
import com.ezen.board.controller.action.BoardPassFormAction;
import com.ezen.board.controller.action.BoardViewAction;
import com.ezen.board.controller.action.BoardViewNoCountAction;
import com.ezen.board.controller.action.BoardWriteAction;
import com.ezen.board.controller.action.BoardWriteFormAction;
import com.ezen.board.controller.action.DeleteBoardAction;
import com.ezen.board.controller.action.DeleteReplyAction;
import com.ezen.board.controller.action.IdCheckAction;
import com.ezen.board.controller.action.JoinAction;
import com.ezen.board.controller.action.JoinFormAction;
import com.ezen.board.controller.action.LoginAction;
import com.ezen.board.controller.action.LoginFormAction;
import com.ezen.board.controller.action.LogoutAction;
import com.ezen.board.controller.action.MainAction;
import com.ezen.board.controller.action.UpdateBoardAction;
import com.ezen.board.controller.action.UpdateBoardFormAction;
import com.ezen.board.controller.action.UpdateMemberAction;
import com.ezen.board.controller.action.UpdateMemberFormAction;

public class ActionFactory {
	//싱글톤 패턴으로 변경
	private ActionFactory() {}
	private static ActionFactory itc = new ActionFactory();
	public static ActionFactory getInstance() { return itc; }
	
	//get Action 생성
	public Action getAction(String command) {
		
		Action ac = null;
		
		if(command.equals("loginForm")) ac = new LoginFormAction();
		else if(command.equals("login")) ac = new LoginAction();
		else if(command.equals("logout")) ac = new LogoutAction();
		else if(command.equals("joinForm")) ac = new JoinFormAction();
		else if(command.equals("idcheck")) ac = new IdCheckAction();
		else if(command.equals("join")) ac = new JoinAction();
		else if(command.equals("updateMemberForm")) ac = new UpdateMemberFormAction();
		else if(command.equals("updateMember")) ac = new UpdateMemberAction();
		else if(command.equals("main")) ac = new MainAction();
		//command=main에 대한 위 if문과 MainAction 클래스를 만듦으로써 
		//updateMemberForm.jsp, LoginAction.java, UpdateMemberAction.java에서
		//main으로 가는 url을 board/main.jsp에서 board.do?command=main으로 변경할 수 있음! 
		else if(command.equals("boardView")) ac = new BoardViewAction();
		else if(command.equals("boardWriteForm")) ac = new BoardWriteFormAction();
		else if(command.equals("boardWrite")) ac = new BoardWriteAction();
		else if(command.equals("boardPassForm")) ac = new BoardPassFormAction();
		else if(command.equals("boardCheckPass")) ac = new BoardCheckPassAction();
		else if(command.equals("updateBoardForm")) ac = new UpdateBoardFormAction();
		else if(command.equals("boardViewNoCount")) ac = new BoardViewNoCountAction();
		else if(command.equals("updateBoard")) ac = new UpdateBoardAction();
		else if(command.equals("deleteBoard")) ac = new DeleteBoardAction();
		else if(command.equals("addReply")) ac = new AddReplyAction();
		else if(command.equals("delRep")) ac = new DeleteReplyAction();
		
		return ac;
	}
}
