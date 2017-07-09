--sys나system 계정으로 로그인
--테이블 스페이스 생성

create tablespace herbmall
datafile 'h:\oracle\data\herbmall.dbf' size 20m;

--사용자 계정 생성
create user herb
identified by herb
default tablespace herbmall;

--사용자 권한 부여
grant connect, resource to herb;
--grant connect, dba to herb with admin option;

--뷰 생성 권한 부여하기
grant create view to herb;