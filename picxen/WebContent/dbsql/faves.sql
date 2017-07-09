create table faves(
    faveNo number primary key not null,
    fUserId Varchar2(30) 
        references member(userid) on delete cascade not null,--즐겨찾기 등록 아이디 : 고유접속번호(세션ID)
    photoNo number
        references photos(photoNo) not null
);

--drop sequence faves_seq;
create sequence faves_seq
    start with 1
    increment by 1
    nocache;

desc member
select * from photos
select * from faves

insert into faves(faveNo, fUserId, photoNo)
  values('1', 'w', '2');
  
  commit;

update photos set faves = faves+1
    where photoNo='2' and (select count(*) from faves where fUserid='w' and photoNo='2') <= 0;

select count(*) from faves where fUserid='w' and photoNo='2';

-----------------------------------------photoLike

select * from iplog where userid ='3' and ptNo='32'

select * from photos where photono = '16'

select * from faves where photoNo='32'

select * from photos where photoNo='32'

select * from likeCnt where userid='3'

        update photos set faves = faves+1
            where photoNo=#photoNo# and 
                (select count(*) from faves where fUserid=#fUserId# and photoNo=#photoNo#) <= 0
                
                
        update photos set likeCnt=likeCnt+1
            where photoNo='32' and
            (select count(*) from likeCnt where userid='3' and ptNo='32')<=1     
            
        select count(*) from likeCnt 
                where userid='3'
                and ptNo='32'                      