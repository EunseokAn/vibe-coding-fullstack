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
- **Frontend Framework**: Bootstrap 5 (CDN 방식)

## 3. 설정 및 구성 (Configuration)
- **설정 파일**: `src/main/resources/application.yml` (YAML 사용)
- **애플리케이션 이름**: `vibeapp`

### 구현된 기능
#### 게시글 관리
- **목록 조회**: `/posts` (Post List Page)
- **상세 조회**: `/posts/{no}` (Post Detail Page)
- **수정 폼**: `/posts/{no}/edit` (Post Edit Form Page)
- **수정 처리**: `/posts/{no}/save` (POST submission, redirects to detail)
- **공개 폼**: `/posts/new` (Creation Form)
- **등록 처리**: `/posts/add` (POST submission, redirects to list)

### 뷰 템플릿 (Thymeleaf & Bootstrap)
- **메인 페이지**: `/` (Home Page)
- **게시글 목록**: `/posts` (Post List Page)
- **게시글 상세**: `/posts/{no}` (Post Detail Page)
- **게시글 작성**: `/posts/new` (New Post Form Page)
- **템플릿 위치**: `src/main/resources/templates/`
  - `home/home.html`
  - `post/posts.html`, `post/post_detail.html`, `post/post_new_form.html`, `post/post_edit_form.html`
- **컨트롤러**: `com.example.vibeapp.home.HomeController`, `com.example.vibeapp.post.PostController`
- **프론트엔드**: Bootstrap 5 (CDN)를 사용하여 반응형 UI 디자인 적용

### 도메인 및 서비스
- **엔티티**: `Post` (게시글 정보 관리를 위한 POJO)
- **리포지토리**: `PostRepository` (ArrayList 기반 인메모리 저장소)
- **서비스**: `PostService` (게시글 비즈니스 로직 처리)

## 5. 프로젝트 구조
```text
vibeapp/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/vibeapp/
│   │   │       ├── VibeApp.java
│   │   │       ├── home/
│   │   │       │   └── HomeController.java
│   │   │       └── post/
│   │   │           ├── Post.java
│   │   │           ├── PostController.java
│   │   │           ├── PostRepository.java
│   │   │           └── PostService.java
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── home/
│   │       │   │   └── home.html
│   │       │   └── post/
│   │       │       ├── post_detail.html
│   │       │       ├── post_edit_form.html
│   │       │       ├── post_new_form.html
│   │       │       └── posts.html
│   │       └── application.yml
├── build.gradle
├── settings.gradle
├── .gitignore
└── PROJECT_SPEC.md
```

## 6. 기타 설정
- **버전 관리**: Git 사용 중
- **무시 목록**: `.gitignore`를 통해 `.gradle/`, `build/`, `bin/`, `*.log` 등 빌드 및 실행 관련 파일 제외 설정 완료
