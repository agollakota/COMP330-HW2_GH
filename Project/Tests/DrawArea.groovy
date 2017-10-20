import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


class DrawAreaTest extends GroovyTestCase {
    void testDAcomponent() {
        DrawAreaTest DrawAreaTest = new DrawAreaTest();
        DrawAreaTest.testClear();
        DrawAreaTest.testBlack();
        assertEquals(DrawAreaTest.testBlue(),"Blue");
        assertEquals(DrawAreaTest.testGreen(),"Green");

    }

   //initGUI is going to be tested in main??
   
   
   
   @Test
    public void mousePressed(){
               System.out.println("Test");
    }

}
