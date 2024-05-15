package all_in_one_bot;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class MessageSearch {

	static WebDriver driver;

	final static String messageClass1 = "_2wUmf message-in focusable-list-item";
	final static String messageClass2 = "_2wUmf _21bY5 message-in focusable-list-item";
	final static String messageClass3 = "_2wUmf _21bY5 _1q25n message-in focusable-list-item";
	final static String messageClass4Pictures = "_2wUmf _1q25n message-in focusable-list-item";
	final static String messageClass5Pictures = "_2wUmf _21bY5 _1q25n message-in focusable-list-item";
	final static String messageClassOut1 = "_2wUmf message-out focusable-list-item";
	final static String messageClassOut2 = "_2wUmf _21bY5 message-out focusable-list-item";
	final static String messageClassOut3 = "_2wUmf _21bY5 _1q25n message-out focusable-list-item";
	final static String messageClassOut4Pictures = "_2wUmf _1q25n message-out focusable-list-item";
	final static String messageClassOut5Pictures = "_2wUmf _21bY5 _1q25n message-out focusable-list-item";
	final static String messageTextPath = "./div/div/div/div[1]/div/span[1]";
	final static String messageTextPathReply = "./div/div/div/div[1]/div[2]/span[1]";
	final static String messageTextPathGroup = "./div/div/div/div[2]/div/span[1]";
	final static String messageTextPathReplyGroup = "./div/div/div/div[2]/div[2]/span[1]";
	final static String messageNamePath = "./div/div/div/div[1]/span";
	final static String messageImageDownloadPath = "./div/div[1]/div/button/div/div/div[3]/div";
	final static String messageImageNamePath = "./div/div[1]/div/button/div/div/div[2]/span";
	final static String messageImageButtonPath = "./div/div[1]/div/button";

	final static String windowsDownloadFolderPath = "C:\\Users\\ornev\\Downloads\\";
	final static String linuxDownloadFolderPath = "/home/or/Downloads/";
	public final static String linuxBotSongsFolderPath = "/home/or/BotSongs/";
	public final static String windowsBotSongsFolderPath = "C:/BotSongs/";

	final static String chatClass = "_3m_Xw";
	final static String searchLabelPath = "//*[@id=\"side\"]/div[1]/div/label";
	final static String searchTextInputDivPath = "//*[@id=\"side\"]/div[1]/div/label/div/div[2]";
	final static String chatTitlePathGroup = "./div/div/div[2]/div[1]/div[1]/span";
	final static String chatTitlePathPersonal = "./div/div/div[2]/div[1]/div[1]/span/span";
	final static String sendFilePath = "//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[1]/div[2]/div/div";
	final static String fileTypeDocumentPath = "//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[1]/div[2]/div/span/div[1]/div/ul/li[4]/button/input";
	final static String sendFileButtonPath = "//*[@id=\"app\"]/div[1]/div[1]/div[2]/div[2]/span/div[1]/span/div[1]/div/div[2]/div/div[2]/div[2]/div/div";
	final static String textBoxPath = "//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[2]";
	final static String sendMessageButtonPath = "//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[2]/button";
	// The order of the second string array is the same as of the first.
	final static String[] whatsappEffectsHtmlNames = { "strong", "i", "del", "code", "em" };
	final static String[] whatsappEffects = { "*", "_", "~", "```", "_*" };

	static boolean issong = false;
	static boolean isstop = false;
	static boolean moving = false;

	public static void searchMessages() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while (true) {
						if (!ImageToEmojis.waitingForCloud) {
							try {
								List<WebElement> arr = driver.findElements(By.xpath("//div[@class='" + messageClass1
										+ "' or @class='" + messageClass2 + "' or @class='" + messageClass3
										+ "' or @class='" + messageClass4Pictures + "' or @class='"
										+ messageClass5Pictures + "' or @class='" + messageClassOut1 + "' or @class='"
										+ messageClassOut2 + "' or @class='" + messageClassOut3 + "' or @class='"
										+ messageClassOut4Pictures + "' or @class='" + messageClassOut5Pictures
										+ "']"));
								WebElement web = arr.get(arr.size() - 1);
								if (ImageToEmojis.waitingForImage) {
									try {
										try {
											web.findElement(By.xpath(messageImageDownloadPath));
											continue;
										} catch (Exception e) {
											// TODO: handle exception
										}
										String file = web.findElement(By.xpath(messageImageNamePath)).getText();
										File f = null;
										String osname = System.getProperty("os.name");
										if (osname.startsWith("Linux")) {
											f = new File(linuxDownloadFolderPath + file);
										} else if (osname.startsWith("Windows")) {
											f = new File(windowsDownloadFolderPath + file);
										}
										if (f.exists()) {
											File to = null;
											if (osname.startsWith("Linux")) {
												to = new File(linuxDownloadFolderPath + "Trash/" + file);
											} else if (osname.startsWith("Windows")) {
												to = new File(windowsDownloadFolderPath + "Trash\\" + file);
											}
											Files.copy(f, to);
											f.delete();
										}
										web.findElement(By.xpath(messageImageButtonPath)).click();
										waitForImageToDownload(f);
									} catch (Exception e) {
										// TODO: handle exception
									}
								} else {
									String input = getAllTextWithEmojisFromLastMessageIfCommand(web);
									if (!input.equals(""))
										listenToCommands(input, web);
								}
							} catch (Exception e) {
							}
							Thread.sleep(1);
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}).start();
	}

	private static void listenToCommands(String input, WebElement web) throws Exception {
		if (input.equals("!random_song")) {
			if (!issong && !isstop) {
				issong = true;
				SpammerV2AndBot.sendRandomSong();
			}
		} else if (input.equals("!random_birthday_song")) {
			if (!issong && !isstop && SpammerV2AndBot.isBirthday) {
				issong = true;
				SpammerV2AndBot.sendRandomBirthdaySong();
			}
		} else {
			try {
				// The order is from the shortest substring cut to the longest so that the
				// exception will not happen before all of the commands were checked.
				if (input.equals("!stop_bot")) {
					if (isAdmin(web)) {
						isstop = true;
					}
				} else if (input.equals("!start_bot")) {
					if (isAdmin(web)) {
						isstop = false;
					}
				} else if (!isstop) {

					if (input.equals("!laugh")) {
						SpammerV2AndBot.laugh();
					} else if (input.equals("!convert_image_to_ariel_emojis")) {
						SpammerV2AndBot.convertImageToArielEmojis();
					} else if (input.equals("!convert_image_to_grandma_emojis")) {
						SpammerV2AndBot.convertImageToGrandmaEmojis();
					} else if (input.equals("!list_songs")) {
						SpammerV2AndBot.list();
					} else if (SpammerV2AndBot.isBirthday && input.equals("!list_birthday_songs")) {
						SpammerV2AndBot.listBirthdaySongs();
					} else if (input.equals("!happened_today")) {
						SpammerV2AndBot.happenedToday();
					} else if (input.equals("!born_today")) {
						SpammerV2AndBot.bornToday();
					} else if (input.equals("!died_today")) {
						SpammerV2AndBot.diedToday();
					} else if (input.equals("!events_today")) {
						SpammerV2AndBot.eventsToday();
					} else if (input.equals("!help")) {
						SpammerV2AndBot.help();
					} else if (input.equals("!random_topic")) {
						SpammerV2AndBot.randomTopic();
					} else if (input.equals("!random_joke")) {
						SpammerV2AndBot.randomJoke();
					} else if (SpammerV2AndBot.isBirthday && input.equals("!random_birthday_joke")) {
						SpammerV2AndBot.randomBirthdayJoke();
					} else if (input.equals("!random_quote")) {
						SpammerV2AndBot.randomQuote();
					} else if (input.equals("!switch_language_method")) {
						if (isAdmin(web)) {
							SpammerV2AndBot.switchLangMethod();
						}
					} else if (input.equals("!שם_תנכי")) {
						SpammerV2AndBot.randomBible();
					} else if (input.substring(0, 7).equals("!write ")) {
						try {
							String emoji = "";
							try {
								emoji = getFirstEmojiFromElement(web.findElement(By.xpath(messageTextPath)));
							} catch (Exception e) {
								// TODO: handle exception
								emoji = getFirstEmojiFromElement(web.findElement(By.xpath(messageTextPathReply)));
								try {
									emoji = getFirstEmojiFromElement(web.findElement(By.xpath(messageTextPathGroup)));
								} catch (Exception eee) {
									// TODO: handle exception
									emoji = getFirstEmojiFromElement(
											web.findElement(By.xpath(messageTextPathReplyGroup)));
								}
							}
							if (emoji.equals(""))
								throw new Exception();
							SpammerV2AndBot.write(input.substring(7).trim(), emoji);
						} catch (Exception e) {
							// TODO: handle exception
							SpammerV2AndBot.sendText("חייבים להכניס גם אימוג'י...");
						}
					} else if (input.substring(0, 9).equals("!born_at ")) {
						SpammerV2AndBot.bornAt(input.substring(9).trim());
					} else if (input.substring(0, 9).equals("!died_at ")) {
						SpammerV2AndBot.diedAt(input.substring(9).trim());
					} else if (input.substring(0, 11).equals("!events_at ")) {
						SpammerV2AndBot.eventsAt(input.substring(11).trim());
					} else if (input.substring(0, 11).equals("!translate ")) {
						SpammerV2AndBot.translate(input.substring(11));
					} else if (input.substring(0, 13).equals("!happened_at ")) {
						SpammerV2AndBot.happenedAt(input.substring(13).trim());
					} else if (input.substring(0, 15).equals("!specific_song ")) {
						if (!issong)
							SpammerV2AndBot.sendSong(input.substring(15).toLowerCase(), false);
						return;
					} else if (input.substring(0, 17).equals("!create_language ")) {
						if (isAdmin(web)) {
							SpammerV2AndBot.createLanguage(input.substring(17));
						}
					} else if (input.substring(0, 18).equals("!translate_hebrew ")) {
						SpammerV2AndBot.translateHebrew(input.substring(18));
					} else if (input.substring(0, 19).equals("!make_shemshon_say ")) {
						String text = "*שמשון:*\n\n";
						text += input.substring(19);
						SpammerV2AndBot.sendText(text);
					} else if (input.substring(0, 24).equals("!specific_birthday_song ")) {
						if (!issong && SpammerV2AndBot.isBirthday)
							SpammerV2AndBot.sendSong(input.substring(15).toLowerCase(), true);
						return;
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				if (input.substring(0, 9).equals("!move_to ")) {
					if (isMe(web)) {
						moveTo(input.substring(9));
					}
				}
			} catch (Exception e) {
				moving = false;
				// TODO: handle exception
			}
			issong = false;
		}
	}

	private static void waitForImageToDownload(File file) throws InterruptedException {
		while (true) {
			Thread.sleep(10);
			if (file.exists())
				break;
		}
		ImageToEmojis.ImageToEmojis(file);
		file.delete();
	}

	static boolean isAdmin(WebElement web) {
		return isMe(web)
				|| SpammerV2AndBot.isAdmin(getAllTextWithEmojisFromElement(web.findElement(By.xpath(messageNamePath))));
	}

	static boolean isMe(WebElement web) {
		return web.getAttribute("class").equals(messageClassOut1) || web.getAttribute("class").equals(messageClassOut2)
				|| web.getAttribute("class").equals(messageClassOut3);
	}

	static String getEmojiFromImageHtml(String imageHtml) {
		return imageHtml.substring(imageHtml.indexOf("alt=") + "alt=\"".length(), imageHtml.indexOf("\" draggable=\""));
	}

	static String getAllTextWithEmojisFromLastMessageIfCommand(WebElement web) {
		WebElement spanElement;
		try {
			spanElement = web.findElement(By.xpath(messageTextPath));
		} catch (Exception e) {
			e.printStackTrace();
			try {
				spanElement = web.findElement(By.xpath(messageTextPathReply));
			} catch (Exception ee) {
				try {
					spanElement = web.findElement(By.xpath(messageTextPathGroup));
				} catch (Exception eee) {
					// TODO: handle exception
					spanElement = web.findElement(By.xpath(messageTextPathReplyGroup));
				}
			}
		}
		String html = getWebElementHtml(spanElement);
		if (html.startsWith("<span") && html.charAt(html.indexOf(">") + 1) == '!')
			return getAllTextWithEmojisFromElementWithHtml(spanElement, html);
		else
			return "";
	}

	static String getWebElementHtml(WebElement web) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].innerHTML;", web);
	}

	static void searchGroup(String text) {
		text = SpammerV2AndBot.replaceRegex(text);
		try {
			WebElement element = driver.findElement(By.xpath(MessageSearch.searchTextInputDivPath));
			((JavascriptExecutor) driver).executeScript("arguments[0].innerHTML = '" + text + "'", element);
			element.sendKeys(".");
			element.sendKeys(Keys.BACK_SPACE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void moveTo(String chatName) throws InterruptedException {
		if (!moving) {
			moving = true;
			while (true) {
				try {
					new WebDriverWait(driver, 20).until(
							ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(searchLabelPath))));
					break;
				} catch (Exception ignored) {
				}
			}
			searchGroup(chatName);
			Thread.sleep(1000);
			List<WebElement> list = driver.findElements(By.xpath("//div[@class='" + chatClass + "']"));
			for (WebElement web2 : list) {
				try {
					if (web2.findElement(By.xpath(chatTitlePathGroup)).getAttribute("title").equals(chatName) || web2
							.findElement(By.xpath(chatTitlePathPersonal)).getAttribute("title").equals(chatName)) {
						web2.click();
						break;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			moving = false;
		}
	}

	static String getAllTextWithEmojisFromElement(WebElement spanElement) {
		String html = getWebElementHtml(spanElement);
		return getAllTextWithEmojisFromElementWithHtml(spanElement, html);
	}

	static String getAllTextWithEmojisFromElementWithHtml(WebElement spanElement, String html) {
		String text = "";
		String[] spans = html.split("<span");
		int[] whatsappEffectsCount = new int[whatsappEffectsHtmlNames.length];
		if (spans.length > 1) {
			for (int i = 1; i < spans.length; i++)
				text += getAllTextWithEmojisFromElement(spanElement.findElement(By.xpath("./span[" + i + "]")));
		} else {
			String[] lines = html.split("<");
			for (int i = 0; i < lines.length; i++) {
				String lineText = lines[i];
				for (int f = 0; f < whatsappEffectsHtmlNames.length; f++)
					if (lineText.startsWith("/" + whatsappEffectsHtmlNames[f] + ">")) {
						text += new StringBuilder(whatsappEffects[f]).reverse().toString();
						lineText = lineText.substring(whatsappEffectsHtmlNames[f].length() + 2);
						break;
					}
				boolean found = false;
				for (int f = 0; f < whatsappEffectsHtmlNames.length; f++)
					if (lineText.startsWith(whatsappEffectsHtmlNames[f]) && !lineText.startsWith("img")) {
						whatsappEffectsCount[f]++;
						text += whatsappEffects[f];
						text += getAllTextWithEmojisFromElement(spanElement.findElement(
								By.xpath("./" + whatsappEffectsHtmlNames[f] + "[" + whatsappEffectsCount[f] + "]")));
						for (; !lines[i].startsWith("/" + whatsappEffectsHtmlNames[f]); i++)
							;
						i--;
						found = true;
						break;
					}
				if (!found) {
					if (lineText.startsWith("img")) {
						text += getEmojiFromImageHtml(lineText);
						text += lineText.substring(lineText.indexOf(">") + 1);
					} else
						text += lineText;
				}
			}
		}
		return text;
	}

	static String getFirstEmojiFromElement(WebElement spanElement) {
		String emoji = "";
		String html = getWebElementHtml(spanElement);
		String[] spans = html.split("<span");
		int[] whatsappEffectsCount = new int[whatsappEffectsHtmlNames.length];
		if (spans.length > 1) {
			for (int i = 1; i < spans.length; i++) {
				emoji = getFirstEmojiFromElement(spanElement.findElement(By.xpath("./span[" + i + "]")));
				if (!emoji.equals(""))
					return emoji;
			}
		} else {
			String[] lines = html.split("<");
			for (int i = 0; i < lines.length; i++) {
				String lineText = lines[i];
				for (int f = 0; f < whatsappEffectsHtmlNames.length; f++)
					if (lineText.startsWith("/" + whatsappEffectsHtmlNames[f] + ">")) {
						lineText = lineText.substring(whatsappEffectsHtmlNames[f].length() + 2);
						break;
					}
				boolean found = false;
				for (int f = 0; f < whatsappEffectsHtmlNames.length; f++)
					if (lineText.startsWith(whatsappEffectsHtmlNames[f]) && !lineText.startsWith("img")) {
						whatsappEffectsCount[f]++;
						emoji = getFirstEmojiFromElement(spanElement.findElement(
								By.xpath("./" + whatsappEffectsHtmlNames[f] + "[" + whatsappEffectsCount[f] + "]")));
						if (!emoji.equals(""))
							return emoji;
						for (; !lines[i].startsWith("/" + whatsappEffectsHtmlNames[f]); i++)
							;
						i--;
						found = true;
						break;
					}
				if (!found) {
					if (lineText.startsWith("img")) {
						emoji = getEmojiFromImageHtml(lineText);
						break;
					}
				}
			}
		}
		return emoji;
	}
}
