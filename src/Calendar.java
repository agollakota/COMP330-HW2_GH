
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.Date;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;

import com.inet.jortho.SpellChecker;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.commons.io.FileUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

//Class allows for memo creation on specified calendar dates.
//Hybrid class made in unison with Windows Builder and Manual Coding

@SuppressWarnings("serial")
public class Calendar extends JFrame {

	private File dir;
	private String dateMemo;
	private FileOutputStream write;

	public Calendar() {

		// Setting JFrame properties
		super("Calendar");

		initGUI();

		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/octopus.png")));

		pack();

		setResizable(false);

		setLocationRelativeTo(null);
	}

	private void initGUI() {
		// Setting frame layout
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 4 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 4 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		// Setting JCalendar details
		JCalendar calendar = new JCalendar();
		calendar.setToolTipText("Select any date to view or create memos.");
		GridBagConstraints gbc_calendar = new GridBagConstraints();
		gbc_calendar.gridheight = 4;
		gbc_calendar.insets = new Insets(0, 0, 0, 5);
		gbc_calendar.fill = GridBagConstraints.BOTH;
		gbc_calendar.gridx = 0;
		gbc_calendar.gridy = 0;
		getContentPane().add(calendar, gbc_calendar);

		// Setting JLabel details
		JLabel lblNewLabel = new JLabel("Select a Date");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		// Setting ScrollPane/TextArea
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		getContentPane().add(scrollPane, gbc_scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setToolTipText("Enter and edit text in this area to save as a memo.");
		textArea.setEditable(true);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane.setViewportView(textArea);

		// Adding property listener to find date selected on calendar
		calendar.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				textArea.setText("");
				String jcalendar = calendar.getDate().toString();
				lblNewLabel.setText(jcalendar);
				char[] holder = jcalendar.toCharArray();
				char[] accept = Arrays.copyOfRange(holder, 0, 10);
				dateMemo = String.valueOf(accept).replaceAll("\\s+", "");

				FileInputStream fis = null;
				try {
					File fil = new File(dir.getPath().toString() + "/" + dateMemo + ".txt");
					fis = new FileInputStream(fil);
					byte[] data = new byte[fis.available()];
					fis.read(data);
					String text1 = new String(data);
					textArea.setText(text1);

					fis.close();
					System.gc();

				} catch (Exception f) {
					try {
						fis.close();
					} catch (Exception j) {
						j.printStackTrace();
					}

					f.printStackTrace();
				}
			}
		});

		// Saves memo specified for certain date in directory
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setToolTipText("Click to save memo for chosen date.");
		btnNewButton.addActionListener(ev -> {
			String area = textArea.getText();

			try {
				File space = new File(dir.getPath().toString() + "/" + dateMemo + ".txt");
				write = new FileOutputStream(space);
				if (area.length() != 0) {
					write.write(area.getBytes());
					write.flush();
					write.close();
					System.gc();

					JOptionPane.showMessageDialog(null, "Memo has been saved successfully.", "Calendar",
							JOptionPane.CLOSED_OPTION, null);
				}
			} catch (Exception e) {
				try {
					write.close();
				} catch (Exception i) {
					i.printStackTrace();
				}
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Please select your directory before saving any text files.",
						"Error", JOptionPane.CLOSED_OPTION, null);
			}
		});

		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 32, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;

		getContentPane().add(btnNewButton, gbc_btnNewButton);

		// Selects directory for storing memos
		JButton btnNewButton_1 = new JButton("Directory");
		btnNewButton_1.setToolTipText("Click to select directory for storing memos.");
		btnNewButton_1.addActionListener(ev -> {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("Open Directory");
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			chooser.setAcceptAllFileFilterUsed(false);
			if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				dir = chooser.getSelectedFile();
			}
			FileInputStream fis = null;
			try {
				File fil = new File(dir.getPath().toString() + "/" + dateMemo + ".txt");
				fis = new FileInputStream(fil);
				byte[] data = new byte[fis.available()];
				fis.read(data);
				String text1 = new String(data);
				textArea.setText(text1);

				fis.close();
				System.gc();

				if (fil.exists()) {
					JOptionPane.showMessageDialog(null, "Please check the text area for today's scheduled memo!",
							"Calendar", JOptionPane.CLOSED_OPTION, null);
				}

			} catch (Exception f) {
				try {
					fis.close();
				} catch (Exception o) {
					o.printStackTrace();
				}
				f.printStackTrace();
			}
		});

		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 1;
		getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
	}
}
