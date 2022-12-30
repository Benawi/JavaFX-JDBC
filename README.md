# JavaFX-JDBC
#Oracle database is used
#Intellij IDEA
#Data will be populated in dynamic table
# Stored Procedure
# please create the following SP in oracle db server
# +++++++++++++++++++++++++++++++++
# create or replace procedure "INSERTDATA"
# (name IN VARCHAR2,
# course IN VARCHAR2)
# is
# begin
# INSERT INTO java VALUES(Name, course);
# end;
# +++++++++++++++++++++++++++++++++
# create or replace procedure "SELECTDATA"
#    (selec  out SYS_REFCURSOR)
# is
# begin
#    OPEN selec FOR
# SELECT * FROM java;
# end SELECTDATA;
# ++++++++++++++++++++++++++++++++