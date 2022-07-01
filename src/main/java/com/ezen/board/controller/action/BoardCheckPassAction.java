package com.ezen.board.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.board.dao.BoardDao;
import com.ezen.board.dto.BoardDto;

public class BoardCheckPassAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pass = request.getParameter("pass");
		
		BoardDao bdao = BoardDao.getInstance();
		BoardDto bdto = bdao.selectBoardOne(num);
		
		//bdto.getPass()와 pass에 담긴 값을 비교 처리 합니다.
		String url = null;
		if(bdto.getPass()==null) {
			//데이터베이스오류
			request.setAttribute("message", "비밀번호 오류. 관리자에게 문의하세요.");
			url = "board/boardCheckPass.jsp";
		} else if(pass.equals(bdto.getPass())) {
			//둘이 같다면 checkSuccess.jsp로 이동
			url = "board/checkSuccess.jsp";
		} else {
			request.setAttribute("message", "비밀번호가 틀렸습니다.");
			url = "board/boardCheckPass.jsp";
		}
		
		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);
	}

}
