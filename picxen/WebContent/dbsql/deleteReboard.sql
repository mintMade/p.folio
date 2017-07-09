create or replace procedure deleteReboard --프로시저 이름 
(
--매개변수
    m_no    number,
    m_step  number,  --원본글 체크
    m_groupNo   number --답변이 있는지 체크
)
is
--변수선언부
    cnt number;
begin
--처리할 내용
    --답변이 달린 원본글인 경우에는 삭제하지 말고
    --delFlag를 Y 로 update하자
    if m_step=0 then  --원본글인 경우
        --답변이 달렸는지 확인
        select count(*) into cnt from reboard
        where groupno=m_groupNo;
        if cnt >1 then  --답변이 달린 경우
            update reboard set delflag='Y'
            where no=m_no;
        else  --답변이 안 달린 경우
            delete from reboard where no=m_no;
        end if;    
    else  --답변글인 경우
        delete from reboard where no=m_no;
    end if;
    
    commit;

EXCEPTION
    WHEN OTHERS THEN
	DBMS_OUTPUT.PUT_LINE( 'failed!');
        ROLLBACK;
end;

--
--exec deleteReboard(3, 2, 1); --답변글 삭제
--exec deleteReboard(1, 0, 1); --답변이 있는 원본글 삭제
--select * from reboard order by no;
