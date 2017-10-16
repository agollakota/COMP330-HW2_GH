import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.inet.jortho.FileUserDictionary;
import com.inet.jortho.PopupListener;
import com.inet.jortho.SpellChecker;
import com.inet.jortho.SpellCheckerOptions;

@SuppressWarnings("serial")
public class TTSGui extends JFrame {

	public TTSGui() {
		
		createView();
		
		setSize(400,800);
		
		pack();
		
		setLocationRelativeTo(null);
		
		setResizable(false);
		
	}

	public void createView() {
		
		JButton speakButton = new JButton("SPEAK");
		speakButton.addActionListener(ev -> {
        	File file = new File("Sample.txt");
			TTSConvert tts = new TTSConvert();
        	tts.speak(file);
		});
		
		JButton convertButton = new JButton("CONVERT");
		convertButton.addActionListener(ev -> {
        	File file = new File("Sample.txt");
			TTSConvert tts = new TTSConvert();
        	tts.audio(file);
		});
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		Box box = Box.createHorizontalBox();
		
		box.add(speakButton);
		box.add(convertButton);
		
		panel.add(box);
	  }
}


