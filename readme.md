

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

<br/>
<br/>
<br/>

### 파일을 저장하는 단계에서 고려해야할 사항
<hr/>

> - 업로드된 확장자가 이미지만 가능하도록 검사 (첨부파일을 이용한 원격 셀)
> - 동일한 이름의 파일이 업로드 된다면 기존 파일을 덮었쓰는 문제 (uuid, 이름에 날짜 추가 등)
> - 업로드된 파일을 저장하는 폴더의 용량 (FAT32 방식의 경우 65534개로 제한 있음)

