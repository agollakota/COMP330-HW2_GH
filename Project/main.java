import java.io.IOException;
import java.io.FileInputStream;
import java.util.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

//public class MyTests {

   



//JUnit Testing ref: http://www.vogella.com/tutorials/JUnit/article.html

public class main{
	
	public static void main(String[] args) throws IOException{
	    
   //going to just call GUI.java which encorporates all of the different GUI's for the project
  
    @Test
       Calendar c = new Calendar();    //
        c.start();

        // assert statements
        //assertEquals("10 x 0 must be 0", 0, tester.multiply(10, 0));
        //assertEquals("0 x 10 must be 0", 0, tester.multiply(0, 10));
        //assertEquals("0 x 0 must be 0", 0, tester.multiply(0, 0));
    

  @Test

       DrawArea d = new DrawArea();
        d.start();


        // assert statements
        //assertEquals("10 x 0 must be 0", 0, tester.multiply(10, 0));
        //assertEquals("0 x 10 must be 0", 0, tester.multiply(0, 10));
        //assertEquals("0 x 0 must be 0", 0, tester.multiply(0, 0));
    


 



NotePad np = new NotePad();
np.start();

//NoteReader nr = new NoteReader();
//nr.start(); ---------------------> This one might not be needed as the note reader gui might be called
//directly within the GUI.java


SpellChecker sc = new SpellChecker();
sc.start();



SwingPaint sp = new SwingPaint();
sp.start();

TestMain tm = new TestMain();
tm.start();

Translation t = new Translation ();
t.start();



Translator tr = new Translator();
tr.start();

TTSConvert tts = new TTSConvert();
tts.start();




	    
	    
	    
	    
	    
	    
	}}