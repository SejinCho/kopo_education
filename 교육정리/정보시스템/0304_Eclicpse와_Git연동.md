---
typora-copy-images-to: images
---

### 정보시스템 개요 

-------

#### Eclicpse와 Git 연동

- Git Repositories 뷰 꺼내오기

  - Window > Show View > Other > Git Repositories

  ![image-20210304112812387](images/image-20210304112812387.png) ![image-20210304112908182](images/image-20210304112908182.png)



- 기존 프로젝트에서 Team-Share Project

  - Team > Share Project

  ![image-20210304113014500](images/image-20210304113014500.png)

  

  - Local git repository 설정
    - 따로 로컬 레포지토리를 생성하지 않고 이클립스 프로젝트를 local git repository로 사용

  ![image-20210304113128383](images/image-20210304113128383.png)



- 원격 레포지토리 생성

  - 원격 레포지토리를 (github.com) 과 연결한다

  ![image-20210304113502403](images/image-20210304113502403.png)

  - Remote (원격 레포지토리)의 이름을 정한다. (default- origin) 

  ![image-20210304113555627](images/image-20210304113555627.png) 

  

  - Origin이 가리키는 원격 정보 주소를 넣는다

  ![image-20210304113619323](images/image-20210304113619323.png) 

  

  ![image-20210304113731226](images/image-20210304113731226.png) 

  

  ![image-20210304113829185](images/image-20210304113829185.png) 



- commit & push

  - Remotes > Team > Commit

  ![image-20210304114719597](images/image-20210304114719597.png) 

  

  - Git hub에 Push할 파일 선택 > 간단한 comment > Commit and Push

  ![image-20210304114325325](images/image-20210304114325325.png) 

  

  - master branch에 push

  ![image-20210304114419364](images/image-20210304114419364.png) 

  - Log in

  ![image-20210304114501473](images/image-20210304114501473.png) 

  

  - Push

  ![image-20210304114557460](images/image-20210304114557460.png) 

  

  - 깃허브에 가서 master branch에 push 된 내용을 확인

  ![image-20210304114651015](images/image-20210304114651015.png) 

  



- 브랜치 생성

  - Team > Switch To > New Branch

  ![image-20210304131423542](images/image-20210304131423542.png)

  

  - branch명 입력

  ![image-20210304131505752](images/image-20210304131505752.png) 

  ![image-20210304131531687](images/image-20210304131531687.png) 

  

  - branch 생성 다른 방법

  ![image-20210304131624096](images/image-20210304131624096.png) 



- Git Repository Clone

  - Github에존재하는repository를내local computer에그대로가져오는기능
    - Zip으로가져오는경우: 해당그snapshot만가져옴
    - Clone하는경우: 모든branch, commit정보를가지고온다.

  

  - 1) github 홈페이지에서repository 주소를copy한다.
    - 위에 사용했던 repository에서 이름을 변경한 것

  ![image-20210304132148745](images/image-20210304132148745.png)

  

  - 2) Git Repositories에서 Clone a Git repository

  ![image-20210304132247758](images/image-20210304132247758.png) 

  

  - 3) Branch Selection 
    - branch는 나중에 추가 가능

  ![image-20210304132326865](images/image-20210304132326865.png) 

  

  - 4) Destination–clone할 위치 선정
    - initial branch는 위에서 master에 java project를 생성했으므로 master를 가져온다.

  ![image-20210304132356665](images/image-20210304132356665.png) 

  

  - 5) Finish

  ![image-20210304132444768](images/image-20210304132444768.png) 

  

  

- Package Explorer에package 추가

  - Working Tree에서Import Projects 선택

  ![image-20210304132542624](images/image-20210304132542624.png) 

  

  - Import Projects from File System of Archive 선택

  ![image-20210304132617316](images/image-20210304132617316.png)

  

  - clone 확인

  ![image-20210304140238750](images/image-20210304140238750.png) 

  

- Clone한repository를실행시키자

  - 실행방법 정의 – Java Application으로실행

  ![image-20210304140453453](images/image-20210304140453453.png)

  - run

  ![image-20210304141613642](images/image-20210304141613642.png)

  

  - 콘솔에 출력된 것을 확인할 수 있다.

  ![image-20210304141627957](images/image-20210304141627957.png)





