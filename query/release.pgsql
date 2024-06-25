-- Bank Info
INSERT INTO public.bank_mst(bank_id, bank_name)VALUES (9223372036854775807,'Kpay');
INSERT INTO public.bank_mst(bank_id, bank_name)VALUES (9223372036854775806,'Cbpay');
INSERT INTO public.bank_mst(bank_id, bank_name)VALUES (9223372036854775805,'Wave');
INSERT INTO public.bank_mst(bank_id, bank_name)VALUES (9223372036854775804,'KBZ');
INSERT INTO public.bank_mst(bank_id, bank_name)VALUES (9223372036854775803,'CB');
INSERT INTO public.bank_mst(bank_id, bank_name)VALUES (9223372036854775802,'AYA');



-- User Account
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (9223372036854775803,'REGISTERED',TRUE,'Admin13@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-23','ROLE_ADMIN','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (9223372036854775804,'REGISTERED',TRUE,'Admin14@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-27','ROLE_ADMIN','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (9223372036854775805,'REGISTERED',TRUE,'Admin15@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-28','ROLE_ADMIN','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (9223372036854775806,'REGISTERED',TRUE,'Admin16@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-11-28','ROLE_ADMIN','photo_sample');
INSERT INTO public.user_account(account_id, account_status, is_mail_verified, mail, password, registered_date, role, photo) VALUES (9223372036854775807,'REGISTERED',TRUE,'SuperAdmin@gmail.com','$2a$10$T6ues0iAD9jXnIqxGWPzzOtppcA5pDx6YqmE/c8J/hYyIP2Z8ZhNW','2021-12-01','ROLE_SUPER_ADMIN','photo_sample');


-- User Info

INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (9223372036854775803,'147st','1989-04-01','Yangon','Yangon','Graduated','male','12/TTT(Naing)111135','9420100023',null,'11235','About myself','Christ');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (9223372036854775804,'148st','1995-10-01','Yangon','Yangon','Graduated','female','12/TTT(Naing)111136','9420100024',null,'11236','About myself','Athen');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (9223372036854775805,'149st','1994-10-01','Yangon','Yangon','Graduated','male','12/TTT(Naing)111137','9420100025',null,'11237','About myself','Bhone');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (9223372036854775806,'150st','1992-11-25','Yangon','Yangon','Graduated','female','12/TTT(Naing)111138','9420100026',null,'11238','About myself','Lisa');
INSERT INTO public.user_info(account_id, address, birth_date, city, division, education, gender, nrc, phone_no, photo, postal_code, self_description, user_name)VALUES (9223372036854775807,'151st','1999-06-01','Yangon','Yangon','Graduated','male','12/TTT(Naing)111139','9420100027',null,'11239','About myself','Juila');

Select * From bank_mst;
Select * From user_account;
Select * From user_info;