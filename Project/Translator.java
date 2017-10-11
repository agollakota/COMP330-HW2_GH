//IMPORTANT: TRANSLATOR FREE TRIAL CAN ONLY BE USED 10 TIMES IN AGGREGATE

package guiTest;
import javax.swing.*;

import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

import java.io.*;

import java.net.HttpURLConnection;
import java.net.URL;

public class Translator extends JFrame{
    private static final String CLIENT_ID = "FREE_TRIAL_ACCOUNT";
    private static final String CLIENT_SECRET = "PUBLIC_SECRET";
    private static final String ENDPOINT = "http://api.whatsmate.net/v1/translation/translate";
  
  
    public Translator(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        setSize(300,250);
      
        pack();
    
        setLocationRelativeTo(null);
      
        setResizable(false);
    }
    public void view(){
        JFileChooser fc = new JFileChooser();
		JTextArea textArea = new JTextArea();
		textArea.setEditable(true);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		JButton readButton = new JButton("OPEN FILE");
		JButton transLateBtn = new JButton("TRANSLATE");
		JPanel panel = new JPanel();
        
        String[] toLanguage = {"fr", "de", "es"};
        //The first JPanel contains a JLabel and JCombobox
        final JPanel comboPanel = new JPanel();
        final JLabel comboLbl = new JLabel("Select 'to' language:");
        final JComboBox<Object> toLanguages = new JComboBox<Object>(toLanguage);
      
        comboPanel.add(comboLbl);
        comboPanel.add(toLanguages);
      
    }
  
  public static void translate(String fromLang, String toLang, String text) throws Exception {
    String jsonPayload = new StringBuilder()
      .append("{")
      .append("\"fromLang\":\"")
      .append(fromLang)
      .append("\",")
      .append("\"toLang\":\"")
      .append(toLang)
      .append("\",")
      .append("\"text\":\"")
      .append(text)
      .append("\"")
      .append("}")
      .toString();

    URL url = new URL(ENDPOINT);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setDoOutput(true);
    conn.setRequestMethod("POST");
    conn.setRequestProperty("X-WM-CLIENT-ID", CLIENT_ID);
    conn.setRequestProperty("X-WM-CLIENT-SECRET", CLIENT_SECRET);
    conn.setRequestProperty("Content-Type", "application/json");

    OutputStream os = conn.getOutputStream();
    os.write(jsonPayload.getBytes());
    os.flush();
    os.close();

    int statusCode = conn.getResponseCode();
    System.out.println("Status Code: " + statusCode);
    BufferedReader br = new BufferedReader(new InputStreamReader(
        (statusCode == 200) ? conn.getInputStream() : conn.getErrorStream()
      ));
    String output;
    while ((output = br.readLine()) != null) {
        System.out.println(output);
    }
    conn.disconnect();
  }

}
    
