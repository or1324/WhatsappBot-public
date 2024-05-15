package all_in_one_bot;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Scanner;

public class WikipediaDays {

	public static String getTodayListByTitleId(String id) {
		String list = "";
		String todayDateName = getTodayDateName();
		boolean isInDate = false;
		InputStream is = WikipediaDays.class.getClassLoader().getResourceAsStream(id + ".txt");
		Scanner sc = new Scanner(is);
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (isInDate) {
				if (line.charAt(0) == '+')
					break;
				else
					list+=line+"\n";
			} else {
				if (line.charAt(0) == '+')
					if (line.endsWith(todayDateName))
						isInDate = true;
			}
		}
		sc.close();
		try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list.equals(""))
			return "לא נמצאו תוצאות במאגר הנתונים";
		else
			return list;
	}

	public static String getListByTitleIdAndDay(String id, String day) throws Exception {
		String list = "";
		boolean isInDate = false;
		day = day.replace(" ", "_");
		InputStream is = WikipediaDays.class.getClassLoader().getResourceAsStream(id + ".txt");
		InputStreamReader isr = new InputStreamReader(is, Charset.forName("UTF-8"));
		Scanner sc = new Scanner(isr);
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (isInDate) {
				if (line.charAt(0) == '+')
					break;
				else
					list+=line+"\n";
			} else {
				if (line.charAt(0) == '+')
					if (line.endsWith(day))
						isInDate = true;
			}
		}
		sc.close();
		is.close();
		if (list.equals(""))
			return "לא נמצאו תוצאות במאגר הנתונים";
		else
			return list;
	}

	private static String getTodayDateName() {
		Calendar c = Calendar.getInstance();
		int d = c.get(Calendar.DAY_OF_MONTH);
		int m = c.get(Calendar.MONTH) + 1;
		String month = "";
		switch (m) {
		case 1:
			month = "ינואר";
			break;

		case 2:
			month = "פברואר";
			break;

		case 3:
			month = "מרץ";
			break;

		case 4:
			month = "אפריל";
			break;

		case 5:
			month = "מאי";
			break;

		case 6:
			month = "יוני";

			break;

		case 7:
			month = "יולי";
			break;

		case 8:
			month = "אוגוסט";
			break;

		case 9:
			month = "ספטמבר";
			break;

		case 10:
			month = "אוקטובר";
			break;

		case 11:
			month = "נובמבר";
			break;

		case 12:
			month = "דצמבר";
			break;
		}
		try {
			return d + "_ב" + month;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
