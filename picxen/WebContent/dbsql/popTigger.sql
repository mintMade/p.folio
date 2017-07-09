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
        (select photoNo, dense_rank() over (order by likeCnt desc) as likeRk from photos where publicset='0') where photoNo=:new.photoNo;
    
    ----------favest순위
    select favesRk INTO vFavesRk from 
        (select photoNo, dense_rank() over (order by faves desc) as favesRk from photos where publicset='0') where photoNo=:new.photoNo;
    
    ----------전체 사진 갯수
    select count(*) INTO vCnt from photos where publicset=0; 
    
    vLike := 100-(vLikeRk/vCnt*100); --like최종값
    vFaves := 100-(vFavesRk/vCnt*100); --faves최종값 
                    
    vPop := (vLike+vFaves)/2;
    
    
--   :new.popular:= vPop || :new.popular;
    
  update photos set popular = vPop
      where photoNo=:old.photoNo;
    
    --dbms_output.put_line(vPopRk);
    dbms_output.put_line('like순위:'||vLikeRk);
    dbms_output.put_line('faves순위:'||vFavesRk);
    dbms_output.put_line('전체사진:'||vCnt);
    
    dbms_output.put_line('like인기도:'||vLike);
    dbms_output.put_line('faves인기도'||vFaves);
    dbms_output.put_line('최종인기도:'||vPop);
end;
/



    
--DROP TRIGGER trigger_pop;
 
------------------------------------------------------프로시져

create or replace PROCEDURE procedure_pop
    (vPhotoNo IN photos.photoNo%type) --파라미터변수
       
IS
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
        (select photoNo, dense_rank() over (order by likeCnt desc) as likeRk from photos where publicset='0') where photoNo=vPhotoNo;
    
    ----------favest순위
    select favesRk INTO vFavesRk from 
        (select photoNo, dense_rank() over (order by faves desc) as favesRk from photos where publicset='0') where photoNo=vPhotoNo;
    
    ----------전체 사진 갯수
    select count(*) INTO vCnt from photos where publicset=0; 
    
    vLike := 100-(vLikeRk/vCnt*100); --like최종값
    vFaves := 100-(vFavesRk/vCnt*100); --faves최종값 
                    
    vPop := (vLike+vFaves)/2;
    
    
--   :new.popular:= vPop || :new.popular;
    
  update photos set popular = vPop
      where photoNo = vPhotoNo;
  
  commit;
    
    --dbms_output.put_line(vPopRk);
    dbms_output.put_line('like순위:'||vLikeRk);
    dbms_output.put_line('faves순위:'||vFavesRk);
    dbms_output.put_line('전체사진:'||vCnt);
    
    dbms_output.put_line('like인기도:'||vLike);
    dbms_output.put_line('faves인기도'||vFaves);
    dbms_output.put_line('최종인기도:'||vPop);
end procedure_pop;
/

---------------
select * from photos where photoNo = 33;

update photos set likeCnt = likeCnt+1
    where photoNo='33';
    
execute procedure_pop(33);     