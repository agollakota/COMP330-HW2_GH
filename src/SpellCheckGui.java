
import com.inet.jortho.PopupListener;
import com.inet.jortho.SpellChecker;
import com.inet.jortho.SpellCheckerOptions;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

@SuppressWarnings("serial")

public class SpellCheckGui extends JFrame {

	public SpellCheckGui() {

		// setting JFrame Details
		super("SpellCheck");

		createView();

		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/octopus.png")));

		setSize(400, 800);

		pack();

		setLocationRelativeTo(null);

		setResizable(false);

	}

	public void createView() {

		// Create spellcheck object to perform spellchecker in listeners
		SpellCheck sc = new SpellCheck();

		// Adding panel to frame
		JPanel panel = new JPanel();
		getContentPane().add(panel);

		// Setting JTextArea details
		JTextArea textArea = new JTextArea();
		textArea.setEditable(true);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		textArea.setToolTipText("<html>Enter or open text into the text area and then click <br/> "
				+ "on the 'Spell Check' button to find any potential errors in <br/> "
				+ "spelling. Potential errors in spelling will be underlined and <br/> "
				+ "when right clicked, suggestions shall be provided for correction.</html>");

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(400, 200));

		// Setting JMenuBar Details + actionListeners for MenuItems--> Open/Save files
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);
		menu.setOpaque(true);

		JMenu menuFile = new JMenu("File");

		menu.add(menuFile);

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

		// Setting JButtons and respective ActionListeners
		JButton readButton = new JButton("Open File");
		readButton.setFont(readButton.getFont().deriveFont(Font.BOLD));
		readButton.setToolTipText("Click to open a text file into the text area for spell check.");

		readButton.addActionListener(ev -> {
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

		JButton spellButton = new JButton("Spell Check");
		spellButton.setFont(readButton.getFont().deriveFont(Font.BOLD));
		spellButton.setToolTipText("Click to analyze the text for errors in spelling.");

		spellButton.addActionListener(ev -> {
			sc.checker(textArea);
		});

		// Create JCheckBox to perform real-time spellcheck while selected
		JCheckBox rtBox = new JCheckBox("Real-Time SpellCheck");
		rtBox.setFont(rtBox.getFont().deriveFont(Font.BOLD));
		rtBox.setToolTipText("Press to enable real-time spell check.");

		// Setting SpellCheckerOptions details to add to PopupMenu
		SpellCheckerOptions sco = new SpellCheckerOptions();
		sco.setCaseSensitive(false);
		sco.setSuggestionsLimitMenu(10);
		sco.setLanguageDisableVisible(true);
		sco.setIgnoreWordsWithNumbers(true);

		JPopupMenu popup = SpellChecker.createCheckerPopup(sco);
		panel.setComponentPopupMenu(popup);

		// Adds itemlistener so when checkbox is checked/unchecked will turn
		// real time spellcheck on and off.
		rtBox.addItemListener(ev -> {
			if (!rtBox.isSelected()) {
				SpellChecker.unregister(textArea);
				textArea.addMouseListener(new PopupListener(popup));
			} else {
				SpellChecker.register(textArea);
			}
		});

		// Use of GridLayout for uniform size of buttons
		JPanel box = new JPanel(new GridLayout(3, 0));
		box.add(readButton);
		box.add(spellButton);
		box.add(rtBox);

		panel.add(scrollPane, BorderLayout.CENTER);
		panel.add(box);
	}
}
