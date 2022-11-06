create table tb_board(
  idx int not null auto_increment, -- 자동증가 (아이디)
  title varchar(100) not null, -- 제목
  contents varchar(4000) not null, -- 내용
  count int, -- 조회수
  writer varchar(30) not null, -- 등록자
  indate datetime default now() not null, -- 등록일 
  primary key(idx)
);

insert into tb_board(title, contents, count, writer)
values('게시판만들기', '게시판만들기', 0, '관리자');

select * from tb_board;