package GUI;

/**
 * Actions - Clasa care se ocupa de executabile si de aplicarea lor.
 * 
 * @author Alexandra Binca
 * @version 1.0, 17 Nov 2012
 */

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.List;

public class Actions {

	/**
	 * Face refresh pe lista de executabile, cautand in workspace 2 fisiere cu
	 * extensiile exe si xsd si acelasi nume.
	 */
	public static void refresh(List execList) {
		execList.removeAll();
		File folder = new File(MainClass.getWorkspacePath());
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			File f = listOfFiles[i];
			String fName = f.getName();
			if (fName.endsWith(".exe")) {
				String execName = fName.substring(0, fName.length() - 4);
				for (int j = 0; j < listOfFiles.length; j++) {
					File g = listOfFiles[j];
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
		preprocessedImageCanv.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				GC gc = new GC(originalImageCanv);
				int x = originalImageCanv.getSize().x;
				int y = originalImageCanv.getSize().y;
				Image image = new Image(originalImageCanv.getDisplay(), x, y);
				preprocessedImageCanv.setSize(x, y);
				gc.copyArea(image, 0, 0);
				Image copy = new Image(preprocessedImageCanv.getDisplay(),
						image, SWT.IMAGE_COPY);

				e.gc.drawImage(copy, 0, 0);
				gc.dispose();
				image.dispose();
				copy.dispose();
			}
		});
		preprocessedImageCanv.redraw();

		for (int i = 0; i < execList.getSelection().length; i++) {
			if (execList.getSelection()[i].equals("rotate")) {
				Rotate rotate = new Rotate();
				rotate.createRotateWindow("Test");
			}
		}

	}
}
