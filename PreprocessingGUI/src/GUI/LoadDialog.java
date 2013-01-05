package GUI;

/**
 *  LoadDialog - Clasa care se ocupa de dialogurile de Load. 
 *  
 *  @author Alexandra Binca
 *  @version 1.1, 18 Nov 2012
 */

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class LoadDialog extends AbstractFileDialog {

	Canvas canv;

	public LoadDialog(Shell shell, Canvas imageCanv) {
		dialog = new FileDialog(shell, SWT.OPEN);
		dialog.setText("Load Image");
		canv = imageCanv;
	}

	@Override
	public void apply() {
		canv.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				ImageData imgData = new ImageData(fileName);
				Image img = new Image(canv.getDisplay(), imgData);
				int x = canv.getSize().x;
				int y = canv.getSize().y;
				double rpWidth = (double) imgData.width / (double) x;
				double rpHeight = (double) imgData.height / (double) y;
				if (rpWidth <= 1 && rpHeight <= 1) {
					x = imgData.width;
					y = imgData.height;
				} else {
					x = (int) (imgData.width / Math.max(rpWidth, rpHeight));
					y = (int) (imgData.height / Math.max(rpWidth, rpHeight));
				}

				e.gc.drawImage(img, 0, 0, imgData.width, imgData.height, 0, 0,
						x, y);

				canv.setSize(x, y);
				img.dispose();
			}
		});
		canv.redraw();
	}

	public String getPath() {
		return fileName;
	}
}
