
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 *
 */

@SuppressWarnings("serial")

public class Gui extends JFrame {

	public Gui() {

		// setting details of JFrame
		super("CAZAPro");

		createView();

		// setting to custom icon (credits to Austin)
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/octopus.png")));

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setSize(470, 470);

		pack();

		setLocationRelativeTo(null);

		setResizable(false);

	}

	public void createView() {

		// Setting GUI background to custom gif (credits to Zach)
		JLabel background = new JLabel(new ImageIcon(this.getClass().getResource("/resources/giphy.gif")));
		background.setLayout(new BorderLayout());
		getContentPane().add(background);

		// Setting JButton details and their accompanying action listeners
		// to call the respective GUIs to their features
		ImageIcon tempIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/octopus.png")));
		Image img = tempIcon.getImage();
		// resizes octopus.png
		Image newimg = img.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		ImageIcon noteIcon = new ImageIcon(newimg);

		// get rid of border and content area in order to use octopus.png as button
		JButton noteButton = new JButton(new ImageIcon(noteIcon.getImage()));
		noteButton.setToolTipText("Open CazaPad");
		noteButton.setBorder(BorderFactory.createEmptyBorder());
		noteButton.setContentAreaFilled(false);
		noteButton.setFont(noteButton.getFont().deriveFont(Font.BOLD));
		noteButton.setBounds(165, 170, 30, 30);

		noteButton.addActionListener(ev -> {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new CazaPad().setVisible(true);
				}
			});
		});

		JButton sortButton = new JButton("OPEN NOTEREADER");
		sortButton.setFont(sortButton.getFont().deriveFont(Font.BOLD));

		sortButton.addActionListener(ev -> {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new NoteReaderGui().setVisible(true);
				}
			});
		});

		JButton TTSButton = new JButton("OPEN TTSCONVERTER");
		TTSButton.setFont(TTSButton.getFont().deriveFont(Font.BOLD));

		TTSButton.addActionListener(ev -> {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new TTSGui().setVisible(true);
				}
			});
		});

		JButton spellingButton = new JButton("OPEN SPELLCHECK");
		spellingButton.setFont(spellingButton.getFont().deriveFont(Font.BOLD));

		spellingButton.addActionListener(ev -> {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new SpellCheckGui().setVisible(true);
				}
			});
		});

		JButton translatorButton = new JButton("OPEN TRANSLATOR");
		translatorButton.setFont(translatorButton.getFont().deriveFont(Font.BOLD));

		translatorButton.addActionListener(ev -> {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new TranslationGui().setVisible(true);
				}
			});
		});

		JButton calendarButton = new JButton("OPEN CALENDAR");
		calendarButton.setFont(calendarButton.getFont().deriveFont(Font.BOLD));

		calendarButton.addActionListener(ev -> {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new Calendar().setVisible(true);
				}
			});
		});

		JButton paintButton = new JButton("OPEN PAINT");
		paintButton.setFont(calendarButton.getFont().deriveFont(Font.BOLD));

		paintButton.addActionListener(ev -> {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new PaintGui().setVisible(true);
				}
			});
		});

		// Setting JPanel details
		JPanel buttonPanel = new JPanel();
		// cazaPanel uses nullLayout to move noteButton to specific position
		// because borderLayout does not support specific positioning
		JPanel cazaPanel = new JPanel();
		cazaPanel.setLayout(null);

		// Use of GridLayout for simplicity in setting buttons to desired position and
		// size
		JPanel box = new JPanel(new GridLayout(3, 2));

		cazaPanel.add(noteButton);
		box.add(paintButton);
		box.add(sortButton);
		box.add(TTSButton);
		box.add(spellingButton);
		box.add(translatorButton);
		box.add(calendarButton);

		background.add(buttonPanel, BorderLayout.SOUTH);
		background.add(cazaPanel);
		buttonPanel.add(box);
		cazaPanel.setOpaque(false);
		buttonPanel.setOpaque(false);
		box.setBackground(new Color(213, 134, 145, 123));

		// Setting exit dialog message
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				JOptionPane.showMessageDialog(null, "Thank you for using CAZAPro and have a wonderful day!", "CAZAPro",
						JOptionPane.CLOSED_OPTION, null);
				dispose();
			}
		});

	}
}
