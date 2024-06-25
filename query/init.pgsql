-- Bank Info
INSERT INTO public.bank_mst(bank_id, bank_name)VALUES (90001,'Kpay');
INSERT INTO public.bank_mst(bank_id, bank_name)VALUES (90002,'Cbpay');
INSERT INTO public.bank_mst(bank_id, bank_name)VALUES (90003,'Wave');
INSERT INTO public.bank_mst(bank_id, bank_name)VALUES (90004,'KBZ');
INSERT INTO public.bank_mst(bank_id, bank_name)VALUES (90005,'CB');
INSERT INTO public.bank_mst(bank_id, bank_name)VALUES (90006,'AYA');



-- User Account
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (10001,'REGISTERED',TRUE,'Rachel@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-10','ROLE_STUDENT','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (10002,'REGISTERED',FALSE,'Michel@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-10','ROLE_STUDENT','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (10003,'SUSPENDED',TRUE,'Bella@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-10','ROLE_STUDENT','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (10004,'REGISTERED',TRUE,'James@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-11','ROLE_STUDENT','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (10005,'REGISTERED',TRUE,'Christine@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-11','ROLE_STUDENT','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (10006,'REGISTERED',TRUE,'Zet@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-12','ROLE_STUDENT','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (10007,'REGISTERED',TRUE,'Lin@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-13','ROLE_STUDENT','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (10008,'REGISTERED',TRUE,'Htet@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-13','ROLE_STUDENT','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (10009,'REGISTERED',TRUE,'Myat@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-13','ROLE_STUDENT','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (10010,'REGISTERED',TRUE,'Min@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-13','ROLE_STUDENT','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (10011,'REGISTERED',FALSE,'Moh@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-13','ROLE_STUDENT','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (10012,'REGISTERED',TRUE,'Mann@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-14','ROLE_STUDENT','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (10013,'REGISTERED',TRUE,'Thu@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-15','ROLE_STUDENT','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (10014,'VERIFIED',TRUE,'Aye@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-15','ROLE_TEACHER','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (10015,'REQUESTED',TRUE,'Pwint@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-16','ROLE_TEACHER','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (10016,'VERIFIED',TRUE,'Hnin@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-18','ROLE_TEACHER','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (20001,'REQUESTED',TRUE,'Zyan@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-18','ROLE_TEACHER','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (20002,'VERIFIED',TRUE,'Thant@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-20','ROLE_TEACHER','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (20003,'REQUESTED',TRUE,'Htoo@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-20','ROLE_TEACHER','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (20004,'VERIFIED',TRUE,'Moe@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-20','ROLE_TEACHER','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (20005,'REQUESTED',TRUE,'Myint@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-20','ROLE_TEACHER','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (20006,'VERIFIED',TRUE,'Dali@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-20','ROLE_TEACHER','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (20007,'REQUESTED',TRUE,'Chaint@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-23','ROLE_TEACHER','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (20008,'VERIFIED',TRUE,'Diana@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-23','ROLE_TEACHER','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (30013,'REGISTERED',TRUE,'Admin13@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-23','ROLE_ADMIN','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (30014,'REGISTERED',TRUE,'Admin14@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-27','ROLE_ADMIN','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (30015,'REGISTERED',TRUE,'Admin15@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-28','ROLE_ADMIN','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (30016,'REGISTERED',TRUE,'Admin16@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-28','ROLE_ADMIN','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (40001,'REGISTERED',TRUE,'SuperAdmin@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-12-01','ROLE_SUPER_ADMIN','photo_sample');


-- User Info
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (10001,'123st','2021-11-10','Yangon','Yangon','Fourth Year','female','12/TTT(Naing)111111','9420099999',null,'11211','About myself','Rachel');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (10002,'124st','20001-01-01','Yangon','Yangon','Third Year','male','12/TTT(Naing)111112','9420100000',null,'11212','About myself','Michel');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (10003,'125st','2002-01-01','Yangon','Yangon','Second Year','female','12/TTT(Naing)111113','9420100001',null,'11213','About myself','Bella');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (10004,'126st','1990-01-01','Yangon','Yangon','First Year','male','12/TTT(Naing)111114','9420100002',null,'11214','About myself','James');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (10005,'127st','2000-01-01','Yangon','Yangon','Grade 11','female','12/TTT(Naing)111115','9420100003',null,'11215','About myself','Christine');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (10006,'128st','2001-10-11','Yangon','Yangon','Grade 10','male','12/TTT(Naing)111116','9420100004',null,'11216','About myself','Zet');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (10007,'129st','2003-01-01','Yangon','Yangon','Grade 9','female','12/TTT(Naing)111117','9420100005',null,'11217','About myself','Lin');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (10008,'130st','2004-02-05','Yangon','Yangon','Grade 8','male','12/TTT(Naing)111118','9420100006',null,'11218','About myself','Htet');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (10009,'131st','2002-10-01','Yangon','Yangon','Grade 7','female','12/TTT(Naing)111119','9420100007',null,'11219','About myself','Myat');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (10010,'132st','2001-11-03','Yangon','Yangon','Grade 6','male','12/TTT(Naing)111120','9420100008',null,'11220','About myself','Min');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (10011,'133st','1994-03-01','Yangon','Yangon','Grade 5','female','12/TTT(Naing)111121','9420100009',null,'11221','About myself','Moh');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (10012,'134st','2001-09-01','Yangon','Yangon','Grade 4','male','12/TTT(Naing)111122','9420100010',null,'11222','About myself','Mann');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (10013,'135st','2002-01-01','Yangon','Yangon','Grade 3','female','12/TTT(Naing)111123','9420100011',null,'11223','About myself','Thu');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (10014,'136st','2001-01-01','Yangon','Yangon','Grade 2','male','12/TTT(Naing)111124','9420100012',null,'11224','About myself','Aye');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (10015,'137st','2002-01-01','Yangon','Yangon','Grade 1','female','12/TTT(Naing)111125','9420100013',null,'11225','About myself','Pwint');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (10016,'138st','2001-01-01','Yangon','Yangon','KG','male','12/TTT(Naing)111126','9420100014',null,'11226','About myself','Hnin');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (20001,'139st','1999-06-01','Yangon','Yangon','Graduated','female','12/TTT(Naing)111127','9420100015',null,'11227','About myself','Zyan');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (20002,'140st','1987-05-22','Yangon','Yangon','Graduated','male','12/TTT(Naing)111128','9420100016',null,'11228','About myself','Thant');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (20003,'141st','1992-05-02','Yangon','Yangon','Graduated','female','12/TTT(Naing)111129','9420100017',null,'11229','About myself','Htoo');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (20004,'142st','1988-04-03','Yangon','Yangon','Graduated','female','12/TTT(Naing)111130','9420100018',null,'11230','About myself','Moe');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (20005,'143st','1985-08-10','Yangon','Yangon','Graduated','male','12/TTT(Naing)111131','9420100019',null,'11231','About myself','Myint');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (20006,'144st','1997-12-01','Yangon','Yangon','Graduated','female','12/TTT(Naing)111132','9420100020',null,'11232','About myself','Dali');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (20007,'145st','1994-02-03','Yangon','Yangon','Graduated','male','12/TTT(Naing)111133','9420100021',null,'11233','About myself','Chaint');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (20008,'146st','1998-04-21','Yangon','Yangon','Graduated','female','12/TTT(Naing)111134','9420100022',null,'11234','About myself','Diana');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (30013,'147st','1989-04-01','Yangon','Yangon','Graduated','male','12/TTT(Naing)111135','9420100023',null,'11235','About myself','Christ');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (30014,'148st','1995-10-01','Yangon','Yangon','Graduated','female','12/TTT(Naing)111136','9420100024',null,'11236','About myself','Athen');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (30015,'149st','1994-10-01','Yangon','Yangon','Graduated','male','12/TTT(Naing)111137','9420100025',null,'11237','About myself','Bhone');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (30016,'150st','1992-11-25','Yangon','Yangon','Graduated','female','12/TTT(Naing)111138','9420100026',null,'11238','About myself','Lisa');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (40001,'151st','1999-06-01','Yangon','Yangon','Graduated','male','12/TTT(Naing)111139','9420100027',null,'11239','About myself','Juila');

-- Course info
INSERT INTO public.course_info(course_id,about_course,category,class_link,class_type,course_name,end_date,course_fees,is_course_approved,level, start_date,  max_stu, prerequisite, uid_fkey) VALUES (50001,'This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.','Computer Science','https://classroom.google.com/u/0/c/...','LIVE','Basic Programming','5.1.2021',150000,'TRUE','Basic','1.1.2021',  50, 'A computer and basic knowledge of how to use it.', 10014);
INSERT INTO public.course_info(course_id,about_course,category,class_link,class_type,course_name,end_date,course_fees,is_course_approved,level, start_date,  max_stu, prerequisite, uid_fkey) VALUES (50002,'This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.','Language','https://classroom.google.com/u/0/c/...','LIVE','Japanese N5','6.1.2021',150000,'TRUE','Basic','2.1.2021',  30, 'No need any prequisites.', 10015);
INSERT INTO public.course_info(course_id,about_course,category,class_link,class_type,course_name,end_date,course_fees,is_course_approved,level, start_date,  max_stu, prerequisite, uid_fkey) VALUES (50003,'This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.','Language','https://classroom.google.com/u/0/c/...','LIVE','Japanese N4','7.1.2021',150000,'TRUE','Basic','3.1.2021', 10, 'Must complete N5 level.', 10016);
INSERT INTO public.course_info(course_id,about_course,category,class_link,class_type,course_name,end_date,course_fees,is_course_approved,level, start_date,  max_stu, prerequisite, uid_fkey) VALUES (50004,'This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.','Language','https://classroom.google.com/u/0/c/...','LIVE','Japanese N3','8.1.2021',200000,'TRUE','Intermediate','4.1.2021',  50, 'Must complete N4 level.', 20001);
INSERT INTO public.course_info(course_id,about_course,category,class_link,class_type,course_name,end_date,course_fees,is_course_approved,level, start_date,  max_stu, prerequisite, uid_fkey) VALUES (50005,'This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.','Language','https://classroom.google.com/u/0/c/...','LIVE','Japanese N2','9.1.2021',250000,'TRUE','Advanced','5.1.2021',  35, 'Must complete N3 level.', 20002);
INSERT INTO public.course_info(course_id,about_course,category,class_link,class_type,course_name,end_date,course_fees,is_course_approved,level, start_date,  max_stu, prerequisite, uid_fkey) VALUES (50006,'This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.','Language','https://classroom.google.com/u/0/c/...','LIVE','Japanese N1','10.1.2021',300000,'TRUE','Advanced','5.1.2021',  25, 'Must complete N2 level.', 20003);
INSERT INTO public.course_info(course_id,about_course,category,class_link,class_type,course_name,end_date,course_fees,is_course_approved,level, start_date,  max_stu, prerequisite, uid_fkey) VALUES (50007,'This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.','Computer Science','https://classroom.google.com/u/0/c/...','VIDEO','Networking','5.2.2021',250000,'TRUE','Intermediate','1.5.2021',  100, 'Basic hardware knowledge.', 20004);
INSERT INTO public.course_info(course_id,about_course,category,class_link,class_type,course_name,end_date,course_fees,is_course_approved,level, start_date,  max_stu, prerequisite, uid_fkey) VALUES (50008,'This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.','Computer Science','https://classroom.google.com/u/0/c/...','LIVE','Java','6.2.2021',200000,'TRUE','Basic','1.7.2021',  50, 'A computer and basic knowledge of how to use it.', 20005);
INSERT INTO public.course_info(course_id,about_course,category,class_link,class_type,course_name,end_date,course_fees,is_course_approved,level, start_date,  max_stu, prerequisite, uid_fkey) VALUES (50009,'This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.','Computer Science','https://classroom.google.com/u/0/c/...','VIDEO','Deep Learning','7.2.2021',300000,'TRUE','Basic','2.10.2021',  40, 'Strong knowlege in programming.', 20006);
INSERT INTO public.course_info(course_id,about_course,category,class_link,class_type,course_name,end_date,course_fees,is_course_approved,level, start_date,  max_stu, prerequisite, uid_fkey) VALUES (50010,'This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.','Computer Science','https://classroom.google.com/u/0/c/...','LIVE','PHP','8.2.2021',250000,'FALSE','Intermediate','3.1.2021',  50, 'Basic PHP programming knowledge', 20007);
INSERT INTO public.course_info(course_id,about_course,category,class_link,class_type,course_name,end_date,course_fees,is_course_approved,level, start_date,  max_stu, prerequisite, uid_fkey) VALUES (50011,'This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.','Computer Science','https://classroom.google.com/u/0/c/...','VIDEO','Python','9.2.2021',250000,'TRUE','Advanced','3.5.2021',  70, 'Strong knowlege in programming.', 20008);
INSERT INTO public.course_info(course_id,about_course,category,class_link,class_type,course_name,end_date,course_fees,is_course_approved,level, start_date,  max_stu, prerequisite, uid_fkey) VALUES (50012,'This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.','Computer Science','https://classroom.google.com/u/0/c/...','VIDEO','Basic Programming','10.2.2021',150000,'FALSE','Basic','6.1.2021',  50, 'A computer and basic knowledge of how to use it.', 10014);
INSERT INTO public.course_info(course_id,about_course,category,class_link,class_type,course_name,end_date,course_fees,is_course_approved,level, start_date,  max_stu, prerequisite, uid_fkey) VALUES (50013,'This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.','Computer Science','https://classroom.google.com/u/0/c/...','VIDEO','Java','5.1.2021',200000,'TRUE','Intermediate','2.1.2021',  80, 'Basic Java programming knowledge.', 10015);
INSERT INTO public.course_info(course_id,about_course,category,class_link,class_type,course_name,end_date,course_fees,is_course_approved,level, start_date,  max_stu, prerequisite, uid_fkey) VALUES (50014,'This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.','Language','https://classroom.google.com/u/0/c/...','VIDEO','Japanese N5','6.1.2021',150000,'TRUE','Basic','2.9.2021',  100, 'No need any prequisites.', 10016);
INSERT INTO public.course_info(course_id,about_course,category,class_link,class_type,course_name,end_date,course_fees,is_course_approved,level, start_date,  max_stu, prerequisite, uid_fkey) VALUES (50015,'This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.','Language','https://classroom.google.com/u/0/c/...','VIDEO','Japanese N4','6.2.2021',150000,'FALSE','Basic','2.1.2021',  20, 'Must complete N5 level.', 20001);
INSERT INTO public.course_info(course_id,about_course,category,class_link,class_type,course_name,end_date,course_fees,is_course_approved,level, start_date,  max_stu, prerequisite, uid_fkey) VALUES (50016,'This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.','Language','https://classroom.google.com/u/0/c/...','VIDEO','Japanese N3','6.3.2021',200000,'TRUE','Intermediate','3.1.2021',  40, 'Must complete N4 level.', 20002);
INSERT INTO public.course_info(course_id,about_course,category,class_link,class_type,course_name,end_date,course_fees,is_course_approved,level, start_date,  max_stu, prerequisite, uid_fkey) VALUES (50017,'This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.','Language','https://classroom.google.com/u/0/c/...','VIDEO','Japanese N2','8.10.2021',250000,'TRUE','Advanced','4.9.2021',  65, 'Must complete N3 level.', 20003);
INSERT INTO public.course_info(course_id,about_course,category,class_link,class_type,course_name,end_date,course_fees,is_course_approved,level, start_date,  max_stu, prerequisite, uid_fkey) VALUES (50018,'This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.','Language','https://classroom.google.com/u/0/c/...','VIDEO','Japanese N1','6.1.2021',300000,'TRUE','Advanced','1.8.2021',  85, 'Must complete N2 level.', 20004);
INSERT INTO public.course_info(course_id,about_course,category,class_link,class_type,course_name,end_date,course_fees,is_course_approved,level, start_date,  max_stu, prerequisite, uid_fkey) VALUES (50019,'This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.','Language','https://classroom.google.com/u/0/c/...','LIVE','English','7.1.2021',20000,'TRUE','Basic','5.1.2021',  100, 'No need any prequisites.', 20005);
INSERT INTO public.course_info(course_id,about_course,category,class_link,class_type,course_name,end_date,course_fees,is_course_approved,level, start_date,  max_stu, prerequisite, uid_fkey) VALUES (50020,'This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.','Language','https://classroom.google.com/u/0/c/...','VIDEO','English','5.5.2021',200000,'TRUE','Basic','3.1.2021',  55, 'No need any prequisites.', 20006);


--Course time
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51001,'Monday','9:00','11:00',50001);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51002,'Tuesday','9:00','11:00',50001);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51003,'Saturday','1:00','3:00',50002);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51004,'Sunday','1:00','3:00',50002);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51005,'Wednesday','11:00','2:00',50003);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51006,'Thursday','11:00','2:00',50003);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51007,'Friday','5:00','7:30',50004);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51008,'Saturday','5:00','7:30',50004);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51009,'Monday','9:00','11:00',50005);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51010,'Tuesday','9:00','11:00',50005);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51011,'Saturday','1:00','3:00',50006);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51012,'Sunday','1:00','3:00',50006);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51013,'Wednesday','11:00','2:00',50007);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51014,'Thursday','11:00','2:00',50007);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51015,'Friday','5:00','7:30',50008);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51016,'Saturday','5:00','7:30',50008);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51017,'Monday','9:00','11:00',50009);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51018,'Tuesday','9:00','11:00',50009);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51019,'Saturday','1:00','3:00',50010);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51020,'Sunday','1:00','3:00',50010);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51021,'Wednesday','11:00','2:00',50011);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51022,'Thursday','11:00','2:00',50011);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51023,'Friday','5:00','7:30',50012);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51024,'Saturday','5:00','7:30',50012);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51025,'Monday','9:00','11:00',50013);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51026,'Tuesday','9:00','11:00',50013);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51027,'Saturday','1:00','3:00',50014);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51028,'Sunday','1:00','3:00',50014);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51029,'Wednesday','11:00','2:00',50015);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51030,'Thursday','11:00','2:00',50015);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51031,'Friday','5:00','7:30',50016);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51032,'Saturday','5:00','7:30',50016);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51033,'Monday','9:00','11:00',50017);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51034,'Tuesday','9:00','11:00',50017);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51035,'Saturday','1:00','3:00',50018);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51036,'Sunday','1:00','3:00',50018);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51037,'Wednesday','11:00','2:00',50019);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51038,'Thursday','11:00','2:00',50019);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51039,'Friday','5:00','7:30',50020);
INSERT INTO public.course_time(course_time_id, course_days, course_start_time, course_end_time, course_id_fkey) VALUES (51040,'Saturday','5:00','7:30',50020);


