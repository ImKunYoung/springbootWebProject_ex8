

### 파일 업로드를 위한 설정
<hr/>

```
spring.servlet.multipart.enabled=true
spring.servlet.multipart.location=C:\\upload
spring.servlet.multipart.max-request-size=30MB
spring.servlet.multipart.max-file-size=10MB
```

|       spring.servlet.multipart.enabled       |  파일 업로드 가능 여부 지정   |
|:--------------------------------------------:|:------------------:|
|      spring.servlet.multipart.location       | 업로드된 파일의 입시 저장 경로  |
|  spring.servlet.multipart.max-request-size   | 한 번에 최대 업로드 가능 용량  |
|    spring.servlet.multipart.max-file-size    |    파일 하나의 최대 크기    |