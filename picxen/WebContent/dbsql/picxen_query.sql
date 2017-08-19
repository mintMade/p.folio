select * from photos where uploader = 'w'
select * from member;
select * from category order by categoryOrder
select viewCnt from photos where photoNo='84'
select * from iplog;
select * from likeCnt;

-- delete from iplog where ptNo >= 1;
-- commit

--update
update photos set viewCnt= viewCnt -1
	where photoNo = '84'
	
-- viewCntUpdate	
	update photos set viewCnt=viewCnt+1
  		  where photoNo='84' and
  		  	(select count(*) from iplog where ip='127.0.0.1' and ptNo='84') < 1

select count(*) from iplog where ptNo = '90' and ip = '127.0.0.1'

-- 카테고리 출력
select * from photos p, category c
   where p.categoryNo = c.categoryNo  
   and categoryName like '%' || '가족' || '%' 
   and publicset <> 1 order by regdate desc
            	            	
-- iplog
MERGE INTO iplog g
    using Dual
    on ((ptNo = '84' and ip = '127.0.0.1'))
    WHEN MATCHED THEN
        Update 
        set g.userid = 'w'
        where  g.userid IS NULL
    WHEN NOT MATCHED THEN
        Insert (logNo, userid, ip, ptNo)
        values (iplog_seq.nextval, 'w', '127.0.0.1', '84')	
	
	
-- iplog viewCnt UPDATE
UPDATE PHOTOS p 
	SET viewCnt = viewCnt +1
	WHERE photoNo= '84'
	AND EXISTS 
   (select 1 from iplog g where ptNo='84' and ip='127.0.0.1' )

-- test select
select * from iplog;
select viewCnt from photos where photoNo='84'
	

-- delete from iplog where ptNo >= 1;
-- commit	
