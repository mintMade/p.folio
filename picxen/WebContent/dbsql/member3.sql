select * from photos
select * from member
select * from category
desc member
desc photos




alter table photos add(uploader varchar2(30)); 
references member(userid) not null; 
 
alter table photos add constraint uploader_FK foreign key (uploader) references member(userid);


--����
alter table photos drop constraint userid_FK;
alter table photos drop(userid);

############### test table�� osy�� number(10)Ÿ���� �÷� �߰� ######################
SQL> alter table test add(osy number(10))

commit