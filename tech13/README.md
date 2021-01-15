## 변경사항
* Activity 를 하나 더 만들어 퍼미션이 없는 상태에서 호출 상황 생성
* 데모B: 예제 [13-15] Key 의 Hash 값을 구하는 코드 추가
* 데모B: 예제 [13-16] AES256 에서의 암호화 코드 추가

## build config
* com.android.tools.build:gradle:4.0.2
* compileSdkVersion 30
* buildToolsVersion "30.0.3"

## keytool cmd

* __Get key hash(use gitbash)__<br/>
keytool -exportcert -alias androiddebugkey -keystore /c/Users/username/.android/debug.keystore | openssl sha1 -binary | openssl base64<br/>
ㄴ%sha256 가능<br/>
ㄴ%%주의점 : 에러메세지를 base64 로 엔코딩하기때문에 이상한 값이 뜰 수 있으므로 주의! 비밀번호를 물어보는지 확인하라

* __Get key fingerprint(md5, sha1, sha256)__<br/>
keytool -list -v -keystore "C:\Users\username\.android\debug.keystore" -alias androiddebugkey -storepass android -keypass android
