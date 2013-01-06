package Main;

import GUI.MainWindow;

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
					.getPath().substring(1);
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

	public static String getProcessedImagePath() {
		return workspacePath + "processedImage.jpg";
	}

	public static String getIntermediateImagePath() {
		return workspacePath + "intermediateImage.jpg";
	}

	public static String getParametersPath() {
		return getWorkspacePath() + "parametri.xml";
	}

	public static String getXMLSchemasPath() {
		return getWorkspacePath() + "\\xml_schemas\\";
	}
}
