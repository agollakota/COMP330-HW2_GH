import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.ResourceBundle;

import com.javanetworkframework.*;
import com.javanetworkframework.rb.util.AbstractWebTranslator;
import org.json.JSONArray;


public class Translation {
	 
	 public String parser(String startLang, String endLang, String word) throws Exception  {

	  String url = "https://translate.googleapis.com/translate_a/single?"+
	    "client=gtx&"+
	    "sl=" + startLang + 
	    "&tl=" + endLang + 
	    "&dt=t&q=" + URLEncoder.encode(word, "UTF-8");    
	  
	  URL obj = new URL(url);
	  HttpURLConnection connection = (HttpURLConnection) obj.openConnection(); 
	  connection.setRequestProperty("User-Agent", "Mozilla/5.0");
	 
	  BufferedReader in = new BufferedReader(
	    new InputStreamReader(connection.getInputStream()));
	  String inputLine;
	  StringBuffer response = new StringBuffer();
	 
	  while ((inputLine = in.readLine()) != null) {
	   response.append(inputLine);
	  }
	  in.close();
	 
	  return parseResult(response.toString());
	 }
	 
	 public String parseResult(String inputJson) throws Exception
	 {

	  
	  JSONArray jsonArray = new JSONArray(inputJson);
	  JSONArray jsonArray2 = (JSONArray) jsonArray.get(0);
	  JSONArray jsonArray3 = (JSONArray) jsonArray2.get(0);
	  
	  return jsonArray3.get(0).toString();
	 }
}