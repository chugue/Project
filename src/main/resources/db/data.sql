insert into user_tb(email, my_name,password,phone,address,birth, business_number,comp_name,homepage,role,created_at)
values ('blackpig4b@naver.com','감자','1234','010-1234-5822','부산광역시 부산진구 범향빌딩402호','1985-11-24','17-15618-1212','삼성','www.samsung.com','2',now());
insert into user_tb(email, my_name,password,phone,address,birth, business_number,comp_name,homepage,role,created_at)
values ('greenpig4b@naver.com','오이','1234','010-5678-5822','부산광역시 부산진구 범향빌딩403호','1985-11-24','18-15618-1212','네이버','www.naver.com','2',now());
insert into user_tb(email, my_name,password,phone,address,birth,role,created_at)
values ('bluepig4b@naver.com','고구마','1234','010-9011-5822','부산광역시 부산진구 범향빌딩405호','1999-05-06','1',now());

insert into jobs_tb(comp_id,title,area,edu,career,content,dead_line,task,created_at) values (1,'백엔드직원을 구해요','부산','고졸','1년이상','회사오면 맛있는거많음','2024-11-15','백엔드',now());

insert into scrap_tb(resume_id, jobs_id, created_at) values(1, 1, now());
insert into scrap_tb(resume_id, jobs_id, created_at) values(2, 3, now());
insert into scrap_tb(resume_id, jobs_id, created_at) values(3, 1, now());