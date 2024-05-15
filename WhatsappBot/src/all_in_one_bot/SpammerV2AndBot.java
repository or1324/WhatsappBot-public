package all_in_one_bot;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.firebase.database.FirebaseDatabase;

public class SpammerV2AndBot {

	// There are secret commands named !convert_image_to_ariel_emojis and
	// !convert_image_to_grandma_emojis

	static boolean isBirthday;
	public static String birthdayName;
	static String birthdayEnglishName;
	static LinkedList<String> birthdayJokes;
	static HashMap<String, String> birthdaySongs;
	static Thread t;
	static HashMap<String, String> songs;
	static String lang = "";
	static boolean isV2 = false;
	static String[] adminNames;
	static final String langSeperator = "▢";
	static String[] jokes;
	static String[] topics;
	static String[] bible;
	static String[] quotes;
	static String credentialPath = "C:\\key\\arielimagetoemojis-firebase-adminsdk-79fw0-8a812ba85b.json";
	static String credentialPathLinux = "/home/or/key/arielimagetoemojis-firebase-adminsdk-79fw0-8a812ba85b.json";
	static FirebaseDatabase database;
	final static String[] commands = { "!random_quote", "!random_song", "!specific_song {song}", "!translate {text}",
			"!translate_hebrew {text}", "!random_joke", "!שם_תנכי", "!laugh", "!events_at {date}",
			"!happened_at {date}", "!born_at {date}", "!died_at {date}", "!events_today", "!happened_today",
			"!born_today", "!died_today", "!write {hebrew text} {emoji}", "!list_songs", "!help", "!random_topic", "!make_shemshon_say {text}" };
	final static String[] birthdayCommands = { "!random_birthday_song", "!specific_birthday_song", "!random_birthday_joke",
			"!list_birthday_songs"};

