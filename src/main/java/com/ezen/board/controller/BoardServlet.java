package com.ezen.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.board.controller.action.Action;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/board.do")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//전달된 command값을 추출
		String command = request.getParameter("command");
		
		//추출한 command값에 따라 해당 기능을 담고 있을 클래스를 new 인스턴스를 인터페이스 레퍼런스 변수에 저장
		//이를 위해서 ActionFactory 클래스에 getAction이라는 메서드가 사용됨
		
		//클래스의 인스턴스 생성
		ActionFactory af = ActionFactory.getInstance();
		//메서드 실행 후 리턴되는 값을 레퍼런스 변수에 저장
		Action ac = af.getAction(command);
		
		if(ac==null) System.out.println("ac가 null입니다. command값을 확인하세요.");
		else ac.execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