--join course user
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90001, 50001,10001);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90002, 50001,10002);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90003, 50001,10003);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90004, 50002,10004);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90005, 50002,10005);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90006, 50002,10006);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90007, 50002,10007);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90008, 50003,10007);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90009, 50004,10007);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90010, 50004,10008);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90011, 50005,10008);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90012, 50005,10009);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90013, 50003,10009);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90014, 50004,10009);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90015, 50006,10010);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90016, 50007,10010);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90017, 50006,10011);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90018, 50007,10011);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90019, 50008,10011);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90020, 50009,10011);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90021, 50006,10012);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90022, 50005,10012);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90023, 50004,10013);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90024, 50010,10014);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90025, 50011,10014);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90026, 50010,10015);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90027, 50011,10016);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90028, 50009,10016);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey)VALUES (90029, 50008,10016);


-- join course teacher

-- Course info
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey) VALUES (90030,50001, 10014);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey) VALUES (90031,50002, 10015);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey) VALUES (90032,50003, 10016);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey) VALUES (90033,50004, 20001);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey) VALUES (90034,50005, 20002);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey) VALUES (90035,50006, 20003);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey) VALUES (90036,50007, 20004);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey) VALUES (90037,50008, 20005);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey) VALUES (90038,50009, 20006);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey) VALUES (90039,50010, 20007);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey) VALUES (90040,50011, 20008);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey) VALUES (90041,50012, 10014);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey) VALUES (90042,50013, 10015);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey) VALUES (90043,50014, 10016);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey) VALUES (90044,50015, 20001);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey) VALUES (90045,50016, 20002);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey) VALUES (90046,50017, 20003);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey) VALUES (90047,50018, 20004);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey) VALUES (90048,50019, 20005);
INSERT INTO public.join_course_user(join_id, course_id_fkey, uid_fkey) VALUES (90049,50020, 20006);






