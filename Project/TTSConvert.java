
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.AudioPlayer;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;
import javax.sound.sampled.AudioFileFormat.Type;

//will need external libraries and pom.xml file 
public class TTSConvert {

	private static final String voiceName = "kevin16";
	private final Voice voice;
	AudioPlayer audioPlayer = null;
	
	
	public TTSConvert() {
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		VoiceManager vm = VoiceManager.getInstance();		
		voice = vm.getVoice(voiceName);
		voice.allocate();
		
	}
	
	public void audio(File file) {
		
		if(file != null) {
			
			FileInputStream inputStream = null;
			try {
			
				inputStream = new FileInputStream(file);
				//revise code to allow user to enter file path and then append what they want to name their file to it
				audioPlayer = new SingleFileAudioPlayer("C:\\Users\\Chris\\Desktop\\hello",Type.WAVE);
				voice.setAudioPlayer(audioPlayer);
				voice.speak(inputStream);
				voice.deallocate();
				audioPlayer.close();
			} catch (FileNotFoundException e) {
				
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
}

