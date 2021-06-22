---
typora-copy-images-to: images
---



## Oracle Linux 8.1에 Oracle 19c 설치



- ### Oracle Linux 8.1 (64bit) 설치 

  -  https://yum.oracle.com/oracle-linux-isos.html **에서 다운로드 받음**

  ![image-20210429162455264](images/image-20210429162455264.png)

  

  - **VMware Workstation 에서 New Virtual Machine 선택**

  ![image-20210429163050587](images/image-20210429163050587.png)

  - **I will install the operating system later. 선택**

  ![image-20210429163310973](images/image-20210429163310973.png)

  - **Linux - Oracle Linux 8 64-bit 선택**

  ![image-20210429163330175](images/image-20210429163330175.png)

  - **Virtual machine 이름 설정**

  ![image-20210429163514723](images/image-20210429163514723.png)

  - **50, Store virtual disk as a single file 선택**

  ![image-20210429163629381](images/image-20210429163629381.png)

  - **Finish**

  ![image-20210429163656060](images/image-20210429163656060.png)

  - **Settings 클릭**

  ![image-20210429163843435](images/image-20210429163843435.png)

  - **Memory > 4096MB 선택**

  ![image-20210429163800506](images/image-20210429163800506.png)

  -  **USB, Printer, 사운드 삭제**

  ![image-20210429163957727](images/image-20210429163957727.png)

  - **CD/DVD 에서 Browse 선택**

  ![image-20210429164108172](images/image-20210429164108172.png)

  - **Oracle linux 8.1 설치 미디어 삽입**

  ![image-20210429164048340](images/image-20210429164048340.png)

  - **전원 ON**

  ![image-20210429164140193](images/image-20210429164140193.png)

  - **Install Oracle Linux 8.1.0 선택**

  ![image-20210429164207650](images/image-20210429164207650.png)

  - **한국어 선택**

  ![image-20210429164540641](images/image-20210429164540641.png)

  - **시간 및 날짜 선택**

  ![image-20210429164635727](images/image-20210429164635727.png)

  - **아시아 - 서울 선택 후 완료**

  ![image-20210429164705804](images/image-20210429164705804.png)

  - **소프트웨어 선택**

  ![image-20210429164747971](images/image-20210429164747971.png)

  - **서버 - GUI 사용 - 레거시 UNIX 호환성, 개발용 툴 선택 후 완료**

  ![image-20210429164829043](images/image-20210429164829043.png)

  - **설치 대상 선택 **

  ![image-20210429165002738](images/image-20210429165002738.png)

  - **Custom 선택 후 완료**

  ![image-20210429165055948](images/image-20210429165055948.png)

  - **수동으로 파티션 설정, + 버튼 **

  ![image-20210429165200998](images/image-20210429165200998.png)

  - **/boot 500MB 마운트 지점 추가**

  ![image-20210429165226766](images/image-20210429165226766.png)

  - **수동으로 파티션 설정, + 버튼 - swap 4096MB 마운트 지점 추가**

  ![image-20210429165312087](images/image-20210429165312087.png)

  - **수동으로 파티션 설정, + 버튼 - / 용량설정X 마운트 지점 추가(자동으로 나머지 모두 할당)**
  
  ![image-20210429165339127](images/image-20210429165339127.png)
  
  - **수동으로 파티션 설정, 설정된 파티션 확인 후 완료** 
  
  ![image-20210429165411841](images/image-20210429165411841.png)
  
  - **변경 사항 적용 선택**
  
  ![image-20210429165439693](images/image-20210429165439693.png)
  
  - **KDUMP **
  
  ![image-20210429165506795](images/image-20210429165506795.png)
  
  - **kdump 활성화 체크 해제 후 완료**
  
  ![image-20210429165520445](images/image-20210429165520445.png)
  
  - **네트워크 & 호스트 이름**
  
  ![image-20210429165538808](images/image-20210429165538808.png)
  
  - **Host Name 설정 후 적용 - 설정**
  
  ![image-20210429165628233](images/image-20210429165628233.png)
  
  - **일반, Connect automatically with priority 체크**
  
  ![image-20210429165717342](images/image-20210429165717342.png)
  
  - **IPv4 설정. 주소 192.168.119.111 설정**
  
  ![image-20210429170003985](images/image-20210429170003985.png)
  
  - **IPv6 설정, 무시**
  
  ![image-20210429170039856](images/image-20210429170039856.png)
  
  - **켬 선택 후 완료**
  
  ![image-20210429170100584](images/image-20210429170100584.png)
  
  - **보안 정책**
  
  ![image-20210429170114356](images/image-20210429170114356.png)
  
  - **끔 선택 후 완료**
  
  ![image-20210429170128748](images/image-20210429170128748.png)
  
  - **Root 암호 선택**
  
  ![image-20210429170402934](images/image-20210429170402934.png)
  
  - **ROOT 암호, root 2번 입력 후 완료 2번 선택**
    - 암호 root
  
  ![image-20210429170325987](images/image-20210429170325987.png)
  
  - **설치 시작**
  
  ![image-20210429170501623](images/image-20210429170501623.png)
  
  - **설치 중**
  
  ![image-20210429171135801](images/image-20210429171135801.png)
  
  - **시스템 재시작 선택**
  
  ![image-20210429173717510](images/image-20210429173717510.png)
  
  - **사용자 생성 선택**
  
  ![image-20210429173956894](images/image-20210429173956894.png)
  
  - **admin/admin 입력 후 완료 2번 선택**
  
  ![image-20210429174045805](images/image-20210429174045805.png)
  
  - **License Information**
  
  ![image-20210429174142014](images/image-20210429174142014.png)
  
  - **약관 동의 후 완료**
  
  ![image-20210429174204617](images/image-20210429174204617.png)
  
  - **설정완료 선택**
  
  ![image-20210429174235656](images/image-20210429174235656.png)
  
  



