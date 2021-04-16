---
typora-copy-images-to: images
---





#### Cloud

- 클라우드 이전
  - was는 failover를 지원한다.
    - Failover란?
      - 
    - was끼리 그룹핑을 하고 그 그룹 안에서는 failover를 지원하도록 한다. 
      - was끼리 클러스터링을 진행(같은 어플끼리 클러스터로 묶는다. )

  

  - WAS 중에 하나는 다른 WAS들을 관리하는 역할을 한다. (매니저 역할을 하는 DAS(Domain Admin Server)가 있다.)
    - DAS안에 여러개의 Cluster가 있고 그 안에 여러개의 MS가 있다. 



- 클라우드 서비스의 종류
  - Infrastructure as a Service(IaaS, 아이아스, 이에스) 
    - 개발사에 제공되는 물리적 자원을 가상화 한다. 
  - Platform as a Service(PaaS, 파스)
    - 여기서 플랫폼은 웹, WAS, DB 등을 의미한다.
    - 개발사에 제공되는 플랫폼을 가상화 한다.
  - Software as a Service(SaaS, 사스)
    - 고객에게 제공되는 소프트웨어를 가상화 한다.



- Private / Public Cloud
  - public
  - private



#### 구글 클라우드 

- 가입

  https://cloud.google.com/gcp/?hl=ko&utm_source=google&utm_medium=cpc&utm_campaign=japac-KR-all-ko-dr-bkws-all-all-trial-e-dr-1009882&utm_content=text-ad-none-none-DEV_c-CRE_205812439628-ADGP_Hybrid%20%7C%20BKWS%20-%20EXA%20%7C%20Txt%20~%20GCP%20~%20General_cloud%20computing%20-%20cloud-KWID_43700061085501429-kwd-300427730551&userloc_1030724-network_g&utm_term=KW_%EA%B5%AC%EA%B8%80%20%ED%81%B4%EB%9D%BC%EC%9A%B0%EB%93%9C&ds_rl=1005428&gclid=Cj0KCQjw38-DBhDpARIsADJ3kjnNJ16MnICWt3ORGTWzZSg42JD3o926f_Px36pCoWC47z5PQiat-AAaAkVxEALw_wcB&gclsrc=aw.ds

  ![image-20210413100753524](images/image-20210413100753524.png)

  

  ![image-20210413100852949](images/image-20210413100852949.png)



- 구글 클라우드

  - 대시보드

  ![image-20210413101349642](images/image-20210413101349642.png)

  

  - VM 인스턴스 만들기

    - VM 인스턴스

    ![image-20210413102218204](images/image-20210413102218204.png)

    ![image-20210413102540590](images/image-20210413102540590.png)

    ![image-20210413102613157](images/image-20210413102613157.png)

    ![image-20210413102839497](images/image-20210413102839497.png)

    - apache, tomcat을 여기서 설치할 수 있다.

    ![image-20210413102858383](images/image-20210413102858383.png)

    

  - 방화벽 세부정보 확인

    ![image-20210413104035914](images/image-20210413104035914.png)

    

    ![image-20210413103123412](images/image-20210413103123412.png)

    

    ![image-20210413103332739](images/image-20210413103332739.png)

    

  - 방화벽 만들기(수신 송신 모두 생성해야 한다.)

    ![image-20210413103412515](images/image-20210413103412515.png)

    

    ![image-20210413103524772](images/image-20210413103524772.png)

    

    ![image-20210413111243745](images/image-20210413111243745.png)

    

    ![image-20210413103656957](images/image-20210413103656957.png)

    

  - tomcat 설치

    - apt-get update

    ![image-20210413104259122](images/image-20210413104259122.png)

    

    - tomcat install 

    ![image-20210413104336021](images/image-20210413104336021.png)

    

    - apache2 설치

    ![image-20210413104848378](images/image-20210413104848378.png)

    

    - 톰캣과 apache 상태 확인

    ![image-20210413105050394](images/image-20210413105050394.png)

    

    - 서버 동작 확인

    ![image-20210413105128063](images/image-20210413105128063.png)

    

    - 설치 확인

    ![image-20210413112008966](images/image-20210413112008966.png)

    ![image-20210413112023516](images/image-20210413112023516.png)

    

    - apache와 tomcat을 연결하는 connector 모듈 install

    ![image-20210413105346334](images/image-20210413105346334.png)

    

    - apache-tomcat connector에 tomcat들의 별명을 지어주고, 정보를 설정

    ![image-20210413105901127](images/image-20210413105901127.png)

    - webserver apache에 apache-tomcat connector에 설정된 tomcat 으로 요청을 보내도록 설정

    ![image-20210413110454309](images/image-20210413110454309.png)

    - WAS tomcat에 특정 port와 특정 protocol로 오는 요청을 받으라고 설정

    ![image-20210413112355219](images/image-20210413112355219.png)



-----------

#### 오라클 웹로직 

- 다운로드

  https://www.oracle.com/middleware/technologies/fusionmiddleware-downloads.html

  ![image-20210413143043799](images/image-20210413143043799.png)

  

- JDK 다운

  https://www.oracle.com/java/technologies/javase-jdk16-downloads.html

  - 환경 변수 설정

  ![image-20210413144341858](images/image-20210413144341858.png)

  ![image-20210413150430425](images/image-20210413150430425.png)

  ![image-20210413144548450](images/image-20210413144548450.png)



- 설치 명령어 (cmd는 관리자 권한으로 실행해야함)

  ![image-20210413144918231](images/image-20210413144918231.png)

  

  ![image-20210413144955172](images/image-20210413144955172.png)

  

  ![image-20210413154227467](images/image-20210413154227467.png)

  

  ![image-20210413145026973](images/image-20210413145026973.png)

  ​	

  ![image-20210413145045528](images/image-20210413145045528.png)

  

  ![image-20210413145118228](images/image-20210413145118228.png)

  

  ![image-20210413145356202](images/image-20210413145356202.png)

  

  - 완료 버튼을 누르면 Configuration Wizard가 실행된다. 

  ![image-20210413145531359](images/image-20210413145531359.png)

  

  ![image-20210413145555318](images/image-20210413145555318.png)

  

  - 관리자 계정 생성

  ![image-20210413145626082](images/image-20210413145626082.png)

  

  - 도메인 모드는 개발을 체크하고 JDK 기본값을 사용

  ![image-20210413145658507](images/image-20210413145658507.png)

  

  ![image-20210413145716665](images/image-20210413145716665.png)

  

  ![image-20210413145732824](images/image-20210413145732824.png)

  

  ![image-20210413150457754](images/image-20210413150457754.png)

  

  ![image-20210413150508098](images/image-20210413150508098.png)
  

  
- C:\Oracle\Middleware\Oracle_Home\user_projects\domains\base_domain\startWebLogic.cmd 입력
    - 새롭게 생성한 도메인 위치 

  ![image-20210413151121797](images/image-20210413151121797.png)

  
  
  - http://localhost:7001/console 
  
  ![image-20210413151252844](images/image-20210413151252844.png)



- **Oracle Weblogic 서버에 application 배포**

  - 배치 버튼 클릭

  ![image-20210413151516948](images/image-20210413151516948.png)

  

  - 설치 버튼 클릭

  ![image-20210413151616431](images/image-20210413151616431.png)

  

  - 올릴 war 파일을 선택

  ![image-20210413151726170](images/image-20210413151726170.png)

  

  ![image-20210413151743286](images/image-20210413151743286.png)

  

  ![image-20210413151822678](images/image-20210413151822678.png)

  

  - 완료 버튼 클릭

  ![image-20210413151839277](images/image-20210413151839277.png)
