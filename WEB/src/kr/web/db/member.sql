-- member table 
create table member(
	num int primary key auto_increment,
	id varchar(20) not null,
	pass varchar(20) not null, 
	name varchar(30) not null, 
	age int not null, 
	email varchar(30) not null, 
	phone varchar(30) not null
)

-- SQL(CRUD), JDBC
-- select(검색) 
select * from member;

-- insert(저장)
insert into member(id, pass, name, age, email, phone)
values('admin', 'admin', '관리자', 40, 'zero7038@naver.com', '010-0000-0000');

-- update(수정)
update member set age = 45, phone = '010-1111-1111' where id = 'admin';

-- delete(삭제)
delete from member where id = 'admin';