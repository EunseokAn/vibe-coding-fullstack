# Project Specification: vibeapp

이 문서는 현재 `vibeapp` 프로젝트의 설정 및 구현 상태를 기록한 명세서입니다.

## 1. 프로젝트 메타데이터
- **Project Name**: vibeapp
- **Group ID**: `com.example`
- **Artifact ID**: `vibeapp`
- **Description**: 최소 기능 스프링부트 애플리케이션을 생성하는 프로젝트다.
- **Main Class**: `com.example.vibeapp.VibeApp`

## 2. 기술 스택 (Technical Stack)
- **JDK**: 25 (Java Toolchain 설정)
- **Java Language Version**: 25
- **Spring Boot**: 4.0.1
- **Gradle**: 9.3.0 이상 (Groovy DSL)
- **Dependencies**:
  - `org.springframework.boot:spring-boot-starter-web`: REST API 및 웹 환경 지원
  - `org.springframework.boot:spring-boot-starter-thymeleaf`: Thymeleaf 뷰 템플릿 엔진 지원
  - `io.spring.dependency-management`: 의존성 버전 관리

## 3. 설정 및 구성 (Configuration)
- **설정 파일**: `src/main/resources/application.yml` (YAML 사용)
- **애플리케이션 이름**: `vibeapp`

## 4. 구현된 기능
### REST API 엔드포인트
- **엔드포인트**: `/api/hello`
- **HTTP Method**: `GET`
- **기능**: `"Hello, Vibe!"` 문자열 반환
- **구현 위치**: `VibeApp.java` (Controller 통합)

### 뷰 템플릿 (Thymeleaf)
- **메인 페이지**: `/` (Home Page)
- **템플릿 위치**: `src/main/resources/templates/home.html`
- **컨트롤러**: `HomeController.java`

## 5. 프로젝트 구조
```text
vibeapp/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/vibeapp/
│   │   │       ├── VibeApp.java
│   │   │       └── HomeController.java
│   │   └── resources/
│   │       ├── templates/
│   │       │   └── home.html
│   │       └── application.yml
├── build.gradle
├── settings.gradle
└── PROJECT_SPEC.md
```
