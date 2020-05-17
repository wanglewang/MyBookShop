```
show databases;      //��ʾ���п���Ϣ
drop database bk;    //ɾ��ԭ�п�
create database bk;

use bk;                //�򿪿�
show tables;

drop table tuser;
create table TUser
(
   uname                varchar(20) not null,
   pwd                  varchar(15),
   role                 int,
   tel                  varchar(11),
   rtime                datetime,
   account              double,
   primary key (uname)
);
insert into tuser values('admin','123456',1,'1362334567',now(),3000);
insert into tuser values('tom','123456',2,'1362334567',now(),1000);
insert into tuser values('jack','123456',3,'1362334567',now(),1000);

commit;


create table TCategory
(
   cid                  varchar(5) not null,
   cname                varchar(20),
   primary key (cid)
);
create table TBook
(
   isbn                 varchar(10) not null,
   cid                  varchar(5),
   bname                varchar(50),
   author               varchar(30),
   press                varchar(30),
   pdate                date,
   pic                  varchar(50),
   info                 varchar(2000),
   discount             double,
   price                double,
   num                  int,
   primary key (isbn)
);
alter table TBook add constraint FK_Reference_1 foreign key (cid)
      references TCategory (cid) on delete restrict on update restrict;

insert into TCategory values('c001','��ʷ');
insert into TCategory values('c002','�Ƽ�');
insert into TCategory values('c003','С˵');
insert into tbook values('1010001234','c001','����־','�޹���','���ĳ�����','1998-10-1','/bkpic/����.jpg' ,'xxxxddddssss',1.0,38.5,1000);
insert into tbook values('1010001236','c003','���μ�','��ж�','�ʵ������','2008-10-1','/bkpic/���μ�.jpg','xxxxddddssss',0.85,58.6,1000);


--�ҵ��ղ�
drop table TCollect;
create table TCollect
(
   cid                  int not null auto_increment,
   uname                varchar(20),
   isbn                 varchar(10),
   primary key (cid)
);

alter table TCollect add constraint FK_Reference_2 foreign key (uname)
      references TUser (uname) on delete restrict on update restrict;

alter table TCollect add constraint FK_Reference_3 foreign key (isbn)
      references TBook (isbn) on delete restrict on update restrict;

--����
drop table TPing;
create table TPing
(
   pid                  int not null auto_increment ,
   uname                varchar(20),
   isbn                 varchar(10),
   info                 varchar(600),
   primary key (pid)
);

alter table TPing add constraint FK_Reference_7 foreign key (uname)
      references TUser (uname) on delete restrict on update restrict;

alter table TPing add constraint FK_Reference_8 foreign key (isbn)
      references TBook (isbn) on delete restrict on update restrict;

--����
create table TOrder
(
   oid                  varchar(28) not null,
   uname                varchar(20),
   allMoney             double,
   payTime              datetime,
   primary key (oid)
);

alter table TOrder add constraint FK_Reference_4 foreign key (uname)
      references TUser (uname) on delete restrict on update restrict;

create table TOrderDetail
(
   dno                  int not null auto_increment,
   oid                  varchar(28),
   isbn                 varchar(10),
   buyNum               int,
   rprice               double,
   primary key (dno)
);

alter table TOrderDetail add constraint FK_Reference_5 foreign key (oid)
      references TOrder (oid) on delete restrict on update restrict;

alter table TOrderDetail add constraint FK_Reference_6 foreign key (isbn)
      references TBook (isbn) on delete restrict on update restrict;
```