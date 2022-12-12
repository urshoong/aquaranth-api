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


Aquaranth API
---
아쿠아란스 API 프로젝트 입니다.

> 해당 어플리케이션은 [URL](https://dq-front.run.goorm.io/) 에서 시연하실 수 있습니다. 

## Getting Started
```shell
./gradlew bootRun
```


## Features

### [@김민준](https://github.com/d0uwhs) 메뉴관리 (개발자 A)  

#### 메뉴사용설정 개발
- [x] 메뉴 트리조회/추가/삭제
- [x] 메뉴 아이콘 업로드 (모든 메뉴는 아이콘 업로드 가능, 업로드시 GNB/LNB 영역에 해당 아이콘 표시)
- [x] 메뉴 정렬 등
- [x] 무한 depth 구조
- [x] 샘플 GNB (1depth 메뉴), 시스템설정 모듈 포함 5개 내외
- [x] 샘플 LNB (2depth 이하 메뉴) (각 GNB 당 10개 내외)
- [x] 시스템설정 하위 LNB : 메뉴사용설정/권한그룹설정/사용자권한설정/회사/부서/사원관리
#### GNB / LNB 개발
- [x] 로그인한 사용자 권한(세션에 저장된 권한)에 따른 GNB / LNB 노출
- [x] LNB 클릭시, 해당 메뉴와 관련된 컴포넌트로 이동 처리
---
### [@임종현](https://github.com/ehek01), [@박준성](https://github.com/urshoong) 권한관리 (개발자 B)

#### 팀장 역할 (메뉴 / 권한 / 조직 구조 전체 이해 필요)
- [x] 최종적으로 로그인한 사용자가 갖고 있는 권한에 맞는 메뉴에만 접근 가능 하도록 확인.
- [x] 회사변경 프로필 팝업에서 회사/부서 정보 변경시에도, 해당 회사/부서/사용자가 갖고 있 는 권한에 맞는 메뉴에만 접근 가능 하도록 최종 확인.
#### 권한그룹설정 개발
- [x] 권한-메뉴 매핑 저장
- [x] 샘플 권한 데이터 10개 내외
#### 사용자권한설정 개발
- [x] 권한-조직 매핑 저장
- [x] 권한-회사, 권한-부서, 권한-사원 매핑
- [x] 샘플 권한-조직 데이터 5개 내외
- [x] admin 계정만, 시스템설정 모듈 접근가능
- [x] 시스템설정 모듈 이외 나머지 모듈은 전체 계정 접근 가능
- [x] 개발자D가 개발한 [공통 조직도 팝업]을 호출하여 해당 팝업에서 리턴 받은 조직 데 이터와 매핑 하여 저장
---

### [@강도영](https://github.com/DoZerrro) 회사 관리 (개발자 C)
#### 회사 개발
- [x] 회사 목록
- [x] 회사 조회
- [x] 회사 등록/삭제
- [x] 샘플 회사 5개 내외
#### 메인 조직도 팝업
- [x] 조직도 트리 / 사원 목록 / 사원 상세
- [x] 마이 그룹
- [x] 마이그룹 생성하여 사원 즐겨찾기
---

### [@박경민](https://github.com/pgm1120) 부서 관리 (개발자 D)
#### 부서 관리
- [x] 부서 트리
- [x] 부서 정보
- [x] 부서원 정보
- [x] 부서 등록 / 삭제
- [x] 샘플 부서 5개 내외
#### 공통 조직도 팝업
- [x] 조직도 트리 / 부서사원 목록 / 선택된 부서 사원 정보
- [x] 사용자 선택(복수 선택 가능), 선택된 사원정보(회사/부서/사원 정보) 콜백처리
---

### [@정수연](https://github.com/suyeonworld) 사원 관리 (개발자 E)
#### 부서 관리
- [x] 사원 목록
- [x] 사원 정보 (로그인 아이디 / 비밀번호)
- [x] 로그인은 간단하게 구현
- [x] 로그인 완료시, 해당 사원이 갖고 있는 모든 권한 세션에 저장
- [x] 샘플 사원 10명 내외
#### 회사변경 프로필 팝업
- [x] 현재 접속한 조직(회사/부서) 정보 / 최근 로그인 시간 / 직전 로그인 IP / 현재 로그인 IP
- [x] 회사/부서 변경 기능
- [x] 변경시 변경된 회사/부서에 대한 권한처리에 의해 메뉴(GNB/LNB) 노출 필요.


## Verifying & Request Sequence Diagram
![seq](https://github.com/devaquariums/aquaranth/blob/main/sequence_diagram/sequence_diagram.png?raw=true)


## Tech Stack

### Client

#### Application 
![](https://img.shields.io/badge/React_16.10.5-61DAFB.svg?style=for-the-badge&logo=React&logoColor=black)
![](https://img.shields.io/badge/React%20Router_5.3.4-CA4245.svg?style=for-the-badge&logo=React-Router&logoColor=white)
![](https://img.shields.io/badge/Redux-764ABC.svg?style=for-the-badge&logo=Redux&logoColor=white)

#### Style
![](https://img.shields.io/badge/styledcomponents-DB7093.svg?style=for-the-badge&logo=styled-components&logoColor=white)

#### Tools
![](https://img.shields.io/badge/React%20Hook%20Form-EC5990.svg?style=for-the-badge&logo=React-Hook-Form&logoColor=white)
![](https://img.shields.io/badge/.ENV-ECD53F.svg?style=for-the-badge&logo=dotenv&logoColor=black)
![](https://img.shields.io/badge/Axios-5A29E4.svg?style=for-the-badge&logo=Axios&logoColor=white)

#### Build Tools
![](https://img.shields.io/badge/npm-CB3837.svg?style=for-the-badge&logo=npm&logoColor=white)
![](https://img.shields.io/badge/Webpack-8DD6F9.svg?style=for-the-badge&logo=Webpack&logoColor=black)
![](https://img.shields.io/badge/Babel-F9DC3E.svg?style=for-the-badge&logo=Babel&logoColor=black)

#### Linting

![](https://img.shields.io/badge/ESLint-4B32C3.svg?style=for-the-badge&logo=ESLint&logoColor=white)
![](https://img.shields.io/badge/Prettier-F7B93E.svg?style=for-the-badge&logo=Prettier&logoColor=black)


### Server

#### Framework
![](https://img.shields.io/badge/Spring%20Boot-6DB33F.svg?style=for-the-badge&logo=Spring-Boot&logoColor=white)

#### Security
![](https://img.shields.io/badge/JSON%20Web%20Tokens-000000.svg?style=for-the-badge&logo=JSON-Web-Tokens&logoColor=white)
![](https://img.shields.io/badge/Spring%20Security-6DB33F.svg?style=for-the-badge&logo=Spring-Security&logoColor=white)

#### Databases
![](https://img.shields.io/badge/Redis-DC382D.svg?style=for-the-badge&logo=Redis&logoColor=white)
![](https://img.shields.io/badge/MariaDB-003545.svg?style=for-the-badge&logo=MariaDB&logoColor=white)

#### Testing
![](https://img.shields.io/badge/JUnit5-25A162.svg?style=for-the-badge&logo=JUnit5&logoColor=white)


#### Build Tools 
![](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)

#### IDE
![](https://img.shields.io/badge/IntelliJ%20IDEA-000000.svg?style=for-the-badge&logo=IntelliJ-IDEA&logoColor=white)

### DevOps
![](https://img.shields.io/badge/goorm-000000.svg?style=for-the-badge&logo=iCloud&logoColor=white)
![](https://img.shields.io/badge/Object_Storage-B63B4B.svg?style=for-the-badge&logo=Amazon-S3&logoColor=white)
