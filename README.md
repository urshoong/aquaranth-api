# aquaranth-api

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
