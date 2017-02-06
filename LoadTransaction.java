// Kevin yenkuanlee
import org.voltdb.SQLStmt;
import org.voltdb.VoltProcedure;
import org.voltdb.VoltTable;

public class LoadTransaction extends VoltProcedure {


  public final SQLStmt InsertToTransaction = new SQLStmt("insert into Transaction values(?,?)");
  public VoltTable[] run(int tid,String line) throws Exception {
	  	  String [] tmp = line.split("#");
	  	  for(String x:tmp){
	  		  if(x.equals(""))continue;
	  		  voltQueueSQL(InsertToTransaction,Integer.parseInt(x),tid);
	  	  }
          return voltExecuteSQL(true);
      }
}
