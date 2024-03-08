-- 개인 유저
insert into user_tb(email, my_name, password, phone, address, birth, role, created_at)
values ('bluepig4b@naver.com','고구마','1234','010-9011-5822','부산광역시 부산진구 범향빌딩405호','1999-05-06','1',now());
insert into user_tb(email, my_name, password, phone, address, birth, role, created_at)
values ('blueocean@naver.com','맛있다','1234','010-1555-5842','부산광역시 부산진구 범향빌딩405호','1989-10-06','1',now());
insert into user_tb(email, my_name, password, phone, address, birth, role, created_at)
values ('kkkk@naver.com','임꺽정','1234','010-8787-5842','서울특별시 마포구 새창로2길 17','1989-10-06','1',now());

-- 기업 유저
insert into user_tb(email, my_name, password, phone, address, birth, business_number, comp_name, homepage, role, created_at)
values ('blackpig4b@naver.com','감자','1234','010-1234-5822','부산광역시 부산진구 범향빌딩402호','1985-11-24','17-15618-1212','삼성','www.samsung.com','2',now());
insert into user_tb(email, my_name, password, phone, address, birth, business_number, comp_name, homepage, role, created_at)
values ('greenpig4b@naver.com','오이','1234','010-5678-5822','부산광역시 부산진구 범향빌딩403호','1985-11-24','18-15618-1212','네이버','www.naver.com','2',now());
insert into user_tb(email, my_name, password, phone, address, birth, business_number, comp_name, homepage, role, created_at)
values ('pinkpig@naver.com','당근','1234','010-9824-5421','부산광역시 부산진구 범향빌딩402호','1985-11-24','606-17-12120','배민','www.bamin.com','2',now());
insert into user_tb(email, my_name, password, phone, address, birth, business_number, comp_name, homepage, role, created_at)
values ('yellopig@naver.com','상추','1234','010-8868-5232','부산광역시 부산진구 범향빌딩403호','1985-11-24','302-18-55710','쿠팡','www.coopang.com','2',now());

-- 이력서 테이블
insert into resume_tb(user_id, title, area, edu, career, introduce, port_link,  created_at) values (1, '능력있고 성실한 개발자!', '판교분당', '고졸', '미들(4~8년)', '열심히 일하는 개발자가 되겠습니다.', 'naver.com/superjjangcoding',now());
insert into resume_tb(user_id, title, area, edu, career, introduce, port_link, created_at) values (1, '신입 개발자 지원합니다', '서울', '고졸', '신입', '안녕하십니까. 엄격한 아버지와 자상한 어머니의 밑에서 자라나 20대가 된 저는', 'nate.com/gugu',now());
insert into resume_tb(user_id, title, area, edu, career, introduce, port_link, created_at) values (2, '이력서 입니다.', '부산', '대졸', '시니어(10년 이상)', '팀장으로 근무했었고 코딩은 자신있습니다.', 'naver.com/codingsin',now());
insert into resume_tb(user_id, title, area, edu, career, introduce, port_link, created_at) values (2, '이력서 입니다2.', '부산', '대졸', '시니어(10년 이상)', '팀장으로 근무했었고 코딩은 자신있습니다.', 'naver.com/codingsin',now());

