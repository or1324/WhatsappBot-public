package all_in_one_bot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class HebrewTyper {
	final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

	public static String execute(String text, String c) {
		String emojiSentance = "";
		try {
			for (int i = 0; i < text.length(); i++) {
				Character cha = text.charAt(i);
				if (cha.equals('א')) {

					String[] lines = new String[5];
					lines[0] = (char) 0x200e + "```" + c + "         " + c;
					lines[1] = (char) 0x200e + "  ‎ " + c + "      " + c;
					lines[2] = (char) 0x200e + " ‎  " + c + " " + c + " " + c;
					lines[3] = (char) 0x200e + "   ‎" + c + "    " + c;
					lines[4] = (char) 0x200e + " ‎  " + c + "       " + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}

				} else if (cha.equals('ב')) {

					String[] lines = new String[5];
					lines[0] = (char) 0x200e + "```" + c + c + c + c + c + "";
					lines[1] = (char) 0x200e + "           " + c;
					lines[2] = (char) 0x200e + "           " + c;
					lines[3] = (char) 0x200e + "           " + c;
					lines[4] = (char) 0x200e + c + c + c + c + c + c + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}

				} else if (cha.equals('ג')) {

					String[] lines = new String[5];
					lines[0] = (char) 0x200e + "```" + c + c + c;
					lines[1] = (char) 0x200e + "       " + c;
					lines[2] = (char) 0x200e + "     " + c + c;
					lines[3] = (char) 0x200e + "   " + c + "  " + c;
					lines[4] = (char) 0x200e + " " + c + "    " + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}

				} else if (cha.equals('ד')) {

					String[] lines = new String[5];
					lines[0] = (char) 0x200e + "```" + c + c + c + c + c;
					lines[1] = (char) 0x200e + "        " + c;
					lines[2] = (char) 0x200e + "        " + c;
					lines[3] = (char) 0x200e + "        " + c;
					lines[4] = (char) 0x200e + "        " + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}

				} else if (cha.equals('ה')) {

					String[] lines = new String[5];
					lines[0] = (char) 0x200e + "```" + c + c + c + c + c;
					lines[1] = (char) 0x200e + "           " + c;
					lines[2] = (char) 0x200e + "" + c + c + c + "   " + c;
					lines[3] = (char) 0x200e + "     " + c + "   " + c;
					lines[4] = (char) 0x200e + "     " + c + "   " + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}

				} else if (cha.equals('ו')) {

					String[] lines = new String[5];
					lines[0] = (char) 0x200e + "```      " + c;
					lines[1] = (char) 0x200e + "      " + c;
					lines[2] = (char) 0x200e + "      " + c;
					lines[3] = (char) 0x200e + "      " + c;
					lines[4] = (char) 0x200e + "      " + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}

				} else if (cha.equals('ז')) {

					String[] lines = new String[5];
					lines[0] = (char) 0x200e + "```" + c + c + c + c + "";
					lines[1] = (char) 0x200e + "    " + c;
					lines[2] = (char) 0x200e + "     " + c;
					lines[3] = (char) 0x200e + "      " + c;
					lines[4] = (char) 0x200e + "       " + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}

				} else if (cha.equals('ח')) {

					String[] lines = new String[5];
					lines[0] = (char) 0x200e + "```" + c + c + c + c;
					lines[1] = (char) 0x200e + "" + c + "      " + c;
					lines[2] = (char) 0x200e + "" + c + "      " + c;
					lines[3] = (char) 0x200e + "" + c + "      " + c;
					lines[4] = (char) 0x200e + "" + c + "      " + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}

				} else if (cha.equals('ט')) {

					String[] lines = new String[5];
					lines[0] = (char) 0x200e + "```" + c + "        " + c;
					lines[1] = (char) 0x200e + "" + c + "      " + c + " " + c;
					lines[2] = (char) 0x200e + "" + c + "    " + c + "   " + c;
					lines[3] = (char) 0x200e + "" + c + "          " + c;
					lines[4] = (char) 0x200e + " " + c + c + c + c + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}

				} else if (cha.equals('י')) {

					String[] lines = new String[5];
					lines[0] = (char) 0x200e + "```      " + c;
					lines[1] = (char) 0x200e + "      " + c;
					lines[2] = (char) 0x200e + "      ";
					lines[3] = (char) 0x200e + "      ";
					lines[4] = (char) 0x200e + "      ```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}

				} else if (cha.equals('כ')) {

					String[] lines = new String[5];
					lines[0] = (char) 0x200e + "```" + c + c + c + c;
					lines[1] = (char) 0x200e + "         " + c;
					lines[2] = (char) 0x200e + "         " + c;
					lines[3] = (char) 0x200e + "         " + c;
					lines[4] = (char) 0x200e + "" + c + c + c + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}

				} else if (cha.equals('ל')) {

					String[] lines = new String[7];
					lines[0] = (char) 0x200e + "```" + c;
					lines[1] = (char) 0x200e + "" + c;
					lines[2] = (char) 0x200e + "" + c + c + c + c;
					lines[3] = (char) 0x200e + "         " + c;
					lines[4] = (char) 0x200e + "         " + c;
					lines[5] = (char) 0x200e + "         " + c;
					lines[6] = (char) 0x200e + "" + c + c + c + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}

				} else if (cha.equals('מ')) {

					String[] lines = new String[5];
					lines[0] = (char) 0x200e + "```" + c + "  " + c + c + c;
					lines[1] = (char) 0x200e + "" + c + c + "      " + c;
					lines[2] = (char) 0x200e + "" + c + "         " + c;
					lines[3] = (char) 0x200e + "" + c + "         " + c;
					lines[4] = (char) 0x200e + c + "   " + c + c + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}

				} else if (cha.equals('נ')) {

					String[] lines = new String[5];
					lines[0] = (char) 0x200e + "```       " + c;
					lines[1] = (char) 0x200e + "       " + c;
					lines[2] = (char) 0x200e + "       " + c;
					lines[3] = (char) 0x200e + "       " + c;
					lines[4] = (char) 0x200e + c + c + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}

				} else if (cha.equals('ס')) {

					String[] lines = new String[5];
					lines[0] = (char) 0x200e + "```     " + c + c;
					lines[1] = (char) 0x200e + "  " + c + "      " + c;
					lines[2] = (char) 0x200e + " " + c + "        " + c;
					lines[3] = (char) 0x200e + "  " + c + "      " + c;
					lines[4] = (char) 0x200e + "     " + c + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}

				} else if (cha.equals('ע')) {

					String[] lines = new String[5];
					lines[0] = (char) 0x200e + "```    " + c + "      " + c;
					lines[1] = (char) 0x200e + "     " + c + "    " + c;
					lines[2] = (char) 0x200e + "      " + c + "  " + c;
					lines[3] = (char) 0x200e + "       " + c + c;
					lines[4] = (char) 0x200e + c + c + c + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}

				} else if (cha.equals('פ')) {

					String[] lines = new String[5];
					lines[0] = (char) 0x200e + "```" + c + c + c + c + "";
					lines[1] = (char) 0x200e + c + "      " + c;
					lines[2] = (char) 0x200e + c + c + "    " + c;
					lines[3] = (char) 0x200e + "         " + c;
					lines[4] = (char) 0x200e + c + c + c + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}

				} else if (cha.equals('צ')) {

					String[] lines = new String[5];
					lines[0] = (char) 0x200e + "```" + c + "       " + c;
					lines[1] = (char) 0x200e + "   " + c + "  " + c;
					lines[2] = (char) 0x200e + "      " + c;
					lines[3] = (char) 0x200e + "         " + c;
					lines[4] = (char) 0x200e + c + c + c + c + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}

				} else if (cha.equals('ק')) {

					String[] lines = new String[8];
					lines[0] = (char) 0x200e + "```" + c + c + c + c + c;
					lines[1] = (char) 0x200e + "          " + c;
					lines[2] = (char) 0x200e + "   " + c + "    " + c;
					lines[3] = (char) 0x200e + "   " + c + " " + c;
					lines[4] = (char) 0x200e + "   " + c;
					lines[5] = (char) 0x200e + "   " + c;
					lines[6] = (char) 0x200e + "   " + c;
					lines[7] = (char) 0x200e + "   " + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}

				} else if (cha.equals('ר')) {

					String[] lines = new String[5];
					lines[0] = (char) 0x200e + "```" + c + c + c + c + c + "";
					lines[1] = (char) 0x200e + "          " + c;
					lines[2] = (char) 0x200e + "          " + c;
					lines[3] = (char) 0x200e + "          " + c;
					lines[4] = (char) 0x200e + "          " + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}

				} else if (cha.equals('ש')) {

					String[] lines = new String[5];
					lines[0] = (char) 0x200e + "```" + c + "      " + c + "    " + c;
					lines[1] = (char) 0x200e + " " + c + "     " + c + "    " + c;
					lines[2] = (char) 0x200e + "  " + c + "   " + c + "    " + c;
					lines[3] = (char) 0x200e + "   " + c + c + "     " + c;
					lines[4] = (char) 0x200e + "    " + c + c + c + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}

				} else if (cha.equals('ת')) {

					String[] lines = new String[5];
					lines[0] = (char) 0x200e + "```     " + c + c + c + c + c;
					lines[1] = (char) 0x200e + "     " + c + "        " + c;
					lines[2] = (char) 0x200e + "     " + c + "        " + c;
					lines[3] = (char) 0x200e + "     " + c + "        " + c;
					lines[4] = (char) 0x200e + c + c + c + "        " + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}

				} else if (cha.equals('ץ')) {

					String[] lines = new String[8];
					lines[0] = (char) 0x200e + "```" + c + "        " + c;
					lines[1] = (char) 0x200e + "  " + c + "      " + c;
					lines[2] = (char) 0x200e + "    " + c + "  " + c;
					lines[3] = (char) 0x200e + "      " + c;
					lines[4] = (char) 0x200e + "       " + c;
					lines[5] = (char) 0x200e + "        " + c;
					lines[6] = (char) 0x200e + "         " + c;
					lines[7] = (char) 0x200e + "          " + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}

				} else if (cha.equals('ף')) {

					String[] lines = new String[8];
					lines[0] = (char) 0x200e + "```" + c + c + c + c;
					lines[1] = (char) 0x200e + c + "       " + c;
					lines[2] = (char) 0x200e + c + c + c + "   " + c;
					lines[3] = (char) 0x200e + "           " + c;
					lines[4] = (char) 0x200e + "           " + c;
					lines[5] = (char) 0x200e + "           " + c;
					lines[6] = (char) 0x200e + "           " + c;
					lines[7] = (char) 0x200e + "           " + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}

				} else if (cha.equals('ן')) {

					String[] lines = new String[8];
					lines[0] = (char) 0x200e + "```   " + c;
					lines[1] = (char) 0x200e + "   " + c;
					lines[2] = (char) 0x200e + "   " + c;
					lines[3] = (char) 0x200e + "   " + c;
					lines[4] = (char) 0x200e + "   " + c;
					lines[5] = (char) 0x200e + "   " + c;
					lines[6] = (char) 0x200e + "   " + c;
					lines[7] = (char) 0x200e + "   " + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}

				} else if (cha.equals('!')) {

					String[] lines = new String[9];
					lines[0] = (char) 0x200e + "```     " + c + "";
					lines[1] = (char) 0x200e + "    " + c + c;
					lines[2] = (char) 0x200e + "    " + c + c;
					lines[3] = (char) 0x200e + "    " + c + c;
					lines[4] = (char) 0x200e + "    " + c + c;
					lines[5] = (char) 0x200e + "    " + c + c;
					lines[6] = (char) 0x200e + "     " + c;
					lines[7] = (char) 0x200e + "";
					lines[8] = (char) 0x200e + "     " + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}

				} else if (cha.equals('ך')) {

					String[] lines = new String[8];
					lines[0] = (char) 0x200e + "```" + c + c + c + c;
					lines[1] = (char) 0x200e + "         " + c;
					lines[2] = (char) 0x200e + "         " + c;
					lines[3] = (char) 0x200e + "         " + c;
					lines[4] = (char) 0x200e + "         " + c;
					lines[5] = (char) 0x200e + "         " + c;
					lines[6] = (char) 0x200e + "         " + c;
					lines[7] = (char) 0x200e + "         " + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}
				} else if (cha.equals('ם')) {

					String[] lines = new String[5];
					lines[0] = (char) 0x200e + "```" + c + c + c + c + c;
					lines[1] = (char) 0x200e + c + "        " + c;
					lines[2] = (char) 0x200e + c + "        " + c;
					lines[3] = (char) 0x200e + c + "        " + c;
					lines[4] = (char) 0x200e + c + c + c + c + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}
				} else if (cha.equals('?')) {

					String[] lines = new String[7];
					lines[0] = (char) 0x200e + "```   " + c + c;
					lines[1] = (char) 0x200e + c + "      " + c;
					lines[2] = (char) 0x200e + "        " + c;
					lines[3] = (char) 0x200e + "      " + c;
					lines[4] = (char) 0x200e + "    " + c;
					lines[5] = (char) 0x200e + "";
					lines[6] = (char) 0x200e + "    " + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}
				} else if (cha.equals(':')) {

					String[] lines = new String[6];
					lines[0] = (char) 0x200e + "``` " + c;
					lines[1] = (char) 0x200e + "";
					lines[2] = (char) 0x200e + "";
					lines[3] = (char) 0x200e + "";
					lines[4] = (char) 0x200e + "";
					lines[5] = (char) 0x200e + " " + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}
				} else if (cha.equals(')')) {

					String[] lines = new String[6];
					lines[0] = (char) 0x200e + "```" + c;
					lines[1] = (char) 0x200e + "  " + c;
					lines[2] = (char) 0x200e + "   " + c;
					lines[3] = (char) 0x200e + "   " + c;
					lines[4] = (char) 0x200e + "  " + c;
					lines[5] = (char) 0x200e + "" + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}
				} else if (cha.equals('(')) {

					String[] lines = new String[6];
					lines[0] = (char) 0x200e + "```   " + c;
					lines[1] = (char) 0x200e + " " + c;
					lines[2] = (char) 0x200e + "" + c;
					lines[3] = (char) 0x200e + "" + c;
					lines[4] = (char) 0x200e + " " + c;
					lines[5] = (char) 0x200e + "   " + c + "```";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}
				} else if (cha.equals(' ')) {

					String[] lines = new String[4];
					lines[0] = (char) 0x200e + "";
					lines[1] = (char) 0x200e + "";
					lines[2] = (char) 0x200e + "";
					lines[3] = (char) 0x200e + "";
					for (int f = 0; f < lines.length; f++) {
						emojiSentance += lines[f]+"\r\n";
					}
				}
				emojiSentance+="\r\n\r\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emojiSentance;
	}
}
