package all_in_one_bot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;

public class Hide {
	JFrame frame;
	String pass = "ON427351!";

	public Hide() {
		frame = new JFrame();
		frame.setLocation(0, 0);
		frame.setResizable(false);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(dimension.width, dimension.height);
		frame.setAlwaysOnTop(true);
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JPasswordField password = new JPasswordField();
		password.setBounds(frame.getWidth()/2-frame.getWidth()/8, 20, frame.getWidth()/4, 100);
		password.setEchoChar('*');
		JButton button = new JButton("Close");
		button.setBounds(frame.getWidth()/2-frame.getWidth()/8, 200, frame.getWidth()/4, 100);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (String.valueOf(password.getPassword()).equals(pass))
					System.exit(0);
			}
		});
		frame.add(password);
		frame.add(button);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Hide();
	}
}
