create table Category
(
    categoryNo number not null Primary key,
    categoryName varchar2(50),
    categoryOrder number Default 0
);

create sequence category_seq
increment by 1
start with 1
nocache;

insert into Category values(category_seq.nextval, '����', 0);
insert into Category values(category_seq.nextval, '�����', 1);
insert into Category values(category_seq.nextval, '����', 2);

select * from category order by categoryNo desc;

commit;

--drop TABLE Photos cascade constraint;

create Table Photos
(
    PhotoNo number Not Null Primary Key, --���� ��ȣ
    categoryNo number references category(categoryNo) not null, --ī�װ���ȣ
    PhotoTitle varchar2(50) not null, --���� ����
    imageURL varchar2(50) not null, --��ǰ�̹���
    tag varchar2(50), --�±�
    description varchar2(4000) null, --�󼼼���
    regdate Date default sysdate, --��ǰ�����
    publicSet number Default 0 not null, --���� ���������
    mileage number Default 0, --���ϸ���
    likeCnt number Default 0 null, --���ƿ�
    ViewCnt number Default 0 null, --��ȸ��
    Faves number Default 0 null,--���ã��
    popular number Default 0 null, --�α��ġ
    popDate Date default sysdate,--�α� ������Ʈ��¥
    uploader number references member(no) not null --���δ��̸�
    --�ǸŰ���, ����ȸ����ȸ�Ͽ���
);

alter table member add(myicon varchar2(50)default 'defaultIcon.jpg' null);

 customerId    VarChar2(20) 
      references member(userid) on delete cascade not null,     --�����й�ȣ : �������ӹ�ȣ(����ID)
  productNo    number 
      references products(productNo) NOT NULL,        --��ǰ������ȣ(Product���̺��� ProductNo�ʵ�)


select * from Photos;
commit;

alter table photos drop(popular)
alter table photos add(popular number(4,1) default 0.0 null); --�α⵵ �Ҽ��� ����

Select * From Category Order By CategoryNo Desc;
select * from member;


create sequence photos_seq
increment by 1
start with 1
nocache;

insert into Photos(PhotoNo, categoryNo, PhotoTitle, imageURL, tag, description, publicSet, 
    mileage, likeCnt, viewCnt, Faves, popular, memberNo )
values(Photos_seq.nextval, 2, '�Ϳ����', 'cuty.jpg', '��', '�� �Ϳ��� �����̿���', 0, 
    70, 1, 1, 1, 1, 2  );

insert into Photos(PhotoNo, categoryNo, PhotoTitle, imageURL, tag, description, publicSet, 
    mileage, likeCnt, viewCnt, Faves, popular, memberNo )
values(Photos_seq.nextval, 3, '������ �����', 'poeple.jpg', '�����', '�������׿�', 0, 
    2000, 1, 1, 1, 1, 4  );

insert into Photos(PhotoNo, categoryNo, PhotoTitle, imageURL, tag, description, publicSet, 
    mileage, likeCnt, viewCnt, Faves, popular, memberNo )
values(Photos_seq.nextval, 4, '����Ծ��', 'tour.jpg', 'see', '�Ƹ���׿�', 0, 
    5000, 1, 1, 1, 1, 2  );
    
insert into Photos(PhotoNo, categoryNo, PhotoTitle, imageURL, tag, description, publicSet, 
    mileage, likeCnt, viewCnt, Faves, popular, memberNo )
values(Photos_seq.nextval, 2, '�����Ѱ���', 'family.jpg', 'wow', '������ �������Դϴ�', 0, 
    1000, 1, 1, 1, 1, 4  );
    
insert into Photos(PhotoNo, categoryNo, PhotoTitle, imageURL, tag, description, publicSet, 
    mileage, likeCnt, viewCnt, Faves, popular, memberNo )
values(Photos_seq.nextval, 4, '�����', 'pepe.jpg', '1ow', '�λ��׿�', 1, 
    8000, 1, 1, 1, 1, 2  );
    
insert into Photos(PhotoNo, categoryNo, PhotoTitle, imageURL, tag, description, publicSet, 
    mileage, likeCnt, viewCnt, Faves, popular, memberNo )
values(Photos_seq.nextval, 2, '������', 'tour1.jpg', 'aow', '��ġ���׿�', 0, 
    1200, 1, 1, 1, 1, 4  );
    
select * from Photos;
select * from member;

commit;

create table eventPhoto(
    eventPhotoNo number primary key, --�⺹Ű , �Ϸù�ȣ
    photoNo number references photos(photoNo) on delete cascade not null,
    eventName varchar2(30)
);

create sequence eventPhoto_seq
increment by 1
start with 1
nocache;

insert into eventPhoto(eventPhotoNo, photoNo, eventName)
values(eventPhoto_seq.nextval,2, 'NEW' );

insert into eventPhoto(eventPhotoNo, photoNo, eventName)
values(eventPhoto_seq.nextval,4, 'NEW' );

insert into eventPhoto(eventPhotoNo, photoNo, eventName)
values(eventPhoto_seq.nextval,5, 'NEW' );

insert into eventPhoto(eventPhotoNo, photoNo, eventName)
values(eventPhoto_seq.nextval,6, 'NEW' );

insert into eventPhoto(eventPhotoNo, photoNo, eventName)
values(eventPhoto_seq.nextval,7, 'NEW' );


insert into eventPhoto(eventPhotoNo, photoNo, eventName)
values(eventPhoto_seq.nextval,2, 'BEST' );

insert into eventPhoto(eventPhotoNo, photoNo, eventName)
values(eventPhoto_seq.nextval,4, 'BEST' );

insert into eventPhoto(eventPhotoNo, photoNo, eventName)
values(eventPhoto_seq.nextval,5, 'BEST' );

insert into eventPhoto(eventPhotoNo, photoNo, eventName)
values(eventPhoto_seq.nextval,6, 'BEST' );

insert into eventPhoto(eventPhotoNo, photoNo, eventName)
values(eventPhoto_seq.nextval,7, 'BEST' );

insert into eventPhoto(eventPhotoNo, photoNo, eventName)
values(eventPhoto_seq.nextval,8, 'BEST' );



insert into eventPhoto(eventPhotoNo, photoNo, eventName)
values(eventPhoto_seq.nextval,5, 'MD' );

insert into eventPhoto(eventPhotoNo, photoNo, eventName)
values(eventPhoto_seq.nextval,6, 'MD' );

insert into eventPhoto(eventPhotoNo, photoNo, eventName)
values(eventPhoto_seq.nextval,7, 'MD' );

insert into eventPhoto(eventPhotoNo, photoNo, eventName)
values(eventPhoto_seq.nextval,8, 'MD' );

select * from eventPhoto;
select * from photos;

commit;

select * from photos p, eventPhoto e
where P.PHOTONO = e.photoNo
and e.eventName = 'MD'; 
-- ���� ���̺�� �̺�Ʈ ���̺��� �̺�Ʈ�̸���MD�ΰ��� ��� �÷��� ��� 

select p.*from photos p, eventphoto e
        where p.photoNo = e.photoNo
        and eventName='MD';