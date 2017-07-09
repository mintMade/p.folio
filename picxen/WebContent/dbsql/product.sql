CREATE TABLE Category
(
    categoryNo		number	NOT NULL Primary Key, --ī�װ� �Ϸù�ȣ
    categoryName		VarChar2(50),                 --ī�װ���
    categoryOrder		number	Default 0             --ī�װ��������¼���
);

create sequence category_seq
increment by 1
start with 1
nocache;

CREATE TABLE Products
(
  productNo	number	NOT NULL	Primary Key,	--��ǰ��ȣ
  categoryNo	number	references category(categoryNo) NOT NULL,	--ī�װ���ȣ
  productName	VarChar2(50),                           --��ǰ��
  sellPrice		number Null,                            --�ǸŰ�
  company		VarChar2(50),                           --����ȸ��
  --eventName	VarChar2(50) default 'GEN'  Null,
	--�Ż�ǰ(NEW), ����Ʈ(BEST), MD��õ(MD), �Ϲ�(GEN), ��������(NONE)
  imageURL		VarChar2(50),                           --��ǰ�̹���
  explain		VarChar2(400),                          --��༳��
  description	varchar2(4000) Null,                    --�󼼼���
  regDate		Date Default sysdate,			--��ǰ�����
  mileage		number Default 0                            --���ϸ���(������)
);

create sequence products_seq
increment by 1
start with 1
nocache;


create table eventproduct(
  eventProductNo	number primary key, --�⺻Ű, �Ϸù�ȣ
  productNo	number references products(productNo) on delete cascade not null, --��ǰ��ȣ
  eventName	VarChar2(30) --NEW, BEST, MD
);

create sequence eventproduct_seq
increment by 1
start with 1
nocache;