--sys나 system 계정으로 로그인해서 사용자 생성, 테이블스페이스 생성하기
--sqlplus "/as sysdba  ==>시스템dba접속

--테이블 스페이스 생성
create tablespace herbmall
datafile 'C:\oracle\data\herbmall.dbf' size 20m;


--사용자 계정 생성
create user herb
identified by herb
default tablespace herbmall;

--사용자 권한부여
grant connect,resource to herb;
--grant connect, dba to herb with admin option;

--뷰 생성 권한 부여하기
grant create view to herb;