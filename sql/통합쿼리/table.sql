#database 가 있으면 삭제, 없으면 삭제X
drop database if exists aquaranth1;
create database aquaranth1;
use aquaranth1;

CREATE TABLE `tbl_emp`
(
    `emp_no`            bigint       NOT NULL auto_increment primary key,
    `emp_name`          VARCHAR(200) NOT NULL,
    `username`          VARCHAR(200) NOT NULL,
    `password`          VARCHAR(200) NOT NULL,
    `gender`            VARCHAR(10)  NOT NULL,
    `emp_phone`         VARCHAR(200) NULL,
    `emp_address`       VARCHAR(200) NULL,
    `emp_profile`       VARCHAR(200) NULL,
    `email`             VARCHAR(100) NULL,
    `last_login_time`   timestamp    NULL,
    `last_login_ip`     VARCHAR(100) NULL,
    `first_hired_date`  date         NOT NULL default now(),
    `last_retired_date` date         NULL,
    `reg_user`          varchar(200) NOT NULL,
    `reg_date`          timestamp    NOT NULL default current_timestamp(),
    `mod_user`          varchar(200) NULL,
    `mod_date`          timestamp    NULL
);

CREATE TABLE `tbl_company`
(
    `company_no`      bigint       NOT NULL auto_increment primary key,
    `orga_no`         bigint       NOT NULL,
    `company_name`    VARCHAR(200) NOT NULL,
    `company_address` VARCHAR(200) NOT NULL,
    `company_tel`     VARCHAR(100) NULL,
    `owner_name`      VARCHAR(100) NOT NULL,
    `founding_date`   date         NULL,
    `business_number` VARCHAR(100) NOT NULL,
    `company_use`     boolean      NOT NULL default true,
    `reg_user`        varchar(200) NOT NULL,
    `reg_date`        timestamp    NOT NULL default current_timestamp(),
    `mod_user`        varchar(200) NULL,
    `mod_date`        timestamp    NULL
);

CREATE TABLE `tbl_menu`
(
    `menu_no`       bigint       NOT NULL auto_increment primary key,
    `upper_menu_no` bigint       NULL,
    `menu_name`     VARCHAR(200) NULL,
    `menu_icon`     VARCHAR(200) NOT NULL,
    `menu_use`      boolean      NOT NULL default true,
    `menu_code`     varchar(200) NOT NULL unique,
    `menu_required` boolean      NOT NULL default false,
    `menu_admin`    boolean      NOT NULL default false,
    `reg_user`      varchar(200) NOT NULL,
    `reg_date`      timestamp    NOT NULL default current_timestamp(),
    `mod_user`      varchar(200) NULL,
    `mod_date`      timestamp    NULL,
    `menu_order`    bigint       NOT NULL default 99,
    `menu_default`  boolean      NULL
);

CREATE TABLE `tbl_orga`
(
    `orga_no`       bigint       NOT NULL auto_increment primary key,
    `upper_orga_no` bigint       NULL,
    `orga_type`     varchar(200) NOT NULL,
    `reg_user`      varchar(200) NOT NULL,
    `reg_date`      timestamp    NOT NULL default current_timestamp(),
    `mod_user`      varchar(200) NULL,
    `mod_date`      timestamp    NULL
);

CREATE TABLE `tbl_dept`
(
    `dept_no`       bigint        NOT NULL auto_increment primary key,
    `upper_dept_no` bigint        NULL,
    `company_no`    bigint        NOT NULL,
    `dept_name`     varchar(200)  NOT NULL,
    `del_flag`      boolean       NOT NULL default false,
    `main_flag`     boolean       NOT NULL default true,
    `dept_desc`     varchar(1000) NULL     default '',
    `gno`           bigint        NOT NULL,
    `ord`           bigint        NOT NULL default 0,
    `depth`         bigint        NOT NULL,
    `last_dno`      bigint        NULL,
    `reg_user`      varchar(200)  NOT NULL,
    `reg_date`      timestamp     NOT NULL default current_timestamp(),
    `mod_user`      varchar(200)  NULL,
    `mod_date`      timestamp     NULL
);

CREATE TABLE `tbl_role_group`
(
    `role_group_no`   bigint       NOT NULL auto_increment primary key,
    `role_group_name` VARCHAR(200) NOT NULL,
    `role_group_use`  boolean      NOT NULL default true,
    `company_no`      bigint       NOT NULL,
    `reg_user`        varchar(200) NOT NULL,
    `reg_date`        timestamp    NOT NULL default current_timestamp(),
    `mod_user`        varchar(200) NULL,
    `mod_date`        timestamp    NULL
);

CREATE TABLE `tbl_orga_role`
(
    `orga_role_no`  bigint       NOT NULL auto_increment primary key,
    `role_group_no` bigint       NOT NULL,
    `orga_no`       bigint       NOT NULL,
    `reg_user`      varchar(200) NOT NULL,
    `reg_date`      timestamp    NOT NULL default current_timestamp(),
    `mod_user`      varchar(200) NULL,
    `mod_date`      timestamp    NULL
);

CREATE TABLE `tbl_menu_role`
(
    `role_group_no` bigint       NOT NULL,
    `menu_no`       bigint       NOT NULL,
    `reg_user`      varchar(200) NOT NULL,
    `reg_date`      timestamp    NOT NULL default current_timestamp(),
    `mod_user`      varchar(200) NULL,
    `mod_date`      timestamp    NULL
);

CREATE TABLE `tbl_favorite`
(
    `favorite_no`     bigint       NOT NULL auto_increment primary key,
    `mygroup_no`      bigint       NOT NULL,
    `favorite_emp_no` bigint       NOT NULL,
    `reg_user`        varchar(200) NOT NULL,
    `reg_date`        timestamp    NOT NULL default current_timestamp(),
    `mod_user`        varchar(200) NULL,
    `mod_date`        timestamp    NULL
);

