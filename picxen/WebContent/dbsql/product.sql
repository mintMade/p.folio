CREATE TABLE Category
(
    categoryNo		number	NOT NULL Primary Key, --카테고리 일련번호
    categoryName		VarChar2(50),                 --카테고리명
    categoryOrder		number	Default 0             --카테고리보여지는순서
);

create sequence category_seq
increment by 1
start with 1
nocache;

CREATE TABLE Products
(
  productNo	number	NOT NULL	Primary Key,	--상품번호
  categoryNo	number	references category(categoryNo) NOT NULL,	--카테고리번호
  productName	VarChar2(50),                           --상품명
  sellPrice		number Null,                            --판매가
  company		VarChar2(50),                           --제조회사
  --eventName	VarChar2(50) default 'GEN'  Null,
	--신상품(NEW), 베스트(BEST), MD추천(MD), 일반(GEN), 진열안함(NONE)
  imageURL		VarChar2(50),                           --상품이미지
  explain		VarChar2(400),                          --요약설명
  description	varchar2(4000) Null,                    --상세설명
  regDate		Date Default sysdate,			--상품등록일
  mileage		number Default 0                            --마일리지(적립금)
);

create sequence products_seq
increment by 1
start with 1
nocache;


create table eventproduct(
  eventProductNo	number primary key, --기본키, 일련번호
  productNo	number references products(productNo) on delete cascade not null, --상품번호
  eventName	VarChar2(30) --NEW, BEST, MD
);

create sequence eventproduct_seq
increment by 1
start with 1
nocache;