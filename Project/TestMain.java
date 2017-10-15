import javax.swing.*;

public class TestMain {

	public static void main(String[] args) {
		  try 
		    { 
		        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
		    } 
		    catch(Exception e){ 
		    }
		  
		SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	new Gui().setVisible(true);
	        }
		});
	}
}
