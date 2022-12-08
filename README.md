Aquaranth API
---
아쿠아란스 API 프로젝트 입니다.

> 해당 어플리케이션은 [URL](https://dq-front.run.goorm.io/) 에서 시연하실 수 있습니다. 

## Getting Started
```shell
./gradlew bootRun
```

## Verifying & Request Sequence Diagram
![seq](https://user-images.githubusercontent.com/110054050/206376493-728d05b1-af20-490c-a970-412259f02ae9.png)

## Project Architecture
![arc](https://user-images.githubusercontent.com/110054050/206377505-5594df0c-6018-4d38-b28b-df090308bb7f.png)



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


## Technical Issues
### [@김민준](https://github.com/d0uwhs) 메뉴관리 (개발자 A)

### 파일 업로드 / 다운로드
##### Question
  - 기능 개발시, 파일과 의존관계가 있는 기능(혹은 모듈) 개발 시, 파일 파편화를 해결하여야 한다.
  - 파일을 제공하는 URL을 불특정 다수가 알게 될 경우, 사원 사진과 같은 개인정보에 대한 보안 이슈가 발생할 수 있다.
##### Answer
  - Amazon S3와 같은 서비스 되고 있는 플랫폼을 이용하기 보다, 직접 구현해 본다.
  - [MinIO](https://en.wikipedia.org/wiki/MinIO) 라는 S3 API와 호환되는 Object Storage를 이용하여 파일서버 직접 구축.
  - 파일명을 UUID를 이용하여 관리하고, 파일 URL을 알더라도, API 서버에 있는 토큰 키가 없으면 파일에 접근할 수 없도록 관리한다.

### 에러 핸들링
##### Question
- 기존 HTTP 에러코드만으로는 클라이언트에서 에러를 핸들링하기에 다소 부족한 부분이 있다.
##### Answer
- Exception Handler를 이용하여 서비스에서 발생하는 에러를 공통적으로 처리하고, 에러를 처리하는 ResponseEntity를 만들어 공통적인 메시지를 전달한다.
- 에러코드를 Enum으로 정의하고, 해당하는 예외에 맞는 에러코드를 클라이언트에게 전달한다.

### 메뉴 권한 체크
##### Question
- 유저가 요청하는 서비스가 정상적인 권한을 가진 요청인지에 대한 판단하여야 한다. (URL을 통한 모듈 접근) 
##### Answer
- Interceptor와 Custom Annotation을 이용하여 권한 체크를 한다.
- 권한을 체크하고자 하는 컨트롤러에 MenuCode Annotation을 붙히고, 접근 권한을 확인할 메뉴코드 Enum을 할당한다.
- JWT 필터를 통해 검증된 유저 정보를 이용하여, 세션(Redis)에 로그인된 사용자의 정보와, 권한 그룹을 받아온 뒤, 해당하는 권한 그룹이 있으면 정상적인 요청으로 판단하고, 없는 경우 요청을 차단한다.

### JWT 토큰 체크
##### Question
- 클라이언트가 요청하는 모든 요청은 JWT 토큰을 헤더에 포함하여야 한다.
- Access Token이 만료되면, 클라이언트의 요청을 방해하지 않고, 갱신 한 뒤, 다시 전송하여야 한다.
##### Answer
- Axios을 통한 공통 인스턴스를 생성하고, Axios Interceptor를 이용하여 모든 요청을 세션스토리지에 저장된 Access Token을 헤더에 포함하여 요청을 전송한다.
- 해당하는 요청이 만료된 토큰인 경우, Interceptor를 통해 토큰을 재발급하고, 재발급이 된 경우, 기존 요청을 다시 전송한다.

### 메뉴 서비스
##### Question
- 클라이언트 모듈들은 서버에서 받은 경로를 통해 동적 Import 되어야 한다.
- 재귀 쿼리를 사용하지 않고, 메뉴 경로, Depth가 등록되어야 한다.
##### Answer
- 리액트 어플리케이션이 실행될 때, 서버에서 모듈 경로(메뉴 경로)를 받아온 뒤, 해당하는 모듈을 호출할 경우에 동적 Import 처리한다.
- 상위 메뉴를 호출한 뒤, 상위 메뉴 경로, Depth를 불러온 뒤, 서비스 레이어에서 처리한 뒤, DB에 저장된다.
--- 
### [@임종현](https://github.com/ehek01) 권한그룹설정 (개발자 E)
### 권한처리
##### Question 사용자가 서비스를 이용할때 수많은요청(클릭)을 할텐데, 권한체크를 어떻게 진행할건가요?
##### Answer 요청시마다 db를 조회하는것은 효율적이지 않다고 생각하여, 로그인시 레디스에 접속한사원이 가지고있는 권한그룹들을 캐쉬해두어, 요청시마다 체크를 진행하였습니다.

### 인증
##### Question 인증처리는 어떻게 하실건가요?
##### Answer 리액트와 스프링은 상태가 지속되지 않기 때문에  인증시 JWT토큰을 발급해주고,  요청시마다 필터에서 인증확인을 하였습니다.

### 레디스
##### Question 레디스에 인증정보를 저장하는데, db에서 유저의 조직/권한정보 변경시 동기화처리는 어떻게 하실건가요?
##### Answer 커스텀 어노테이션을 만들어서 db에 query가 일어나는 시점에 Spring AOP를 이용하여 레디스에도 동기화를 시켜주었습니다.
--- 
### [@정수연](https://github.com/suyeonworld) 사원 관리 (개발자 E)
### 접속한 사원에 대한 회사/부서/사원 정보 출력
##### Question
- 한 사원이 여러 회사에 속할 수 있고, 회사는 여러 부서를 가지고 있을 수 있다.
- 계층형 구조로 나타내야한다.
- List나 Hashmap 사용 시, 리액트에서 처리가 어렵다.
##### Answer
- 게시판의 대댓글 기능과 같은 구조라는 점을 깨닫고 resultMap을 이용하였다.


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
