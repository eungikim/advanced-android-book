## 변경사항
* LocalUnitTest project 합침
* Mockito 샘플 추가
* LocalBroadcastManager class 가 final class 로 변환되어 유닛 테스트 불가

## build config
* com.android.tools.build:gradle:4.1.1
* compileSdkVersion 30
* buildToolsVersion "30.0.3"
* JUnit 4.13.1
* mockito-core 2.+

## Mockito에서 제공하는 @Mock, @Spy, @InjectMocks과 같은 annotation을 사용하는 field를 초기화 하는 방법 3가지.
1. 테스트 클래스의 어노테이션 ```@RunWith(MockitoJUnitRunner.class)```
2. ```MockitoAnnotations.initMocks(this);``` initMocks 메서드를 @Before 에서 호출
3. ``` java
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();
    ```
    JUnit rule 이용

> https://jongmin92.github.io/2019/02/10/Java/mockito-anotation-field-initialize/

## @Spy @Mock 간단 비교
단순히 모킹하려면 @Mock 사용, 해당 클래스의 메서드를 이용하려면 @Spy 사용

## doReturn().when() 과 when().thenReturn 의 차이
doReturn 부터 사용할 경우 뒤에오는 메서드는 실행이 되지 않음.<br/>
when 으로 사용할 경우 일단 메서드가 실행되기 때문에 익셉션등을 처리해야함.

> https://stackoverflow.com/a/29394497