import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Window;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class HeadlessFrame extends Window {

	public static final int DO_NOTHING_ON_CLOSE = 0;

	public static final int HIDE_ON_CLOSE = 1;

	public static final int DISPOSE_ON_CLOSE = 2;

	public static final int EXIT_ON_CLOSE = 3;

	private int defaultCloseOperation = HIDE_ON_CLOSE;

	public JFrame frame;

	public HeadlessFrame(JFrame e, Color c) throws HeadlessException {
		super(e);
		frame = e;
		setBackground(c);
	}

	public HeadlessFrame(JFrame e) throws HeadlessException {
		super(e);
		frame = e;
		setBackground(new Color(0, 0, 0, 0));
	}

	public void setDefaultCloseOperation(int operation) {
		if (operation != DO_NOTHING_ON_CLOSE && operation != HIDE_ON_CLOSE && operation != DISPOSE_ON_CLOSE
				&& operation != EXIT_ON_CLOSE) {
			throw new IllegalArgumentException("defaultCloseOperation must be"
					+ " one of: DO_NOTHING_ON_CLOSE, HIDE_ON_CLOSE," + " DISPOSE_ON_CLOSE, or EXIT_ON_CLOSE");
		}

		if (operation == EXIT_ON_CLOSE) {
			SecurityManager security = System.getSecurityManager();
			if (security != null) {
				security.checkExit(0);
			}
		}
		if (this.defaultCloseOperation != operation) {
			int oldValue = this.defaultCloseOperation;
			this.defaultCloseOperation = operation;
			firePropertyChange("defaultCloseOperation", oldValue, operation);
		}
	}

	public void frameFadeIn() {
		try {
			for (int i = 0; i < 100; i++) {
				this.setOpacity((float) i / 100);
				TimeUnit.MICROSECONDS.sleep(2222);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void frameFadeOut() {
		try {
			for (int i = 100; i >= 0; i--) {
				this.setOpacity((float) i / 100);
				TimeUnit.MICROSECONDS.sleep(111);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public JFrame getFrame() {
		return frame;
	}

}