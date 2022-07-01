create table board(
	num number(5) primary key,
	pass varchar2(30), --게시물의 수정 삭제를 위한 비밀번호
	userid varchar2(30), 
	email varchar2(30),
	title varchar2(50),
	content varchar2(1000),
	readcount number(4) default 0, --조회수
	writedate date default sysdate --작성일자
);

select * from member;
select * from board;

create sequence board_seq start with 1 increment by 1;

drop table board;
drop sequence board_seq;

insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'hong', 'abc@naver.com', '1234', '첫방문입니다', 
	'반갑습니다. 앞으로 많은 격려와 지도편달 부탁드립니다.');

insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'somi', 'adddnaver.com', '1234', '게시판 개설',
	'축하드립니다. 무궁한 발전을 기원할께요');

insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'light', 'bnbn@naver.com', '1234', '돼지골마을',
	'돼지 삼겹살이 맛있습니다');

insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'dong', 'hana@daum.net', '1234', '2022년 겨울' , 
	'몹시 추울 것 같아요. 다들 건강 유의 하세요.');
	
insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'dong', 'hana@daum.net', '1234', '코로나바이러스' , 
	'사회적 거리두기가 끝나갑니다. 일상으로 복귀 등등등');

insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'hong', 'abc@naver.com', '1234', '첫방문입니다', 
	'반갑습니다. 앞으로 많은 격려와 지도편달 부탁드립니다.');

insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'somi', 'adddnaver.com', '1234', '게시판 개설',
	'축하드립니다. 무궁한 발전을 기원할께요');

insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'light', 'bnbn@naver.com', '1234', '돼지골마을',
	'돼지 삼겹살이 맛있습니다');

insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'dong', 'hana@daum.net', '1234', '2022년 겨울' , 
	'몹시 추울 것 같아요. 다들 건강 유의 하세요.');
	

create table reply(
	replynum number(7) primary key, --댓글 순번
	boardnum number(5), --댓글의 해당 게시물 번호
	userid varchar2(20), --댓글 작성자
	writedate date default sysdate, --작성일
	content varchar2(1000) --작성 내용
);
--댓글은 board테이블에 저장되지 않습니다. board테이블에 댓글필드를 두세개 생성하고 저장해도 되지만,
--게시판에 있는 각 게시물들에 대한 댓글은 작성될 수 있는 개수가 제한이 없기 때문에 모든 댓글은 하나의 테이블에 저장합니다.
--이때 반드시 댓글에 어느 게시물의 댓글인지 게시물 번호를 같이 저장해야 합니다.
--그래야 해당 게시물이 화면에 표시될 때 그 게시물의 댓글만 조회(검색)해서 따로 화면에 표시할 수 있습니다.

create sequence reply_seq start with 1 increment by 1;

select * from reply;

insert into reply values(reply_seq.nextVal, 1, 'somi', sysdate, '게시판 개설을 축하드립니다.');
insert into reply values(reply_seq.nextVal, 2, 'light', sysdate, '첫게시글 작성 감사합니다.');
insert into reply values(reply_seq.nextVal, 3, 'scott', sysdate, '맛있겠다');


-- 페이징 하기 위한 쿼리 --
select * from board where rownum>=1 and rownum<=10 order by num desc;

select * from ( 
	select * from ( 
		select rownum as rn, b.* from ( 
			(select * from board order by num desc) b)
 	) where rn>=1
) where rn<=10;

-- board테이블에 댓글개수를 저장할 replycnt 추가
alter table board add replycnt number(3) default 0;

-- board테이블에 이미지파일이름을 저장할 컬럼 추가
alter table board add imgfilename varchar2(255);