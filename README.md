# JavaFX-JDBC
#Oracle database is used
#Intellij IDEA
#Data will be populated in dynamic table
![image](https://user-images.githubusercontent.com/21217148/210169681-cfa90e12-7694-491a-84ae-2ec508f07f24.png)
## Stored Procedure
### create the following SP in oracle db server
### **********************************************
 create or replace procedure "INSERTDATA"
 (name IN VARCHAR2,
 course IN VARCHAR2)
 is
 begin
 INSERT INTO java VALUES(Name, course);
 end;
### **********************************************
 create or replace procedure "SELECTDATA"
    (selec  out SYS_REFCURSOR)
is
 begin
   OPEN selec FOR
 SELECT * FROM java;
 end SELECTDATA;
### **********************************************