## 오라클 설치 전 사전 설정

- **oracle linux로 들어간 후 /etc/hosts 설정**

  ```shell
  vi /etc/hosts
  ```

  ![image-20210429175241415](images/image-20210429175241415.png)

  

- **/etc/hostname 확인**

  ```shell
  vi /etc/hostname 
  ```

  ![image-20210429175324380](images/image-20210429175324380.png)



- **자동 설정**

  ```shell
  yum -y install oracle-database-preinstall-19c
  ```

![image-20210429184142203](images/image-20210429184142203.png)



- **수동 설정**

  - **/etc/sysctl.conf에 아래 내용 추가**

  ```shell
  vi /etc/sysctl.conf
  ```

  ```shell
  fs.file-max = 6815744
  kernel.sem = 250 32000 100 128
  kernel.shmmni = 4096
  kernel.shmall = 1073741824
  kernel.shmmax = 4398046511104
  kernel.panic_on_oops = 1
  net.core.rmem_default = 262144
  net.core.rmem_max = 4194304
  net.core.wmem_default = 262144
  net.core.wmem_max = 1048576
  net.ipv4.conf.all.rp_filter = 2
  net.ipv4.conf.default.rp_filter = 2
  fs.aio-max-nr = 1048576
  net.ipv4.ip_local_port_range = 9000 65500
  ```

  

  - **잘 입력되었는지 확인**

  ```shell
  /sbin/sysctl -p
  ```

  ```shell
  fs.file-max = 6815744
  kernel.sem = 250 32000 100 128
  kernel.shmmni = 4096
  kernel.shmall = 1073741824
  kernel.shmmax = 4398046511104
  kernel.panic_on_oops = 1
  net.core.rmem_default = 262144
  net.core.rmem_max = 4194304
  net.core.wmem_default = 262144
  net.core.wmem_max = 1048576
  net.ipv4.conf.all.rp_filter = 2
  net.ipv4.conf.default.rp_filter = 2
  fs.aio-max-nr = 1048576
  net.ipv4.ip_local_port_range = 9000 65500
  ```

  

  - **cf. 커널 파라미터란?!**

    오라클 프로세스에게 RAM 메모리를 할당하기 위해 sysctl.conf 파일에 적혀있는 커널 파라미터를 참조하여 메모리를 할당한다. 

    - shmmax : 공유 메모리 세그먼트의 최대 크기를 정의 
      - 커널이 응용 프로그램들에게 메모리를 할당해 줄 때 작게 여러 번 할당하지 않고 큰 덩어리(=세그먼트)로 한꺼번에 준다. 이 값이 너무 작으면 DB를 시작 시켰을 때 ORA-27123 : unable to attach to shared moemory segment라는 메시지가 발생할 수 있고 너무 크면 세그먼트의 사용되지 않는 빈 공간을 두기 때문에 메모리 낭비가 발생 
      - 이 값의 기본값은 32MB입니다. 그러나 오라클이 사용하기엔 양이 부족하므로 2GB로 설정도록 권장
    - shmmni : 공유 메모리 세그먼트의 최대 개수를 설정
      - 기본값 4096으로 냅둬도 괜찮음
    - shmall : 특정 시점에서 시스템에서 사용 가능한 공유 메모리의 최대 크기를 설정
      - 이 값은 "ceil(shmmax/page_size)"으로 권장
      - 디폴트 사이즈는 2097152 바이트이다.
    - shmmin : 단일 공유메모리 세그먼트의 최소 크기
    - shmseg : 1개의 프로세스에 부여될 수 있는 공유메모리 세그먼트의 최대 개수 
      - 위에서 살펴본 shmmni는 시스템 전체에서 사용가능한 공유 메모리 세그먼트의 최대 개수이고 이 파라미터는 1개의 프로세스가 사용할 수 있는 공유 메모리 세그먼트의 최대 개수이다.

    

  - **아래 파일 생성 후 내용 추가**
    - nproc : User당 사용할 수 있는 프로세스 최대 개수
    - nofile : User당 오픈할 수 있는 파일 개수 (리눅스에서는 모든 개체를 파일로 본다.)

  ```shell
  vi /etc/security/limits.d/oracle-database-preinstall-19c.conf
  ```

  ```shell
  # oracle-database-preinstall-19c setting for nofile soft limit is 1024
  oracle   soft   nofile    1024
  # oracle-database-preinstall-19c setting for nofile hard limit is 65536
  oracle   hard   nofile    65536
  # oracle-database-preinstall-19c setting for nproc soft limit is 16384
  # refer orabug15971421 for more info.
  oracle   soft   nproc    16384
  # oracle-database-preinstall-19c setting for nproc hard limit is 16384
  oracle   hard   nproc    16384
  # oracle-database-preinstall-19c setting for stack soft limit is 10240KB
  oracle   soft   stack    10240
  # oracle-database-preinstall-19c setting for stack hard limit is 32768KB
  oracle   hard   stack    32768
  # oracle-database-preinstall-19c setting for memlock hard limit is maximum of 128GB on x86_64 or 3GB on x86 OR 90 % of RAM
  oracle   hard   memlock    134217728
  # oracle-database-preinstall-19c setting for memlock soft limit is maximum of 128GB on x86_64 or 3GB on x86 OR 90% of RAM
  oracle   soft   memlock    134217728
  ```

  

  - **rpm 설치용 쉘파일 생성**

  ```shell
  vi rpm.sh
  ```

  ```shell
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



- **쉘 실행**

  ```shell
  sh rpm.sh
  ```
  
  

- **유저 및 그룹 생성**

  ```shell
  groupadd -g 54321 oinstall
  groupadd -g 54322 dba
  groupadd -g 54323 oper 
  useradd -u 54321 -g oinstall -G dba,oper oracle
  ```

  

- **selinux disable 설정**

  ```shell
  vi /etc/selinux/config
  ```

  ![image-20210429185416280](images/image-20210429185416280.png)



- **방화벽 해제**

  ```shell
  systemctl stop firewalld
  systemctl disable firewalld
  ```

![image-20210429185553709](images/image-20210429185553709.png)



- **설치 경로 생성**

  ```shell
  mkdir -p /ORA19/app/oracle/product/19.0.0/db_1/
  mkdir -p /ORA19/app/oradata
  chown -R oracle:oinstall /ORA19
  chmod -R 775 /ORA19
  ```

  

- **서버에 Oracle Database 설치 미디어 업로드 후 권한 부여**

  - **window에서 설치 후 리눅스로 파일 옮기기**

  ![image-20210429191632521](images/image-20210429191632521.png)

  

  - **option > shared folders > add**

  ![image-20210429191732159](images/image-20210429191732159.png)

  

  - **next**

  ![image-20210429191807386](images/image-20210429191807386.png)

  

  - **옮길 파일이 있는 window 폴더 위치** 

  ![image-20210429191843568](images/image-20210429191843568.png)

  

  - **finish**

  ![image-20210429191902885](images/image-20210429191902885.png)

  

  - **window에서 linux로 옮긴 폴더 위치**

  ![image-20210429191932048](images/image-20210429191932048.png)
  
  
  
  - **zip 파일을 아까 생성한 /ORA19/app/oracle/product/19.0.0/db_1/ 폴더로 옮기기**
  
    ```shell
    mv LINUX.X64_193000_db_home.zip  /ORA19/app/oracle/product/19.0.0/db_1/
    ```
  
    ![image-20210429192429779](images/image-20210429192429779.png)
  
    
  
  - **서버에 Oracle Database 설치 미디어 업로드 후 권한 부여**
  
    ```
    chown oracle:oinstall LINUX.X64_193000_db_home.zip
    ```



-  **.bash_profile 에 아래 내용 추가**

  ```shell
  su - oracle
  vi .bash_profile
  ```

  ```shell
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



