--������� ���̺�
create table board
(
    no number primary key, --��ȣ
    name varchar2(20) not null, --��й�ȣ
    pwd varchar2(20) not null,
    title varchar2(100) null, --����
    email varchar2(80) null, --�̸���
    regdate date default sysdate, --�ۼ���
    readcount number default 0 null, --��ȸ��
    content clob null -- ����
);

create sequence board_seq
increment by 1
start with 1
nocache;

commit

select * from board;