--syllabus
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52001,'Chapter 1 : Introduction',50001);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52002,'Chapter 2 : Basic Programming',50001);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52003,'Chapter 1 : Introduction',50002);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52004,'Chapter 2 : Basic Alphabets',50002);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52005,'Chapter 1 : Introduction',50003);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52006,'Chapter 2 : N4 Grammar',50003);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52007,'Chapter 1 : Introduction',50004);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52008,'Chapter 2 : N3 Grammar',50004);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52009,'Chapter 1 : Introduction',50005);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52010,'Chapter 2 : N2 Grammar',50005);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52011,'Chapter 1 : Introduction',50006);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52012,'Chapter 2 : N1 Grammar',50006);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52013,'Chapter 1 : Introduction',50007);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52014,'Chapter 2 : Basic Networking',50007);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52015,'Chapter 1 : Introduction',50008);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52016,'Chapter 2 : Basic Java',50008);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52017,'Chapter 1 : Introduction',50009);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52018,'Chapter 2 : Deep Learning',50009);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52019,'Chapter 1 : Introduction',50010);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52020,'Chapter 2 : Basic PHP',50010);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52021,'Chapter 1 : Introduction',50011);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52022,'Chapter 2 : Basic Python',50011);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52023,'Chapter 1 : Introduction',50012);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52024,'Chapter 2 : Basic Programming',50012);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52025,'Chapter 1 : Introduction',50013);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52026,'Chapter 2 : Basic Java',50013);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52027,'Chapter 1 : Introduction',50014);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52028,'Chapter 2 : Basic Alphabets',50014);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52029,'Chapter 1 : Introduction',50015);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52030,'Chapter 2 : N4 Grammar',50015);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52031,'Chapter 1 : Introduction',50016);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52032,'Chapter 2 : N3 Grammar',50016);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52033,'Chapter 1 : Introduction',50017);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52034,'Chapter 2 : N2 Grammar',50017);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52035,'Chapter 1 : Introduction',50018);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52036,'Chapter 2 : N1 Grammar',50018);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52037,'Chapter 1 : Introduction',50019);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52038,'Chapter 2 : Basic Grammar',50019);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52039,'Chapter 1 : Introduction',50020);
INSERT INTO public.syllabus(syllabus_id, title, course_id_fkey) VALUES (52040,'Chapter 2 : Basic Grammar',50020);


