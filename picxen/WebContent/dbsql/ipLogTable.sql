
create table iplog(
    logNo number primary key,
    userid varchar2(30) references member(userid) null,
    ip varchar2(15) null,
    ptNo number references photos(photoNo) null,
    idate date default sysdate
);

create sequence iplog_seq
increment by 1
start with 1
nocache;

commit

select * from photos

select * from iplog

alter table iplog rename column ip to regip;
commit
alter table iplog drop(ptNo);
alter table iplog add constraint 

alter table photos add constraint uploader_FK foreign key (uploader) references member(userid);

drop table iplog cascade constraint;

drop sequence iplog_seq;
-----------------------------------------------------------

commit

insert into iplog(logNo, userid, ip, ptNo)
            values(1, 'w', '0000', 23);
            
desc iplog
alter table iplog modify(ip varchar2(40));
commit

        update photos set viewCnt=viewCnt+1
            where photoNo=#ptNo#
            
update photos set viewCnt=viewCnt+1
where  ptNo IN (SELECT eno
                   FROM iplog
                   WHERE ip ='127.0.0.1' and ptNo='26'  );
                   
Merge into photos p
    Using iplog i
   on (ip='127.0.0.1')
when matched then               
    update set viewCnt = viewCnt+0
when not matched then
    update set viewCnt = viewCnt+1; 
    
select * from photos
select * from iplog
select ip, ptNo from iplog where ip='127.0.0.1' and ptNo='25' 


select count(*) from iplog where ip='127.0.0.1' and ptNo='25'

merge into iplog i
    using (select ip from iplog where ip='127.0.0.1' and ptNo='25')
    on (i.ptNo='25' = p.photoNo='25')
when matched then 
    update set viewCnt = viewCnt+1;
    
    
    
update photos set viewCnt = viewCnt+1
    where photoNo='25'  (select ptNo from iplog 
    where ip='127.0.0.1');
    
 
update photos set viewCnt = viewCnt+1
    where photoNo='25' and iplog.ip='127.0.0.1' and iplog.ptNo='25';


select * from photos
select * from iplog

--------------------------------------------------------------------------------
update photos set viewCnt=viewCnt+1
    where photoNo='33' and(select count(*) from iplog where ip='127.0.0.1' and ptNo='33') <=1;     
    
    
    
select * from photos where photoNo='24' 
select * from iplog where userid='w' and ptNo='33' and ip='127.0.0.1'

select count(*) from iplog where ip='127.0.0.1' and ptNo='33'

select * from iplog where ip='127.0.0.1' and ptNo='33'

select * from iplog where ip='127.0.0.1' and ptNo='33' and userid='w'

select * from iplog where ip='127.0.0.1' and ptNo='33'
--------------------------------------------------------------------------------
