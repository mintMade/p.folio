create or replace TRIGGER trigger_pop
    after update 
    OF likeCnt, Faves 
    On photos
       for each row 
declare
    vPop number(4,1);--�α⵵ ���  �ۼ�Ʈ
    --vPopRk number(10);--�α⵵ ����
    publicset photos.publicset%Type; --����/�����(�����Ȱ͸�)
    photoNo photos.photoNo%Type;--�ش� ������ȣ
    vCnt number(10);--��ü ���� ����
    
    vLike number(4,1); --like ����ۼ�Ʈ
    vLikeRk number(10);--
 
    
    vFaves number(4,1); --faves����ۼ�Ʈ
    vFavesRk number(10); --faves����
begin
    ----------likeCnt����
   select likeRk INTO vLikeRk from 
        (select photoNo, dense_rank() over (order by likeCnt desc) as likeRk from photos where publicset='0') where photoNo=:new.photoNo;
    
    ----------favest����
    select favesRk INTO vFavesRk from 
        (select photoNo, dense_rank() over (order by faves desc) as favesRk from photos where publicset='0') where photoNo=:new.photoNo;
    
    ----------��ü ���� ����
    select count(*) INTO vCnt from photos where publicset=0; 
    
    vLike := 100-(vLikeRk/vCnt*100); --like������
    vFaves := 100-(vFavesRk/vCnt*100); --faves������ 
                    
    vPop := (vLike+vFaves)/2;
    
    
--   :new.popular:= vPop || :new.popular;
    
  update photos set popular = vPop
      where photoNo=:old.photoNo;
    
    --dbms_output.put_line(vPopRk);
    dbms_output.put_line('like����:'||vLikeRk);
    dbms_output.put_line('faves����:'||vFavesRk);
    dbms_output.put_line('��ü����:'||vCnt);
    
    dbms_output.put_line('like�α⵵:'||vLike);
    dbms_output.put_line('faves�α⵵'||vFaves);
    dbms_output.put_line('�����α⵵:'||vPop);
end;
/



    
--DROP TRIGGER trigger_pop;
 
------------------------------------------------------���ν���

create or replace PROCEDURE procedure_pop
    (vPhotoNo IN photos.photoNo%type) --�Ķ���ͺ���
       
IS
    vPop number(4,1);--�α⵵ ���  �ۼ�Ʈ
    --vPopRk number(10);--�α⵵ ����
    publicset photos.publicset%Type; --����/�����(�����Ȱ͸�)
    photoNo photos.photoNo%Type;--�ش� ������ȣ
    vCnt number(10);--��ü ���� ����
    
    vLike number(4,1); --like ����ۼ�Ʈ
    vLikeRk number(10);--
 
    
    vFaves number(4,1); --faves����ۼ�Ʈ
    vFavesRk number(10); --faves����
begin
    ----------likeCnt����
   select likeRk INTO vLikeRk from 
        (select photoNo, dense_rank() over (order by likeCnt desc) as likeRk from photos where publicset='0') where photoNo=vPhotoNo;
    
    ----------favest����
    select favesRk INTO vFavesRk from 
        (select photoNo, dense_rank() over (order by faves desc) as favesRk from photos where publicset='0') where photoNo=vPhotoNo;
    
    ----------��ü ���� ����
    select count(*) INTO vCnt from photos where publicset=0; 
    
    vLike := 100-(vLikeRk/vCnt*100); --like������
    vFaves := 100-(vFavesRk/vCnt*100); --faves������ 
                    
    vPop := (vLike+vFaves)/2;
    
    
--   :new.popular:= vPop || :new.popular;
    
  update photos set popular = vPop
      where photoNo = vPhotoNo;
  
  commit;
    
    --dbms_output.put_line(vPopRk);
    dbms_output.put_line('like����:'||vLikeRk);
    dbms_output.put_line('faves����:'||vFavesRk);
    dbms_output.put_line('��ü����:'||vCnt);
    
    dbms_output.put_line('like�α⵵:'||vLike);
    dbms_output.put_line('faves�α⵵'||vFaves);
    dbms_output.put_line('�����α⵵:'||vPop);
end procedure_pop;
/

---------------
select * from photos where photoNo = 33;

update photos set likeCnt = likeCnt+1
    where photoNo='33';
    
execute procedure_pop(33);     