--Content
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54001,'Introduction to programming',52001);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54002,'Programming Environments',52002);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54003,'Introduction to Japanese',52003);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54004,'Hiragana and Katakana',52004);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54005,'Revision for N5',52005);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54006,'Chapter 26 : Plain form + んです',52006);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54007,'Revision for N4',52007);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54008,'N3 Grammar',52008);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54009,'Revision for N3',52009);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54010,'N2 Grammar',52010);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54011,'Revision for N2',52011);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54012,'N1 Grammar',52012);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54013,'Introduction to Networking',52013);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54014,'Basic Requirements',52014);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54015,'Introduction to Java',52015);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54016,'Basic Requirements',52016);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54017,'Introduction to Deep Learning',52017);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54018,'Basic Requirements',52018);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54019,'Introduction to PHP',52019);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54020,'Basic Requirements',52020);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54021,'Introduction to Python',52021);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54022,'Basic Requirements',52022);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54023,'Introduction to programming',52023);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54024,'Programming Environments',52024);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54025,'Introduction to Java',52025);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54026,'Basic Requirements',52026);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54027,'Introduction to Japanese',52027);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54028,'Hiragana and Katakana',52028);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54029,'Revision for N5',52029);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54030,'Chapter 26 : Plain form + んです',52030);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54031,'Revision for N4',52031);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54032,'N3 Grammar',52032);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54033,'Revision for N3',52033);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54034,'N2 Grammar',52034);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54035,'Revision for N2',52035);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54036,'N1 Grammar',52036);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54037,'Introduction to English',52037);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54038,'English Grammar',52038);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54039,'Introduction to English',52039);
INSERT INTO public.content(content_id, content, syllabus_id) VALUES (54040,'English Grammar',52040);


