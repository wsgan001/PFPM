# PFPM
frequent pattern mining by using VoltDB and MQTT
<br><br><br>

## Get Dataset
http://fimi.ua.ac.be/data/connect.dat


## dataset to voltdb
- (0) voltdb 啟動 <br>
- (1) create transaction table and partition <br>
  * $ voltdb < volt.sql <br>
- (2) load java class to voltdb procedure <br>
  * $ python load_proc.py LoadTransaction
- (3) 執行 load data<br>
  * $ java DataToVolt <br>
  
## create index and get L1 view
- (1) create index 
  * voltdb
    * create index Tindex on transaction(tid);
    * create index Iindex on transaction(Iid);
- (2) get L1 view
  * voltdb
    * create view L1(iid,support) as select iid,COUNT(*) from transaction group by iid;

## Get L2 by Tproc and MQTT
- (0) transaction table 處理 (刪掉不在L1的item)
- (1) load java class to voltdb procedure
  * $ python load_proc.py Tproc
  * voltdb
    * exec Tproc Wnum,Wid;
      * Mnum : 平行worker數
      * Mid : worker ID
      * 一個worker會執行一次Tproc procedure, 將transaction table中特定多個tid打散成item pairs : (iid1, iid2)
        * 不同server同於voltdb cluster, 各可開數個worker, 達到分散效果
- (2) MQTT處理item pair
    * 進行中 ......
    * 在Tproc.java中要加入MQTT的code, 將item pair傳給local MQTT server ...[1]
      * 有嘗試過直接將item pair透過voltQueueSQL批次插入voltdb table, 但會有out of memory議題
        * 證實無法直接透過voltdb完成PFPM, 故套入MQTT
    * MQTT透過特定channel接收local Tproc傳來的item pair, 並進行counting ...[2]
    * Counting結果再透過特定channel傳給其他server進行整合 ...[3]
    * [1,2,3]分別對應map, combine, reduce
    
## Get Ln
coming soon
