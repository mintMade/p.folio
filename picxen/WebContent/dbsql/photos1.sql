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

insert into Category values(category_seq.nextval, '가족', 0);
insert into Category values(category_seq.nextval, '사람들', 1);
insert into Category values(category_seq.nextval, '여행', 2);

select * from category order by categoryNo desc;

commit;

--drop TABLE Photos cascade constraint;

create Table Photos
(
    PhotoNo number Not Null Primary Key, --사진 번호
    categoryNo number references category(categoryNo) not null, --카테고리번호
    PhotoTitle varchar2(50) not null, --사진 제목
    imageURL varchar2(50) not null, --상품이미지
    tag varchar2(50), --태그
    description varchar2(4000) null, --상세설명
    regdate Date default sysdate, --상품등록일
    publicSet number Default 0 not null, --공개 비공개설정
    mileage number Default 0, --마일리지
    likeCnt number Default 0 null, --좋아요
    ViewCnt number Default 0 null, --조회수
    Faves number Default 0 null,--즐겨찾기
    popular number Default 0 null, --인기수치
    popDate Date default sysdate,--인기 업데이트날짜
    uploader number references member(no) not null --업로더이름
    --판매가격, 제조회사제회하였음
);

alter table member add(myicon varchar2(50)default 'defaultIcon.jpg' null);

 customerId    VarChar2(20) 
      references member(userid) on delete cascade not null,     --고객구분번호 : 고유접속번호(세션ID)
  productNo    number 
      references products(productNo) NOT NULL,        --상품고유번호(Product테이블의 ProductNo필드)


select * from Photos;
commit;

alter table photos drop(popular)
alter table photos add(popular number(4,1) default 0.0 null); --인기도 소수점 적용

Select * From Category Order By CategoryNo Desc;
select * from member;


create sequence photos_seq
increment by 1
start with 1
nocache;

insert into Photos(PhotoNo, categoryNo, PhotoTitle, imageURL, tag, description, publicSet, 
    mileage, likeCnt, viewCnt, Faves, popular, memberNo )
values(Photos_seq.nextval, 2, '귀여운딸', 'cuty.jpg', '딸', '내 귀여운 딸아이에요', 0, 
    70, 1, 1, 1, 1, 2  );

insert into Photos(PhotoNo, categoryNo, PhotoTitle, imageURL, tag, description, publicSet, 
    mileage, likeCnt, viewCnt, Faves, popular, memberNo )
values(Photos_seq.nextval, 3, '따뜻한 사람들', 'poeple.jpg', '사람들', '보기좋네요', 0, 
    2000, 1, 1, 1, 1, 4  );

insert into Photos(PhotoNo, categoryNo, PhotoTitle, imageURL, tag, description, publicSet, 
    mileage, likeCnt, viewCnt, Faves, popular, memberNo )
values(Photos_seq.nextval, 4, '여행왔어요', 'tour.jpg', 'see', '아름답네요', 0, 
    5000, 1, 1, 1, 1, 2  );
    
insert into Photos(PhotoNo, categoryNo, PhotoTitle, imageURL, tag, description, publicSet, 
    mileage, likeCnt, viewCnt, Faves, popular, memberNo )
values(Photos_seq.nextval, 2, '소중한가족', 'family.jpg', 'wow', '소중한 내가족입니다', 0, 
    1000, 1, 1, 1, 1, 4  );
    
insert into Photos(PhotoNo, categoryNo, PhotoTitle, imageURL, tag, description, publicSet, 
    mileage, likeCnt, viewCnt, Faves, popular, memberNo )
values(Photos_seq.nextval, 4, '사람들', 'pepe.jpg', '1ow', '인상깊네요', 1, 
    8000, 1, 1, 1, 1, 2  );
    
insert into Photos(PhotoNo, categoryNo, PhotoTitle, imageURL, tag, description, publicSet, 
    mileage, likeCnt, viewCnt, Faves, popular, memberNo )
values(Photos_seq.nextval, 2, '멋지죠', 'tour1.jpg', 'aow', '경치좋네요', 0, 
    1200, 1, 1, 1, 1, 4  );
    
select * from Photos;
select * from member;

commit;

create table eventPhoto(
    eventPhotoNo number primary key, --기복키 , 일련번호
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
-- 포토 태이블과 이벤트 테이블에서 이벤트이름이MD인것의 모든 컬럼을 출력 

select p.*from photos p, eventphoto e
        where p.photoNo = e.photoNo
        and eventName='MD';