--Payment account
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81001,'Khaing Inzali','0978626754',10001,90001);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81002,'Ag Phyo','0943055467',10001,90003);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81003,'Myint Eindra Hlaing','0976543554',10002,90001);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81004,'Htun Naing ','1156785453',10003,90004);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81005,'Inzali Nandar','786736753',10003,90006);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81006,'Nyan Phone','0986523134',10004,90002);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81007,'Lin Thu','0945689723',10005,90002);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81008,'Yi Thawdar','0344536728',10006,90005);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81009,'Lin Tun Ko Thura','0378675613',10006,90006);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81010,'Lei Myitzu','095105674',10007,90001);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81011,'Thuta Pyay Zeyar Soe','0976544889',10008,90001);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81012,'Thuta Pyay Zeyar Soe','0986546677',10009,90001);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81013,'Phyo Lin Myint','0975478634',10010,90001);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81014,'Tun Htet Soe Tun','0978756476',10011,90002);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81015,'Tun Htet Soe Tun','0945546786',10012,90002);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81016,'Tun Htet Soe Tun','0945377889',10013,90002);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81017,'Kay Hnin Oo','1000987657',10013,90006);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81018,'Thandar Naing Aye','876786756',10014,90005);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81019,'Aung Nyan','878656645',10014,90006);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81020,'Aung Nyan','0973454367',10015,90001);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81021,'Ohmar Thi','0945673322',10016,90002);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81022,'Ohmar Thi','0945344889',20001,90003);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81023,'Ohmar Thi','095107886',20002,90003);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81024,'Hlaing Yee','0979966542',20003,90003);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81025,'Thura Ko','576849337',20004,90004);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81026,'Ye Thuta ','85769473',20005,90005);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81027,'Ye Thuta ','00274639067',20006,90005);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81028,'Htun Thiha ','2007465789',20007,90005);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81029,'Tun Khin','1000564789',20008,90004);
INSERT INTO public.payment_account(payment_account_id, account_name, account_number, uid_fkey,bank_id_fkey)VALUES (81030,'Su Pwint Ohnmar ','2000987677',20008,90004);


