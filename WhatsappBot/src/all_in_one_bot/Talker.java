package all_in_one_bot;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechSynthesisResult;
import com.microsoft.cognitiveservices.speech.SpeechSynthesizer;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;

public class Talker {
	static String lang = "he-IL";
	static String voice = "he-IL-Asaf";
	public static void makeFile(String minutes) throws IOException, URISyntaxException, InterruptedException, ExecutionException, UnsupportedAudioFileException, LineUnavailableException {
        try {   
			voice = "he-IL-Asaf";
			lang = "he-IL";
		// Replace below with your own subscription key
            String speechSubscriptionKey = "44e99845115a4214811acc60afba4e21";

            // Replace below with your own region identifier from here: https://aka.ms/speech/sdkregion
            String serviceRegion = "northeurope";

            // Replace below with your own filename.
            File file = new File("c:\\Audio");
            file.mkdir();
            SpeechConfig config = SpeechConfig.fromSubscription(speechSubscriptionKey, serviceRegion);
            assert(config != null);
            config.setSpeechSynthesisLanguage(lang);
            config.setSpeechSynthesisVoiceName(voice);
            AudioConfig audioOutput = AudioConfig.fromWavFileInput("c:\\Audio\\Happy Birthday.wav");
            assert(audioOutput != null);

            SpeechSynthesizer synth = new SpeechSynthesizer(config, audioOutput);
            assert(synth != null);
            Future<SpeechSynthesisResult> task = synth.SpeakTextAsync("שלום, אני הבות של אור ני ני ני ורציתי לאחל ל "+SpammerV2AndBot.birthdayName+" מזל טוב לכבוד היום הולדת. יום ההולדת כבר נמשך "+minutes+" דקות.");
            assert(task != null);
            SpeechSynthesisResult result = task.get();
            result.close();
            config.close();
            audioOutput.close();
            synth.close();
        }
        catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void makeSong() throws IOException, URISyntaxException, InterruptedException, ExecutionException, UnsupportedAudioFileException, LineUnavailableException {
        try {
			voice = "he-IL-Asaf";
			lang = "he-IL";
        	String song = "אחת, שתיים, אחת, שתים, שלוש\r\n" + 
    				"למי יש יום הולדת?.\r\n" + 
    				"\r\n" + 
    				"אולי יש לו חתול? - לא ולא,\r\n" + 
    				"אולי יש לו אווירון? - לא ולא,\r\n" + 
    				"אולי יש לו כובע? – לא ולא,\r\n" + 
    				"\r\n" + 
    				"למי יש יום הולדת מי כאן לי עוזר\r\n" + 
    				"למי יש יום הולדת,\r\n" + 
    				"למי שיש לו זר - על הראש.\r\n" + 
    				"\r\n" + 
    				"ל"+SpammerV2AndBot.birthdayName+" יום הולדת לו כולם שרים,\r\n" + 
    				"ל"+SpammerV2AndBot.birthdayName+" יום הולדת, לו כפיים מוחאים.\r\n" + 
    				"\r\n" + 
    				"אחת, שתיים, אחת, שתים, שלוש\r\n" + 
    				"למי יש יום הולדת?.\r\n" + 
    				"\r\n" + 
    				"אולי יש לה שרשרת? - לא ולא,\r\n" + 
    				"אולי יש לה תיק? - לא ולא,\r\n" + 
    				"אולי יש לה מטפחת? - לא ולא,\r\n" + 
    				"\r\n" + 
    				"למי יש יום הולדת מי כאן לי עוזר\r\n" + 
    				"למי יש יום הולדת,\r\n" + 
    				"למי שיש לו זר - על הראש.\r\n" + 
    				"\r\n" + 
    				"ל"+SpammerV2AndBot.birthdayName+" יום הולדת לה כולם שרים,\r\n" + 
    				"ל"+SpammerV2AndBot.birthdayName+" יום הולדת, לה כפיים מוחאים.";
        	// Replace below with your own subscription key
            String speechSubscriptionKey = "44e99845115a4214811acc60afba4e21";

            // Replace below with your own region identifier from here: https://aka.ms/speech/sdkregion
            String serviceRegion = "northeurope";

            // Replace below with your own filename.
            File file = new File("c:\\Audio");
            file.mkdir();
            SpeechConfig config = SpeechConfig.fromSubscription(speechSubscriptionKey, serviceRegion);
            assert(config != null);
            config.setSpeechSynthesisLanguage(lang);
            config.setSpeechSynthesisVoiceName(voice);
            AudioConfig audioOutput = AudioConfig.fromWavFileInput("c:\\BotSongs\\אחת שתים שלוש יום הולדת.wav");
            assert(audioOutput != null);

            SpeechSynthesizer synth = new SpeechSynthesizer(config, audioOutput);
            assert(synth != null);
            Future<SpeechSynthesisResult> task = synth.SpeakTextAsync(song);
            assert(task != null);
            SpeechSynthesisResult result = task.get();
            result.close();
            config.close();
            audioOutput.close();
            synth.close();
        }
        catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void makeSong2() throws IOException, URISyntaxException, InterruptedException, ExecutionException, UnsupportedAudioFileException, LineUnavailableException {
        try {
        	lang = "en-US";
        	voice = "en-US-AriaRUS";
        	String song = "Happy Birthday to You\r\n" + 
        			"Happy Birthday to You\r\n" + 
        			"Happy Birthday Dear "+SpammerV2AndBot.birthdayEnglishName+"\r\n" + 
        			"Happy Birthday to You.\r\n" + 
        			"\r\n" + 
        			"From good friends and true,\r\n" + 
        			"From old friends and new,\r\n" + 
        			"May good luck go with you,\r\n" + 
        			"And happiness too.";
        	// Replace below with your own subscription key
            String speechSubscriptionKey = "44e99845115a4214811acc60afba4e21";

            // Replace below with your own region identifier from here: https://aka.ms/speech/sdkregion
            String serviceRegion = "northeurope";
            // Replace below with your own filename.
            File file = new File("c:\\Audio");
            file.mkdir();
            SpeechConfig config = SpeechConfig.fromSubscription(speechSubscriptionKey, serviceRegion);
            assert(config != null);
            config.setSpeechSynthesisLanguage(lang);
            config.setSpeechSynthesisVoiceName(voice);
            AudioConfig audioOutput = AudioConfig.fromWavFileInput("c:\\BotSongs\\traditional happy birthday.wav");
            assert(audioOutput != null);

            SpeechSynthesizer synth = new SpeechSynthesizer(config, audioOutput);
            assert(synth != null);
            Future<SpeechSynthesisResult> task = synth.SpeakTextAsync(song);
            assert(task != null);
            SpeechSynthesisResult result = task.get();
            result.close();
            config.close();
            audioOutput.close();
            synth.close();
        }
        catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isHebrew(String s) {
		for(int i = 0; i<s.length(); i++) {
			if(s.charAt(i) == 'א'|| s.charAt(i) == 'ב'|| s.charAt(i) == 'ג'|| s.charAt(i) == 'ד'|| s.charAt(i) == 'ה'|| s.charAt(i) == 'ו'|| s.charAt(i) == 'ז'|| s.charAt(i) == 'ח'|| s.charAt(i) == 'ט'|| s.charAt(i) == 'י'|| s.charAt(i) == 'כ'|| s.charAt(i) == 'ל'|| s.charAt(i) == 'מ'|| s.charAt(i) == 'נ'|| s.charAt(i) == 'ס'|| s.charAt(i) == 'ע'|| s.charAt(i) == 'פ'|| s.charAt(i) == 'צ'|| s.charAt(i) == 'ק'|| s.charAt(i) == 'ר'|| s.charAt(i) == 'ש'|| s.charAt(i) == 'ת') {
				return true;
			}
		}
		return false;
	}
}
