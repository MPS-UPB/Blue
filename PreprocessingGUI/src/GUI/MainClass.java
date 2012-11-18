package GUI;

public class MainClass {
	private static String workspacePath;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			workspacePath = window.getClass().getClassLoader().getResource(".")
					.getPath();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setWorkspacePath(String path) {
		workspacePath = path;
	}

	public static String getWorkspacePath() {
		return workspacePath;
	}
}
