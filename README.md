# **CAZAPro(TM)**
-----------------------
CAZAPro is a note-taking tool that simplifies the lives of multitasking, busy individuals. 

*Look and Feel : Noire (JTattoo)*

*Included Features : Paint, Translator, Text-to-Speech, Calendar, SpellCheck, CAZAPad*

## **Installation**
--------------------------------
1) Ensure JDK 7 is installed on your device
2) Download the JAR file from the repository 
3) Open the file from your downloads folder

## **Features**
--------------------------------
### Paint

- Choose from a wide variety of colors to create artwork limited only to your own imagination
- Use the "+" and "-" signs to alternate between a bigger and smaller brush size
- Use the eraser icon to erase any mistakes on the canvas
- Use the sweeper icon to clear the canvas completely
- Open/Save supported for .png files

### Translator

- Translate text from one language to another from a number of supported languages from the Google Translator API
- Ensure that the correct initial and final language formats are correctly specified
- Open/Save supported for .txt files

### Text-to-Speech

- Dictate any text files aloud with a supported voice provided by the FreeTTS library
- Save text-to-speech audio files to the .mp3 format supported by JAVE encoding

### Calendar

- Specify a directory for storage of your memos
- Calendar allows users to choose a date and save memos on that date
- Calendar supported by the Jcalendar library
- Upon specification of the directory and the presence of a memo for the current date, a notice will occur

### SpellCheck

- Enter text and check for any spelling errors supported by the JOrtho library
- Enable/Disable real-time spellchecking (underlining for errors)
- Open/Save supported for .txt files

### CazaPad

- Enter text onto the CazaPad for any task such as making a to-do list or writing a report
- (Real-time) spellchecking is available for CazaPad
- Open/Save supported for .txt files

### Additional - [NoteReader]

- Previous Project 1 Feature that allows for searching/sorting through a directory of text files
- A directory containing specifically formatted text files must first be set by the User
- "NotesByEntry"/"SortByEntry" require input strings for outputting the necessary data (unless user is searching for space occurrences)
- The rest of the methods only require a specified directory and can run without input


## **Technologies**
--------------------------------
### Tools

[Free TTS](https://freetts.sourceforge.io/)

[Google Translate API](https://cloud.google.com/translate/)

[JOrtho Spell Checker](http://jortho.sourceforge.net/)

### Design

[JTattoo](http://www.jtattoo.net/)

[JCalendar](https://toedter.com/jcalendar/)

### Testing

[JUnit Testing](http://junit.org/)

[Groovy Testing](http://groovy-lang.org/testing.html) 

### Encoding

[Jave](http://www.sauronsoftware.it/projects/jave/)

## **Addendum**
--------------------------------
### Adapted Code
[Google Translate API workaround](http://archana-testing.blogspot.com/2016/02/calling-google-translation-api-in-java.html)

[Paint GUI Example](http://forum.codecall.net/topic/58137-java-mini-paint-program/)

### Support
[Google Translate API ISO Code List](http://archana-testing.blogspot.com/2016/02/calling-google-translation-api-in-java.html)

### Most Annoying Bug Award

[File.delete() issues](https://stackoverflow.com/questions/991489/file-delete-returns-false-even-though-file-exists-file-canread-file-canw/21522963#21522963) - Time Expenditure: 4.5 hours  :(
