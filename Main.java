import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		initLoadingScreen();
		initComponentsUI();
	}

	private static void initLoadingScreen() {
		LoadingScreen ls = new LoadingScreen(new JFrame());
		ls.run();
	}

	private static void initComponentsUI() {
		RandomPasswordGenerator rpg = new RandomPasswordGenerator();
		rpg.run();
	}

}
