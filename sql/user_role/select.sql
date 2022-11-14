## 사용자권한설정 - 권한그룹기준/사용자기준 - 권한그룹 조회 조건 중 회사명 목록 조회
select company_no, company_name
from tbl_company
where company_no = ${companyNo}
;

## 사용자권한설정 - 권한그룹기준 - 권한그룹 조회
# @param(company_name) : 회사명 선택값
# @param(search) : 권한그룹명 검색어 입력값
select role_group_no, role_group_name, company_no
from tbl_role_group
where role_group_use = true
and company_no = ${companyNo}
and role_group_name like concat('%', ${roleGroupSearch}, '%')
;

## 사용자권한설정 - 권한그룹기준 - 선택된 권한그룹이 부여된 조직 목록 조회
# @param() : #

# 로그인한 사용자의 회사 정보 기준으로 동작하는 것이 전제
# 선택한 권한그룹이 부여된 모든 조직(회사/부서/사원/) 목록 출력
# 선택한 권한그룹이 부여된 orga를 찾아서 emp를 연결하는게 좋은가?
# 조직정보, 직급, 이름, ID 필요함

SELECT FN_GET_HIERARCHY_ORGA(case when rg.orga_type = 'emp' then rg.upper_orga_no else rg.orga_no end)
     , tem.emp_rank
     , te.emp_name
     , te.username
     , rg.orga_no
     , rg.upper_orga_no
     , rg.orga_type
     , td.dept_name
FROM (
         SELECT trg.role_group_no
              , trg.role_group_name
              , trg.company_no
              , tor.orga_role_no
              , tor.orga_no
              , to2.upper_orga_no
              , to2.orga_type
           FROM tbl_role_group trg
           LEFT JOIN tbl_orga_role tor ON tor.role_group_no = trg.role_group_no
           LEFT JOIN tbl_orga to2 ON to2.orga_no = tor.orga_no
          WHERE trg.role_group_use = true
            AND trg.role_group_no = ${roleGroupNo}
            AND trg.company_no = ${companyNo}
     ) rg
JOIN tbl_orga og ON og.orga_no = rg.orga_no
LEFT JOIN tbl_emp_mapping tem ON tem.orga_no = og.orga_no
LEFT JOIN tbl_emp te ON te.emp_no = tem.emp_no
LEFT JOIN tbl_dept_mapping tdm ON og.upper_orga_no = tdm.orga_no
LEFT JOIN tbl_dept td ON tdm.dept_no = td.dept_no
WHERE (
        td.dept_name LIKE CONCAT('%', ${userListSearch}, '%') OR
        tem.emp_rank LIKE CONCAT('%', ${userListSearch}, '%') OR
        te.emp_name  LIKE CONCAT('%', ${userListSearch}, '%') OR
        te.username  LIKE CONCAT('%', ${userListSearch}, '%')
      )
;

select * from tbl_role_group;
select * from tbl_orga_role;
# insert into tbl_orga_role (role_group_no, orga_no, reg_user) values(1, 1, '준성');


## 사용자권한설정 - 사용자기준 - 선택된 회사에 소속된 사용자 목록 조회
# 회사코드 기준으로 조직에서 사용자 정보 조회
# 회사이름, 부서이름, [직급], 이름(ID) 그리드에 출력
# @param() : #
