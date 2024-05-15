package all_in_one_bot;

import java.util.LinkedList;

public class LanguageCalculation {

	static String[] words;

	public static void createLanguage(String text) {
		SpammerV2AndBot.lang = text.charAt(0) + "";
		for (int i = 1; i < text.length(); i++)
			for (int j = 0; j < SpammerV2AndBot.lang.length(); j++) {
				if (text.charAt(i) == SpammerV2AndBot.lang.charAt(j))
					break;
				if (j == SpammerV2AndBot.lang.length() - 1) {
					SpammerV2AndBot.lang += text.charAt(i);
					break;
				}
			}
		SpammerV2AndBot.lang.replace(SpammerV2AndBot.langSeperator, "");
		SpammerV2AndBot.lang.replace("-", "");
	}

	public static String translate(String text) {
		String[] newLangWords = text.split("-");
		String[] hebrewWords = new String[newLangWords.length];
		for (int w = 0; w < newLangWords.length; w++) {
			hebrewWords[w] = translateNewLangWord(newLangWords[w]);
		}
		String hebrewSentence = "";
		for (int i = 0; i < hebrewWords.length; i++)
			hebrewSentence += hebrewWords[i] + " ";
		return hebrewSentence;
	}

	public static String translateHebrew(String text) {
		String[] hebrewWords = text.split(" ");
		String[] newLangWords = new String[hebrewWords.length];
		for (int w = 0; w < hebrewWords.length; w++) {
			newLangWords[w] = translateHebrewWord(hebrewWords[w]);
		}
		String newLangSentence = "";
		for (int i = 0; i < newLangWords.length; i++)
			newLangSentence += "-" + newLangWords[i];
		return newLangSentence.substring(1);
	}

	public static String translateHebrewV2(String text) {
		String[] hebrewWords = text.split(" ");
		String[] newLangWords = new String[hebrewWords.length];
		for (int w = 0; w < hebrewWords.length; w++) {
			newLangWords[w] = translateHebrewWordV2(hebrewWords[w]);
		}
		String newLangSentence = "";
		for (int i = 0; i < newLangWords.length; i++)
			newLangSentence += "-" + SpammerV2AndBot.langSeperator+newLangWords[i]+SpammerV2AndBot.langSeperator;
		return newLangSentence.substring(1);
	}

	private static String translateHebrewWordV2(String text) {
		String newLangWord = "";
		int hebrewWordDecimal = 0;
		for (int i = 0; i < text.length(); i++) {
			hebrewWordDecimal += getDecimalValue(text.charAt(i));
		}
		LinkedList<Integer> newLangWordDecimal = getNewLangCharArray(hebrewWordDecimal);
		for (int j = 0; j < newLangWordDecimal.size(); j++) {
			char newLangChar = SpammerV2AndBot.lang.charAt(newLangWordDecimal.get(j));
			newLangWord += newLangChar;
		}
		return newLangWord;
	}
	
	static int time = 0;

	public static String translateV2(String text) {
		String finalText = "*כל האפשרויות בעברית למשפט שהכנסת הן:*\n\n";
		String sentences = getPossibilities(text.replace(SpammerV2AndBot.langSeperator, ""));
		return finalText + sentences;
	}

