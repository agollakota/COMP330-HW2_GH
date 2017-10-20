import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class CalenderTest extends GroovyTestCase {
    void testCalendarcomponent() {
        CalenderTest CalenderTest = new CalenderTest();
        CalenderTest.testClear();
        CalenderTest.testBlack();
        assertEquals(CalenderTest.propertyChange(),"Propertychangetest");
      

    }

   //initGUI is going to be tested in main??
   
   
   
   @Test
			public void propertyChange() {
			     System.out.println("testing propertychange");
			}
			

}
