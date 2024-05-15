package all_in_one_bot;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.imageio.ImageIO;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference.CompletionListener;

public class ImageToEmojis {

	public static volatile boolean waitingForImage = false;
	public static volatile boolean waitingForCloud = false;
	public static volatile boolean isGrandma = false;

	public static void ImageToEmojis(File file) {
		try {
			// BufferedImage image = getImage(imageElement);
			// BufferedImage image = getImage(imageElement);
			byte[] imageBytes = Files.readAllBytes(file.toPath());
			sendImageToOr(imageBytes);
			// File emojiImageFile = convertImageToArielEmojis(image);
			// sendImage(emojiImageFile);
		} catch (Exception e) {
			waitingForImage = false;
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	// This method uploads the image to the cloud so my phone will see it.
	private static void sendImageToOr(byte[] image) throws IOException {
		// ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		// ImageIO.write(image, "jpeg", byteArrayOutputStream);
		// byte[] imageBytes = byteArrayOutputStream.toByteArray();
		String osname = System.getProperty("os.name");
		Storage storage = null;
		if (osname.startsWith("Linux")) {
			storage = StorageOptions.newBuilder().setCredentials(
					ServiceAccountCredentials.fromStream(new FileInputStream(SpammerV2AndBot.credentialPathLinux)))
					.build().getService();
		} else if (osname.startsWith("Windows")) {
			storage = StorageOptions.newBuilder()
					.setCredentials(
							ServiceAccountCredentials.fromStream(new FileInputStream(SpammerV2AndBot.credentialPath)))
					.build().getService();
		}
		Bucket bucket = storage.get("arielimagetoemojis.appspot.com");
		String bucketName;
		if (isGrandma) {
			bucketName = "image2";
		} else
			bucketName = "image";
		bucket.create(bucketName, image);
		waitingForCloud = true;
		waitingForImage = false;
		SpammerV2AndBot.database.getReference().child(bucketName).setValue(true, new CompletionListener() {

			@Override
			public void onComplete(DatabaseError error, DatabaseReference ref) {
				// TODO Auto-generated method stub
				if (error != null) {
					waitingForCloud = false;
					SpammerV2AndBot.sendText("הייתה שגיאה בהעלאת התמונה לענן");
				} else
					SpammerV2AndBot.sendText("התמונה הועלתה לענן והטלפון של אור אמור להחזיר את התמונה החדשה בקרוב :)");
			}
		});
	}

	// This method recieves the image that my phone uploaded to the cloud and sends
	// it.

	public static void recieveImage(byte[] imageBytes) throws IOException {
		InputStream inputStream = new ByteArrayInputStream(imageBytes);
		BufferedImage image = ImageIO.read(inputStream);
		File f;
		if (isGrandma)
			f = new File("GrandmaEmojis.png");
		else
			f = new File("ArielEmojis.png");
		if (!f.exists())
			f.createNewFile();
		ImageIO.write(image, "png", f);
		if (f.length() < 1024 * 1024 * 100)
			SpammerV2AndBot.sendFile(f);
		else
			SpammerV2AndBot.sendText("הקובץ הסופי גדול מדי ואי אפשר לשלוח אותו בוואטסאפ...");
		waitingForCloud = false;
		isGrandma = false;
	}

	/*
	 * private static BufferedImage getImage(WebElement imageElement) throws
	 * IOException { byte[] imageBytes =
	 * imageElement.getScreenshotAs(OutputType.BYTES); InputStream inputStream = new
	 * ByteArrayInputStream(imageBytes); BufferedImage image =
	 * ImageIO.read(inputStream); int type = image.getType() == 0 ?
	 * BufferedImage.TYPE_INT_ARGB : image.getType(); return resizeImage(image,
	 * type); }
	 */

	/*
	 * private static BufferedImage resizeImage(BufferedImage originalImage, int
	 * type) { int preWidth = originalImage.getWidth(); int preHeight =
	 * originalImage.getHeight(); int IMG_WIDTH; int IMG_CLAHEIGHT; if (preWidth >
	 * preHeight) { IMG_WIDTH = 400; IMG_CLAHEIGHT = (int) (((double) IMG_WIDTH /
	 * (double) preWidth) * (double) preHeight); } else { IMG_CLAHEIGHT = 400;
	 * IMG_WIDTH = (int) (((double) IMG_CLAHEIGHT / (double) preHeight) * (double)
	 * preWidth); } BufferedImage resizedImage = new BufferedImage(IMG_WIDTH,
	 * IMG_CLAHEIGHT, type); Graphics2D g = resizedImage.createGraphics();
	 * g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_CLAHEIGHT, null);
	 * g.dispose(); return resizedImage; }
	 */

	/*
	 * private static File convertImageToArielEmojis(BufferedImage image) throws
	 * IOException { String result = ""; for (int i = 0; i < image.getHeight(); i++)
	 * { for (int j = 0; j < image.getWidth(); j++) { int pixel = image.getRGB(j,
	 * i); // The following three lines of code convert a number to an RGB number
	 * int red = get3BitsColor(((float) ((pixel & 0xff0000) >> 16)) / 255f); int
	 * green = get3BitsColor(((float) ((pixel & 0xff00) >> 8)) / 255f); int blue =
	 * get3BitsColor(((float) ((pixel & 0xff))) / 255f); result += colorToEmoji(red,
	 * green, blue); } result += "\r\n"; } return textToImage(result); }
	 */

	// This method gets colors if there were only 3 colors to each color and returns
	// the emoji that the colors represent.
	/*
	 * private static String colorToEmoji(int red, int green, int blue) { if (red ==
	 * 0) { return redZeroToEmoji(green, blue); } else if (red == 1) { return
	 * redOneToEmoji(green, blue); } return redTwoToEmoji(green, blue); }
	 */

	// This method gets colors except red if there were only 3 colors to each color
	// and if the red is zero, and returns the emoji that the colors represent.

	/*
	 * private static String redZeroToEmoji(int green, int blue) { if (green == 0) {
	 * if (blue == 0) { return "⬛"; } return "\uD83E\uDD8B"; } else if (green == 1)
	 * { if (blue == 0) { return "\uD83E\uDD6C"; } else if (blue == 1) { return
	 * "\uD83E\uDD8B"; } return "\uD83E\uDD8B"; } if (blue == 0) { return
	 * "\uD83E\uDD6C"; } else if (blue == 1) { return "\uD83E\uDD6C"; } return
	 * "\uD83E\uDD76"; }
	 */

	// This method gets colors except red if there were only 3 colors to each color
	// and if the red is one, and returns the emoji that the colors represent.

	/*
	 * private static String redOneToEmoji(int green, int blue) { if (green == 0) {
	 * if (blue == 0) { return "\uD83D\uDCD5"; } return purple(); } else if (green
	 * == 1) { if (blue == 0) { return "\uD83C\uDF4D"; } else if (blue == 1) {
	 * return "\uD83D\uDC04"; } return purple(); } if (blue == 0) { return
	 * "\uD83E\uDD51"; } else if (blue == 1) { return "\uD83E\uDD51"; } return
	 * "\uD83E\uDD76"; }
	 */

	// This method gets colors except red if there were only 3 colors to each color
	// and if the red is two, and returns the emoji that the colors represent.

	/*
	 * private static String redTwoToEmoji(int green, int blue) { if (green == 0) {
	 * if (blue == 0) { return "\uD83D\uDCD5"; } return "\uD83D\uDC5B"; } else if
	 * (green == 1) { if (blue == 0) { return "\uD83D\uDCF4"; } else if (blue == 1)
	 * { return "\uD83D\uDC37"; } return "\uD83D\uDC37"; } if (blue == 0) { return
	 * yellow(); } else if (blue == 1) { return yellow(); } return white(); }
	 */

	/*
	 * private static String yellow() { return "\uD83D\uDFE1"; }
	 * 
	 * private static String purple() { return "\uD83D\uDFEA"; }
	 * 
	 * private static String white() { Random r = new Random(); int i =
	 * r.nextInt(2); if (i == 0) return "\uD83D\uDC29"; return "\uD83D\uDCAC"; }
	 */

	// This method gets a float number which represents a color (red, green or blue)
	// and returns the color if there were only 3 colors.
	/*
	 * private static int get3BitsColor(float color) { if (color * 3 < 1) { return
	 * 0; } else if (color * 3 < 2) { return 1; } else return 2; }
	 */

	/*
	 * private static File textToImage(String text) throws IOException { // Because
	 * font metrics is based on a graphics context, we need to create a // small,
	 * temporary image so we can ascertain the width and height of the final //
	 * image BufferedImage img = new BufferedImage(1, 1,
	 * BufferedImage.TYPE_INT_ARGB); Graphics2D g2d = img.createGraphics(); Font
	 * font = new Font("Proxima Nova", Font.PLAIN, 20); g2d.setFont(font);
	 * FontMetrics fm = g2d.getFontMetrics(); String[] lines = text.split("\r\n");
	 * int width = fm.stringWidth(text) / lines.length + 4; int height =
	 * fm.getHeight() * lines.length + 4; g2d.dispose();
	 * 
	 * img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); g2d =
	 * img.createGraphics();
	 * g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
	 * RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	 * g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	 * RenderingHints.VALUE_ANTIALIAS_ON);
	 * g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
	 * RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	 * g2d.setRenderingHint(RenderingHints.KEY_DITHERING,
	 * RenderingHints.VALUE_DITHER_ENABLE);
	 * g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
	 * RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	 * g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	 * RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	 * g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
	 * RenderingHints.VALUE_RENDER_QUALITY);
	 * g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
	 * RenderingHints.VALUE_STROKE_PURE); g2d.setFont(font); fm =
	 * g2d.getFontMetrics(); g2d.setColor(Color.BLACK); int y = 0; for (String line
	 * : lines) g2d.drawString(line, 0, y += fm.getHeight()); g2d.dispose(); File f
	 * = new File("Emojis.png"); if (!f.exists()) f.createNewFile();
	 * ImageIO.write(img, "png", f); return f; }
	 */

}
