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
------------------------------�������ϱ�
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

-------------�α⵵ ���� ����

select photoNo, likeCnt, 
    dense_rank() over (order by likeCnt desc) as likeRk from photos where publicset='0' and photoNo = '35'  

select count(photoNo) from photos where publicset = '0' --��ä ����

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
    vpop number(4,1);--�α⵵ ���
    vpopRk number(10);--�α⵵ ����
    publicset photos.publicset%Type; --����/�����(�����Ȱ͸�)
    photoNo photos.photoNo%Type;--�ش� ������ȣ
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
-------------------------------------------- like, faves �ջ� ��ü�α⵵ �ۼ�Ʈ()
declare
    vPop number(4,1);--�α⵵ ���  �ۼ�Ʈ
    --vPopRk number(10);--�α⵵ ����
    publicset photos.publicset%Type; --����/�����(�����Ȱ͸�)
    photoNo photos.photoNo%Type;--�ش� ������ȣ
    vCnt number(10);--��ü ���� ����
    
    vLike number(4,1); --like ����ۼ�Ʈ
    vLikeRk number(10);--likeCnt����
    
    vFaves number(4,1); --faves����ۼ�Ʈ
    vFavesRk number(10); --faves����
    
begin
    ----------likeCnt����
    select dense_rank() over (order by likeCnt desc) as likeRk INTO vLikeRk  
                from photos where publicset=0 and photoNo =33;
    
    ----------favest����
    select dense_rank() over (order by faves desc) as favesRk INTO vFavesRk
        from photos where publicset=0 and photoNo=33;
    
    ----------��ü ���� ����
    select count(*) INTO vCnt from photos where publicset=0; 
    
    vLike := 100-(vLikeRk/vCnt*100); --like������
    vFaves := 100-(vFavesRk/vCnt*100); --faves������ 
    
                    
    vPop := (vLike+vFaves)/2;
                    
    
    
    --dbms_output.put_line(vPopRk);
    dbms_output.put_line('like����:'||vLikeRk);
    dbms_output.put_line('faves����:'||vFavesRk);
    dbms_output.put_line('��ü����:'||vCnt);
    
    dbms_output.put_line('like�α⵵:'||vLike);
    dbms_output.put_line('faves�α⵵'||vFaves);
    dbms_output.put_line('�����α⵵:'||vPop);
end;
/  

/*<TOAD_FILE_CHUNK>*/
-------------------------------------------------------Ȯ��
select * from photos where publicset = 0;

select * from photos where photoNo ='35'; 

select count(*) from photos where publicset=0;    

select dense_rank() over (order by likeCnt desc) as popRk from photos where publicset='0' and photoNo = '35';

---------------------------------------------------------------------------------------------�ش� ��ȣ�� ��ũ�� ����ΰ�
select popRk from
(select photoNo, dense_rank() over (order by likeCnt desc) as popRk from photos where publicset='0') where photoNo='35'; 

-------------------------------------------------------------renew
-------------------------------------------- like, faves �ջ� ��ü�α⵵ �ۼ�Ʈ()
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
        (select photoNo, dense_rank() over (order by likeCnt desc) as likeRk from photos where publicset='0') where photoNo='33';
    
    ----------favest����
    select favesRk INTO vFavesRk from 
        (select photoNo, dense_rank() over (order by faves desc) as favesRk from photos where publicset='0') where photoNo='33';
    
    ----------��ü ���� ����
    select count(*) INTO vCnt from photos where publicset=0; 
    
    vLike := 100-(vLikeRk/vCnt*100); --like������
    vFaves := 100-(vFavesRk/vCnt*100); --faves������ 
    
                    
    vPop := (vLike+vFaves)/2;
    
    --dbms_output.put_line(vPopRk);
    dbms_output.put_line('like����:'||vLikeRk);
    dbms_output.put_line('faves����:'||vFavesRk);
    dbms_output.put_line('��ü����:'||vCnt);
    
    dbms_output.put_line('like�α⵵:'||vLike);
    dbms_output.put_line('faves�α⵵'||vFaves);
    dbms_output.put_line('�����α⵵:'||vPop);
end;
/  
    
---------------------------------------------------------------------------------------------------Ʈ���Ź�
---------------------------------------------------------------------------------------------------

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
        (select photoNo, dense_rank() over (order by likeCnt desc) as likeRk from photos where publicset='0') where photoNo=:photos.photoNo;
    
    ----------favest����
    select favesRk INTO vFavesRk from 
        (select photoNo, dense_rank() over (order by faves desc) as favesRk from photos where publicset='0') where photoNo=:photos.photoNo;
    
    ----------��ü ���� ����
    select count(*) INTO vCnt from photos where publicset=0; 
    
    vLike := 100-(vLikeRk/vCnt*100); --like������
    vFaves := 100-(vFavesRk/vCnt*100); --faves������ 
                    
    vPop := (vLike+vFaves)/2;
    
    update photos set popular = vPop
       where photoNo=:photos.photoNo;
    
    --dbms_output.put_line(vPopRk);
    dbms_output.put_line('like����:'||vLikeRk);
    dbms_output.put_line('faves����:'||vFavesRk);
    dbms_output.put_line('��ü����:'||vCnt);
    
    dbms_output.put_line('like�α⵵:'||vLike);
    dbms_output.put_line('faves�α⵵'||vFaves);
    dbms_output.put_line('�����α⵵:'||vPop);
end;
/  

---------------------------------------------------------------------------
select * from photos where publicset = 0 and photoNo= 35;

       update photos set likeCnt=likeCnt+1
            where photoNo='32' and
            (select count(*) from likeCnt where userid='3' and ptNo='32')<=1 