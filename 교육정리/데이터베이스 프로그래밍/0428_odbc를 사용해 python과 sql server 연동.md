---
typora-copy-images-to: images
---



## 1번



- ### **고객 연령별 비율**

![image-20210428131555896](images/image-20210428131555896.png)



```sql
--연령대, 성별 고객의 수
select case 
    when birth_dt < '60/01/01'  then '50년생미만'
    when birth_dt between '60/01/01' and '69/12/31' then '60년생'
    when birth_dt between '70/01/01' and '79/12/31' then '70년생'
    when birth_dt between '80/01/01' and '89/12/31' then '80년생'
    when birth_dt between '90/01/01' and '99/12/31' then '90년생'
    when birth_dt > '99/12/31'  then '20년생~'
end as age, count(*) as 고객수 from customer
group by case 
    when birth_dt < '60/01/01'  then '50년생미만'
    when birth_dt between '60/01/01' and '69/12/31' then '60년생'
    when birth_dt between '70/01/01' and '79/12/31' then '70년생'
    when birth_dt between '80/01/01' and '89/12/31' then '80년생'
    when birth_dt between '90/01/01' and '99/12/31' then '90년생'
    when birth_dt > '99/12/31'  then '20년생~'
end
;
```



![image-20210428131519236](images/image-20210428131519236.png)





![그림2](images/그림2.png)



- ### 지역별 고객 비율



```sql
--지역별 고객 수
select decode(substr(address1,1,instr(address1,' ', 1, 1) -1 ), 'uC778천','인천광역시',substr(address1,1,instr(address1,' ', 1, 1) -1 )) 
as local, count(*) as "고객수" from customer group by substr(address1,1,instr(address1,' ', 1, 1) -1 );

```



![그림2](images/그림2-1619584102874.png)



![그림4](images/그림4.png)



- ### 각 부서별 사원 수

```sql
select d.dname ,count(*) from emp e
inner join dept d on e.DEPTNO = d.DEPTNO group by d.dname;
```



![image-20210428133251730](images/image-20210428133251730.png)



![image-20210428133306539](images/image-20210428133306539.png)



![그림6](images/그림6.png)





- ### 부서 별 고객 수

```sql
--부서 별 고객 수
select d.dname as 부서, count(*) as 고객수 from customer c 
inner join emp e
on c.ACCOUNT_MGR = e.EMPNO 
inner join dept d
on e.deptno = d.DEPTNO
group by d.DNAME;
```



![image-20210428133615949](images/image-20210428133615949.png)



## 파이썬 설치

- https://www.python.org/downloads/

![image-20210428182936345](images/image-20210428182936345.png)

![image-20210428182954229](images/image-20210428182954229.png)



- 아나콘다 설치

  https://www.anaconda.com/products/individual#download-section

![image-20210428183043709](images/image-20210428183043709.png)





![image-20210428183116454](images/image-20210428183116454.png)



![image-20210428183129830](images/image-20210428183129830.png)

![image-20210428183140765](images/image-20210428183140765.png)

![image-20210428183153684](images/image-20210428183153684.png)





![image-20210428183404288](images/image-20210428183404288.png)



![image-20210428183815945](images/image-20210428183815945.png)



![image-20210428183842368](images/image-20210428183842368.png)



```
import pyodbc
import pandas as pd
import numpy as np
import numpy as np
import matplotlib.pyplot as plt

cnxn = pyodbc.connect("DSN=ORCL; uid=scott; pwd=tiger")

query = "SELECT e.ENAME AS ENAME, COUNT(c.ID) AS customer_count, AVG(SAL)*12 AS annual_salary FROM CUSTOMER c, EMP e WHERE c.ACCOUNT_MGR = e.EMPNO GROUP BY e.ENAME;"
df = pd.read_sql(query, cnxn)

plt.figure(figsize=(20, 10))

plt.rc('font', family='Malgun Gothic')

plt.title("사원별 고객의 수와 연봉")

plt.scatter(df.CUSTOMER_COUNT, df.ANNUAL_SALARY)

plt.xlabel('고객수', fontsize=20)

plt.ylabel('연봉', fontsize=20)

plt.show()


js = df.to_json(orient = 'table')

#출력 : print(json.dumps(js, ensure_ascii=False,indent"\t"))
 
with open('df.json', 'w', encoding='utf-8') as file:
    df.to_json(file, force_ascii=False)

```



![image-20210428190825692](images/image-20210428190825692.png)



![image-20210428190647765](images/image-20210428190647765.png)



![image-20210428190746626](images/image-20210428190746626.png)



![image-20210428190757724](images/image-20210428190757724.png)