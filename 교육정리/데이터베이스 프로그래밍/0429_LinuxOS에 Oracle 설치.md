---
typora-copy-images-to: images
---



## LinuxOS에 Oracle 12g R2or 19cR2 설치 및 구성



- Oracle Linux 8.1 (64bit) 설치 

  -  https://yum.oracle.com/oracle-linux-isos.html

  ![image-20210429162455264](images/image-20210429162455264.png)

  ​	![image-20210429163050587](images/image-20210429163050587.png)

  ![image-20210429163310973](images/image-20210429163310973.png)

  ![image-20210429163330175](images/image-20210429163330175.png)

  ![image-20210429163514723](images/image-20210429163514723.png)

  ![image-20210429163629381](images/image-20210429163629381.png)

  ![image-20210429163656060](images/image-20210429163656060.png)

  ![image-20210429163843435](images/image-20210429163843435.png)

  ![image-20210429163800506](images/image-20210429163800506.png)

  ![image-20210429163957727](images/image-20210429163957727.png)

  ![image-20210429164108172](images/image-20210429164108172.png)

  ![image-20210429164048340](images/image-20210429164048340.png)

  ![image-20210429164140193](images/image-20210429164140193.png)

  ![image-20210429164207650](images/image-20210429164207650.png)

  ![image-20210429164540641](images/image-20210429164540641.png)

  ![image-20210429164635727](images/image-20210429164635727.png)

  ![image-20210429164705804](images/image-20210429164705804.png)

  ![image-20210429164747971](images/image-20210429164747971.png)

  ![image-20210429164829043](images/image-20210429164829043.png)

  ![image-20210429165002738](images/image-20210429165002738.png)

  ![image-20210429165055948](images/image-20210429165055948.png)

  ![image-20210429165200998](images/image-20210429165200998.png)

  ![image-20210429165226766](images/image-20210429165226766.png)

  ![image-20210429165312087](images/image-20210429165312087.png)

  ![image-20210429165339127](images/image-20210429165339127.png)

  ![image-20210429165411841](images/image-20210429165411841.png)

  ![image-20210429165439693](images/image-20210429165439693.png)

  ![image-20210429165506795](images/image-20210429165506795.png)

  ![image-20210429165520445](images/image-20210429165520445.png)

  ![image-20210429165538808](images/image-20210429165538808.png)

  ![image-20210429165628233](images/image-20210429165628233.png)

  ![image-20210429165717342](images/image-20210429165717342.png)

  ![image-20210429170003985](images/image-20210429170003985.png)

  ![image-20210429170039856](images/image-20210429170039856.png)

  ![image-20210429170100584](images/image-20210429170100584.png)

  ![image-20210429170114356](images/image-20210429170114356.png)

  ![image-20210429170128748](images/image-20210429170128748.png)

  ![image-20210429170402934](images/image-20210429170402934.png)

  ![image-20210429170325987](images/image-20210429170325987.png)

  ![image-20210429170501623](images/image-20210429170501623.png)

  ![image-20210429171135801](images/image-20210429171135801.png)

  ![image-20210429173717510](images/image-20210429173717510.png)

  

  ![image-20210429173956894](images/image-20210429173956894.png)

  ![image-20210429174045805](images/image-20210429174045805.png)

  ![image-20210429174142014](images/image-20210429174142014.png)

  ![image-20210429174204617](images/image-20210429174204617.png)

  ![image-20210429174235656](images/image-20210429174235656.png)

  





## 환경설정



- **/etc/hosts 설정**

![image-20210429175241415](images/image-20210429175241415.png)

- **/etc/hostname 확인**

![image-20210429175324380](images/image-20210429175324380.png)



- 오라클 설치 전 사전 설정

![image-20210429184142203](images/image-20210429184142203.png)



- rpm 설치용 쉘파일 생성

