import javax.swing.JFrame;


public class Splash {
    /** This function creates a new Gui called splashPage
     *The EXIT ON CLOSE will close when user press the X in the corner
     * The size is 900 by 750
     * The page will be visible when started
     */

    public static void startScreen() {

        Gui splashPage = new Gui();
        splashPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        splashPage.setSize(500, 500);

        splashPage.setVisible(true);






    }




}
