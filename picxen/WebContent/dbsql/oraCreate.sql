--sys�� system �������� �α����ؼ� ����� ����, ���̺����̽� �����ϱ�
--sqlplus "/as sysdba  ==>�ý���dba����

--���̺� �����̽� ����
create tablespace herbmall
datafile 'C:\oracle\data\herbmall.dbf' size 20m;


--����� ���� ����
create user herb
identified by herb
default tablespace herbmall;

--����� ���Ѻο�
grant connect,resource to herb;
--grant connect, dba to herb with admin option;

--�� ���� ���� �ο��ϱ�
grant create view to herb;