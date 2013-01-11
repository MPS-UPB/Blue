package GUI;

/**
 * Actions - Clasa care se ocupa de executabile si de aplicarea lor.
 * 
 * @author Alexandra Binca
 * @version 1.0, 17 Nov 2012
 */

import java.io.File;
import java.io.IOException;

import javax.xml.transform.TransformerException;

import org.apache.commons.io.FileUtils;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.List;

import Main.MainClass;
import Parser.Parser;
import Parser.XMLCreate;

public class Actions {

	/**
	 * Face refresh pe lista de executabile, cautand in workspace 2 fisiere cu
	 * extensiile exe si xsd si acelasi nume.
	 */
	public static void refresh(List execList) {
		execList.removeAll();
		File folder_exe = new File(MainClass.getWorkspacePath());
		File folder_xsd = new File(MainClass.getXMLSchemasPath());
		File[] listOfFiles_exe = folder_exe.listFiles();
		File[] listOfFiles_xsd = folder_xsd.listFiles();
		if (listOfFiles_exe == null || listOfFiles_xsd == null) {
			return;
		}
		for (int i = 0; i < listOfFiles_exe.length; i++) {
			File f = listOfFiles_exe[i];
			String fName = f.getName();
			if (fName.endsWith(".exe")) {
				String execName = fName.substring(0, fName.length() - 4);
				for (int j = 0; j < listOfFiles_xsd.length; j++) {
					File g = listOfFiles_xsd[j];
					String gName = g.getName();
					if (gName.endsWith(".xsd")
							&& execName.equals(gName.substring(0,
									gName.length() - 4))) {
						execList.add(execName);
					}
				}
			}
		}

	}

	/** Aplica transformarile selectate. */
	public static void apply(final Canvas originalImageCanv,
			final Canvas preprocessedImageCanv, List execList) {

		String inputFile = MainClass.getIntermediateImagePath();
		String outputFile = MainClass.getIntermediateImagePath();
		boolean canceled = false;

		for (int i = 0; i < execList.getSelection().length; i++) {
			String executablePath = MainClass.getWorkspacePath()
					+ execList.getSelection()[i] + ".exe";
			Parser parser = new Parser(new File(MainClass.getXMLSchemasPath()
					+ execList.getSelection()[i] + ".xsd"));
			parser.parseSchema();
			Transform transform = new Transform(parser.simpleTypeList,
					parser.complexTypeList,
					parser.elementGroup.getElementsList(), inputFile,
					outputFile);
			Window window = transform.createWindow();
			window.drawWindow();
			window.setVisible(true);

			while (window.isVisible()) {
				try {
					Thread.sleep(10);
				} catch (Exception ex) {
				}
			}

			if (window.process == -1) {
				canceled = true;
				break; // a fost apasat x (close)
			}

			if (window.process == 1) { // a fost apasat Process
				XMLCreate x = new XMLCreate();
				try {
					x.xmlCreate(window.getParametersValue(), parser);
				} catch (TransformerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			String cmd = executablePath + " " + MainClass.getParametersPath();

			try {
				Runtime.getRuntime()
						.exec(cmd, null, new File(MainClass.getWorkspacePath()))
						.waitFor();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (!canceled) {
			try {
				FileUtils.copyFile(
						new File(MainClass.getIntermediateImagePath()),
						new File(MainClass.getProcessedImagePath()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ImageLoader.loadImageInCanvas(MainClass.getProcessedImagePath(),
					preprocessedImageCanv);
		} else {
			try {
				FileUtils.copyFile(new File(MainClass.getProcessedImagePath()),
						new File(MainClass.getIntermediateImagePath()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
