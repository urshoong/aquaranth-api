use dq;


CREATE TABLE `emp`
(
    `emp_no`          bigint       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `emp_name`        VARCHAR(200) NOT NULL,
    `username`        VARCHAR(200) NOT NULL,
    `password`        VARCHAR(200) NOT NULL,
    `gender`          VARCHAR(10)  NOT NULL,
    `emp_phone`       VARCHAR(200) NULL,
    `emp_address`     VARCHAR(200) NULL,
    `emp_profile`     VARCHAR(200) NULL,
    `email`           VARCHAR(100) NULL,
    `last_login_time` datetime     NULL,
    `last_login_ip`   VARCHAR(100) NULL,
    `first_hiredate`  date         NOT NULL,
    `last_retiredate` date         NULL
);

CREATE TABLE `company`
(
    `company_no`       bigint       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `company_name`     VARCHAR(200) NULL,
    `company_address`  VARCHAR(200) NULL,
    `company_tel`      VARCHAR(100) NULL,
    `owner_name`       VARCHAR(100) NOT NULL,
    `company_category` VARCHAR(100) NOT NULL,
    `business_status`  VARCHAR(100) NOT NULL,
    `founding_date`    date         NOT NULL,
    `opening_date`     date         NOT NULL,
    `closing_date`     date         NULL,
    `default_domain`   VARCHAR(100) NOT NULL,
    `corporation`      VARCHAR(100) NOT NULL,
    `business_number`  VARCHAR(100) NOT NULL
);

CREATE TABLE `menu`
(
    `menu_no`       bigint       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `upper_menu_no` bigint       NULL,
    `menu_name`     VARCHAR(200) NULL,
    `menu_icon`     VARCHAR(200) NOT NULL,
    `menu_use`      boolean      NOT NULL
);

CREATE TABLE `orga`
(
    `orga_no`          bigint      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `company_no`       bigint      NOT NULL,
    `emp_no`           bigint      NOT NULL,
    `dept_no`          bigint      NOT NULL,
    `company_category` VARCHAR(10) NULL,
    `dept_category`    VARCHAR(10) NULL,
    `hiredate`         date        NULL,
    `retiredate`       date        NULL
);

CREATE TABLE `dept`
(
    `dept_no`         bigint       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `upper_dept_no`   bigint       NULL,
    `dept_name`       VARCHAR(200) NOT NULL,
    `dept_use`        boolean      NOT NULL,
    `dept_management` boolean      NOT NULL,
    `dept_sort`       int          NOT NULL
);

CREATE TABLE `role_group`
(
    `role_group_no`   bigint       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `role_group_name` VARCHAR(200) NOT NULL,
    `role_group_use`  boolean      NOT NULL,
    `company_name`    VARCHAR(200) NOT NULL
);

CREATE TABLE `orga_role`
(
    `orga_role_no`  bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `role_group_no` bigint NOT NULL,
    `orga_no`       bigint NOT NULL
);

CREATE TABLE `menu_role`
(
    `role_group_no` bigint NOT NULL,
    `menu_no`       bigint NOT NULL
);

CREATE TABLE `favorite`
(
    `favorite_no`     bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `mygroup_no`      bigint NOT NULL,
    `favorite_emp_no` bigint NOT NULL
);

CREATE TABLE `mygroup`
(
    `mygroup_no` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `emp_no`     bigint NOT NULL
);

ALTER TABLE `menu`
    ADD CONSTRAINT `FK_menu_TO_menu_1` FOREIGN KEY (`upper_menu_no`) REFERENCES `menu` (`menu_no`);

ALTER TABLE `orga`
    ADD CONSTRAINT `FK_company_TO_orga_1` FOREIGN KEY (`company_no`) REFERENCES `company` (`company_no`);

ALTER TABLE `orga`
    ADD CONSTRAINT `FK_emp_TO_orga_1` FOREIGN KEY (`emp_no`) REFERENCES `emp` (`emp_no`);

ALTER TABLE `orga`
    ADD CONSTRAINT `FK_dept_TO_orga_1` FOREIGN KEY (`dept_no`) REFERENCES `dept` (`dept_no`);

ALTER TABLE `dept`
    ADD CONSTRAINT `FK_dept_TO_dept_1` FOREIGN KEY (`upper_dept_no`) REFERENCES `dept` (`dept_no`);

ALTER TABLE `orga_role`
    ADD CONSTRAINT `FK_role_group_TO_orga_role_1` FOREIGN KEY (`role_group_no`) REFERENCES `role_group` (`role_group_no`);

ALTER TABLE `orga_role`
    ADD CONSTRAINT `FK_orga_TO_orga_role_1` FOREIGN KEY (`orga_no`) REFERENCES `orga` (`orga_no`);

ALTER TABLE `menu_role`
    ADD CONSTRAINT `FK_role_group_TO_menu_role_1` FOREIGN KEY (`role_group_no`) REFERENCES `role_group` (`role_group_no`);

ALTER TABLE `menu_role`
    ADD CONSTRAINT `FK_menu_TO_menu_role_1` FOREIGN KEY (`menu_no`) REFERENCES `menu` (`menu_no`);

ALTER TABLE `favorite`
    ADD CONSTRAINT `FK_mygroup_TO_favorite_1` FOREIGN KEY (`mygroup_no`) REFERENCES `mygroup` (`mygroup_no`);

ALTER TABLE `favorite`
    ADD CONSTRAINT `FK_emp_TO_favorite_1` FOREIGN KEY (`favorite_emp_no`) REFERENCES `emp` (`emp_no`);

ALTER TABLE `mygroup`
    ADD CONSTRAINT `FK_emp_TO_mygroup_1` FOREIGN KEY (`emp_no`) REFERENCES `emp` (`emp_no`);

