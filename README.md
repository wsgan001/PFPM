# PFPM
frequent pattern mining by using VoltDB and MQTT
<br><br><br>

##### Get Dataset
http://fimi.ua.ac.be/data/connect.dat


##### dataset to voltdb
- (0) voltdb 啟動 <br>
- (1) create transaction table and partition <br>
  * $ voltdb < volt.sql <br>
- (2) load java class to voltdb procedure <br>
  * $ python load_proc.py LoadTransaction
- (3) 執行 load data<br>
  * $ java DataToVolt <br>
  
##### create index and get L1 view
- (1) create index 
  * voltdb
    * create index Tindex on transaction(tid);
    * create index Iindex on transaction(Iid);
- (2) get L1 view
  * voltdb
    * create view L1(iid,support) as select iid,COUNT(*) from transaction group by iid;
