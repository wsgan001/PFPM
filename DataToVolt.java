import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.voltdb.VoltProcedure;
import org.voltdb.client.Client;
import org.voltdb.client.ClientFactory;
public class DataToVolt extends VoltProcedure{
	
	   public static ArrayList<String> GetData()throws IOException{
	   FileReader fr = new FileReader("connect.dat");
 	BufferedReader br = new BufferedReader(fr);
 	ArrayList<String> data = new ArrayList<String>();
 	while (br.ready()) {
 		String line = br.readLine();
 		line = line.replace("\n","");
 		line = line.replace(" ","#");
 		data.add(line);

 	}
 	return data;
}
	
	public static void main(String[] args) throws IOException {
		Client myApp;
		String ip = "localhost";
		ArrayList<String> data = GetData();
		myApp = ClientFactory.createClient();
		try {
			myApp.createConnection("localhost");
			for(int i=0;i<data.size();i++){
				myApp.callProcedure("LoadTransaction",i,data.get(i));
		}
		return ;
		} catch (Exception e) {
			e.printStackTrace();
			return ;
		}
    }
}
