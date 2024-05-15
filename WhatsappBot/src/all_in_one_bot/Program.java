package all_in_one_bot;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Program {

	static Thread t;
	final static String linuxChromedriverPath = "/home/or/Selenium/chromedriver";
	final static String windowsChromedriverPath = "C:\\Selenium\\chromedriver.exe";
	final static String windowsChromeProfilePath = "C:\\Users\\ornev\\AppData\\Local\\Google\\Chrome\\User Data";
	final static String linuxChromeProfilePath = "/home/or/.config/google-chrome";
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String osname = System.getProperty("os.name");
		if (osname.startsWith("Linux")) {
			System.setProperty("webdriver.chrome.driver", linuxChromedriverPath);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--no-sandbox");
			options.addArguments("user-data-dir="+linuxChromeProfilePath);
			options.addArguments("--disable-div-shm-usage");
			options.addArguments("--headless");
			options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3641.0 Safari/537.36");
			options.addArguments("--start-maximized");
			System.out.println("Going to open chrome driver");
			MessageSearch.driver = new ChromeDriver(options);
		} else if (osname.startsWith("Windows")) {
			System.setProperty("webdriver.chrome.driver", windowsChromedriverPath);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--no-sandbox");
			options.addArguments("user-data-dir="+windowsChromeProfilePath);
			//options.addArguments("--headless");
			options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3641.0 Safari/537.36");
			options.addArguments("--start-maximized");
			MessageSearch.driver = new ChromeDriver(options);
		}
		SpammerV2AndBot bot = new SpammerV2AndBot();
	}
}
