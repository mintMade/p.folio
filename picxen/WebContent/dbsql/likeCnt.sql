/*<TOAD_FILE_CHUNK>*/
select * from photos
select * from member;
select * from likeCnt;
desc member

create table likeCnt (
    likeNo number not null primary key,
    memNo number references member(memberNo) not null,
    ptNo number references photos(photoNo) not null
);


create sequence likeCnt_seq
increment by 1
start with 1
nocache;

commit;

alter table likeCnt drop (memNo);
alter table likeCnt add(userid varchar2(30) references member(userid) not null);


                
        update photos set likeCnt=likeCnt+1
            where photoNo='32' and
            (select count(*) from likeCnt where userid='3' and ptNo='32')<=1     
            
        select count(*) from likeCnt 
                where userid='3'
                and ptNo='32'        
------------------------------순위구하기
select photoNo , dense_rank() over (order by point desc) as rank
from
    (select (select photoNo from photos where publicset='0') as photoNo, 
        (select popular from photos where photoNo='35') as point from dual 

    );
    
select '35' as photoNo from photos where uploader ='w' and photoNo='35'

select popular from photos where photoNo='35'

select popular, dense_rank() over from photos where publicset='0' order by desc

select * from photos



update photos set pupular = 

-------------인기도 순위 쿼리

select photoNo, likeCnt, 
    dense_rank() over (order by likeCnt desc) as likeRk from photos where publicset='0' and photoNo = '35'  

select count(photoNo) from photos where publicset = '0' --전채 개수

declare 
    pop int:= 0;
    rk int:=0;
    cnt int:=0;
begin
    pop := 100 - ((select dense_rank() over (order by popular desc) as popRk 
                from photos where publicset='0' and photoNo = '35') 
                / (select count(photoNo) from photos where publicset = '0') * 100);
            end;/    
            

/*<TOAD_FILE_CHUNK>*/
declare 
    pop int:= 0;
    photoNo int:='35';
    publicset int := '0';
    
    popRk int:=0;
    cnt int:=0;
begin
    select dense_rank() over (order by popular desc) as popRk 
                from photos where publicset=:publicset and photoNo =: photoNo;
                
    select count(photoNo) as cnt from photos where publicset =:publicset;
    
    pop := 100 - (popRk / cnt  * 100);
            end;
            

            
declare
    --------
    vpop number(4,1);--인기도 결과
    vpopRk number(10);--인기도 순위
    publicset photos.publicset%Type; --공개/비공개(공개된것만)
    photoNo photos.photoNo%Type;--해당 사진번호
    vcnt number(10);
begin
    select dense_rank() over (order by popular desc) as popRk INTO vpopRk  
                from photos where publicset=0 and photoNo =35;
                
    select count(*) INTO vcnt from photos; 
                    
    vpop := 100-(vpopRk/vcnt*100);
                    
    
    
    dbms_output.put_line(vpopRk);
    dbms_output.put_line(vcnt);
    dbms_output.put_line(vpop);
end;
/            

/*<TOAD_FILE_CHUNK>*/
-------------------------------------------- like, faves 합산 전체인기도 퍼센트()
declare
    vPop number(4,1);--인기도 결과  퍼센트
    --vPopRk number(10);--인기도 순위
    publicset photos.publicset%Type; --공개/비공개(공개된것만)
    photoNo photos.photoNo%Type;--해당 사진번호
    vCnt number(10);--전체 사진 개수
    
    vLike number(4,1); --like 결과퍼센트
    vLikeRk number(10);--likeCnt순위
    
    vFaves number(4,1); --faves결과퍼센트
    vFavesRk number(10); --faves순위
    
begin
    ----------likeCnt순위
    select dense_rank() over (order by likeCnt desc) as likeRk INTO vLikeRk  
                from photos where publicset=0 and photoNo =33;
    
    ----------favest순위
    select dense_rank() over (order by faves desc) as favesRk INTO vFavesRk
        from photos where publicset=0 and photoNo=33;
    
    ----------전체 사진 갯수
    select count(*) INTO vCnt from photos where publicset=0; 
    
    vLike := 100-(vLikeRk/vCnt*100); --like최종값
    vFaves := 100-(vFavesRk/vCnt*100); --faves최종값 
    
                    
    vPop := (vLike+vFaves)/2;
                    
    
    
    --dbms_output.put_line(vPopRk);
    dbms_output.put_line('like순위:'||vLikeRk);
    dbms_output.put_line('faves순위:'||vFavesRk);
    dbms_output.put_line('전체사진:'||vCnt);
    
    dbms_output.put_line('like인기도:'||vLike);
    dbms_output.put_line('faves인기도'||vFaves);
    dbms_output.put_line('최종인기도:'||vPop);
