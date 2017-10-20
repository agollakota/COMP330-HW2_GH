
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.AudioPlayer;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;

import javax.sound.sampled.AudioFileFormat.Type;

//class that converts text/txt.files to text-to-speech or .wav

public class TTSConvert {

	private static final String voiceName = "kevin16";
	private final Voice voice;
	AudioPlayer audioPlayer = null;

	public TTSConvert() {

		// setting up voice
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		VoiceManager vm = VoiceManager.getInstance();
		voice = vm.getVoice(voiceName);
		voice.allocate();

	}

	// converts text file to text to speech
	public void talk(File file) {

		if (file != null) {

			FileInputStream inputStream = null;
			try {

				inputStream = new FileInputStream(file);
				voice.speak(inputStream);
				voice.deallocate();

			} catch (FileNotFoundException e) {

				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	// converts text file to .wav and then encodes to .mp3 w/ JAVE library
	public void audio(File file, File path) {

		if (file != null) {

			FileInputStream inputStream = null;
			try {
				inputStream = new FileInputStream(file);
				audioPlayer = new SingleFileAudioPlayer(path.getPath().toString(), Type.WAVE);
				voice.setAudioPlayer(audioPlayer);
				voice.speak(inputStream);
				voice.deallocate();
				audioPlayer.drain();
				audioPlayer.reset();
				audioPlayer.close();

				File source = new File(path.getPath().toString() + ".wav");
				File target = new File(path.getPath().toString() + ".mp3");
				AudioAttributes audio = new AudioAttributes();
				audio.setCodec("libmp3lame");
				audio.setBitRate(new Integer(128000));
				audio.setChannels(new Integer(2));
				audio.setSamplingRate(new Integer(44100));
				EncodingAttributes attrs = new EncodingAttributes();
				attrs.setFormat("mp3");
				attrs.setAudioAttributes(audio);
				Encoder encoder = new Encoder();
				try {
					encoder.encode(source, target, attrs);
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (InputFormatException e) {

					e.printStackTrace();
				} catch (EncoderException e) {

					e.printStackTrace();
				}
				source.delete();
			} catch (FileNotFoundException e) {

				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