```
# vi rpm.sh
dnf install -y bc    
dnf install -y binutils
#dnf install -y compat-libcap1
dnf install -y compat-libstdc++-33
#dnf install -y dtrace-modules
#dnf install -y dtrace-modules-headers
#dnf install -y dtrace-modules-provider-headers
#dnf install -y dtrace-utils
dnf install -y elfutils-libelf
dnf install -y elfutils-libelf-devel
dnf install -y fontconfig-devel
dnf install -y glibc
dnf install -y glibc-devel
dnf install -y ksh
dnf install -y libaio
dnf install -y libaio-devel
#dnf install -y libdtrace-ctf-devel
dnf install -y libXrender
dnf install -y libXrender-devel
dnf install -y libX11
dnf install -y libXau
dnf install -y libXi
dnf install -y libXtst
dnf install -y libgcc
dnf install -y librdmacm-devel
dnf install -y libstdc++
dnf install -y libstdc++-devel
dnf install -y libxcb
dnf install -y make
dnf install -y net-tools # Clusterware
dnf install -y nfs-utils # ACFS
dnf install -y python # ACFS
dnf install -y python-configshell # ACFS
dnf install -y python-rtslib # ACFS
dnf install -y python-six # ACFS
dnf install -y targetcli # ACFS
dnf install -y smartmontools
dnf install -y sysstat
dnf install -y unixODBC
 
# New for OL8
dnf install -y libnsl
dnf install -y libnsl.i686
dnf install -y libnsl2
dnf install -y libnsl2.i686
```

![image-20210429184844503](images/image-20210429184844503.png)



- 쉘 실행

  sh rpm.sh



- 유저 및 그룹 생성

```
groupadd -g 54321 oinstall
groupadd -g 54322 dba
groupadd -g 54323 oper 
useradd -u 54321 -g oinstall -G dba,oper oracle

```



- selinux disable 설정
  - vi /etc/selinux/config

![image-20210429185416280](images/image-20210429185416280.png)



- 방화벽 해제

  ```
  systemctl stop firewalld
  systemctl disable firewalld
  
  ```

![image-20210429185553709](images/image-20210429185553709.png)



- 설치 경로 생성

```
mkdir -p /ORA19/app/oracle/product/19.0.0/db_1/
mkdir -p /ORA19/app/oradata
chown -R oracle:oinstall /ORA19
chmod -R 775 /ORA19
```



- **서버에 Oracle Database 설치 미디어 업로드 후 권한 부여**

  - window에서 설치 후 리눅스로 파일 옮기기

  ![image-20210429191632521](images/image-20210429191632521.png)

  - option > shared folders > add

  ![image-20210429191732159](images/image-20210429191732159.png)

  - next

  ![image-20210429191807386](images/image-20210429191807386.png)

  - 폴더 위치 

  ![image-20210429191843568](images/image-20210429191843568.png)

  - finish

  ![image-20210429191902885](images/image-20210429191902885.png)

  - 폴더 위치

  ![image-20210429191932048](images/image-20210429191932048.png)

  - zip 파일을 아까 생성한 /ORA19/app/oracle/product/19.0.0/db_1/ 폴더로 옮기기

    mv LINUX.X64_193000_db_home.zip  /ORA19/app/oracle/product/19.0.0/db_1/

    ![image-20210429192429779](images/image-20210429192429779.png)

  - 서버에 Oracle Database 설치 미디어 업로드 후 권한 부여

  ```
  chown oracle:oinstall LINUX.X64_193000_db_home.zip
  ```



- 오라클 계정 접속 후 **.bash_profile 에 아래 내용 추가**

```
# su - oracle
$ vi .bash_profile
```

