import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SwingPaint {
// ADAPTED FROM http://www.ssaurel.com/blog/learn-how-to-make-a-swing-painting-and-drawing-application/


   //creating buttons for colors
    JButton clearBtn, blackBtn, blueBtn, greenBtn, redBtn, yellowBtn, whiteBtn;

  //create drawing area
   Paint drawArea;


   //adding menu but not working rn
    private MenuBar menuBar = new MenuBar(); // first, create a MenuBar item
    private Menu file = new Menu(); // our File menu
    private MenuItem openFile = new MenuItem();
    private MenuItem saveFile = new MenuItem();
    private MenuItem close = new MenuItem();
    private Menu invert=new Menu();
    private MenuItem normal = new MenuItem();
    private MenuItem inverted = new MenuItem();




    ActionListener actionListener = new ActionListener() {


        //used to choose what to do when the button is pushed
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearBtn) {
                drawArea.clear();
            } else if (e.getSource() == blackBtn) {
                drawArea.black();
            } else if (e.getSource() == blueBtn) {
                drawArea.blue();
            } else if (e.getSource() == greenBtn) {
                drawArea.green();
            } else if (e.getSource() == redBtn) {
                drawArea.red();
            } else if (e.getSource() == yellowBtn) {
                drawArea.yellow();
            }
            else if (e.getSource() == whiteBtn){
                drawArea.white();
            }
        }
    };

    public static void main(String[] args) {
        new SwingPaint().show();

    }

    public void show() {
        JFrame frame = new JFrame("CAZA Paint");





        Container content = frame.getContentPane();

        content.setLayout(new BorderLayout());
        drawArea = new Paint();


        content.add(drawArea, BorderLayout.CENTER);





        JPanel controls = new JPanel();
//labels for the button
        clearBtn = new JButton("Clear");
        clearBtn.addActionListener(actionListener);
        blackBtn = new JButton("Black");
        blackBtn.addActionListener(actionListener);
        blueBtn = new JButton("Blue");
        blueBtn.addActionListener(actionListener);
        greenBtn = new JButton("Green");
        greenBtn.addActionListener(actionListener);
        redBtn = new JButton("Red");
        redBtn.addActionListener(actionListener);
       yellowBtn= new JButton("Yellow");
        yellowBtn.addActionListener(actionListener);

       whiteBtn= new JButton("Eraser");
        whiteBtn.addActionListener(actionListener);
        // add to paint the canvas
        controls.add(greenBtn);
        controls.add(blueBtn);
        controls.add(blackBtn);
        controls.add(redBtn);
        controls.add(yellowBtn);
        controls.add(whiteBtn);
        controls.add(clearBtn);

        // add to content pane
        content.add(controls, BorderLayout.NORTH);

        frame.setSize(600, 600);
        // can close frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // show the swing paint result
        frame.setVisible(true);

        // Now you can try our Swing Paint !!! Enjoy <img draggable="false" class="emoji" alt="😀" src="https://s.w.org/images/core/emoji/2.3/svg/1f600.svg">
    }

}