import javax.swing.JFrame
import javax.swing.JLabel
import java.awt.Dimension

class SpellCheckGuiTest extends GroovyTestCase {
    void createWindow() {

            JFrame frame = new JFrame("Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel textLabel = new JLabel("Test",SwingConstants.CENTER);
            textLabel.setPreferredSize(new Dimension(300, 100));
            frame.getContentPane().add(textLabel, BorderLayout.CENTER);


            frame.setLocationRelativeTo(null);
            frame.pack();
            frame.setVisible(true);
    }
    void main(String ...args){
        createWindow();
    }
}