--Leave info
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70001,'2021-11-10','9:00','11:00','Sick', 90001);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70002,'2021-11-10','9:00','11:00','High Fever', 90002);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70003,'2021-11-10','9:00','10:00','A relative is going to live abroad.', 90002);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70004,'2021-11-11','1:00','3:00','A dentist’s appointment.', 90004);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70005,'2021-11-11','1:30','3:00','Excessive job-related stress.', 90004);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70006,'2021-11-12','2:00','3:00','Bereavement leave.', 90004);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70007,'2021-11-13','1:00','3:00','Emergency medical procedure', 90005);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70008,'2021-11-13','11:00','2:00','You have an important appointment with your doctor that you totally forgot about.', 90008);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70009,'2021-11-13','11:00','2:00','Daycare child problems', 90008);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70010,'2021-11-13','11:00','2:00','Relative’s wedding.', 90010);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70011,'2021-11-13','5:00','7:30','Suffered from an accident.', 90010);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70012,'2021-11-14','9:00','11:00','Loss or theft.', 90011);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70013,'2021-11-15','9:00','10:00','Due to illness or injury ', 90012);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70014,'2021-11-15','1:00','2:00','Family emergency', 90017);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70015,'2021-11-16','1:00','3:00','Days of mourning for the death of a relative', 90015);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70016,'2021-11-18','1:00','3:00','For vehicle or transportation problems', 90017);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70017,'2021-11-18','1:00','3:00','For taking a mental health Day', 90021);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70018,'2021-11-20','2:00','3:00','For emergency at home', 90015);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70019,'2021-11-20','2:00','3:00','For medical appointments', 90021);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70020,'2021-11-20','11:00','12:00','By Jury duty/ Duties of a citizen', 90016);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70021,'2021-11-20','11:00','12:00','For unforeseen events', 90018);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70022,'2021-11-20','5:00','7:30','For falling asleep', 90019);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70023,'2021-11-23','5:00','6:00','Medical tests',  90027);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70024,'2021-11-23','5:00','7:30','Pets emergencies', 90026);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70025,'2021-11-23','5:00','7:30','Deaths and funerals', 90023);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70026,'2021-11-27','9:00','11:00','Shipments of packages, furniture, etc.', 90014);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70027,'2021-11-28','9:00','11:00','Tragedy at home', 90020);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70028,'2021-11-28','9:00','10:00','Personal reasons.', 90022);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70029,'2021-12-01','1:00','3:00','Family emergency', 90025);
INSERT INTO public.leave_info(leave_id, leave_date, leave_start_time, leave_end_time, reason, join_fkey)VALUES (70030,'2021-12-02','1:00','3:00','Medical emergency', 90028);