```
export TMP=/tmp
export TMPDIR=$TMP
 
export ORACLE_HOSTNAME=oel8
export ORACLE_UNQNAME=oracle19
export ORACLE_BASE=/ORA19/app/oracle
export ORACLE_HOME=$ORACLE_BASE/product/19.0.0/db_1
export ORA_INVENTORY=/ORA19/oraInventory
export ORACLE_SID=oracle19
export DATA_DIR=/ORA19/app/oradata
export PATH=/usr/sbin:/usr/local/bin:$PATH
export PATH=$ORACLE_HOME/bin:$PATH
export LD_LIBRARY_PATH=$ORACLE_HOME/lib:/lib:/usr/lib
export CLASSPATH=$ORACLE_HOME/jlib:$ORACLE_HOME/rdbms/jlib
 
export DISPLAY=192.168.119.111:0.0
```

![image-20210429192855808](images/image-20210429192855808.png)



- 오라클 설치 파일 압축 해제

```
$ cd $ORACLE_HOME
$ unzip Oracle\ Database\ 19.3.0.0.0\ for\ Linux\ x86-64\(V982063-01\).zip 
```

![image-20210429193145071](images/image-20210429193145071.png)





## Oracle Software 설치



- profile 로 들어가서 주석처리

  ![image-20210429193410201](images/image-20210429193410201.png)

  ![image-20210429193516282](images/image-20210429193516282.png)



- 환경변수 적용 완료

![image-20210429193556928](images/image-20210429193556928.png)



- 윈도우에서 xming 다운로드 - gui 환경설정

  https://sourceforge.net/projects/xming/

  ![image-20210429193710209](images/image-20210429193710209.png)

  ![image-20210429193746263](images/image-20210429193746263.png)

  ![image-20210429193800575](images/image-20210429193800575.png)

  ![image-20210429193815873](images/image-20210429193815873.png)

  ![image-20210429193832086](images/image-20210429193832086.png)

  ![image-20210429193912825](images/image-20210429193912825.png)

  ![image-20210429193925341](images/image-20210429193925341.png)

  ![image-20210429194002193](images/image-20210429194002193.png)

  ![image-20210429194032934](images/image-20210429194032934.png)

  

  ![image-20210429194058782](images/image-20210429194058782.png)

  ![image-20210429194113603](images/image-20210429194113603.png)

  ![image-20210429194124108](images/image-20210429194124108.png)

  

- putty 접속 후 오라클 설치

  - 오라클 설치

  ![image-20210429194226182](images/image-20210429194226182.png)

  ![image-20210429194346263](images/image-20210429194346263.png)

  ![image-20210429194434234](images/image-20210429194434234.png)

  - 비밀번호 : oracle
    - export LANG=c : 한글 깨짐 방지

  ![image-20210429194513184](images/image-20210429194513184.png)

  - 오라클 설치

  ![image-20210429194605114](images/image-20210429194605114.png)

  - 에러 발생

  ![image-20210429194715857](images/image-20210429194715857.png)

  - 에러 조치 방법

    - **CV_ASSUME_DISTID 값을 설정**

    ```
    $ export CV_ASSUME_DISTID=RHEL7.6
    $ ./runInstaller
    ```

    ![image-20210429194920626](images/image-20210429194920626.png)

    - 에러 원인 : **OS 호환성 문제**
      - **oracle 19c docs 를 보니 oracle linux 8은 아직 지원하지 않는다고 나옴**

  - **Set Up Software Only 선택**

  ![image-20210429195030398](images/image-20210429195030398.png)

  - **Single instance database installation 선택**

  ![image-20210429195059452](images/image-20210429195059452.png)

  - **Enterprise Edition 선택**

  ![image-20210429195122104](images/image-20210429195122104.png)

  - **Oracle base 경로 설정**

  ![image-20210429195143797](images/image-20210429195143797.png)

  - **oraInventory 경로 선택**

  ![image-20210429195206955](images/image-20210429195206955.png)

  - **그룹 권한 선택**

  ![image-20210429195233653](images/image-20210429195233653.png)

  - **./runinstaller 실행후 자동으로 root 스크립트 실행을 위한 root 패스워드 입력****(root.sh 등)**
    - passwd : root

  ![image-20210429195256773](images/image-20210429195256773.png)

  - install

  ![image-20210429195338448](images/image-20210429195338448.png)

  - **root 스크립트를 돌리기 전 확인차 물어보는 메세지 Yes 선택**

  ![image-20210429195419770](images/image-20210429195419770.png)

  - db 소프트웨어(엔진) 설치 완료 Close 선택

  ![image-20210429195449423](images/image-20210429195449423.png)



