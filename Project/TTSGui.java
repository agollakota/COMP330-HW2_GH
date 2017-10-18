import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TTSGui extends JFrame {

	public TTSGui() {

		// Setting details of JFrame
		super("TTSConverter");

		createView();

		setIconImage(Toolkit.getDefaultToolkit().getImage("octopus.png"));

		setSize(400, 800);

		pack();

		setLocationRelativeTo(null);

		setResizable(false);

	}

	public void createView() {

		// Setting details of JButtons
		JButton speakButton = new JButton("SPEAK");
		speakButton.putClientProperty("Quaqua.Button.style", "bevel");
		speakButton.setFont(speakButton.getFont().deriveFont(Font.BOLD));
		speakButton.addActionListener(ev -> {
			File file = new File("Sample.txt");
			TTSConvert tts = new TTSConvert();
			tts.speak(file);
		});

		JButton convertButton = new JButton("CONVERT");
		convertButton.putClientProperty("Quaqua.Button.style", "bevel");
		convertButton.setFont(convertButton.getFont().deriveFont(Font.BOLD));
		convertButton.addActionListener(ev -> {
			File file = new File("Sample.txt");
			TTSConvert tts = new TTSConvert();
			tts.audio(file);
		});

		// creating JPanel and adding JButtons
		JPanel panel = new JPanel();
		getContentPane().add(panel);

		Box box = Box.createHorizontalBox();

		box.add(speakButton);
		box.add(convertButton);

		panel.add(box);
	}
}