end;
/  

/*<TOAD_FILE_CHUNK>*/
-------------------------------------------------------확인
select * from photos where publicset = 0;

select * from photos where photoNo ='35'; 

select count(*) from photos where publicset=0;    

select dense_rank() over (order by likeCnt desc) as popRk from photos where publicset='0' and photoNo = '35';

---------------------------------------------------------------------------------------------해당 번호의 랭크가 몇등인가
select popRk from
(select photoNo, dense_rank() over (order by likeCnt desc) as popRk from photos where publicset='0') where photoNo='35'; 

-------------------------------------------------------------renew
-------------------------------------------- like, faves 합산 전체인기도 퍼센트()
declare
    vPop number(4,1);--인기도 결과  퍼센트
    --vPopRk number(10);--인기도 순위
    publicset photos.publicset%Type; --공개/비공개(공개된것만)
    photoNo photos.photoNo%Type;--해당 사진번호
    vCnt number(10);--전체 사진 개수
    
    vLike number(4,1); --like 결과퍼센트
    vLikeRk number(10);--
 
    
    vFaves number(4,1); --faves결과퍼센트
    vFavesRk number(10); --faves순위

    
begin
    ----------likeCnt순위
   select likeRk INTO vLikeRk from 
        (select photoNo, dense_rank() over (order by likeCnt desc) as likeRk from photos where publicset='0') where photoNo='33';
    
    ----------favest순위
    select favesRk INTO vFavesRk from 
        (select photoNo, dense_rank() over (order by faves desc) as favesRk from photos where publicset='0') where photoNo='33';
    
    ----------전체 사진 갯수
    select count(*) INTO vCnt from photos where publicset=0; 
    
    vLike := 100-(vLikeRk/vCnt*100); --like최종값
    vFaves := 100-(vFavesRk/vCnt*100); --faves최종값 
    
                    
    vPop := (vLike+vFaves)/2;
    
    --dbms_output.put_line(vPopRk);
    dbms_output.put_line('like순위:'||vLikeRk);
    dbms_output.put_line('faves순위:'||vFavesRk);
    dbms_output.put_line('전체사진:'||vCnt);
    
    dbms_output.put_line('like인기도:'||vLike);
    dbms_output.put_line('faves인기도'||vFaves);
    dbms_output.put_line('최종인기도:'||vPop);
end;
/  
    
---------------------------------------------------------------------------------------------------트리거문
---------------------------------------------------------------------------------------------------

create or replace TRIGGER trigger_pop
    after update 
    OF likeCnt, Faves 
    On photos
    for each row 
declare
    vPop number(4,1);--인기도 결과  퍼센트
    --vPopRk number(10);--인기도 순위
    publicset photos.publicset%Type; --공개/비공개(공개된것만)
    photoNo photos.photoNo%Type;--해당 사진번호
    vCnt number(10);--전체 사진 개수
    
    vLike number(4,1); --like 결과퍼센트
    vLikeRk number(10);--
 
    
    vFaves number(4,1); --faves결과퍼센트
    vFavesRk number(10); --faves순위

    
begin
    ----------likeCnt순위
   select likeRk INTO vLikeRk from 
        (select photoNo, dense_rank() over (order by likeCnt desc) as likeRk from photos where publicset='0') where photoNo=:photos.photoNo;
    
    ----------favest순위
    select favesRk INTO vFavesRk from 
        (select photoNo, dense_rank() over (order by faves desc) as favesRk from photos where publicset='0') where photoNo=:photos.photoNo;
    
    ----------전체 사진 갯수
    select count(*) INTO vCnt from photos where publicset=0; 
    
    vLike := 100-(vLikeRk/vCnt*100); --like최종값
    vFaves := 100-(vFavesRk/vCnt*100); --faves최종값 
                    
    vPop := (vLike+vFaves)/2;
    
    update photos set popular = vPop
       where photoNo=:photos.photoNo;
    
    --dbms_output.put_line(vPopRk);
    dbms_output.put_line('like순위:'||vLikeRk);
    dbms_output.put_line('faves순위:'||vFavesRk);
    dbms_output.put_line('전체사진:'||vCnt);
    
    dbms_output.put_line('like인기도:'||vLike);
    dbms_output.put_line('faves인기도'||vFaves);
    dbms_output.put_line('최종인기도:'||vPop);
end;
/  

---------------------------------------------------------------------------
select * from photos where publicset = 0 and photoNo= 35;

       update photos set likeCnt=likeCnt+1
            where photoNo='32' and
            (select count(*) from likeCnt where userid='3' and ptNo='32')<=1 