- **오라클 설치 파일 압축 해제**	

  ```shell
  cd $ORACLE_HOME
  unzip Oracle\ Database\ 19.3.0.0.0\ for\ Linux\ x86-64\(V982063-01\).zip 
  ```

![image-20210429193145071](images/image-20210429193145071.png)





## Oracle Software 설치

- **oracle linux로 들어가서 profile 로 들어가서 주석처리**

  ![image-20210429193410201](images/image-20210429193410201.png)

  ![image-20210429193516282](images/image-20210429193516282.png)



- **환경변수 적용 완료**

![image-20210429193556928](images/image-20210429193556928.png)



- **윈도우에서 xming 다운로드 - gui 환경설정**

  https://sourceforge.net/projects/xming/

  ![image-20210429193710209](images/image-20210429193710209.png)

  ![image-20210429193746263](images/image-20210429193746263.png)

  ![image-20210429193800575](images/image-20210429193800575.png)

  ![image-20210429193815873](images/image-20210429193815873.png)

  ![image-20210429193832086](images/image-20210429193832086.png)

  ![image-20210429193912825](images/image-20210429193912825.png)

  ![image-20210429193925341](images/image-20210429193925341.png)

  - XLaunch

  ![image-20210429194002193](images/image-20210429194002193.png)

  ![image-20210429194032934](images/image-20210429194032934.png)

  

  ![image-20210429194058782](images/image-20210429194058782.png)

  ![image-20210429194113603](images/image-20210429194113603.png)

  ![image-20210429194124108](images/image-20210429194124108.png)

  

