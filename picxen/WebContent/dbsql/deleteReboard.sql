create or replace procedure deleteReboard --���ν��� �̸� 
(
--�Ű�����
    m_no    number,
    m_step  number,  --������ üũ
    m_groupNo   number --�亯�� �ִ��� üũ
)
is
--���������
    cnt number;
begin
--ó���� ����
    --�亯�� �޸� �������� ��쿡�� �������� ����
    --delFlag�� Y �� update����
    if m_step=0 then  --�������� ���
        --�亯�� �޷ȴ��� Ȯ��
        select count(*) into cnt from reboard
        where groupno=m_groupNo;
        if cnt >1 then  --�亯�� �޸� ���
            update reboard set delflag='Y'
            where no=m_no;
        else  --�亯�� �� �޸� ���
            delete from reboard where no=m_no;
        end if;    
    else  --�亯���� ���
        delete from reboard where no=m_no;
    end if;
    
    commit;

EXCEPTION
    WHEN OTHERS THEN
	DBMS_OUTPUT.PUT_LINE( 'failed!');
        ROLLBACK;
end;

--
--exec deleteReboard(3, 2, 1); --�亯�� ����
--exec deleteReboard(1, 0, 1); --�亯�� �ִ� ������ ����
--select * from reboard order by no;