	private static String getPossibilities(String text) {
		LinkedList<LinkedList<String>> words = getWords(text);
		if (words.size() != 0) {
			int size = 1;
			for (int i = 0; i < words.size(); i++)
				size *= words.get(i).size();
			String[][] print = new String[size][words.size()];
			try {
			print = getSentences(words, 0, print);
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			time = 0;
			String sentences = "";
			for (int i = 0; i < print.length; i++) {
				for (int j = 0; j < print[i].length; j++)
					sentences += print[i][j] + " ";
				sentences += "\n";
			}
			return sentences;
		}
		return "\n";
	}

	private static String[][] getSentences(LinkedList<LinkedList<String>> words, int ind, String[][] print) {
		try {
		if (ind == print[0].length) {
			time++;
			return print;
		}
		}
		catch (Exception e) {
			// TODO: handle exception
			return print;
		}
		LinkedList<String> list = words.get(ind);
		for (int i = 0; i < list.size(); i++) {
			String word = list.get(i);
			print[time][ind] = word;
			int previousTime = time;
			print = getSentences(words, ind + 1, print);
			for (int j = previousTime+1; j < time; j++)
				print[j][ind] = word;
		}		
		return print;
	}

	private static LinkedList<LinkedList<String>> getWords(String text) {
		String[] words = text.split("\\-");
		LinkedList<LinkedList<String>> sentences = new LinkedList<>();
		for (String word : words) {
			int hebrewWordDecimal = 0;
			for (int f = 0; f < word.length(); f++) {
				char c = word.charAt(f);
				if (SpammerV2AndBot.lang.contains(c + "")) {
					hebrewWordDecimal += SpammerV2AndBot.lang.indexOf(c) * Math.pow(SpammerV2AndBot.lang.length(), f);
				}
			}
			sentences.add(getEqualWords(hebrewWordDecimal));
		}
		return sentences;
	}

	private static LinkedList<String> getEqualWords(int num) {
		LinkedList<String> matchWords = new LinkedList<>();
		for (String line : words) {
			String[] fields = line.split(" ");
			if (Integer.parseInt(fields[0]) == num) {
				for (int i = 1; i < fields.length; i++)
					matchWords.add(fields[i]);
				break;
			}
		}
		return matchWords;
	}

	private static String translateHebrewWord(String text) {
		String[] newLangWords = new String[text.length()];
		for (int i = 0; i < newLangWords.length; i++)
			newLangWords[i] = "";
		for (int i = 0; i < text.length(); i++) {
			int hebrewCharDecimal = getDecimalValue(text.charAt(i));
			LinkedList<Integer> newLangWordDecimal = getNewLangCharArray(hebrewCharDecimal);
			for (int j = 0; j < newLangWordDecimal.size(); j++) {
				char newLangChar = SpammerV2AndBot.lang.charAt(newLangWordDecimal.get(j));
				newLangWords[i] += newLangChar;
			}
		}
		String newLangText = "";
		for (int i = 0; i < newLangWords.length; i++)
			newLangText += SpammerV2AndBot.langSeperator + newLangWords[i];
		newLangText += SpammerV2AndBot.langSeperator;
		return newLangText;
	}

	private static String translateNewLangWord(String text) {
		String[] words = text.split(SpammerV2AndBot.langSeperator);
		String hebrewWord = "";
		for (int i = 1; i < words.length; i++) {
			try {
				String word = words[i];
				int hebrewCharDecimal = 0;
				for (int f = 0; f < word.length(); f++) {
					char c = word.charAt(f);
					if (SpammerV2AndBot.lang.contains(c + "")) {
						hebrewCharDecimal += SpammerV2AndBot.lang.indexOf(c)
								* Math.pow(SpammerV2AndBot.lang.length(), f);
					}
				}
				if (i == words.length - 1)
					hebrewWord += getCapChar(getHebrewChar(hebrewCharDecimal));
				else
					hebrewWord += getHebrewChar(hebrewCharDecimal);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return hebrewWord;
	}

	private static LinkedList<Integer> getNewLangCharArray(int digitDecimal) {
		LinkedList<Integer> indexes = new LinkedList<>();
		while (digitDecimal != 0) {
			indexes.add(digitDecimal % SpammerV2AndBot.lang.length());
			digitDecimal /= SpammerV2AndBot.lang.length();
		}
		return indexes;
	}

	private static char getCapChar(char c) {
		if (c == 'מ')
			return 'ם';
		if (c == 'פ')
			return 'ף';
		if (c == 'כ')
			return 'ך';
		if (c == 'צ')
			return 'ץ';
		if (c == 'נ')
			return 'ן';
		return c;
	}

	private static char getHebrewChar(int c) {
		String first = "אבגדהוזחט";
		String second = "יכלמנסעפצ";
		String third = "קרשת";
		if (c < 10) {
			return first.charAt(c - 1);
		} else if (c < 100) {
			return second.charAt((c / 10) - 1);
		} else {
			return third.charAt((c / 100) - 1);
		}
	}

	private static int getDecimalValue(char c) {
		String first = "אבגדהוזחט";
		String second = "יכלמנסעפצ";
		String third = "קרשת";
		if (c == 'ם')
			return 40;
		if (c == 'ף')
			return 80;
		if (c == 'ך')
			return 20;
		if (c == 'ץ')
			return 90;
		if (c == 'ן')
			return 50;
		if (c < 'י') {
			return first.indexOf(c) + 1;
		} else if (c < 'ק') {
			return (second.indexOf(c) + 1) * 10;
		} else {
			return (third.indexOf(c) + 1) * 100;
		}
	}

}
