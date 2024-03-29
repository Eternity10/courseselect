create database student;
use student;
create table s
(sno char(4) not null primary key,
 sname char(8) not null,
 sex char(2) not null,
 age char(2) not null,
 sdept char(10) not null,
 logn char(20) not null,
 pswd char(20) not null);
create table c
(cno char(4) not null primary key,
 cname char(20) not null,
 credit integer not null,
 cdept char(10) not null,
 tname char(8) not null);
create table sc
(sno char(4) not null references s(sno),
 cno char(4) not null references c(cno),
 grade integer,
 primary key(sno,cno));
 
 insert into s values('S1','李铭','男','19','计算机软件','S1','S1');
 insert into s values('S2','刘晓鸣','男','20','计算机应用','S2','S2');
 insert into s values('S3','李明','男','22','计算机应用','S3','S3');
 insert into s values('S4','张鹰','女','21','计算机软件','S4','S4');
 insert into s values('S5','刘竟晓','女','22','计算机软件','S5','S5');
 insert into s values('S6','刘成刚','男','21','计算机软件','S6','S6');
 
 insert into c values('C1','高级语言程序设计','4','计算机应用','王晓名');
 insert into c values('C2','数据结构','4','计算机应用','刘红');
 insert into c values('C3','离散数学','4','计算机应用','李严劲');
 insert into c values('C4','计算机原理','6','计算机软件','王晓名');
 insert into c values('C5','数据库原理','4','计算机应用','吴志钢');
 insert into c values('C6','WINDOW技术','4','计算机软件','吴志钢');
 insert into c values('C8','编译原理','4','计算机软件','蒋莹岳');
 insert into c values('C9','系统结构','6','计算机应用','蒋莹岳');
 
 insert into sc values('S1','C2','66');
 insert into sc values('S1','C3','88');
 insert into sc values('S1','C4','60');
 insert into sc values('S4','C1','67');
 insert into sc values('S4','C2','76');
 insert into sc values('S4','C3','67');
 insert into sc values('S3','C1','88');
 insert into sc values('S5','C1','67');
 insert into sc values('S6','C1','78');
 insert into sc values('S2','C9','90');
 insert into sc values('S2','C6',null);
 
