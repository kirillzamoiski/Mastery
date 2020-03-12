TRUNCATE TABLE employee RESTART IDENTITY CASCADE;
TRUNCATE TABLE department RESTART IDENTITY CASCADE;
INSERT INTO public.department(department_name, date_of_create) VALUES ('MMO','1990-01-01 10:10:10');
INSERT INTO public.employee(first_name, last_name, job_name, gender, date_of_birth, department_id) VALUES ('Alex','Ivanov','TESTER','MALE','2015-02-02 17:23:23',1),('Alice','Ivanova','HR','FEMALE','1993-03-03 16:23:23',1);