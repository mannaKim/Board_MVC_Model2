package com.ezen.board.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.board.dao.BoardDao;
import com.ezen.board.dto.BoardDto;
import com.ezen.board.util.Paging;

public class MainAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//게시물을 조회해서 ArrayList에 담고, 그를 다시 request에 담아서 main.jsp로 포워딩합니다.
		BoardDao bdao = BoardDao.getInstance();
		
		// ※ 페이징
		//처음 게시판을 열려고 할 때 page 값은 1
		int p = 1;
		
		//사용자가 2 또는 다른 페이지 번호를 클릭했을 때, board.do?command=main&page=2
		if(request.getParameter("page")!=null) 
			p = Integer.parseInt(request.getParameter("page"));
		
		//그렇게 결정된 현재 페이지 번호를 Paging객체를 만들고 멤버변수에 저장합니다.
		Paging paging = new Paging();
		paging.setPage(p);
		
		//데이터베이스에 Access해서 총 레코드 개수를 리턴받습니다.
		int count = bdao.getAllCount();
		//리턴받는 개수를 Paging 객체의 totalCount변수에 저장
		paging.setTotalCount(count); //<-- 이 값을 정의하면서 paging()메서드 실행 : Paging객체의 멤버변수 값이 모두 정의 됨
		
		//paging 객체를 selectBoard메서드에 전달해서 필요한 만큼의 board만 가져옴
		ArrayList<BoardDto> list = bdao.selectBoard(paging);
		
		//리스트를 리턴받아와서 리퀘스트에 애트리뷰트로 담기 전에 각 게시물의 댓글 갯수를 조회해서
		//BoardDto 객체의 replycnt라는 멤버변수에 담습니다.
		for(BoardDto bdto : list) {
			count = bdao.getRelpycnt(bdto.getNum());
			bdto.setReplycnt(count);
		}
		
		request.setAttribute("bList", list);
		request.setAttribute("paging", paging); //paging 객체도 리퀘스트에 담음
		RequestDispatcher rd = request.getRequestDispatcher("board/main.jsp");
		rd.forward(request, response);

	}

}
