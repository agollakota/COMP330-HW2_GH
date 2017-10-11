import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/**

 *
 */


/**This function sets all private attributes
 *
 *  b1 set as JButton
 *  l1 set as JLabel
 *  reg set as JButton but is not used
 *
 */

public class Gui extends JFrame {
    private JButton reg;

    private JButton b1;
    private JLabel l1;
    /**
     * This function creates the gif image taken from the file folder
     * and b1 is a button created that says PLAY, but has an empty side label
     * handlerclass is what happens when the button is pressed
     * If the user exits the gui then the override method will show up and print out message instead of just closing
     */

    public Gui() {
        super("CAZA");



        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(null, "Have a great day!");
                dispose();


            }
        });


        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("giphy.gif")));
        setLayout(new FlowLayout());
        setBackground(Color.BLACK);
        l1 = new JLabel("");
        b1 = new JButton("OPEN NOTE PAD");

        b1.setForeground(Color.white);
        b1.setBackground(Color.BLACK);





        add(b1);

        HandlerClass handler = new HandlerClass();

        b1.addActionListener(handler);





    }

    /**this function starts the notepad when the the play button is pressed
     *
     *
     */

    public  void startGame(){
        NotePad note = new NotePad();

    }





    /** This function takes from the Interface ActionListener which handles the button function.
     * Sub class of Gui
     *
     */

    public class HandlerClass implements ActionListener{



        public void actionPerformed(ActionEvent event){
            dispose();

            startGame();





        }
        /** When the button is pressed the window will close and go to the startGame function
         * @param event
         *
         */





    }}