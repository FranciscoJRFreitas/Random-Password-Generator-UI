import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class RandomPasswordGenerator {

	private HeadlessFrame frame = new HeadlessFrame(new JFrame(), new Color(66, 245, 210));
	private JSpinner spinner = new JSpinner(new SpinnerNumberModel(8/* init */, 0/* min */, 999/* max */, 1/* step */));
	private JTextField resultText = new JTextField();
	private JButton closeBt = new JButton("Close");
	private JButton genBt = new JButton("Generate");
	private JButton copyBt = new JButton("Copy Password");
	private JButton donoBt = new JButton("Donate");
	private JButton clearBt = new JButton("Clear");
	private JCheckBox upCheck = new JCheckBox();
	private JCheckBox lowCheck = new JCheckBox();
	private JCheckBox numCheck = new JCheckBox();
	private JCheckBox symbCheck = new JCheckBox();
	private JLabel copiedAlert = new JLabel("Copied to clipboard!");
	private JLabel pwdLen, pwdGen, nums, up, low, symb, title;

	public RandomPasswordGenerator() {
		initFrame(frame);
		initTextField(frame, resultText);
		initButtons(frame, resultText, closeBt, genBt, copyBt, clearBt, spinner, donoBt, upCheck, lowCheck, numCheck,
				symbCheck, copiedAlert);
		initSpinner(frame, spinner);
		initLabels(frame, copiedAlert, upCheck, lowCheck, numCheck, symbCheck);
		initCheckBoxs(frame, upCheck, lowCheck, numCheck, symbCheck);

	}

	public void run() {
		frame.setVisible(true);
		frame.frameFadeIn();
	}

	private void initLabels(HeadlessFrame frame, JLabel copiedAlert, JCheckBox upCheck, JCheckBox lowCheck,
			JCheckBox numCheck, JCheckBox symbCheck) {

		pwdLen = new JLabel("Enter password length:");
		pwdLen.setBounds(50, 90, 300, 30);
		pwdLen.setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));
		frame.add(pwdLen);
		pwdGen = new JLabel("Generated Password:");
		pwdGen.setBounds(20, 140, 300, 30);
		pwdGen.setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));
		frame.add(pwdGen);

		up = new JLabel("Uppercase");
		up.setBounds(360, 60, 60, 15);
		up.setFont(new Font("Consolas", Font.BOLD, 10));
		frame.add(up);
		low = new JLabel("LowerCase");
		low.setBounds(360, 80, 60, 15);
		low.setFont(new Font("Consolas", Font.BOLD, 10));
		frame.add(low);
		nums = new JLabel("Numbers");
		nums.setBounds(360, 100, 60, 15);
		nums.setFont(new Font("Consolas", Font.BOLD, 10));
		frame.add(nums);
		symb = new JLabel("Symbols");
		symb.setBounds(360, 120, 60, 15);
		symb.setFont(new Font("Consolas", Font.BOLD, 10));
		frame.add(symb);

		copiedAlert.setBounds(105, 275, 150, 30);
		copiedAlert.setFont(new Font("Consolas", Font.BOLD, 8));
		frame.add(copiedAlert);
		copiedAlert.setVisible(false);

		title = new JLabel("Random Password Generator");
		title.setBounds(20, 20, 300, 30);
		title.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
		frame.add(title);
	}

	private void initCheckBoxs(HeadlessFrame frame, JCheckBox upCheck, JCheckBox lowCheck, JCheckBox numCheck,
			JCheckBox symbCheck) {
		upCheck.setBounds(420, 61, 18, 15);
		upCheck.setSelected(true);
		frame.add(upCheck);
		lowCheck.setBounds(420, 80, 18, 15);
		lowCheck.setSelected(true);
		frame.add(lowCheck);
		numCheck.setBounds(420, 100, 18, 15);
		numCheck.setSelected(true);
		frame.add(numCheck);
		symbCheck.setBounds(420, 120, 18, 15);
		symbCheck.setSelected(true);
		frame.add(symbCheck);
	}

	private void initFrame(HeadlessFrame frame) {
		frame.setSize(500, 400);
		frame.setAlwaysOnTop(true);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initSpinner(HeadlessFrame frame, JSpinner spinner) {
		spinner.setBounds(240, 92, 75, 30);
		frame.add(spinner);
	}

	private void initTextField(HeadlessFrame frame, JTextField resultText) {
		resultText.setBounds(20, 170, 400, 30);
		resultText.setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));
		frame.add(resultText);
	}

	private void initButtons(HeadlessFrame frame, JTextField resultText, JButton closeBt, JButton genBt, JButton copyBt,
			JButton clearBt, JSpinner spinner, JButton donoBt, JCheckBox upCheck, JCheckBox lowCheck,
			JCheckBox numCheck, JCheckBox symbCheck, JLabel copiedAlert) {
		closeBt.setBounds(275, 300, 150, 50);
		closeBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.frameFadeOut();
				System.exit(0);
			}
		});
		frame.add(closeBt);

		genBt.setBounds(175, 220, 150, 50);
		genBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultText.setText("");
				resultText.setText(rng((int) spinner.getValue(), upCheck.isSelected(), lowCheck.isSelected(),
						numCheck.isSelected(), symbCheck.isSelected()));
				copiedAlert.setVisible(false);
			}
		});
		frame.add(genBt);

		copyBt.setBounds(75, 300, 150, 50);
		copyBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(new StringSelection(resultText.getText()), null);
				copiedAlert.setVisible(true);
			}
		});
		frame.add(copyBt);

		donoBt.setBounds(410, 370, 80, 20);
		donoBt.setFont(new Font(Font.MONOSPACED, Font.BOLD, 8));
		donoBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				URI url;
				try {
					url = new URI("https://www.paypal.com/paypalme/franfreitas2002");
					Desktop.getDesktop().browse(url);
					resultText.setText("");
					resultText.setText("Thank you!!!");
				} catch (URISyntaxException | IOException e1) {
					resultText.setText("");
					resultText.setText("Unexpected error, try again.");
				}
			}
		});
		frame.add(donoBt);

		clearBt.setBounds(435, 172, 50, 25);
		clearBt.setFont(new Font(Font.MONOSPACED, Font.BOLD, 5));
		clearBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultText.setText("");
			}
		});
		frame.add(clearBt);
	}

	private String rng(int len, boolean up, boolean low, boolean num, boolean symb) {
		Random r = new Random();
		String res = "";
		if (!up && !low && !num && !symb)
			return "Select at least one element!";
		while (len > 0) {
			int n = r.nextInt(4);
			if (n == 0 && up) {
				res += (char) (r.nextInt(25) + 65);
				len--;
			} else if (n == 1 && num) {
				res += (r.nextInt(9));
				len--;
			} else if (n == 2 && low) {
				res += (char) (r.nextInt(25) + 97);
				len--;
			} else if (n == 3 && symb) {
				int tempR = r.nextInt(14) + 33;
				res += tempR == 34 ? (char) (33) : tempR == 39 ? (char) (38) : (char) (tempR); // eliminating " and '
																								// from symbols
				len--;
			}
		}
		return res;
	}

}
