
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Component;

//Class converts text files to text-to-speech and saves as .wav
//Class made half with windowbuilder half manually

@SuppressWarnings("serial")
public class TTSGui extends JFrame {
	private File file = null;

	public TTSGui() {

		// Setting JFrame properties
		super("Text-to-Speech");

		initGUI();

		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/octopus.png")));

		setMinimumSize(new Dimension(250, 250));

		pack();

		setResizable(false);

		setLocationRelativeTo(null);
	}

	private void initGUI() {
		// Instantiating necessary variables
		JFileChooser fc = new JFileChooser();
		TTSConvert tts = new TTSConvert();

		// Used a gridbaglayout for this gui
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		// setting text area with scroll pane and other properties
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setMaximumSize(new Dimension(32767, 32767));
		scrollPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 12;
		gbc_scrollPane.gridheight = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		getContentPane().add(scrollPane, gbc_scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane.setViewportView(textArea);

		// setting JButtons
		JButton btnSpeak = new JButton("Speak");
		btnSpeak.setToolTipText("Click to convert text file to text-to-speech.");
		btnSpeak.addActionListener(ev -> {
			if (file == null) {
				JOptionPane.showMessageDialog(null, "Please open a text file before using Text-to-Speech.",
						"Text-to-Speech", JOptionPane.CLOSED_OPTION, null);
			}
			tts.talk(file);
		});

		GridBagConstraints gbc_btnSpeak = new GridBagConstraints();
		gbc_btnSpeak.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSpeak.gridwidth = 3;
		gbc_btnSpeak.insets = new Insets(0, 0, 5, 5);
		gbc_btnSpeak.gridx = 5;
		gbc_btnSpeak.gridy = 6;
		getContentPane().add(btnSpeak, gbc_btnSpeak);

		JLabel lblFile = new JLabel("Open a File.");
		GridBagConstraints gbc_lblFile = new GridBagConstraints();
		gbc_lblFile.gridwidth = 10;
		gbc_lblFile.insets = new Insets(0, 0, 0, 5);
		gbc_lblFile.gridx = 1;
		gbc_lblFile.gridy = 8;
		getContentPane().add(lblFile, gbc_lblFile);

		JButton btnOpen = new JButton("Open");
		btnOpen.setToolTipText("Click to export text file as .mp3 file.");
		btnOpen.addActionListener(ev -> {
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				file = fc.getSelectedFile();
				lblFile.setText(file.getPath().toString());
				lblFile.setToolTipText(file.getPath().toString());
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

		GridBagConstraints gbc_btnOpen = new GridBagConstraints();
		gbc_btnOpen.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOpen.insets = new Insets(0, 5, 5, 5);
		gbc_btnOpen.gridx = 2;
		gbc_btnOpen.gridy = 6;
		getContentPane().add(btnOpen, gbc_btnOpen);

		JButton btnSave = new JButton("Save");
		btnSave.setToolTipText("Click to export text file as .mp3 file.");
		btnSave.addActionListener(ev -> {
			if (file == null) {
				JOptionPane.showMessageDialog(null, "Please open a text file before conversion to .mp3 format.",
						"Text-to-Speech", JOptionPane.CLOSED_OPTION, null);
			} else if (file != null) {
				int returnVal = fc.showSaveDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File ttsFile = fc.getSelectedFile();
					try {
						tts.audio(file, ttsFile);

					} catch (Exception e) {
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "File has been exported to .mp3 format successfully.",
							"Text-to-Speech", JOptionPane.CLOSED_OPTION, null);
					textArea.setText("");
					file = null;
					lblFile.setText("Open a File.");
				}
			} else {
				System.out.println("Operation Cancelled...");
			}
		});
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 9;
		gbc_btnSave.gridy = 6;
		getContentPane().add(btnSave, gbc_btnSave);
	}
}
