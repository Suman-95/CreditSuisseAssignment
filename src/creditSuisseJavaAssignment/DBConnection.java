package creditSuisseJavaAssignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONObject;

public class DBConnection {

public void connectHSQLDB(List<JSONObject> js) throws ClassNotFoundException, SQLException {
	Connection con=null;
	Class.forName("org.hsqldb.jdbc.JDBCDriver");
	con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
	if (con!= null){
        System.out.println("Connection created successfully");
        
     }else{
        System.out.println("Problem with creating connection");
     }
	Statement stm=con.createStatement();
	Statement stm2=con.createStatement();
	
	 Statement stm3=con.createStatement(); 
	 int dropresult=stm3.executeUpdate("drop table finalDetailtable");
	
	String query="CREATE TABLE finalDetailtable (\r\n"
			+ "			   eventid VARCHAR(50) NOT NULL,\r\n"
			+ "			   alert VARCHAR(50) NOT NULL,\r\n"
			+ "			   duration INT NOT NULL,\r\n"
			+ "			   type VARCHAR(50),\r\n"
			+ "			   host VARCHAR(50) \r\n"
			+ "			);  ";
	
int	result = stm.executeUpdate(query);  
for(int i=0;i<js.size();i++) {
String insertQuery="insert into finalDetailtable values("+"'"+js.get(i).getString("eventID")+"',"
													+"'"+js.get(i).getString("alert")+"',"
													+js.get(i).getInt("duration")+","
													+"'"+js.get(i).getString("type")+"',"
													+"'"+js.get(i).getString("host")+"'"+")";
System.out.println(insertQuery);
int insertresult=stm2.executeUpdate(insertQuery);
}
ResultSet rs=stm.executeQuery("select * from finalDetailtable");	
while(rs.next()) {
	System.out.println(rs.getString("eventid"));
	System.out.println(rs.getString("alert"));
	System.out.println(rs.getInt("duration"));
	System.out.println(rs.getString("type"));
	System.out.println(rs.getString("host"));
}
}





}
