import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class LoadingScreen extends HeadlessFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static JProgressBar pbar = new JProgressBar();

	private static JLabel msg = new JLabel("", SwingConstants.CENTER);

	public LoadingScreen(JFrame e) throws HeadlessException {
		super(e);
		this.setSize(500, 100);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		pBarInit();
		msgInit();
	}
	
	private void pBarInit() {
		pbar.setValue(0);
		pbar.setBounds(0, 0, 500, 50);
		pbar.setStringPainted(true);
		pbar.setFont(new Font("Calibri", Font.BOLD, 20));
		pbar.setForeground(new Color(66, 245, 210));
		pbar.setBackground(Color.white);
	}

	private void msgInit() {
		msg.setBounds(100, 50, 300, 50);
		msg.setBackground(new Color(0, 0, 0, 0));
		msg.setForeground(Color.white);
		msg.setOpaque(true);
		msg.setFont(new Font("Calibri", Font.PLAIN, 20));
	}

	public void run() {
		this.setComponentsVisible(true);
		this.frameFadeIn();
		this.fill();
		this.frameFadeOut();
		this.setComponentsVisible(false);
	}
	
	private void setComponentsVisible(boolean toggle) {
		if (toggle) {
			this.add(pbar);
			this.add(msg);
			msg.setVisible(true);
			pbar.setVisible(true);
			this.setVisible(true);
		} else {
			pbar.setVisible(false);
			msg.setVisible(false);
			this.setVisible(false);
		}
	}

	public void fill() {
		int i = 0;
		int red = 0;
		int green = 0;
		int blue = 0;
		msg.setText("Loading resources...");

		try {
			while (i <= 100) {
				// fill the menu bar
				pbar.setValue(i);
				if (i < 25) {
					pbar.setForeground(new Color(red, green += 10, blue += 10));
					msg.setForeground(new Color(red, green, blue));
				} else if (i < 50) {
					pbar.setForeground(new Color(red += 8, green -= 5, blue -= 6));
					msg.setForeground(new Color(red, green, blue));
				} else if (i < 75) {
					pbar.setForeground(new Color(red -= 2, green += 4, blue -= 2));
					msg.setForeground(new Color(red, green, blue));
				} else {
					pbar.setForeground(new Color(red += 3, green -= 6, blue += 4));
					msg.setForeground(new Color(red, green, blue));
				}
				// delay the thread
				TimeUnit.MILLISECONDS.sleep(35);
				i += 1;
			}
			TimeUnit.MILLISECONDS.sleep(500);

		} catch (Exception e) {
			System.out.println("Error in fill pbar");
		}
	}

}
