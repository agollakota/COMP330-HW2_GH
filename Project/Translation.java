import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONArray;

//adapted from http://archana-testing.blogspot.com/2016/02/calling-google-translation-api-in-java.html

public class Translation {
	 
	 public String parser(String startLang, String endLang, String text) throws Exception  {

	  //concatenates startLang and endLang ISO codes to URL to perform URL request
	  //and encodes desired text to UTF-8 for conversion to endLang through Google Translate API		 
	  String url = "https://translate.googleapis.com/translate_a/single?"+
	    "client=gtx&"+
	    "sl=" + startLang + 
	    "&tl=" + endLang + 
	    "&dt=t&q=" + URLEncoder.encode(text, "UTF-8");    
	  
	  URL con = new URL(url);
	  HttpURLConnection connection = (HttpURLConnection) con.openConnection(); 
	  connection.setRequestProperty("User-Agent", "Mozilla/5.0");
	 
	  BufferedReader reader = new BufferedReader(
	    new InputStreamReader(connection.getInputStream()));
	  String inputLine;
	  StringBuffer output = new StringBuffer();
	 
	  while ((inputLine = reader.readLine()) != null) {
	   output.append(inputLine);
	  }
	  reader.close();
	 
	  return parseOutput(output.toString());
	 }
	 
	 public String parseOutput(String text) throws Exception {
		 
	  //Must use javascript object notation arrays to transmit data between the application and server
	  //Use of three arrays to grab indexes and expel unnecessary accompanying data 
	  JSONArray firstArray = new JSONArray(text);
	  JSONArray secondArray = (JSONArray) firstArray.get(0);
	  JSONArray thirdArray = (JSONArray) secondArray.get(0);
	  
	  return thirdArray.get(0).toString();
	 }
}