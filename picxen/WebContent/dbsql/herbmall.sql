--sys��system �������� �α���
--���̺� �����̽� ����

create tablespace herbmall
datafile 'h:\oracle\data\herbmall.dbf' size 20m;

--����� ���� ����
create user herb
identified by herb
default tablespace herbmall;

--����� ���� �ο�
grant connect, resource to herb;
--grant connect, dba to herb with admin option;

--�� ���� ���� �ο��ϱ�
grant create view to herb;