package creditSuisseJavaAssignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;

public class JsonManipulation {
	public List<String> readTextFile(String filepath) throws IOException,FileNotFoundException {
		
		FileInputStream fis=new FileInputStream(new File(filepath));
		BufferedReader bfr=new BufferedReader(new InputStreamReader(fis));
		String eachLine;
		List<String> textContent=new ArrayList<String>();
		while((eachLine=bfr.readLine())!=null) {
			textContent.add(eachLine);
		}
fis.close();
return textContent;
	
}

public List<JSONObject> flagEventsMorethan4ms(List<String> textContent) {
	List<JSONObject> ljs=new ArrayList<JSONObject>();
	for(String str:textContent) {
		JSONObject js=new JSONObject(str);
		ljs.add(js);
		}
	Set<String> eventID=new HashSet<String>();
	for(JSONObject jo:ljs) {
		eventID.add(jo.getString("id"));
	}
	List<Integer> timestamp=new ArrayList<Integer>();
	String host="";
	String type="";
	List<JSONObject> finalJson=new ArrayList<JSONObject>();
	for(String eachEvent:eventID) {
		timestamp.clear();
		int duration=0;
		for(JSONObject jo:ljs) {
			if(jo.getString("id").equals(eachEvent)) {
				timestamp.add(jo.getInt("timestamp"));
				try{
					host=jo.getString("host");
				}catch(Exception e) {
					host="";
				}
				try {
				type=jo.getString("type");
				}catch(Exception e) {
					type="";
				}
			}
		}
		System.out.println("Time stamp size:"+timestamp.size());
		if(timestamp.size()==1) {
			System.err.println("Only one record is available for this event.");
		}
		if(timestamp.size()==2) {
			if(timestamp.get(0)>timestamp.get(1)) {
				duration=timestamp.get(0)-timestamp.get(1);
			}
			if(timestamp.get(1)>timestamp.get(0)) {
				duration=timestamp.get(1)-timestamp.get(0);
			}			
		}
			JSONObject json=new JSONObject();
			json.put("eventID", eachEvent);
			json.put("duration", duration);
			if(duration>=4) {
				json.put("alert", "true");
				}
			if(duration<4) {
				json.put("alert", "false");
				}
			json.put("host",host);
				json.put("type", type);
			finalJson.add(json);
			}
		
	return finalJson;
	
		
	}
}
