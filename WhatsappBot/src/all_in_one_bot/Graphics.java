package all_in_one_bot;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Graphics {

	static JFrame frame;
	static JTextArea text;
	static JTextField numOfSpams;
	static JButton generate;
	static JButton hide;
	static JCheckBox isnumbers;
	static JCheckBox isBirthday;
	static JLabel adminsLabel;
	static JTextArea admins;
	static JTextField bName;
	static JTextField bEnglishName;
	static JLabel textToSpamLabel;

	public static void createGraphics() {
		frame = new JFrame("Whatsapp Bot");
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize((int) (dimension.getWidth() / 1.5), (int) (dimension.getHeight() / 1.2));
		frame.setLocation((int) (dimension.getWidth() / 2) - (int) (frame.getWidth() / 2),
				(int) (dimension.getHeight() / 2) - (int) (frame.getHeight() / 2));
		frame.getContentPane().setBackground(new Color(92, 92, 92));
		frame.setFocusable(true);
		double ratio = (((double) frame.getWidth() + (double) frame.getHeight()) / 2d)
				/ ((1920d / 3d + (1080d / 1.2d)) / 2d);
		Font font = new Font("or", Font.ITALIC, (int) (18d * ratio));
		text = createTextArea(font);
		JScrollPane scrollPane = new JScrollPane(text);
		scrollPane.setBounds(10, 70, frame.getWidth() / 2 - 40, frame.getHeight() / 2);
		numOfSpams = createTextField("numOfSpams", font);
		numOfSpams.setBounds(15, scrollPane.getY() + scrollPane.getHeight() + 30, frame.getWidth() / 5,
				frame.getHeight() / 20);
		bName = createTextField("birthdayName", font);
		bName.setLocation(scrollPane.getWidth() + 20, 70);
		bName.setSize(frame.getWidth() - bName.getX() - 30, 60);
		bEnglishName = createTextField("birthdayEnglishName", font);
		bEnglishName.setLocation(scrollPane.getWidth() + 20, bName.getY() + bName.getHeight() + 70);
		bEnglishName.setSize(frame.getWidth() - bName.getX() - 30, 60);
		isnumbers = new JCheckBox("isNumbers");
		isnumbers.setBounds(frame.getWidth() - (frame.getWidth() / 5 + 20),
				scrollPane.getY() + scrollPane.getHeight() + 30, frame.getWidth() / 5, frame.getHeight() / 20);
		isnumbers.setBackground(new Color(92, 92, 92));
		isnumbers.setForeground(Color.white);
		Font font2 = new Font("amit", Font.BOLD, (int) (15d * ratio));
		generate = new JButton("<html><center>Spam</center></html>");
		generate.setBackground(new Color(71, 150, 108));
		generate.setForeground(Color.white);
		generate.setBounds(frame.getWidth() - ((frame.getWidth() / 4) + 60),
				scrollPane.getHeight() + (frame.getHeight() - scrollPane.getHeight()) / 3, frame.getWidth() / 4,
				frame.getHeight() / 20);
		generate.setFont(font2);
		admins = createTextArea(font);
		JScrollPane scrollPane2 = new JScrollPane(admins);
		scrollPane2.setLocation(numOfSpams.getX() + numOfSpams.getWidth() + 10,
				scrollPane.getY() + scrollPane.getHeight() + 60);
		scrollPane2.setSize(generate.getX() - scrollPane2.getX() - 10, (frame.getHeight() - scrollPane2.getY()) / 2);
		isBirthday = new JCheckBox("isBirthday");
		isBirthday.setBounds(15, numOfSpams.getY() + numOfSpams.getHeight() + 30, frame.getWidth() / 5,
				frame.getHeight() / 20);
		isBirthday.setBackground(new Color(92, 92, 92));
		isBirthday.setForeground(Color.white);
		adminsLabel = createLabel("admins:", font);
		adminsLabel.setBounds(admins.getX(), scrollPane.getY() + scrollPane.getHeight() + 10, admins.getWidth() - 100,
				50);
		textToSpamLabel = createLabel("spam text:", font);
		textToSpamLabel.setBounds(10, 10, scrollPane.getWidth(), 50);
		hide = new JButton("<html><center>Hide</center></html>");
		hide.setBackground(new Color(71, 150, 108));
		hide.setForeground(Color.white);
		hide.setBounds(frame.getWidth() - ((frame.getWidth() / 4) + 60),
				scrollPane.getHeight() + (frame.getHeight() - scrollPane.getHeight()) / 3 + generate.getHeight() + 10,
				frame.getWidth() / 4, frame.getHeight() / 20);
		hide.setFont(font2);
		frame.add(hide);
		frame.add(generate);
		frame.add(isBirthday);
		frame.add(isnumbers);
		frame.add(bName);
		frame.add(textToSpamLabel);
		frame.add(adminsLabel);
		frame.add(bEnglishName);
		frame.add(numOfSpams);
		frame.add(scrollPane2);
		frame.add(scrollPane);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	private static JLabel createLabel(String text, Font font) {
		JLabel label = new JLabel(text, JLabel.CENTER);
		label.setForeground(Color.white);
		label.setFont(font);
		return label;
	}

	private static JTextField createTextField(String name, Font font) {
		JTextField textField = new JTextField(name);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(font);
		textField.setBackground(new Color(59, 59, 59));
		textField.setForeground(Color.white);
		textField.setCaretColor(Color.white);
		textField.setSelectionColor(Color.lightGray);
		return textField;
	}

	private static JTextArea createTextArea(Font font) {
		JTextArea text = new JTextArea();
		text.setSize(frame.getWidth(), frame.getHeight());
		text.setBackground(new Color(59, 59, 59));
		text.setForeground(Color.white);
		text.setCaretColor(Color.white);
		text.setFont(font);
		text.setSelectionColor(Color.lightGray);
		return text;
	}

	public static void listenToGraphicEvents() {
		generate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SpammerV2AndBot.spam();
			}
		});
		numOfSpams.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					Integer.parseInt(numOfSpams.getText());
				} catch (Exception e) {
					numOfSpams.setText("numOfSpams");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (numOfSpams.getText().equals("numOfSpams"))
					numOfSpams.setText("");

			}
		});

		frame.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				frame.requestFocus();
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});

		hide.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					String osname = System.getProperty("os.name");
					if (osname.startsWith("Linux")) {
						Desktop.getDesktop().open(new File("/home/or/Documents/Java/Hide.jar"));
					} else if (osname.startsWith("Windows")) {
						Desktop.getDesktop().open(new File("C:\\Users\\ornev\\Documents\\!Desktop\\Hide.jar"));
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		isBirthday.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				SpammerV2AndBot.isBirthday = isBirthday.isSelected();
			}
		});

		admins.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				SpammerV2AndBot.adminNames = admins.getText().split("\n");
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				SpammerV2AndBot.adminNames = admins.getText().split("\n");
			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {

			}
		});

		bName.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (!bName.getText().equals("birthdayName"))
					SpammerV2AndBot.birthdayName = bName.getText();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				if (!bName.getText().equals("birthdayName"))
					SpammerV2AndBot.birthdayName = bName.getText();
				}

			@Override
			public void changedUpdate(DocumentEvent arg0) {

			}
		});
		
		bEnglishName.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (!bEnglishName.getText().equals("birthdayEnglishName"))
					SpammerV2AndBot.birthdayEnglishName = bEnglishName.getText();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				if (!bEnglishName.getText().equals("birthdayEnglishName"))
					SpammerV2AndBot.birthdayEnglishName = bEnglishName.getText();
				}

			@Override
			public void changedUpdate(DocumentEvent arg0) {

			}
		});

		listenToJTextField(bName, "birthdayName");
		listenToJTextField(bEnglishName, "birthdayEnglishName");

	}

	private static void listenToJTextField(JTextField field, String hint) {
		field.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (field.getText().equals(""))
					field.setText(hint);
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (field.getText().equals(hint))
					field.setText("");

			}
		});
	}

}
