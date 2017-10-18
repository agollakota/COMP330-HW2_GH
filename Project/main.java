import java.io.IOException;
import java.io.FileInputStream;
import java.util.*;
import java.io.*;




//JUnit Testing ref: http://www.vogella.com/tutorials/JUnit/article.html

public class main{
	
	
	
	public static void main(String[] args) throws IOException{
	    
    
Calendar c = new Calendar();    
c.start();

DrawArea d = new DrawArea();
d.start();

//Did not call GUI.java yet

NotePad np = new NotePad();
np.start();

NoteReader nr = new NoteReader();
nr.start();

SpellChecker sc = new SpellChecker();
sc.start();

//Also did not call SpellCheckGui.java yet

SwingPaint sp = new SwingPaint();
sp.start();

TestMain tm = new TestMain();
tm.start();

Translation t = new Translation ();
t.start();

//need to call TranslationGUI

Translator tr = new Translator();
tr.start();

TTSConvert tts = new TTSConvert();
tts.start();

//need to call TTS GUI


	    
	    
	    
	    
	    
	    
	}}