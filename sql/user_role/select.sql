## 사용자권한설정 - 권한그룹기준/사용자기준 - 권한그룹 조회 조건 중 회사명 목록 조회
select company_no, company_name
from company
;

## 사용자권한설정 - 권한그룹기준 - 권한그룹 조회
# @param(company_name) : 회사명 선택값
# @param(search) : 권한그룹명 검색어 입력값
select role_group_no, role_group_name, company_name
from role_group
where role_group_use = true
and company_name = ${companyName}
and role_group_name like concat('%', ${search}, '%')
;

## 사용자권한설정 - 권한그룹기준 - 선택된 권한그룹이 부여된 사용자 목록 조회
# @param() : #
SELECT c.company_no, c.company_name, dt.dept_no, dt.dname, em.emp_no, em.emp_name, em.ID
     , CONCAT(c.company_name, '>', dt.dname) oname
FROM (
         SELECT d.dept_no, d.company_no, d.dname, d.dept_sort
              , o.orga_no, o.orga_type
         FROM tbl_dept d
                  LEFT JOIN deptMapping dm ON dm.dept_no = d.dept_no
                  LEFT JOIN orga o ON o.orga_no = dm.orga_no
         WHERE d.del_flag = FALSE
           AND d.main_flag = TRUE
           AND d.company_no = 1  ## 회사 구분
     ) dt
         JOIN (
    SELECT e.emp_no, e.emp_name, e.username ID
         , em.emp_rank, o.upper_orga_no
    FROM emp e
             LEFT JOIN empMapping em ON em.emp_no = e.emp_no
             LEFT JOIN orga o ON o.orga_no = em.orga_no
) em ON em.upper_orga_no = dt.orga_no
         JOIN company c ON c.company_no = dt.company_no AND c.company_use = true
;



## 사용자권한설정 - 사용자기준 - 선택된 회사에 소속된 사용자 목록 조회
# 회사코드 기준으로 조직에서 사용자 정보 조회
# 회사이름, 부서이름, [직급], 이름(ID) 그리드에 출력
# @param() : #
