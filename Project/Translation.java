import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONArray;


public class Translation {
	 
	 public String parser(String startLang, String endLang, String word) throws Exception  {

	  //concatenates startLang and endLang ISO codes to URL to perform URL request
	  //and encodes desired text to convert to endLang to UTF-8 format	 
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
	 
	 public String parseResult(String input) throws Exception
	 {
	  JSONArray jsonArray = new JSONArray(input);
	  JSONArray jsonArray2 = (JSONArray) jsonArray.get(0);
	  JSONArray jsonArray3 = (JSONArray) jsonArray2.get(0);
	  
	  return jsonArray3.get(0).toString();
	 }
}