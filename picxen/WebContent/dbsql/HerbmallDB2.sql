--[3] ��ٱ���
drop table Cart cascade constraint;
CREATE TABLE Cart
(
  cartNo	number	Primary Key NOT NULL, 	--�Ϸù�ȣ
  customerId	VarChar2(20) 
	  references member(userid) on delete cascade not null,	 --�����й�ȣ : �������ӹ�ȣ(����ID)
  productNo	number 
	  references products(productNo) NOT NULL,		--��ǰ������ȣ(Product���̺��� ProductNo�ʵ�)
  quantity		number NOT NULL,		--����
  regdate	 	Date    Default sysdate		--������
);

drop sequence cart_seq;
create sequence cart_seq
start with 1
increment by 1
nocache;

Select * From Cart;


--[4] �ֹ�
--drop table orders cascade constraint;
CREATE TABLE Orders
(
  orderNo		number	NOT NULL Primary Key,   --�ֹ���ȣ
  customerId	varchar2(20) 
	references member(userid) NOT NULL ,		--��������ȣ(ȸ�����̵�)(FK)
  totalPrice		number Null,			--�ֹ��ѱݾ�
  deliveryStatus	VarChar2(20) default '�Ա�Ȯ����',		--�ֹ���Ȳ_��ۻ�Ȳ(1:�Ա�Ȯ����, 2:�����Ϸ�, 3:�����, (����غ���) 4:��ۿϷ�)
  orderDate		Date default sysdate ,		--�ֹ�����
  deliveryDate		Date  NULL,		--�������
  paymentEndDate	Date Null,				--�����Ϸ���
  message			varchar2(150),			--����������
  -- pwd		VarChar2(20) Null		--�ֹ���й�ȣ_��ȸ��

  customerName	VarChar2(50),		--�޴»���̸�
  --tel			VarChar2(20) Null,	--��ȭ��ȣ
  hp				VarChar2(20) Null,	--�޴�����ȣ
  zipcode			VarChar2(7) Null,	--�����ȣ
  address			VarChar2(100) Null,	--�ּ�
  addressDetail		VarChar2(50) Null	--���ּ�
);

--drop sequence orders_seq;
create sequence orders_seq
start with 1
increment by 1
nocache;


Select * From Orders;


--[5] �ֹ���
--drop table OrderDetails cascade constraint;
CREATE TABLE OrderDetails
(
	orderNo	number 
		references orders(orderNo) on delete cascade NOT NULL ,	--�ֹ���ȣ(�����Ϸù�ȣ)
	productNo	number 
		references products(productNo) NOT NULL,	--��ǰ��ȣ
	quantity	number NOT NULL,	--�ֹ�����
	constraint orderdetails_pk primary key(orderNo, productNo)
);


Select * From OrderDetails;

--[6] ����� ����
--drop table Delivery cascade constraint;
/*CREATE TABLE Delivery
(
  orderNo		number  primary key 
	references orders(orderNo) on delete cascade Not Null,	--�ֹ���ȣ
  customerName	VarChar2(50),		--�޴� ��� �̸�
  --tel			VarChar2(20) Null,	--��ȭ��ȣ
  hp				VarChar2(20) Null,	--�޴�����ȣ
  zipcode			VarChar2(7) Null,	--�����ȣ
  address			VarChar2(100) Null,	--�ּ�
  addressDetail		VarChar2(50) Null	--���ּ�
);
*/
--------------------------------------------------------------------------------
select  c.cartno, c.quantity, c.productno,
p.imageurl, p.productname, p.sellprice
from cart c,  products  p
where C.PRODUCTNO=p.productno
and c.customerid='hong'

--view �����
--cart, products ���̺��� �����ؼ� ���� ��
--��ٱ��� ��Ͽ��� ���
create or replace view CartView
as
select  c.cartno, c.quantity, c.productno,
p.imageurl, p.productname, p.sellprice, c.customerid
from cart c,  products  p
where C.PRODUCTNO=p.productno;
--------------------------------

select * from cartView
where customerid='hong';

------------------------------------------
--�ֹ��Ϸ� ���������� ����� �� �����
--[1] OrderDetailsView
--orderDetails, product ���̺��� �����ؼ� �ֹ� ������ ��ȸ�ϴ� ��

create or replace view OrderDetailsView
as
select o.orderno, o.productno, o.quantity,
p.productname, p.sellprice, p.imageurl 
from orderDetails o, products p 
where o.productNo=p.productNo;

-------------------------------
select * from OrderDetailsView
where orderno=1;

select * from orderdetails;
select * from products;

--[2] OrdersView
--orders, member2 ���̺��� �����ؼ� ��������� ��ȸ�ϴ� ��
--=> �޴»��, ������ ����� ��� ��ȸ�ؾ� ��

create or replace view OrdersView
as
select o.orderNo, o.customerId, o.totalPrice,
o.deliveryStatus, o.orderDate, o.deliveryDate, 
o.paymentEndDate,
o.message, o.customername,o.hp, o.zipcode, o.address,
o.addressDetail, m.name as sender
from orders o, member2 m
where o.customerid=m.userid;
------------------------
select * from OrdersView
where orderno=1;

select * from orders;
select * from member2;





-----------------------------------------------
Select * From cart;
Select * From orders;
Select * From orderDetails;

Select * From member;

Select * From products;
Select * From eventProduct;


