
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.inet.jortho.PopupListener;
import com.inet.jortho.SpellChecker;
import com.inet.jortho.SpellCheckerOptions;

//Class that provides specific .txt file creation
//Embeds features of some of the other GUIs to enhance
//text creation such as spellcheck or translation.

@SuppressWarnings("serial")
public class CazaPad extends JFrame {

	public CazaPad() {

		// setting JFrame Details
		super("CazaPad");

		createView();

		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/octopus.png")));

		pack();

		setLocationRelativeTo(null);

		setResizable(false);
	}

	public void createView() {

		// Instantiating necessary variables
		SpellCheck sc = new SpellCheck();

		// Creating scrollable text area
		JPanel panel = new JPanel();
		getContentPane().add(panel);

		// Setting JTextArea details
		JTextArea textArea = new JTextArea();
		textArea.setEditable(true);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		SpellChecker.register(textArea);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(800, 800));

		// Setting JMenuBar Details + actionListeners for MenuItems--> Open/Save files
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);
		menu.setOpaque(true);

		JMenu menuFile = new JMenu("File");
		menu.add(menuFile);

		JMenu toolFile = new JMenu("Tools");
		menu.add(toolFile);

		JMenuItem open = new JMenuItem("Open File");
		menuFile.add(open);

		JFileChooser fc = new JFileChooser();

		open.addActionListener(ev -> {
			int returnVal = fc.showOpenDialog(panel);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				try {
					BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
					textArea.read(input, "Reading File...");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Operation Cancelled...");
			}
		});

		JMenuItem save = new JMenuItem("Save File");
		menuFile.add(save);

		save.addActionListener(ev -> {
			int returnVal = fc.showSaveDialog(panel);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String area = textArea.getText();
				File file = fc.getSelectedFile();
				try {
					FileWriter fw = new FileWriter(file.getPath() + ".txt");
					fw.write(area);
					fw.flush();
					fw.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Operation Cancelled...");
			}
		});

		// Setting JPopup Menu

		JPopupMenu popup = new JPopupMenu();
		panel.setComponentPopupMenu(popup);

		SpellCheckerOptions sco = new SpellCheckerOptions();
		sco.setCaseSensitive(false);
		sco.setSuggestionsLimitMenu(10);
		sco.setLanguageDisableVisible(false);
		sco.setIgnoreWordsWithNumbers(true);

		JMenu spellMenu = SpellChecker.createCheckerMenu(sco);
		popup.add(spellMenu);

		JCheckBoxMenuItem spellCheck = new JCheckBoxMenuItem("SpellCheck");
		spellCheck.setSelected(true);
		toolFile.add(spellCheck);
		spellCheck.addItemListener(ev -> {
			if (!spellCheck.isSelected()) {
				SpellChecker.unregister(textArea);
				textArea.addMouseListener(new PopupListener(popup));
			} else {
				SpellChecker.register(textArea);
			}
		});

		JMenu ttsMenu = new JMenu("Text-to-Speech");
		ttsMenu.addItemListener(ev -> {

		});

		// Adding all items to panel
		panel.add(scrollPane, BorderLayout.CENTER);
	}
}