--Payment receive
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80001,'1.1.2021','COMPLETED','https://www.w26schools.com/images/picture.jpg', 90001);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80002,'2.1.2021','COMPLETED','https://www.w27schools.com/images/picture.jpg', 90002);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80003,'3.1.2021','COMPLETED','https://www.w28schools.com/images/picture.jpg', 90003);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80004,'4.1.2021','COMPLETED','https://www.w29schools.com/images/picture.jpg', 90004);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80005,'5.1.2021','COMPLETED','https://www.w30schools.com/images/picture.jpg', 90005);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80006,'6.1.2021','COMPLETED','https://www.w31schools.com/images/picture.jpg', 90006);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80007,'7.1.2021','COMPLETED','https://www.w32schools.com/images/picture.jpg', 90007);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80008,'8.1.2021','PENDING','https://www.w33schools.com/images/picture.jpg', 90008);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80009,'9.1.2021','REQUESTED','https://www.w34schools.com/images/picture.jpg', 90009);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80010,'1.1.2021','ERROR','https://www.w35schools.com/images/picture.jpg', 90010);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80011,'1.1.2021','PENDING','https://www.w36schools.com/images/picture.jpg', 90011);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80012,'2.1.2021','PENDING','https://www.w37schools.com/images/picture.jpg', 90012);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80013,'3.1.2021','PENDING','https://www.w38schools.com/images/picture.jpg', 90013);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80014,'4.1.2021','PENDING','https://www.w39schools.com/images/picture.jpg', 90014);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80015,'5.1.2021','PENDING','https://www.w40schools.com/images/picture.jpg', 90015);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80016,'6.1.2021','PENDING','https://www.w41schools.com/images/picture.jpg', 90016);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80017,'7.1.2021','REQUESTED','https://www.w42schools.com/images/picture.jpg', 90017);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80018,'8.1.2021','ERROR','https://www.w43schools.com/images/picture.jpg', 90018);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80019,'9.1.2021','COMPLETED','https://www.w44schools.com/images/picture.jpg', 90019);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80020,'2.2.2021','COMPLETED','https://www.w45schools.com/images/picture.jpg', 90020);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80021,'1.2.2021','COMPLETED','https://www.w46schools.com/images/picture.jpg', 90021);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80022,'3.1.2021','COMPLETED','https://www.w47schools.com/images/picture.jpg', 90022);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80023,'4.1.2021','COMPLETED','https://www.w48schools.com/images/picture.jpg', 90023);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80024,'5.1.2021','COMPLETED','https://www.w49schools.com/images/picture.jpg', 90024);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80025,'6.1.2021','COMPLETED','https://www.w50schools.com/images/picture.jpg', 90025);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80026,'6.1.2021','PENDING','https://www.w51schools.com/images/picture.jpg', 90026);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80027,'7.1.2021','PENDING','https://www.w52schools.com/images/picture.jpg', 90027);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80028,'8.1.2021','PENDING','https://www.w53schools.com/images/picture.jpg', 90028);
INSERT INTO public.payment_receive(payment_receive_id,payment_receive_date, payment_status, slip, join_id) VALUES (80029,'9.1.2021','COMPLETED','https://www.w54schools.com/images/picture.jpg', 90029);


