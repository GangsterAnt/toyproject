
도커 이미지를 빌드하려면 `docker build` 명령어를 사용하면 됩니다. 예를들어, 현재 디렉토리에 있는 Dockerfile을 사용하여 이미지를 빌드 하려면 다음과 같이 입력합니다.

```docker build -t 이미지이름:태그```

도커 이미지를 실행하려면 docker run 명령어를 사용하면 됩니다. 예를들어, `my-spring-boot-app`이라는 이름으로 이미지를 실행하려면 다음과 같이 입력합니다.

```docker run -p 8080:8080 my-spring-boot-app```    

- -p : 포트포워딩 (포트 설정) (호스트의 포트 (앞의 포트)를 컨테이너의 포트(뒤의포트)에 연결함)

실행중인 도커 컨테이너 목록을 표시하려면 docker ps 명령어를 사용하면 됩니다. 다음과 같이 입력합니다.

```docker ps```

실행중인 도커 컨테이너를 중지하려면 docker stop 명령어를 사용하면 됩니다. 예를들어, `my-spring-boot-app`이라는 이름으로 실행중인 컨테이너를 중지하려면 다음과 같이 입력합니다.

```docker stop my-spring-boot-app```

컨테이너 ID를 사용할 수도 있습니다. 예를들어 `docker ps` 명령어를 사용하여 컨테이너 ID를 확인하고, 해당 ID를 사용하여 컨테이너를 중지할 수 있습니다.

```docker stop 컨테이너ID```



# 도커와 mySql연동하기
아래 명령어를 통해 mySql docker image를 받는다 5.7이 stable 하기에 이를 추천한다.

```docker puill --platform linux/amd64 mysql:5.7```

_refer: https://velog.io/@jinny-l/Docker%EB%A1%9C-MySQL-%EC%84%A4%EC%B9%98%ED%95%98%EA%B8%B0MySQL-5.7-Mac-M1_

아래 명령어를 통해 다운받은 도커 MYsql:5.7을 돌릴수 있다.
` docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=**** -d -p 3306:3306 mysql:5.7`

- --name : mysql docker이름
- -e : 환경변수 설정
- -d : 백그라운드로 실행
- -p : 포트포워딩 (포트 설정)
- mysql:5.7 : mysql 5.7 버전


## Docker volume을 통해 db 같은 설정을 변경할 수 있다.

`docker volume create mysql-volume`

`docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=**** -d -v {absolut-directory}:{container 내부 경로} -p 3306:3306 mysql:5.7`

/Users/francis/IntelliJProject/toyproject/db
/Users/francis/IntelliJProject/docker/toyproject/volume
:/var/lib/mysql

local 과 docker 컨테이너 안의 db연동
TODO : local host 3306이란?


TODO:
## Template
- Multi Module
  - domain (jpa)
  - api (web, security)
  - admin (web,thymeleaf, security)
  - batch (batch)
- Dockerized
  - api
  - batch
  - admin
  - nginx
  - mysql
## Side Project
- api
  - 로그인 하기
  - 게시글 쓰기 
  - 게시글 읽기
  - 게시글 업데이트
  - 게시글 삭제
  - 게시글 좋아요/싫어요
  - 댓글 쓰기
  - 댓글 삭제
  - 댓글 업데이트
  - 배너 보여주기 (Optional)
- batch
  - 해당 날짜에 쓴 게시글을 파일 형식으로 저장
- admin
  - 관리자 로그인
  - 게시글 삭제
  - 댓글 삭제
  - 배너 설정 (Optional)
  - 금지어 설정 (Optional)
  - 회원 강제 탈퇴 (Optional)