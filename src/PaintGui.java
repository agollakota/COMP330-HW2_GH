
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

//Adapted from - http://forum.codecall.net/topic/58137-java-mini-paint-program/

@SuppressWarnings("serial")
public class PaintGui extends JFrame {

	public PaintGui() {

		super("Paint");

		createView();

		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/octopus.png")));

		setMinimumSize(new Dimension(600, 600));

		pack();

		setLocationRelativeTo(null);

		setResizable(false);
	}

	public void createView() {

		// instantiate Paint class to call methods
		final Paint paint = new Paint();
		Container content = getContentPane();
		content.setLayout(new BorderLayout());
		content.add(paint, BorderLayout.CENTER);

		// Setting JPanel details
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(32, 68));
		panel.setMinimumSize(new Dimension(32, 68));
		panel.setMaximumSize(new Dimension(32, 68));

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
					paint.load(file);
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
				File file = fc.getSelectedFile();
				try {
					paint.save(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Operation Cancelled...");
			}
		});
		// Setting buttons for changing colors
		JButton redButton = new JButton();
		redButton.setBackground(Color.red);
		redButton.setToolTipText("Red");
		redButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paint.red();
			}

		});

		JButton blackButton = new JButton();
		blackButton.setToolTipText("Black");
		blackButton.setBackground(Color.black);
		blackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paint.black();
			}
		});

		JButton magentaButton = new JButton();
		magentaButton.setToolTipText("Magenta");
		magentaButton.setBackground(Color.magenta);
		magentaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paint.magenta();
			}
		});

		JButton blueButton = new JButton();
		blueButton.setBackground(Color.blue);
		blueButton.setToolTipText("Blue");
		blueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paint.blue();
			}
		});

		JButton greenButton = new JButton();
		greenButton.setBackground(Color.green);
		greenButton.setToolTipText("Green");
		greenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paint.green();
			}
		});

		JButton yellowButton = new JButton();
		yellowButton.setBackground(Color.yellow);
		yellowButton.setToolTipText("Yellow");
		yellowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paint.yellow();
			}
		});

		JButton grayButton = new JButton();
		grayButton.setBackground(Color.gray);
		grayButton.setToolTipText("Gray");
		grayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paint.gray();
			}
		});

		JButton pinkButton = new JButton();
		pinkButton.setBackground(Color.pink);
		pinkButton.setToolTipText("Pink");
		pinkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paint.pink();
			}
		});

		JButton cyanButton = new JButton();
		cyanButton.setBackground(Color.cyan);
		cyanButton.setToolTipText("Cyan");
		cyanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paint.cyan();
			}
		});

		JButton orangeButton = new JButton();
		orangeButton.setBackground(Color.orange);
		orangeButton.setToolTipText("Orange");
		orangeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paint.orange();
			}
		});

		// Setting png file as background for eraserButton
		ImageIcon tempIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/erasericon.png")));
		Image img = tempIcon.getImage();
		// resizes erasericon.png
		Image newimg = img.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
		ImageIcon eraseIcon = new ImageIcon(newimg);

		// get rid of border and content area in order to use erasericon.png as button
		JButton eraseButton = new JButton(new ImageIcon(eraseIcon.getImage()));
		eraseButton.setToolTipText("Click to use eraser.");
		eraseButton.setBorder(BorderFactory.createEmptyBorder());
		eraseButton.setContentAreaFilled(false);

		eraseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paint.erase();
			}
		});

		// Setting clear button for clearing screen of paint
		// Setting png file as background for clearButton
		ImageIcon fillIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/clearbutton.png")));
		Image tempImg = fillIcon.getImage();

		// resizes clearbutton.png
		Image clearImg = tempImg.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
		ImageIcon clearIcon = new ImageIcon(clearImg);

		// get rid of border and content area in order to use clearbutton.png as button
		JButton clearButton = new JButton(new ImageIcon(clearIcon.getImage()));
		clearButton.setToolTipText("Click to clear canvas.");
		clearButton.setBorder(BorderFactory.createEmptyBorder());
		clearButton.setContentAreaFilled(false);

		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paint.clear();
			}
		});

		// Setting smallerbrush button for clearing screen of paint
		// Setting png file as background for smallButton
		ImageIcon filler = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/smallerbrush.png")));
		Image holdImg = filler.getImage();

		// resizes smallerbrush.png
		Image smallImg = holdImg.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
		ImageIcon smallIcon = new ImageIcon(smallImg);

		// get rid of border and content area in order to use smallerbrush.png as button
		JButton smallButton = new JButton(new ImageIcon(smallIcon.getImage()));
		smallButton.setToolTipText("Click to make toolsize small.");
		smallButton.setBorder(BorderFactory.createEmptyBorder());
		smallButton.setContentAreaFilled(false);

		smallButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paint.setState(true);
			}
		});

		smallButton.setBorder(BorderFactory.createEmptyBorder());
		smallButton.setContentAreaFilled(false);
		smallButton.setFont(smallButton.getFont().deriveFont(Font.BOLD));

		// Setting clear button for clearing screen of paint
		// Setting png file as background for bigButton
		ImageIcon bigIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/biggerbrush.png")));
		Image bigImg = bigIcon.getImage();

		// resizes biggerbrush.png
		Image biggerImg = bigImg.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
		ImageIcon biggerIcon = new ImageIcon(biggerImg);

		// get rid of border and content area in order to use biggerbrush.png as button
		JButton bigButton = new JButton(new ImageIcon(biggerIcon.getImage()));
		bigButton.setToolTipText("Click to make toolsize big.");
		bigButton.setBorder(BorderFactory.createEmptyBorder());
		bigButton.setContentAreaFilled(false);

		bigButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paint.setState(false);
			}
		});

		// Set button sizes
		blackButton.setPreferredSize(new Dimension(16, 16));
		magentaButton.setPreferredSize(new Dimension(16, 16));
		redButton.setPreferredSize(new Dimension(16, 16));
		blueButton.setPreferredSize(new Dimension(16, 16));
		greenButton.setPreferredSize(new Dimension(16, 16));
		yellowButton.setPreferredSize(new Dimension(16, 16));
		grayButton.setPreferredSize(new Dimension(16, 16));
		pinkButton.setPreferredSize(new Dimension(16, 16));
		cyanButton.setPreferredSize(new Dimension(16, 16));
		orangeButton.setPreferredSize(new Dimension(16, 16));

		// Add to buttonPanel
		panel.add(greenButton);
		panel.add(blueButton);
		panel.add(magentaButton);
		panel.add(blackButton);
		panel.add(redButton);
		panel.add(yellowButton);
		panel.add(grayButton);
		panel.add(pinkButton);
		panel.add(cyanButton);
		panel.add(orangeButton);
		panel.add(smallButton);
		panel.add(bigButton);
		panel.add(eraseButton);
		panel.add(clearButton);

		// Add panel to left side of GUI
		content.add(panel, BorderLayout.WEST);
	}
}