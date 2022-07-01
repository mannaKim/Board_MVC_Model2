package com.ezen.board.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.board.dao.BoardDao;

public class DeleteBoardAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//전달받은 게시물 번호로 게시물을 삭제한 후 main.jsp 페이지로 되돌아가는 포워딩 실행
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDao bdao = BoardDao.getInstance();
		bdao.deleteBoard(num);
		bdao.deleteReplyByboardnum(num);
		
		RequestDispatcher dp = request.getRequestDispatcher("board.do?command=main");
		dp.forward(request, response);
	}

}
