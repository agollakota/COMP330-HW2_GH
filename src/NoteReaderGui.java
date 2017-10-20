import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//Class for old Project1 Requirements
//Made with both window builder and manual coding

@SuppressWarnings("serial")
public class NoteReaderGui extends JFrame {
	
	//Need directory in scope for all actionlisteners
	private File dir = null;
	
	public NoteReaderGui() {

		//Setting JFrame properties
				super("NoteReader");

				initGUI();

				setIconImage(Toolkit.getDefaultToolkit().getImage("octopus.png"));
				
				setMinimumSize(new Dimension(300,300));

				pack();

				setResizable(false);

				setLocationRelativeTo(null);
	}
	private void initGUI() {
		
		//instantiate noteReader for all of the calls
		NoteReader nr = new NoteReader();
		
		//Trying out MigLayout for Windowbuilder
		getContentPane().setLayout(new MigLayout("", "[grow][grow][]", "[][][][][][][][grow]"));
		
		
		JLabel lblEnterInputFor = new JLabel("Enter input for desired search function:");
		getContentPane().add(lblEnterInputFor, "cell 0 3 3 1,alignx center");
		
		//Setting scrolled textarea
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 0 6 3 2,grow");
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(true);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane.setViewportView(textArea);
		
		JTextField textField = new JTextField();
		getContentPane().add(textField, "cell 0 4 3 1,growx");
		textField.setColumns(10);
		
		JLabel lblOutput = new JLabel("Output:");
		getContentPane().add(lblOutput, "cell 1 5,alignx center");
		
		//Setting JButtons w/ actionlisteners that call functions from proj1 code
		JButton btnNotesbyentry = new JButton("NotesByEntry");
		btnNotesbyentry.setToolTipText("<html> Click to return list of files that contain <br/>"
											+ "at least one occurence of input string.</html>");
		btnNotesbyentry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dir == null) {
					JOptionPane.showMessageDialog(null, "Please select a directory before"
							+ " using functions.", "NoteReader",
							JOptionPane.CLOSED_OPTION, null);
				}else if(textField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter into text field before"
							+ " using functions.", "NoteReader",
							JOptionPane.CLOSED_OPTION, null);
				}
				System.out.println(dir.getPath().toString());
				System.out.println(textField.getText());
				textArea.setText(nr.notesByEntry(dir, textField.getText()));
			}
		});
		getContentPane().add(btnNotesbyentry, "cell 0 0");
		
		JButton btnSortbyentry = new JButton("SortByEntry");
		btnSortbyentry.setToolTipText("<html> Click to return list of files organized by the  <br/>"
											+ "number of occurences of input string from <br/>"
											+ "least to greatest.</html>");
		btnSortbyentry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dir == null) {
					JOptionPane.showMessageDialog(null, "Please select a directory before"
							+ " using functions.", "NoteReader",
							JOptionPane.CLOSED_OPTION, null);
				}else if(textField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter into text field before"
							+ " using functions.", "NoteReader",
							JOptionPane.CLOSED_OPTION, null);
				}
				System.out.println(dir.getPath().toString());
				System.out.println(textField.getText());
				textArea.setText(nr.sortByEntry(dir, textField.getText()));
			}
		});
		getContentPane().add(btnSortbyentry, "cell 1 0");
		
		JButton btnContainsmention = new JButton("ContainsMention");
		btnContainsmention.setToolTipText("<html> Click to return list of number of occurences <br/>"
												+ "of a specific mention amongst all files.</html>");
		btnContainsmention.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dir == null) {
					JOptionPane.showMessageDialog(null, "Please select a directory before"
							+ " using functions.", "NoteReader",
							JOptionPane.CLOSED_OPTION, null);
				}
				System.out.println(dir.getPath().toString());
				System.out.println(textField.getText());
				textArea.setText(nr.containsMention(dir));
			}
		});
		
		getContentPane().add(btnContainsmention, "cell 2 0");
		
		JButton btnGetkeywords = new JButton("GetKeywords");
		btnGetkeywords.setToolTipText("<html> Click to return list of all existing keywords amongst the files.</html>");
		btnGetkeywords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dir == null) {
					JOptionPane.showMessageDialog(null, "Please select a directory before"
							+ " using functions.", "NoteReader",
							JOptionPane.CLOSED_OPTION, null);
				}
				System.out.println(dir.getPath().toString());
				System.out.println(textField.getText());
				textArea.setText(nr.getKeywords(dir));
			}
		});
		getContentPane().add(btnGetkeywords, "cell 0 1");
		
		JButton btnDirectory = new JButton("Directory");
		btnDirectory.setToolTipText("<html> Click to set the directory containing all existing notes.</html>");
		btnDirectory.addActionListener(ev -> {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Select Directory");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				chooser.setAcceptAllFileFilterUsed(false);
				if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
					dir = chooser.getSelectedFile();
				}
		});
		
		getContentPane().add(btnDirectory, "cell 1 1,growx");
		
		JButton btnTopsort = new JButton("TopSort");
		btnTopsort.setToolTipText("<html> Click to return list of files sorted by total weight, <br/>"
											+ "defined by how many mentions and keywords are<br/> "
											+ "present in the file from greatest to least. </html>");
		
		btnTopsort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dir == null) {
					JOptionPane.showMessageDialog(null, "Please select a directory before"
							+ " using functions.", "NoteReader",
							JOptionPane.CLOSED_OPTION, null);
				}
				System.out.println(dir.getPath().toString());
				System.out.println(textField.getText());
				textArea.setText(nr.topSort(dir));
			}
		});
		getContentPane().add(btnTopsort, "cell 2 1,growx");
		
	}

}
