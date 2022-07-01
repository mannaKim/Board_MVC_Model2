package com.ezen.board.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.board.dao.BoardDao;
import com.ezen.board.dto.BoardDto;
import com.ezen.board.dto.ReplyDto;

public class BoardViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//전달된 게시물 번호로 게시물 하나를 검색 조회해서 리턴받아서 boardView.jsp로 포워딩합니다.
		//이때 조회수를 +1하는 동작을 포함시킵니다.
		
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDao bdao = BoardDao.getInstance();
		
		//#1. 하나의 메서드에서 두가지 동작을 실행
		//BoardDto bdto = bdao.selectBoard(num); //조회수+1 동작이 포함된 메서드 호출
									//mainAction에서 사용한 selectBoard메서드 오버라이딩
		
		//#2. 두개의 메서드 생성해서 각각 따로 호출
		// - 게시물 조회
		// - 조회수 증가
		bdao.readCountPlusOne(num);
		BoardDto bdto = bdao.selectBoardOne(num);
		
		//해당 게시물의 댓글만 조회해서 리턴
		ArrayList<ReplyDto> list = bdao.selectReply(num);
		request.setAttribute("replyList", list);
		
		request.setAttribute("board", bdto);
		RequestDispatcher rd = request.getRequestDispatcher("board/boardView.jsp");
		rd.forward(request, response);
	}

}
