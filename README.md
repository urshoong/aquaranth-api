# aquaranth-api

# 레디스

## 현재 레디스에 올라가있는 정보
1. 어플리케이션 시작시 메뉴코드에 매핑되어있는 권한그룹들을 저장합니다.
2. 로그인시, 접속한 사원의 회사객체, 부서객체, 사원객체, 권한그룹객체리스트가 저장됩니다.

## 레디스 업데이트 되어야 할 때
1. 회사정보 추가, 수정, 삭제시
2. 부서정보 추가, 수정, 삭제시
3. 사원정보 추가, 수정, 삭제시
4. 권한그룹에 대한 메뉴권한 변경

## 업데이트를 시키는 여러가지 방안
1. 이벤트리스너를 이용하여 레디스 업데이트가 필요한 메서드에서 이벤트를 발생시킨다.
2. aop 를 이용하여 레디스 업데이트가 필요한 메서드에서 비즈니스 로직을 수행한다.
3. (가능할까?) @RedisUpdate 라는 어노테이션을 만들어서 필요한 메서드 위에 붙힌다.


수정
수정 어떻게 할거지? 
TodoDTO 타입 데이터를 수정해서 모든 테이블 컬럼이 다 보이게 해야겠찌?
그럼 Controller 에서도 TodoDTO타입으로 반환해야겠네?
파라미터도 TodoDTO타입으로 반환하고 
# service (비지니스 로직)
1. 수정하고자 하는 tno 값을 뽑는다.

2. 뽑은 tno 값을 getOne으로 조회해서 /  TodoDTO타입인 변수에 초기화 시킨다.





# 에로 사항
1. 등록구현 해놓고 swagger에서 등록이 안되는 상황 발생
Type mismatch 에러가 떠서 select Key에 result Type을 long타입으로 바꾸니 해결 됨

2. Service와 Controller의 정확한 차이와 비지니스 로직은 어디서 처리되는 것이 맞는지 생각해봐야 겠다.

3. @Param을 사용 하는 이유 : MyBatis에서 2개 이상의 파라미터를 인식 못해서 사용

4. builder는 값을 설정하지 않으면 자동으로  null을 채우는데 직접 기본값을 설정하기 위해 사용


## 권한그룹 등록

[//]: # (TODO : add, delete)

1. tbl_dept테이블 drop하고 다시만든후 insert 테스트 해보면 getNextOrd 메서드에 int로 선언했는데 null값을 준다고 에러 뜸

2. service와 controller 구현후 swagger에서 부서 등록작업을 하면 ord값과 lastDno값이 들어가지 않음
{
   "result newGno": 1,
   "parentDeptNo": 2,
   "result lastDno": 0,
   "result newDeptNo": 0,
   "result ord": 3,
   "result newParent": 2
}



