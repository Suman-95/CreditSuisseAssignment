package creditSuisseJavaAssignment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONObject;
import org.testng.annotations.Test;

public class RunnerClass {

	@Test
	public void executeTestcase() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		 DBConnection csa=new DBConnection();
		 JsonManipulation jmp=new JsonManipulation();
		 List<JSONObject> ljs=jmp.flagEventsMorethan4ms(jmp.readTextFile(System.getProperty("user.dir")+
		  "//Resources//logfile.txt"));
		csa.connectHSQLDB(ljs);

	}
}
