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


## 사용자권한설정 - 사용자기준 - 선택된 회사에 소속된 사용자 목록 조회
# 회사코드 기준으로 조직에서 사용자 정보 조회
# 회사이름, 부서이름, [직급], 이름(ID) 그리드에 출력
# @param() : #
