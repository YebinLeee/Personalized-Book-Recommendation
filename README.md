# Personalized-Book-Recommendation
:books: Spring Boot 학습 내용을 기반으로 한 도서 추천 시스템 서버 개발 프로젝트

<br>

## Tech
- JVM Language : `Java 17`
- Framework : `Spring Boot 3.0.1`
- IDE : `IntelliJ Community`
- Build : `Gradle Groovy`
- ORM : `JPA` 
- Database : `H2 DB Engine`
- Unit Test : `JUnit5` 
- Dependencies, Libraries : `Thymeleaf`, `json`, `Jsoup`, `Lombok`

<br>

## System Architecture
- Client-Server 구조
- 자가대출반납기에 탑재되는 C# 기반 WPF로 개발되는 클라이언트와 HTTP 요청, 응답을 JSON 형태로 주고 받는 Spring Boot 기반의 Restful API를 개발
- Spring MVC를 이용해 Thymeleaf 기반으로 Template View를 만들어 Client View 대체하여 로컬에서 테스트 후 `ResponseEntity` 를 이용해 Json 데이터를 전달하는 방향으로 리팩토링중

<p align="center"><img src="https://user-images.githubusercontent.com/71310074/222335926-24c8c9ad-e453-4b60-81a1-8ce8ff3f7736.png" width=750></p>

## UML Diagram 

### Overall Sequence Diagram for System Scenario 
<p align="center"><img src="https://user-images.githubusercontent.com/71310074/222337721-567f85a7-59a5-4102-ad44-c7c5e51c6d26.png" width=650></p>
<p align="center"><img src="https://user-images.githubusercontent.com/71310074/222338364-ca9bf93f-7a9c-445f-9f94-abc9a4fe3cb5.png" width=650></p>

### Class(Service) Diagram of Abstract Interface and Implementation 
- `객체 지향 설계(OOP)`, `제어의 역전(IoC, Inversion of Control)`, `의존 관계 주입(DI, Dependency Injection)`을 기반으로 하여 추상화된 인터페이스를 기반으로 클래스를 구체화한 회원 서비스(MemberService), 책 추천 서비스(Recommendation Service), 책 추천 결과 서비스(Book Result Service) 간의 관계를 도식화
<p align="center"><img src="https://user-images.githubusercontent.com/71310074/222337139-4f677bc4-46a0-4532-ba98-05aab085b8a5.png" width=900></p>

### Member Service
<p align="center"><img src="https://user-images.githubusercontent.com/71310074/222339275-b7f80752-093d-4932-b195-a0fa7c478a38.png" width=500></p>
<p align="center"><img src="https://user-images.githubusercontent.com/71310074/222339397-48dcb7aa-c167-4562-b239-5480c20af5a3.png" width=500></p>
- Controller level
<p align="center"><img src="https://user-images.githubusercontent.com/71310074/222338694-52e4b0d3-683b-4dd7-ac4c-98b2a038d7a4.png" width=500></p>

### Weather Service

<p align="center"><img src="https://user-images.githubusercontent.com/71310074/222339073-d1f66410-e59d-4c88-8930-e2067079f026.png" width=300></p>


### Book Recommendation Services

<p align="center"><img src="https://user-images.githubusercontent.com/71310074/222338976-18e50041-b53c-403b-b891-5ecc405d68c7.png" width=600></p>



<br>

### Scenario for Book Recommendation
- 책 추천에 사용되는 API는 알라딘 도서 자료 Open API와 도서관 정보나루 빅데이터 Open API
<p align="center"><img src="https://user-images.githubusercontent.com/71310074/222338563-1ec8a035-a939-4a32-999d-ab7f84c7bc6f.png" width=450></p>


## API

### 날씨 요청 API
- URL : `/weather`
- Method : `GET`


<table>
    <tr>
        <th>구분</th>
        <th width="400">정보</th>
        <th>형식</th>
        <th width="400">기본값</th>
        <th>타입</th>
        <th width="400">필요 여부</th>
        <th>예시 (실패)</th>
        <th>예시</td>
    </tr>
    <tr>
        <td>Response</td>
        <td>현재 날씨 정보</td>
        <td>weather_code</td>
        <td>0</td>
        <td>Number</td>
        <td>필수</td>
<td>  

```js
  {
    code : 400,
    message : "날씨 API 호출 실패",
    data : null
  }
```
        
</td>
<td>

```js
  {
    code : 200,
    message : "날씨 API 호출 성공",
    data : {
      weather_code : 0,
    }
  }
```
        
</td>
    </tr>
    <tr>
        <td>비고</td>
<td colspan="7"> 
        
```
날씨 API에 요청(위치 정보: 성동구 고정값(x:61, y:127)) - Front에서 주기적으로 호출하여 저장 후 갱신 (30분 또는 1시간)
코드 value 정보
0 맑음 (기본값)
1 흐림
2 비
3 눈
```
</td>
      
</table>


<br>

## Issues
- 개발 중 이슈 및 과정 기록 (블로그)
- 최근 순서로 상시 업데이트 

| 순서  | 제목                                                                                                                                               |
|:---:|:-------------------------------------------------------------------------------------------------------------------------------------------------|
|  1  | [[Spring] @JsonProperty를 이용해 Json 데이터의 파라미터 key 값을 변경하여 전달하기](https://dream-and-develop.tistory.com/471) |
|  2  | [[HTML/Web] URL 링크 인코딩 과정에서 % 뒤에 숫자 25가 자동적으로 추가되는 에러 해결하기 (HTML URL Encoding Reference 참고)](https://dream-and-develop.tistory.com/470) |
|  2  | [[Spring/Thymeleaf] 타임리프 템플릿 View에서 서버로부터 전달된 model 객체의 필드 값이 null인 경우 해결하기 (Safe Navigation Operator, ${객체?.필드})](https://dream-and-develop.tistory.com/466)                                                    |
|  3  | [[Javascript] input value 값 없을 때 form submit 막기 (서버에 데이터 전송 전 alert 창, onsubmit false 값으로 해결)](https://dream-and-develop.tistory.com/465)                                                                                       |
|  4  | [[Spring] View에서 Thymeleaf 문법을 이용하여 url 링크로 이미지 업로드하기 (img th:src)](https://dream-and-develop.tistory.com/462) |
|  4  | [[Spring] Thymeleaf View에 List로 담은 객체 전달하기 - 알라딘 도서 검색 Open API 활용](https://dream-and-develop.tistory.com/461) |
