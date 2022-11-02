## 사용자권한설정 - 권한그룹기준/사용자기준 - 권한그룹 조회 조건 중 회사명 목록 조회
select company_no, company_name
from company
;

## 사용자권한설정 - 권한그룹기준 - 권한그룹 조회
# @param(company_name) : 회사명 선택값
# @param(search) : 권한그룹명 검색어 입력값
select *
from role_group
where role_group_use = true
and company_name = ${company_name}
and role_group_name like concat('%', ${serach}, '%');
;

## 사용자권한설정 - 권한그룹기준 - 선택된 권한그룹이 부여된 사용자 목록 조회
# @param(role_group_no)
SELECT o.company_no, c.company_name, o.dept_no, d.dept_name, o.emp_no, e.emp_name, e.username
#      o.rank #차후 직급 추가 시
FROM (
      SELECT ogr.role_group_no, ogr.orga_no, og.company_no, og.dept_no, og.emp_no
      FROM orga_role ogr
      JOIN orga og ON og.orga_no = ogr.orga_no
      WHERE ogr.role_group_no = #{role_group_no}
      AND og.dept_no IS NOT NULL
      AND og.emp_no IS NOT NULL
     ) o
     LEFT JOIN company c ON c.company_no = o.company_no
     LEFT JOIN dept d ON d.dept_no = o.dept_no
     LEFT JOIN emp e ON e.emp_no = o.emp_no

## 사용자권한설정 - 사용자기준 - 선택된 회사에 소속된 사용자 목록 조회
# 회사코드 기준으로 조직에서 사용자 정보 조회
# 회사이름, 부서이름, [직급], 이름(ID) 그리드에 출력
# @param(company_no) : 선택한 회사 코드 기준으로 검색
# @param(search1) : 이름/ID 검색 조건(예정)
# @param(search2) : 권한명 검색 조건(예정)
SELECT o.company_no, c.company_name, o.dept_no, d.dept_name, o.emp_no, e.emp_name, e.username
FROM (
      SELECT og.orga_no, ogr.role_group_no, og.company_no, og.dept_no, og.emp_no
      FROM orga og
      JOIN orga_role ogr ON ogr.orga_no = og.orga_no
      WHERE og.company_no = #{company_no}
	AND og.dept_no IS NOT NULL
	AND og.emp_no IS NOT NULL
     ) o
    LEFT JOIN company c ON c.company_no = o.company_no
    LEFT JOIN dept d ON d.dept_no = o.dept_no
    LEFT JOIN emp e ON e.emp_no = o.emp_no
where
;