- 리스너 설정

  netca

  ![image-20210429195545466](images/image-20210429195545466.png)

  - **Listener configuration 선택**

  ![image-20210429195607083](images/image-20210429195607083.png)

  - **Add 선택**

  ![image-20210429195636226](images/image-20210429195636226.png)

  - **리스너 이름 설정**

  ![image-20210429195653961](images/image-20210429195653961.png)

  - 프로토콜 설정

  ![image-20210429195714290](images/image-20210429195714290.png)

  - **Use the standard port number of 1521 선택**

  ![image-20210429195732544](images/image-20210429195732544.png)

  - no

  ![image-20210429195749368](images/image-20210429195749368.png)

  - next

  ![image-20210429195803118](images/image-20210429195803118.png)

  - finish

  ![image-20210429195815799](images/image-20210429195815799.png)



- db생성

  ```
  $ dbca
  ```

  ![image-20210429195909640](images/image-20210429195909640.png)

  - **Create a database 선택**

  ![image-20210429200001042](images/image-20210429200001042.png)

  - **Advanced configuration 선택**

  ![image-20210429200024279](images/image-20210429200024279.png)

  - **Custom Database 선택**

  ![image-20210429200056940](images/image-20210429200056940.png)

  - oracle SID 입력

  ![image-20210429200139903](images/image-20210429200139903.png)

  

  - - **Use following for the database storage attributes 선택**

    ![image-20210430103929369](images/image-20210430103929369.png)

    - next 선택

    ![image-20210430104035924](images/image-20210430104035924.png)

    - 리스너 선택

    ![image-20210430104111015](images/image-20210430104111015.png)

    - **모두 체크 해제**

    ![image-20210430104155602](images/image-20210430104155602.png)

    - **Memory 에서 AMM 사이즈는 1024MB 선택**

    ![image-20210430104233594](images/image-20210430104233594.png)

    - **Choose from the list of character sets 는 KO16MSWIN949 선택**

    ![image-20210430104352977](images/image-20210430104352977.png)

    - **Connection mode 는 Dedicated server mode 선택**

    ![image-20210430104431339](images/image-20210430104431339.png)

    - **Sample schemas 는 선택 안함**

    ![image-20210430104459337](images/image-20210430104459337.png)

    - **EM 체크 해제**

    ![image-20210430104525533](images/image-20210430104525533.png)

    - **sys 계정 패스워드 설정**
      - passwd : oracle

    ![image-20210430104643473](images/image-20210430104643473.png)

    - Create database 선택

    ![image-20210430104827587](images/image-20210430104827587.png)

    - **Finish 선택**

    ![image-20210430104851282](images/image-20210430104851282.png)

    - db instance 생성 중

    ![image-20210430104909041](images/image-20210430104909041.png)

    - **db 인스턴스 생성 완료**

    ![image-20210430110621745](images/image-20210430110621745.png)

    - **db 접속 후 버전 및 상태 확인**

      - 리눅스에서

      ```
      $ sqlplus / as sysdba
      SQL> select instance_name, version, status from v$instance;
      ```

    ![image-20210430111030036](images/image-20210430111030036.png)

    

  - db를 사용할 수 있는 계정을 생성

    - user name : linux_scott / passwd : 1234
    - 그리고 계정에 권한주기 (모든 권한주기)

    ```
    SQL> CREATE USER linux_scott IDENTIFIED BY 1234;
    
    SQL> GRANT CONNECT, DBA, RESOURCE TO linux_scott;
    ```

    ![image-20210430111504119](images/image-20210430111504119.png)

  

  - SQL Developer에 접속

    ![image-20210430112017554](images/image-20210430112017554.png)

  

  

  

