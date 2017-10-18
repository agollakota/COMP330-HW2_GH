
import com.inet.jortho.FileUserDictionary;
import com.inet.jortho.PopupListener;
import com.inet.jortho.SpellChecker;
import com.inet.jortho.SpellCheckerOptions;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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

//class that spellchecks text or text file and provides
//suggestions for corrections

@SuppressWarnings("serial")

public class SpellCheck extends JFrame {
	
	//setting up JOrtho
	public SpellCheck() {
		
		SpellChecker.setUserDictionaryProvider(new FileUserDictionary());      
		SpellChecker.registerDictionaries(this.getClass().getResource("/dictionary"), "en");
		
	}
	//registering JOrtho to JComponent
	public void checker(JTextArea textArea) {
		SpellChecker.register(textArea);
	}
}
