![header](https://capsule-render.vercel.app/api?type=wave&color=auto&height=300&section=header&text=Navi%20Batch&fontSize=90&animation=fadeIn&fontAlignY=38)
## Kotlin Navi Batch
###주소정보 저장 배치 프로그램

- juso.go.kr 의 주소정보를 DB에 저장

- 개발자 센터의 네비게이션 오라클 DB 마이그레이션 배치 

- [개발자 센터 - 네비게이션 데이터](https://www.juso.go.kr/addrlink/addressBuildDevNew.do?menu=navi)


1. resource 내 DB createTable.sql 파일을 이용하여 테이블 생성한다. 
2. 데이터를 ./batchdata 디렉토리에 넣는다. 
3. 배치를 수행한다. 

##### 토이 프로젝트 주소 정보 마이그레이션 배치를 만들어보자.