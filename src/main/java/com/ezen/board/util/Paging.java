package com.ezen.board.util;

public class Paging {
	private int page = 1;			//현재 화면에 표시할 페이지 번호
	private int totalCount;			//게시물의 전체 개수
	private int displayRow = 10;	//한 페이지에 몇개의 게시물을 표시할건지
	private int displayPage = 10;	//이전(◀)과 다음(▶) 버튼 사이에 몇개의 페이지를 표시할건지
	private int beginPage; 			//표시될 시작 페이지 번호 1 or 11 or 21 or 31 ...
	private int endPage;			//표시될 끝 페이지 번호 10 or 20 or 30 or 40 ...
	private boolean prev;			//prev 버튼이 보일건지 안보일건지(true/false)
	private boolean next;			//next 버튼이 보일건지 안보일건지(true/false)
	private int startNum;			//현재 페이지에 표시될 게시물 번호의 시작 번호
	private int endNum;				//현재 페이지에 표시될 게시물 번호의 끝 번호
	
	// 페이지 표시 예
	// [1] 2 3 4 5 6 7 8 9 10 [next] -- 1페이지부터 표시, prev 없음, 게시물 개수가 100개 이상인 경우
	// [prev] 11 12 13 14 [15] 16 17 18 19 20 [next] -- 게시물 개수가 200개 이상인 경우
	// [prev] 21 22 23 24 [25] 26 27 28 29 30 [next] -- 게시물 개수가 300개 이상인 경우
	// [prev] 11 12 13 14 -- 게시물 개수가 141~149개인 경우, next 없음
	// 본 클래스는 하나의 게시판에 객체 하나를 할당해서 전체 게시물 수에 따라 계산된 페이지의 각 요소들을
	// 각 멤버 변수에 저장하고 화면에 표시할 내용을 사용하는 클래스입니다.
	
	// 각 변수값을 계산해주는 멤버 메서드
	private void paging() {
		
		// 1. beginPage와 endPage를 계산
		double temp = page / (double)displayPage;
			// 1/10->0.1		9/10->0.9		15/10->1.5		25/10->2.5
		
		// 위 연산결과에서 소수점 첫째자리 올림연산
		temp = Math.ceil(temp);
			// 0.1->1.0		0.9->1.0	1.5->2.0	2.5->3.0
		endPage = (int)(temp * displayPage);
			// 1.0->10		1.0->10		2.0->20		3.0->30
		//endPage=((int)(Math.ceil(page/(double)displayPage)))*displayPage;
		
		beginPage = endPage - (displayPage - 1);
			//endPage가 10이면 beginPage가 1
			//endPage가 20이면 beginPage가 11
			//endPage가 30이면 beginPage가 21
		
		
		// 2. totalPage 계산
		// 총게시물수(totalCount)를 한 화면에 표시될 게시물수(displayRow)로 나누고
		// 소수점 자리 올림
		int totalPage = (int)Math.ceil(totalCount/(double)displayRow);
			// 전체게시물이 108개일 때
			// 108/10 -> 10.8 -> 11.0 -> 11 : 필요한 총 페이지 수는 11페이지
			// 전체게시물이 75개일 때
			// 75/10 -> 7.5 -> 8.0 -> 8 : 필요한 총 페이지 수는 8페이지
		
		
		// 3. next, prev 계산
		if(totalPage < endPage) {
			// 총 페이지가 화면에 표시할 끝 페이지보다 작다면
			endPage = totalPage; //endPage를 총페이지수로 대체하고
			next = false;	//다음버튼은 표시하지 않음
		} else {
			// endPage 뒤로 페이지가 더 있으니 다음버튼을 표시
			next = true;
		}
		
		prev = (beginPage==1)?false:true; //시작페이지가 1인 경우만 prev 표시 안함
		
		
		// 4. startNum, endNum : ROWNUM을 대상으로 하여 설정하는 표시될 게시물 번호
		startNum = (page-1)*displayRow+1;
			//현재 화면의 시작 게시물 번호 1page:1, 2page:11, 3page:21, 4page:31
		endNum = page*displayRow;
			//현재 화면의 끝 게시물 번호 1page:10, 2page:20, 3page:30, 4page:40
		
		System.out.println(beginPage+" "+endPage+" "+startNum+" "+endNum);
		
	}
		
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		paging();
		//totalCount 변수에 값이 세팅되면 paging()메서드도 호출
	}
	public int getDisplayRow() {
		return displayRow;
	}
	public void setDisplayRow(int displayRow) {
		this.displayRow = displayRow;
	}
	public int getDisplayPage() {
		return displayPage;
	}
	public void setDisplayPage(int displayPage) {
		this.displayPage = displayPage;
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	public int getEndNum() {
		return endNum;
	}
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
}