CREATE TABLE `tbl_mygroup`
(
    `mygroup_no` bigint       NOT NULL auto_increment primary key,
    `emp_no`     bigint       NOT NULL,
    `reg_user`   varchar(200) NOT NULL,
    `reg_date`   timestamp    NOT NULL default current_timestamp(),
    `mod_user`   varchar(200) NULL,
    `mod_date`   timestamp    NULL
);

CREATE TABLE `tbl_emp_mapping`
(
    `orga_no`      bigint       NOT NULL,
    `emp_no`       bigint       NOT NULL,
    `emp_role`     varchar(200) NOT NULL,
    `emp_rank`     varchar(200) NOT NULL default 'emp',
    `hired_date`   date         NOT NULL default now(),
    `retired_date` date         NULL,
    `reg_user`     varchar(200) NOT NULL,
    `reg_date`     timestamp    NOT NULL default current_timestamp(),
    `mod_user`     varchar(200) NULL,
    `mod_date`     timestamp    NULL
);

CREATE TABLE `tbl_dept_mapping`
(
    `dept_no`   bigint       NOT NULL,
    `orga_no`   bigint       NOT NULL,
    `dept_main` boolean      NOT NULL default false,
    `reg_user`  varchar(200) NOT NULL,
    `reg_date`  timestamp    NOT NULL default current_timestamp(),
    `mod_user`  varchar(200) NULL,
    `mod_date`  timestamp    NULL
);


ALTER TABLE `tbl_company`
    ADD CONSTRAINT `FK_tbl_orga_TO_tbl_company_1` FOREIGN KEY (
                                                               `orga_no`
        )
        REFERENCES `tbl_orga` (
                               `orga_no`
            );

ALTER TABLE `tbl_menu`
    ADD CONSTRAINT `FK_tbl_menu_TO_tbl_menu_1` FOREIGN KEY (
                                                            `upper_menu_no`
        )
        REFERENCES `tbl_menu` (
                               `menu_no`
            );

ALTER TABLE `tbl_orga`
    ADD CONSTRAINT `FK_tbl_orga_TO_tbl_orga_1` FOREIGN KEY (
                                                            `upper_orga_no`
        )
        REFERENCES `tbl_orga` (
                               `orga_no`
            );

ALTER TABLE `tbl_dept`
    ADD CONSTRAINT `FK_tbl_company_TO_tbl_dept_1` FOREIGN KEY (
                                                               `company_no`
        )
        REFERENCES `tbl_company` (
                                  `company_no`
            );

ALTER TABLE `tbl_orga_role`
    ADD CONSTRAINT `FK_tbl_role_group_TO_tbl_orga_role_1` FOREIGN KEY (
                                                                       `role_group_no`
        )
        REFERENCES `tbl_role_group` (
                                     `role_group_no`
            );

ALTER TABLE `tbl_orga_role`
    ADD CONSTRAINT `FK_tbl_orga_TO_tbl_orga_role_1` FOREIGN KEY (
                                                                 `orga_no`
        )
        REFERENCES `tbl_orga` (
                               `orga_no`
            );

ALTER TABLE `tbl_menu_role`
    ADD CONSTRAINT `FK_tbl_role_group_TO_tbl_menu_role_1` FOREIGN KEY (
                                                                       `role_group_no`
        )
        REFERENCES `tbl_role_group` (
                                     `role_group_no`
            );

ALTER TABLE `tbl_menu_role`
    ADD CONSTRAINT `FK_tbl_menu_TO_tbl_menu_role_1` FOREIGN KEY (
                                                                 `menu_no`
        )
        REFERENCES `tbl_menu` (
                               `menu_no`
            );

ALTER TABLE `tbl_favorite`
    ADD CONSTRAINT `FK_tbl_mygroup_TO_tbl_favorite_1` FOREIGN KEY (
                                                                   `mygroup_no`
        )
        REFERENCES `tbl_mygroup` (
                                  `mygroup_no`
            );

ALTER TABLE `tbl_favorite`
    ADD CONSTRAINT `FK_tbl_emp_TO_tbl_favorite_1` FOREIGN KEY (
                                                               `favorite_emp_no`
        )
        REFERENCES `tbl_emp` (
                              `emp_no`
            );

ALTER TABLE `tbl_mygroup`
    ADD CONSTRAINT `FK_tbl_emp_TO_tbl_mygroup_1` FOREIGN KEY (
                                                              `emp_no`
        )
        REFERENCES `tbl_emp` (
                              `emp_no`
            );

ALTER TABLE `tbl_emp_mapping`
    ADD CONSTRAINT `FK_tbl_orga_TO_tbl_emp_mapping_1` FOREIGN KEY (
                                                                   `orga_no`
        )
        REFERENCES `tbl_orga` (
                               `orga_no`
            );

ALTER TABLE `tbl_emp_mapping`
    ADD CONSTRAINT `FK_tbl_emp_TO_tbl_emp_mapping_1` FOREIGN KEY (
                                                                  `emp_no`
        )
        REFERENCES `tbl_emp` (
                              `emp_no`
            );

ALTER TABLE `tbl_dept_mapping`
    ADD CONSTRAINT `FK_tbl_dept_TO_tbl_dept_mapping_1` FOREIGN KEY (
                                                                    `dept_no`
        )
        REFERENCES `tbl_dept` (
                               `dept_no`
            );

ALTER TABLE `tbl_dept_mapping`
    ADD CONSTRAINT `FK_tbl_orga_TO_tbl_dept_mapping_1` FOREIGN KEY (
                                                                    `orga_no`
        )
        REFERENCES `tbl_orga` (
                               `orga_no`
            );
