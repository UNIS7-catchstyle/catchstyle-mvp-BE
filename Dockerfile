# 1단계: 빌드 환경 구성
FROM gradle:jdk17 AS builder
WORKDIR /app

# 소스 코드 복사
COPY build.gradle settings.gradle ./
COPY src ./src

# 테스트를 생략하고 실행 가능한 jar 파일 생성
RUN gradle clean build -x test --no-daemon --info --stacktrace

# 2단계: 실행 환경 구성 (컨테이너 용량 최적화)
# 마찬가지로 jre 버전을 프로젝트에 맞게 수정
FROM eclipse-temurin:17-jre-focal
WORKDIR /app

# 1단계에서 빌드된 jar 파일만 추출하여 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# Render가 인식할 포트 개방
EXPOSE 8080

# 컨테이너 구동 시 서버 실행
ENTRYPOINT ["java", "-jar", "app.jar"]