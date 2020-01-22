CREATE TYPE positions as ENUM ('PROGRAMMER','HR','TESTER','TEAM_LEAD');
CREATE TABLE department (
  id SERIAL NOT NULL,
  name CHARACTER VARYING(32) NOT NULL,
  date_of_create timestamp without time zone NOT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE employee (
    employee_id SERIAL NOT NULL,
    first_name CHARACTER VARYING (255) NOT NULL,
    last_name CHARACTER VARYING (255) NOT NULL,
    job_name positions,
    gender CHARACTER VARYING (255) NOT NULL,
    date_of_birth timestamp without time zone NOT NULL,
    PRIMARY KEY (employee_id),
    department_id INTEGER default NULL,
    FOREIGN KEY (department_id) REFERENCES department(id)
);
