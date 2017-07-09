select * from member where userid='w'

select * from photos
select * from member

select lName, fName
from photos p, member m
where p.uploader = m.userid
and p.uploader='w';
    

select lName, fName
from photos p, member m
where P.UPLOADER = m.userid
and photoNo=18;

select lName, fName, userid
from photos p, member m
where P.UPLOADER = m.userid
and photoNo=18;

select lName, fName, userid
from photos p, member m
where p.uploader = m.userid
and photoNo=18;


select * 
from member 
where lName='w' and fName='w';

select * from member
where userid='w';

-------------userphoto-----------------

select * from photos;

select * 
from photos p, member m 
where P.UPLOADER = m.userid
and p.uploader='w';

select *
from photos
minus 
select * from  photos where publicset='1'; 

select p.*
from photos p, member m 
where p.uploader = m.userid
and p.uploader='w'
and p.publicset <>1;

select p.*
from photos p, member m 
where p.uploader = m.userid
and m.userid='w'
and p.publicset <>1;
 

select p.*
from photos p, member m 
where p.uploader = m.userid
and m.userid='w'
and p.publicset <>1;

desc photos;
desc member

alter table member modify(userid varchar2(30));
commit;

alter table photos modify(publicset varchar2(30));

select * from photos

select * 
from photos 
where publicset <>1 order by regdate desc;


