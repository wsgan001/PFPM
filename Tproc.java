import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;
import org.voltdb.VoltTable;

public class Tproc extends VoltProcedure {
  public final SQLStmt GetTnumber = new SQLStmt("select COUNT(distinct tid) from transaction;");
  public final SQLStmt GetT2 = new SQLStmt("select t1.iid,t2.iid from transaction as t1,transaction as t2 where t1.tid=t2.tid and t1.tid=?;");

  public VoltTable[] run(int Mnum, int Mid) throws VoltAbortException {
	voltQueueSQL(GetTnumber);
	VoltTable[] Tresult = voltExecuteSQL();
	int Tnumber = (int)Tresult[0].fetchRow(0).getLong(0);
  	for(int i=0;i<Tnumber;i++){ // number of 
		if(i%Mnum!=Mid)continue;
		voltQueueSQL(GetT2,i);
	}
	VoltTable[] results = voltExecuteSQL();
/*
	for(int i=0;i<results.length;i++){
		for(int j=0;j<results[0].getRowCount();j++){
			///// i1 : String.valueOf(results[i].fetchRow(j).getLong(0))
			///// i2 : String.valueOf(results[i].fetchRow(j).getLong(1))
			///// MQTT
		}
	}
*/
	return voltExecuteSQL();
  }
}
