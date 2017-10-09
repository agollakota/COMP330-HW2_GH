
import com.inet.jortho.FileUserDictionary;
import com.inet.jortho.PopupListener;
import com.inet.jortho.SpellChecker;
import com.inet.jortho.SpellCheckerOptions;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SuppressWarnings("serial")

public class SpellCheck extends JFrame {
	
	public SpellCheck() {
		
		createView();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setSize(400,800);
		
		pack();
		
		setLocationRelativeTo(null);
		
		setResizable(false);
		
	}

	public void createView() {
		
		JFileChooser fc = new JFileChooser();
		JTextArea textArea = new JTextArea();
		textArea.setEditable(true);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		JButton readButton = new JButton("OPEN FILE");
		JButton spellButton = new JButton("SPELL CHECK");
		JPanel panel = new JPanel();
		
		SpellChecker.setUserDictionaryProvider(new FileUserDictionary());      
		SpellChecker.registerDictionaries(this.getClass().getResource("/dictionary"), "en");
		  
		SpellCheckerOptions sco = new SpellCheckerOptions();
		
		sco.setCaseSensitive(false);
		sco.setSuggestionsLimitMenu(10);
		sco.setLanguageDisableVisible(false);
		sco.setIgnoreWordsWithNumbers(true);
		
		JPopupMenu popup = SpellChecker.createCheckerPopup(sco);
		textArea.addMouseListener(new PopupListener(popup));
		
		getContentPane().add(panel);
		
		spellButton.addActionListener(ev -> {
			SpellChecker.register(textArea);
		});
	    
		readButton.addActionListener(ev -> {
	      int returnVal = fc.showOpenDialog(panel);
	      if (returnVal == JFileChooser.APPROVE_OPTION) {
	        File file = fc.getSelectedFile();
	        try {
	          BufferedReader input = new BufferedReader(new InputStreamReader(
	              new FileInputStream(file)));
	          textArea.read(input, "READING FILE :-)");
	        } catch (Exception e) {
	          e.printStackTrace();
	        }
	      } else {
	        System.out.println("Operation is CANCELLED :(");
	      }
	    });

		Box box = Box.createVerticalBox();
		box.add(readButton);
		box.add(spellButton);
		
	    panel.add(scrollPane, BorderLayout.CENTER);
	    panel.add(box);
	  }
}
