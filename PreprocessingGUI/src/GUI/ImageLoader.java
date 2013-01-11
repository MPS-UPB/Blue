package GUI;

import java.util.HashMap;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Canvas;

public class ImageLoader {
	private static HashMap<Canvas, PaintListener> hm = new HashMap<Canvas, PaintListener>();

	public static void loadImageInCanvas(final String imagePath,
			final Canvas canv) {

		PaintListener pl = new PaintListener() {
			public void paintControl(PaintEvent e) {
				ImageData imgData = new ImageData(imagePath);
				Image img = new Image(canv.getDisplay(), imagePath);

				int x = canv.getSize().x;
				int y = canv.getSize().y;

				int iw = imgData.width;
				int ih = imgData.height;
				double rpWidth = (double) iw / (double) x;
				double rpHeight = (double) ih / (double) y;
				if (rpWidth <= 1 && rpHeight <= 1) {
					x = iw;
					y = ih;
				} else {
					x = (int) (iw / Math.max(rpWidth, rpHeight));
					y = (int) (ih / Math.max(rpWidth, rpHeight));
				}

				e.gc.drawImage(img, 0, 0, iw, ih, 0, 0, x, y);

				canv.setSize(x, y);
				img.dispose();
			}
		};

		PaintListener oldPl = hm.put(canv, pl);
		if (oldPl != null)
			canv.removePaintListener(oldPl);
		canv.addPaintListener(pl);
		canv.redraw();
	}
}
