# PFPM
frequent pattern mining by using VoltDB and MQTT

# Get Dataset
http://fimi.ua.ac.be/data/connect.dat


# dataset to voltdb
- (0) voltdb 啟動 <br>
- (1) create transaction table and partition <br>
  * $ voltdb < volt.sql <br>
- (2) load java class to voltdb procedure <br>
  * $ python load_proc.py LoadTransaction
  * $ python load_proc.py Tproc
- (3) 執行 load data<br>
  * $ java DataToVolt <br>
- (4) 
