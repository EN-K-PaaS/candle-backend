# 1. 베이스 이미지로 OpenJDK 사용
FROM openjdk:17-jdk-slim

# 2. 작업 디렉토리 생성
WORKDIR /app

# 3. JAR 파일을 컨테이너로 복사
COPY build/libs/api-0.0.1-SNAPSHOT.jar  /app/myapp.jar

# 4. 애플리케이션 실행 명령
ENTRYPOINT ["java", "-jar", "/app/myapp.jar"]