#베이스 이미지 선택
FROM eclipse-temurin:17-jdk

#작업 디텍토리 설정
WORKDIR /app

#$JAR 파일 복사
COPY build/libs/board-api-0.0.1-SNAPSHOT.jar app.jar

#애플리케이션 실행 변수
ENTRYPOINT ["java", "-jar", "app.jar"]