- **putty 접속 후 오라클 설치**

  - **SSH - X11**

  ![image-20210429194226182](images/image-20210429194226182.png)

  - **Session 설정**

  ![image-20210429194346263](images/image-20210429194346263.png)

  - **oracle 리눅스에 있는 oracle 계정으로 login**
  

![image-20210429194434234](images/image-20210429194434234.png)

- **비밀번호 : oracle**
    - export LANG=c : 한글 깨짐 방지

  ![image-20210429194513184](images/image-20210429194513184.png)

  

  - **오라클 설치**

  ![image-20210429194605114](images/image-20210429194605114.png)

  

  - **에러 발생**
  
  ![image-20210429194715857](images/image-20210429194715857.png)
  
  
  
- **에러  조치 방법**
  
  - **CV_ASSUME_DISTID 값을 설정**
  
    ```shell
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



- **Inventory 경로 선택**
  

![image-20210429195206955](images/image-20210429195206955.png)



- **그룹 권한 선택**
  
  ![image-20210429195233653](images/image-20210429195233653.png)
  
  
  
  - **./runinstaller 실행후 자동으로 root 스크립트 실행을 위한 root 패스워드 입력****(root.sh 등)**
    - passwd : root
  
  ![image-20210429195256773](images/image-20210429195256773.png)
  
  
  
  - **install**
  
  ![image-20210429195338448](images/image-20210429195338448.png)
  
  
  
  - **root 스크립트를 돌리기 전 확인차 물어보는 메세지 Yes 선택**
  
  ![image-20210429195419770](images/image-20210429195419770.png)
  
  
  
  - **db 소프트웨어(엔진) 설치 완료 Close 선택**
  
  ![image-20210429195449423](images/image-20210429195449423.png)



- #### 리스너 설정

  - **오라클 리눅스에서 netca 입력**

  ![image-20210429195545466](images/image-20210429195545466.png)

  - **Listener configuration 선택**

  ![image-20210429195607083](images/image-20210429195607083.png)

  - **Add 선택**

  ![image-20210429195636226](images/image-20210429195636226.png)

  - **리스너 이름 설정**

  ![image-20210429195653961](images/image-20210429195653961.png)

  - **프로토콜 설정**

  ![image-20210429195714290](images/image-20210429195714290.png)

  - **Use the standard port number of 1521 선택**

  ![image-20210429195732544](images/image-20210429195732544.png)

  - **no**

  ![image-20210429195749368](images/image-20210429195749368.png)

  - **next**

  ![image-20210429195803118](images/image-20210429195803118.png)

  - **finish**

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

  - **oracle SID 입력**

  ![image-20210429200139903](images/image-20210429200139903.png)

  

  - - **Use following for the database storage attributes 선택**

    ![image-20210430103929369](images/image-20210430103929369.png)

    - **next 선택**

    ![image-20210430104035924](images/image-20210430104035924.png)

    - **리스너 선택**

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

    - **Create database 선택**

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

  

  

  