	public SpammerV2AndBot() throws Exception {
		Map<String, String> newenv = new HashMap<>();
		String osname = System.getProperty("os.name");
		if (osname.startsWith("Linux")) {
			newenv.put("GOOGLE_APPLICATION_CREDENTIALS", credentialPathLinux);
		} else if (osname.startsWith("Windows")) {
			newenv.put("GOOGLE_APPLICATION_CREDENTIALS", credentialPath);
		}
		setEnv(newenv);
		MessageSearch.driver.get("https://web.whatsapp.com");
		WebDriverWait wait = new WebDriverWait(MessageSearch.driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"side\"]/div[1]/div/label")));
		MessageSearch.moveTo("שמשון הבוט");
		Initialize.firebase();
		Graphics.createGraphics();
		Initialize.addSongs();
		Initialize.addBible();
		Initialize.addJokes();
		Initialize.addTopics();
		Initialize.addQuotes();
		Initialize.setWords();
		Initialize.sceduleBirthdayFile();
		Initialize.addBirthdaySongs();
		Initialize.addBirthdayJokes();
		MessageSearch.searchMessages();
		Graphics.listenToGraphicEvents();
	}

	public static void randomBirthdayJoke() {
		if (!MessageSearch.isstop) {
			Collections.shuffle(birthdayJokes);
			sendText(birthdayJokes.getFirst());
		}
	}

	public static void convertImageToArielEmojis() {
		sendText("מצאתם פקודה סודית! כל הכבוד! עכשיו תשלחו תמונה והבוט ימיר אותה לאימוג'ים של אריאל.");
		ImageToEmojis.waitingForImage = true;
		ImageToEmojis.isGrandma = true;
	}

	public static void convertImageToGrandmaEmojis() {
		sendText("מצאתם פקודה סודית! כל הכבוד! עכשיו תשלחו תמונה והבוט ימיר אותה לאימוג'ים של סבתא.");
	}

	public static void randomBible() {
		Random r = new Random();
		int bibleNum = r.nextInt(bible.length);
		String bibleName = "*שם תנכי:*\n\n" + bible[bibleNum];
		sendText(bibleName);
	}

	public static void randomJoke() {
		Random r = new Random();
		int jokeNum = r.nextInt(jokes.length);
		String joke = "*בדיחה:*\n\n" + jokes[jokeNum];
		sendText(joke);
	}

	public static void randomQuote() {
		Random r = new Random();
		int quoteNum = r.nextInt(quotes.length);
		String quote = "*ציטוט:*\n\n" + quotes[quoteNum];
		sendText(quote);
	}

	public static void switchLangMethod() {
		isV2 = !isV2;
		if (isV2)
			sendText("עכשיו השפה בנויה בשיטה השנייה");
		else
			sendText("עכשיו השפה בנויה בשיטה הראשונה");
	}

	// this function allows the user to send hebrew chars as emojis.
	public static void write(String hebrewString, String emoji) {
		String birthdayString = HebrewTyper.execute(hebrewString, emoji);
		sendText(birthdayString);
	}

	public static void help() {
		String help = "שלום, אני שמשון.\nאם מישהו יכתוב פה הודעה שהתוכן שלה הוא אחת מן הפקודות (טקסט) שמופיעות פה למטה, אני אציית לה באופן אוטומטי :)";
		help += "\n\n";
		help += "*אלו הפקודות שאתם יכולים להשתמש בהן:*\n\n";
		for (String s : commands) {
			help += s + "\n";
		}
		if (isBirthday) {
			for (String s : birthdayCommands) {
				help += s + "\n";
			}
		}
		help += "\n";
		help += "*פקודות למנהלים:*\n\n";
		help += "!stop_bot\n";
		help += "!start_bot\n";
		help += "!create_language {text except " + langSeperator + " and -}\n";
		help += "!switch_language_method\n";
		help += "\n";
		help += "*המנהלים:*\n\n";
		if (adminNames != null)
			for (String s : adminNames) {
				help += s + "\n";
			}
		help += "\n";
		help += "*פקודות רק לאור:*\n\n";
		help += "!move_to {chatName}\n";
		help += "\n\n";
		help += "*הסבר על השפה:*\n\n";
		help += "בעזרת הבוט אפשר ליצור שפה חדשה שמתלבשת על עברית. ישנן 2 אופציות שבעזרתן אפשר ליצור את השפה. האופציה הראשונה היא שלכל אות בעברית יהיה צירוף אותיות בשפה החדשה שמייצג אותה. הצירוף נוצר לפי הערך הגימטרי של כל אות בעברית ולפי הסדר של האותיות בשפה החדשה. כל מילה בשפה החדשה (אות בשפה העברית) מופרדת בעזרת הסימן "
				+ langSeperator + " וכל גוש מילים בשפה החדשה (מילה בשפה העברית) מופרד על ידי מקף.\n";
		help += "האופציה השנייה (והמגניבה יותר) מבוססת על הערך הגימטרי של מילים שלמות במקום של אותיות. השיטה בה היא להמיר כל מילה לערך עשרוני (בעזרת גימטריה) ואז למצוא את כל המילים בעברית שיש להן את אותו הערך ולהראות את כל האופציות הקיימות למשפט ששלחתם.";
		help += "\n\n";
		help += "הבוט נוצר על ידי אור נבט";
		sendText(help);

	}

	public static void list() {
		String str = "*Songs:*\n\n";
		for (String s : songs.keySet()) {
			str += s + "\n";
		}
		sendText(str);
	}
	
	public static void listBirthdaySongs() {
		String str = "*Birthday Songs:*\n\n";
		for (String s : birthdaySongs.keySet()) {
			str += s + "\n";
		}
		sendText(str);
	}

	public static void sendText(String text) {
		text = replaceRegex(text);
		if (text.length() > 65536) {
			sendText(
					"*שגיאה*\n\nהתגובה שלי להודעה שלכם ארוכה מדי ולכן היא לא יכולה להישלח דרך וואטסאפ :(");
		} else {
			try {
				WebElement element = MessageSearch.driver.findElement(By.xpath(MessageSearch.textBoxPath));
				((JavascriptExecutor) MessageSearch.driver).executeScript("arguments[0].innerHTML = '" + text + "'",
						element);
				element.sendKeys(".");
				element.sendKeys(Keys.BACK_SPACE);
			} catch (Exception e) {
				e.printStackTrace();
			}
			MessageSearch.driver.findElement(By.xpath(MessageSearch.sendMessageButtonPath)).click();
		}
	}

	public static void sendSong(String name, boolean isBirthday) {
		try {
			String song = null;
			if (isBirthday) {
				if (birthdaySongs.containsKey(name)) {
					song = birthdaySongs.get(name);
				}
			} else if (songs.containsKey(name)) {
				song = songs.get(name);
			}
			if (song != null) {
				String str = "*" + name + ":*" + "\n" + song;
				sendText(str);
				String osname = System.getProperty("os.name");
				File file = null;
				if (osname.startsWith("Linux")) {
					file = new File(MessageSearch.linuxBotSongsFolderPath + name + ".wav");
				} else if (osname.startsWith("Windows")) {
					file = new File(MessageSearch.windowsBotSongsFolderPath + name + ".wav");
				}
				sendFile(file);
			}
		} catch (

		Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	public static void spam() {
		String[] lines = Graphics.text.getText().split("\n");
		int end = Integer.parseInt(Graphics.numOfSpams.getText());
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (t.isAlive())
					;
				for (int i = 1; i <= end; i++) {
					if (Graphics.isnumbers.isSelected()) {
						MessageSearch.driver.findElement(By.xpath(MessageSearch.textBoxPath)).sendKeys(i + "");
						MessageSearch.driver.findElement(By.xpath(MessageSearch.sendMessageButtonPath)).click();
					} else {
						for (int f = 0; f < lines.length; f++) {
							if (lines[f].equals(""))
								continue;
							MessageSearch.driver.findElement(By.xpath(MessageSearch.textBoxPath)).sendKeys(lines[f]);
							MessageSearch.driver.findElement(By.xpath(MessageSearch.sendMessageButtonPath)).click();
						}
					}
				}
			}
		}).start();
	}

	public static void sendRandomSong() {
		Random r = new Random();
		int rand = r.nextInt(songs.size());
		String name = "";
		int l = 0;
		for (String s : songs.keySet()) {
			if (l == rand)
				name = s;
			l++;
		}
		sendSong(name, false);
	}

	public static void sendRandomBirthdaySong() {
		Random r = new Random();
		int rand = r.nextInt(birthdaySongs.size());
		String name = "";
		int l = 0;
		for (String s : birthdaySongs.keySet()) {
			if (l == rand)
				name = s;
			l++;
		}
		sendSong(name, true);
	}

	public static boolean isAdmin(String name) {
		for (String na : adminNames) {
			if (name.equals(na))
				return true;
		}
		return false;
	}

	public static void happenedAt(String day) {
		try {
			String text = WikipediaDays.getListByTitleIdAndDay("אירועים_היסטוריים_ביום_זה", day);
			text = "*אירועים היסטוריים שקרו היום*:\n\n\n" + text;
			sendText(text);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void bornAt(String day) {
		try {
			String text = WikipediaDays.getListByTitleIdAndDay("נולדו", day);
			text = "*אנשים שנולדו היום*:\n\n\n" + text;
			sendText(text);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void diedAt(String day) {
		try {
			String text = WikipediaDays.getListByTitleIdAndDay("נפטרו", day);
			text = "*אנשים שנפטרו היום*:\n\n\n" + text;
			sendText(text);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void eventsAt(String day) {
		try {
			String text = WikipediaDays.getListByTitleIdAndDay("חגים_ואירועים_החלים_ביום_זה", day);
			text = "*חגים ואירועים החלים היום*:\n\n\n" + text;
			sendText(text);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void happenedToday() {
		String text = WikipediaDays.getTodayListByTitleId("אירועים_היסטוריים_ביום_זה");
		text = "*אירועים היסטוריים שקרו היום*:\n\n\n" + text;
		sendText(text);

	}

	public static void bornToday() {
		String text = WikipediaDays.getTodayListByTitleId("נולדו");
		text = "*אנשים שנולדו היום*:\n\n\n" + text;
		sendText(text);

	}

	public static void diedToday() {
		String text = WikipediaDays.getTodayListByTitleId("נפטרו");
		text = "*אנשים שנפטרו היום*:\n\n\n" + text;
		sendText(text);

	}

	public static void eventsToday() {
		String text = WikipediaDays.getTodayListByTitleId("חגים_ואירועים_החלים_ביום_זה");
		text = "*חגים ואירועים החלים היום*:\n\n\n" + text;
		sendText(text);

	}

	public static void randomTopic() {
		Random r = new Random();
		int topicNum = r.nextInt(topics.length);
		String topic = "*הנושא שנבחר הוא:*\n\n" + topics[topicNum];
		sendText(topic);

	}

	public static void createLanguage(String text) {
		LanguageCalculation.createLanguage(text);
		sendText("*תגובה:*\n\nהשפה נוצרה בהצלחה :)\n\n*האותיות בה:*\n\n" + lang);
	}

	public static void translate(String text) {
		if (lang.equals("")) {
			sendText("*תגובה:*\n\nשכחת ליצור שפה...");
		} else {
			if (isV2)
				sendText("*המשפט בעברית:*\n\n" + LanguageCalculation.translateV2(text));
			else
				sendText("*המשפט בעברית:*\n\n" + LanguageCalculation.translate(text));
		}
	}

	public static void translateHebrew(String text) {
		if (lang.equals("")) {
			sendText("*תגובה:*\n\nשכחת ליצור שפה...");
		} else {
			if (isV2)
				sendText("*המשפט בשפה החדשה:*\n\n" + LanguageCalculation.translateHebrewV2(text));
			else
				sendText("*המשפט בשפה החדשה:*\n\n" + LanguageCalculation.translateHebrew(text));
		}
	}

	public static void laugh() {
		for (int i = 0; i < 5; i++) {
			sendText("יאח יאח יאח 🤣🤣🤣🤣🤣🤣");
		}
		File file = null;
		String osname = System.getProperty("os.name");
		if (osname.startsWith("Linux")) {
			file = new File("/home/BotSongs/" + "Laugh.wav");
		} else if (osname.startsWith("Windows")) {
			file = new File("C:/BotSongs/" + "Laugh.wav");
		}
		sendFile(file);
	}

	public static String replaceRegex(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '\n') {
				String temp = str.substring(0, i);
				str = temp + '\\' + 'n' + str.substring(i + 1);
				i++;
			} else if (str.charAt(i) == '\r') {
				String temp = str.substring(0, i);
				str = temp + '\\' + 'r' + str.substring(i + 1);
				i++;
			} else if (str.charAt(i) == '\t') {
				String temp = str.substring(0, i);
				str = temp + '\\' + 't' + str.substring(i + 1);
				i++;
			} else if (str.charAt(i) == '\b') {
				String temp = str.substring(0, i);
				str = temp + '\\' + 'b' + str.substring(i + 1);
				i++;
			} else if (str.charAt(i) == '\f') {
				String temp = str.substring(0, i);
				str = temp + '\\' + 'f' + str.substring(i + 1);
				i++;
			} else if (str.charAt(i) == '\\') {
				String temp = str.substring(0, i);
				str = temp + '\\' + '\\' + str.substring(i + 1);
				i++;
			} else if (str.charAt(i) == '\'') {
				String temp = str.substring(0, i);
				str = temp + '\\' + '\'' + str.substring(i + 1);
				i++;
			} else if (str.charAt(i) == '\"') {
				String temp = str.substring(0, i);
				str = temp + '\\' + '\"' + str.substring(i + 1);
				i++;
			}
		}
		return str;
	}

	protected static void setEnv(Map<String, String> newenv) throws Exception {
		try {
			Class<?> processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment");
			Field theEnvironmentField = processEnvironmentClass.getDeclaredField("theEnvironment");
			theEnvironmentField.setAccessible(true);
			Map<String, String> env = (Map<String, String>) theEnvironmentField.get(null);
			env.putAll(newenv);
			Field theCaseInsensitiveEnvironmentField = processEnvironmentClass
					.getDeclaredField("theCaseInsensitiveEnvironment");
			theCaseInsensitiveEnvironmentField.setAccessible(true);
			Map<String, String> cienv = (Map<String, String>) theCaseInsensitiveEnvironmentField.get(null);
			cienv.putAll(newenv);
		} catch (NoSuchFieldException e) {
			Class[] classes = Collections.class.getDeclaredClasses();
			Map<String, String> env = System.getenv();
			for (Class cl : classes) {
				if ("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
					Field field = cl.getDeclaredField("m");
					field.setAccessible(true);
					Object obj = field.get(env);
					Map<String, String> map = (Map<String, String>) obj;
					map.clear();
					map.putAll(newenv);
				}
			}
		}
	}

	public static void sendFile(File file) {
		MessageSearch.driver.findElement(By.xpath(MessageSearch.sendFilePath)).click();
		WebElement fileInput = MessageSearch.driver.findElement(By.xpath(MessageSearch.fileTypeDocumentPath));
		fileInput.sendKeys(file.getAbsolutePath());
		WebDriverWait wait = new WebDriverWait(MessageSearch.driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MessageSearch.sendFileButtonPath)));
		MessageSearch.driver.findElement(By.xpath(MessageSearch.sendFileButtonPath)).click();
		MessageSearch.driver.switchTo().window(MessageSearch.driver.getWindowHandle());
	}

}
