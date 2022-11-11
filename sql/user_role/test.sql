# 회사
select * from company;
# 부서
select * from tbl_dept;
# 부서 맵핑
select * from deptMapping;
# 사원
select * from emp;
# 사원 맵핑
select * from empMapping;
# 조직
select * from orga;
# 메뉴
select * from menu;
# 권한 그룹
select * from role_group;
# 메뉴 맵핑
select * from menu_role;
# 조직 권한
select * from orga_role;

###########################################################################################

select * from menu;

## memu 계층구조 조회 쿼리
WITH RECURSIVE CTE AS (
    SELECT
        menu_no,
        menu_name,
        IFNULL(upper_menu_no, 0) AS upper_menu_no,
        concat(menu_no) AS NO_PATH,
        concat('/', menu_code) AS MENU_PATH,
        1 AS DEPTH
    FROM menu
    WHERE IFNULL(upper_menu_no, 0) = 0
    UNION ALL
    SELECT
        m.menu_no,
        m.menu_name,
        IFNULL(m.upper_menu_no, 0),
        concat(C.NO_PATH, '->', m.menu_no) AS NO_PATH,
        concat(C.MENU_PATH, '/', m.menu_code) AS MENU_PATH,
        1 + C.DEPTH AS DEPTH
    FROM menu m
             INNER JOIN CTE C ON C.menu_no = m.upper_menu_no
)
SELECT
    CONCAT(LPAD('    ', 2*(CTE.DEPTH-1),' '), CTE.menu_no) AS menu_no,
    IFNULL(CTE.upper_menu_no, 0) AS upper_menu_no,
    CONCAT(LPAD('    ', 2*(CTE.DEPTH-1),' '),CTE.menu_name) AS menu_name,
    CTE.NO_PATH,
    CTE.MENU_PATH,
    #    RANK() OVER (PARTITION BY (CASE WHEN CTE.upper_menu_no IS NOT NULL THEN 1 ELSE 0 END) ORDER BY CTE.NO_PATH DESC) AS rank,
        #	DENSE_RANK() OVER (PARTITION BY (CASE WHEN upper_menu_no IS NOT NULL THEN 1 ELSE 0 END) ORDER BY NO_PATH DESC) AS dense_rank,
        #	ROW_NUMBER() OVER (PARTITION BY (CASE WHEN upper_menu_no IS NOT NULL THEN 1 ELSE 0 END) ORDER BY NO_PATH DESC) AS row_num,
        DEPTH
FROM CTE
ORDER BY NO_PATH
;

###########################################################################################

# dmap 원본
SELECT c.company_no AS ORGA_CODE
     , c.company_name AS dname
     , o.orga_no
     , o.upper_orga_no
     , o.orga_type
FROM company c
         LEFT JOIN orga o ON o.orga_no = c.orga_no
WHERE c.company_use = true
UNION ALL
SELECT d.dept_no AS ORGA_CODE
     , d.dname
     , o.orga_no
     , o.upper_orga_no
     , o.orga_type
FROM tbl_dept d
         LEFT JOIN deptMapping dm ON dm.dept_no = d.dept_no
         LEFT JOIN orga o ON o.orga_no = dm.orga_no
UNION ALL
SELECT e.emp_no AS ORGA_CODE
     , e.emp_name AS dname
     , o.orga_no
     , o.upper_orga_no
     , o.orga_type
FROM emp e
         LEFT JOIN empMapping em ON em.emp_no = e.emp_no
         LEFT JOIN orga o ON o.orga_no = em.orga_no
;

###########################################################################################

#회사 / 부서/ 사원 계층구조 쿼리

WITH RECURSIVE CTE AS (
    SELECT
        ORGA_CODE,
        orga_no,
        ORGA_NAME,
        IFNULL(upper_orga_no, 0) AS upper_orga_no,
        CONCAT(orga_no) AS ID_PATH,
        CONCAT(ORGA_NAME) AS NAME_PATH,
        1 AS DEPTH,
        SORT,
        orga_type
    FROM dmap
    WHERE IFNULL(upper_orga_no, 0) = 0
    UNION ALL
    SELECT
        m.ORGA_CODE,
        m.orga_no,
        m.ORGA_NAME,
        IFNULL(m.upper_orga_no, 0),
        CONCAT(C.ID_PATH, '->', m.orga_no) AS ID_PATH,
        CONCAT(C.NAME_PATH, '>', m.ORGA_NAME) AS NAME_PATH,
        1 + C.DEPTH AS DEPTH,
        m.SORT,
        m.orga_type
    FROM dmap m
    INNER JOIN CTE C ON C.orga_no = m.upper_orga_no
),
dmap AS (
    select t.ORGA_CODE
         , t.ORGA_NAME
         , t.orga_no
         , t.SORT
         , o.upper_orga_no
         , o.orga_type
    from (
             select c.company_no   AS ORGA_CODE
                  , c.company_name as ORGA_NAME
                  , c.orga_no
                  , 0              AS SORT
             from company c
             where c.company_use = true
#                and c.company_no = 1 ## 회사 선택
             UNION
             select d.dept_no   AS ORGA_CODE
                  , d.dname     AS ORGA_NAME
                  , dm.orga_no
                  , d.dept_sort AS SORT
             from tbl_dept d
             left join deptMapping dm on d.dept_no = dm.dept_no
             where del_flag = false
               and main_flag = true
             UNION
             select e.emp_no   AS ORGA_CODE
                  , e.emp_name AS ORGA_NAME
                  , em.orga_no
                  , 0          AS SORT
             from emp e
             left join empMapping em on e.emp_no = em.emp_no
         ) t
    left join orga o on o.orga_no = t.orga_no
)
SELECT
    ORGA_CODE,
    orga_no, ## CONCAT(LPAD('    ', 2*(DEPTH-1),' '),orga_no) AS orga_no,
        IFNULL(upper_orga_no, 0) AS upper_orga_no,
    ORGA_NAME, ## CONCAT(LPAD('    ', 2*(DEPTH-1),' '),dname) AS dname,
        ID_PATH,
    NAME_PATH,
    DEPTH,
    SORT,
    orga_type
FROM CTE
         # WHERE orga_type = 'emp' ## 사원만 조회
ORDER BY ID_PATH
;

###########################################################################################

###########################################################################################

###########################################################################################

###########################################################################################

###########################################################################################

###########################################################################################

###########################################################################################

###########################################################################################

