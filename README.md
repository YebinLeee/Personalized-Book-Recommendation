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