--Review
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60001,'2021-11-10','This class is very helpful to me. Currently, I am still learning this class which makes up a lot of basic music knowledge.',0,5,90001);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60002,'2021-11-10','like!Prof and TAs are helpful and the discussion among students are quite active. Very rewarding learning experience!',0,5,90002);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60003,'2021-11-10','Excellent score!',2,0,90003);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60004,'2021-11-11','Retake exam on December 1st',1,0,90004);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60005,'2021-11-11','Check up twice before uploading the assignment It all wrong',1,0,90005);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60006,'2021-11-12','One of the most useful course on IT Management!',0,5,90006);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60007,'2021-11-13','I was disappointed because the name is misleading. The course provides a good introduction & overview of the responsibilities of the CTO, but has very little specifically digital content.',0,5,90007);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60008,'2021-11-13','Super content. I will definitely re-do the course',0,3,90008);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60009,'2021-11-13','Scores keep getting declined Try better',1,0,90009);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60010,'2021-11-13','Is there any reason why you should not apply the course by BCG?)Its content is pretty unique and includes a high level analysis and a wide range of knowledge needed to cover all detailed aspects.Best regards,Oleg Serov',0,5,90010);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60011,'2021-11-13','Excellent course and teachers. Congratulations!!',0,5,90011);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60012,'2021-11-14','This is a good course for an CIO in a non technical company.',0,5,90012);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60013,'2021-11-15','Good content, but the course setting does (at least for me) not allow learn the content long term due to missing reading material.',0,5,90013);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60014,'2021-11-15','Very structured approach. Thank you for sharing with me.',0,3,90014);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60015,'2021-11-16','Program demystifies the evolving world of CIOs in a typical global corporation.Coverage being introductory familiarizes participants nicely through some of the nuances & emerging trends.',0,5,90015);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60016,'2021-11-18','Very relevant and useful course designed for CIOs',0,4,90016);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60017,'2021-11-18','This course does not say anything about digitization which is the core subject of the digital wave.',0,2,90017);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60018,'2021-11-20','Well done keep going',2,0,90018);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60019,'2021-11-20','Absent too much',1,0,90019);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60020,'2021-11-20','Great piece of work, I especially liked a few lifehacks for the CIO',0,3,90020);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60021,'2021-11-20','Focus more on lesson',1,0,90021);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60022,'2021-11-20','One of the excellent courses at Coursera for information technology bosses and managers.',0,5,90022);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60023,'2021-11-23','Dont be late on class',1,0,90023);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60024,'2021-11-23','Easy to follow and includes a lot basic and important techniques to use sketchup.',0,5,90024);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60025,'2021-11-23','Really nice teacher!I could got the point eazliy but the v',0,5,90025);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60026,'2021-11-27','Great course - I recommend it for all, especially IT and Business Managers!',0,4,90026);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60027,'2021-11-28','Congratulations for receiving highest mark',2,0,90027);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60028,'2021-11-28','Videos that are presented in French could have been translated to English.',0,2,90028);
INSERT INTO public.review(
 review_id, assigned_date, feedback, review_type, star, join_fkey)
 VALUES (60029,'2021-12-01','The course content is quite good, though it could have been deeper in some areas.But its peer review system is not working well, if at all.Regards, An Le.',0,4,90029);
INSERT INTO public.review(
review_id, assigned_date, feedback, review_type, star, join_fkey)
VALUES (60030,'2021-12-02','Keep up with deadlines',1,0,50010,20008);