-- 공고 테이블
INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at) VALUES (5, '부산', '배달의 민족 24년도 백엔드 신입 개발자 모집', '학력무관', '미들(4~8년)', '성실하고 열심히 일하실 분 모집!', '2024-03-10', '백엔드', NOW());
INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at) VALUES (5, '서울', '24년도 04월 마감! Oracle 사용자', '4년제', '신입', '성실하고 열심히 일하실 분 모집!', '2024-05-22', '백엔드', NOW());
INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at) VALUES (5, '울산', '프런트 엔드/백엔드 개발자 채용', '학력무관', '시니어(10년 이상)', '성실하고 열심히 일하실 분 모집!', '2024-04-09', '백엔드', NOW());
-- INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at) VALUES (5, '부산', '함께 일하실 분 모집!', '학력무관', '신입', '성실하고 열심히 일하실 분 모집!', '2024-03-29', '백엔드', NOW());
-- INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at) VALUES (4, '부산', '배달의 민족 24년도 백엔드 신입 개발자 모집', '학력무관', '미들(4~8년)', '성실하고 열심히 일하실 분 모집!', '2024-03-10', '백엔드', NOW());
-- INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at) VALUES (4, '서울', '24년도 04월 마감! Oracle 사용자', '4년제', '신입', '성실하고 열심히 일하실 분 모집!', '2024-05-22', '백엔드', NOW());
-- INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at) VALUES (4, '울산', '프런트 엔드/백엔드 개발자 채용', '학력무관', '시니어(10년 이상)', '성실하고 열심히 일하실 분 모집!', '2024-04-09', '백엔드', NOW());
-- INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at) VALUES (5, '부산', '함께 일하실 분 모집!', '학력무관', '신입', '성실하고 열심히 일하실 분 모집!', '2024-03-29', '백엔드', NOW());
-- INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at) VALUES (4, '부산', '배달의 민족 24년도 백엔드 신입 개발자 모집', '학력무관', '미들(4~8년)', '성실하고 열심히 일하실 분 모집!', '2024-03-10', '백엔드', NOW());
-- INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at) VALUES (4, '서울', '24년도 04월 마감! Oracle 사용자', '4년제', '신입', '성실하고 열심히 일하실 분 모집!', '2024-05-22', '백엔드', NOW());
-- INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at) VALUES (4, '울산', '프런트 엔드/백엔드 개발자 채용', '학력무관', '시니어(10년 이상)', '성실하고 열심히 일하실 분 모집!', '2024-04-09', '백엔드', NOW());
-- INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at) VALUES (5, '부산', '함께 일하실 분 모집!', '학력무관', '신입', '성실하고 열심히 일하실 분 모집!', '2024-03-29', '백엔드', NOW());
-- INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at) VALUES (4, '부산', '배달의 민족 24년도 백엔드 신입 개발자 모집', '학력무관', '미들(4~8년)', '성실하고 열심히 일하실 분 모집!', '2024-03-10', '백엔드', NOW());
-- INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at) VALUES (4, '서울', '24년도 04월 마감! Oracle 사용자', '4년제', '신입', '성실하고 열심히 일하실 분 모집!', '2024-05-22', '백엔드', NOW());
-- INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at) VALUES (4, '울산', '프런트 엔드/백엔드 개발자 채용', '학력무관', '시니어(10년 이상)', '성실하고 열심히 일하실 분 모집!', '2024-04-09', '백엔드', NOW());
-- INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at) VALUES (5, '부산', '함께 일하실 분 모집!', '학력무관', '신입', '성실하고 열심히 일하실 분 모집!', '2024-03-29', '백엔드', NOW());
-- INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at) VALUES (4, '부산', '배달의 민족 24년도 백엔드 신입 개발자 모집', '학력무관', '미들(4~8년)', '성실하고 열심히 일하실 분 모집!', '2024-03-10', '백엔드', NOW());
-- INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at) VALUES (4, '서울', '24년도 04월 마감! Oracle 사용자', '4년제', '신입', '성실하고 열심히 일하실 분 모집!', '2024-05-22', '백엔드', NOW());
-- INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at) VALUES (4, '울산', '프런트 엔드/백엔드 개발자 채용', '학력무관', '시니어(10년 이상)', '성실하고 열심히 일하실 분 모집!', '2024-04-09', '백엔드', NOW());
-- INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at) VALUES (5, '부산', '함께 일하실 분 모집!', '학력무관', '신입', '성실하고 열심히 일하실 분 모집!', '2024-03-29', '백엔드', NOW());
-- INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at) VALUES (4, '부산', '배달의 민족 24년도 백엔드 신입 개발자 모집', '학력무관', '미들(4~8년)', '성실하고 열심히 일하실 분 모집!', '2024-03-10', '백엔드', NOW());
-- INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at) VALUES (4, '서울', '24년도 04월 마감! Oracle 사용자', '4년제', '신입', '성실하고 열심히 일하실 분 모집!', '2024-05-22', '백엔드', NOW());
-- INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at) VALUES (4, '울산', '프런트 엔드/백엔드 개발자 채용', '학력무관', '시니어(10년 이상)', '성실하고 열심히 일하실 분 모집!', '2024-04-09', '백엔드', NOW());
-- INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at) VALUES (5, '부산', '함께 일하실 분 모집!', '학력무관', '신입', '성실하고 열심히 일하실 분 모집!', '2024-03-29', '백엔드', NOW());
-- INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at) VALUES (4, '부산', '배달의 민족 24년도 백엔드 신입 개발자 모집', '학력무관', '미들(4~8년)', '성실하고 열심히 일하실 분 모집!', '2024-03-10', '백엔드', NOW());


-- 스킬 테이블 (개인)
insert into skill_tb (resume_id, name, role) values(1, 'Java', 1);
insert into skill_tb (resume_id, name, role) values(1, 'JavaScript', 1);
insert into skill_tb (resume_id, name, role) values(1, 'HTML/CSS', 1);
insert into skill_tb (resume_id, name, role) values(1, 'Webpack', 1);
insert into skill_tb (resume_id, name, role) values(2, 'Jquery', 1);
insert into skill_tb (resume_id, name, role) values(2, 'Oracle', 1);
insert into skill_tb (resume_id, name, role) values(3, 'React', 1);



-- 스킬 테이블 (기업)
INSERT INTO skill_tb(jobs_id, name, role) VALUES(1, 'Java', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(1, 'Jquery', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(1, 'React', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(2, 'HTML/CSS', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(3, 'JavaScript', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(3, 'Java', 2);



-- 스크랩 테이블
-- insert into scrap_tb(user_id, resume_id, created_at) values(1, 1, now());
-- insert into scrap_tb(user_id, resume_id, created_at) values(2, 1, now());
-- insert into scrap_tb(user_id, jobs_id, created_at) values(3, 1, now());

-- 스크랩 테이블 (기업)

insert into scrap_tb(user_id, resume_id, created_at) values(6, 1, now());
insert into scrap_tb(user_id, resume_id, created_at) values(6, 2, now());
insert into scrap_tb(user_id, resume_id, created_at) values(6, 3, now());

insert into scrap_tb(user_id, resume_id, created_at) values(1, 1, now());
insert into scrap_tb(user_id, resume_id, created_at) values(2, 2, now());


-- 기업에 지원한 유저
-- values (첫번째 이력서가 , 첫번째 공고에 지원, 대기중)
insert into apply_tb(resume_id, jobs_id, is_pass, created_at) values(1,1,1,now());
insert into apply_tb(resume_id, jobs_id, is_pass, created_at) values(2,1,1,now());
insert into apply_tb(resume_id, jobs_id, is_pass, created_at) values(1,2,1,now());
insert into apply_tb(resume_id, jobs_id, is_pass, created_at) values(3,1,1,now());
insert into apply_tb(resume_id, jobs_id, is_pass, created_at) values(2,3,1,now());


-- 기업이 제안한 이력서
-- 첫번째 이력서를 첫번째 공고가 제안함, 대기중
insert into offer_tb(resume_id, jobs_id, status, created_at) values(1,1,1,now());
insert into offer_tb(resume_id, jobs_id, status, created_at) values(2,2,1,now());
insert into offer_tb(resume_id, jobs_id, status, created_at) values(3,3,1,now());