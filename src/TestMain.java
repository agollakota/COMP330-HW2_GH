
import java.util.Properties;

import javax.swing.*;

import com.jtattoo.plaf.noire.NoireLookAndFeel;

public class TestMain {

	public static void main(String[] args) {
		try {
			Properties props = new Properties();
			props.put("logoString", "");
			NoireLookAndFeel.setCurrentTheme(props);
			UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
		} catch (Exception e) {
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Gui().setVisible(true);
			}
		});
	}
}
