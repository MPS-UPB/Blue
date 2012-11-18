package GUI;

/**
 *  SaveDialog - Clasa care se ocupa de dialogul de Save.
 *  
 *  @author Alexandra Binca
 *  @version 1.1, 18 Nov 2012
 */
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class SaveDialog extends AbstractFileDialog {

	Canvas canv;

	public SaveDialog(Shell shell, Canvas ImageCanv) {
		dialog = new FileDialog(shell, SWT.SAVE);
		dialog.setText("Save Image");
		canv = ImageCanv;

	}

	@Override
	public void apply() {
		GC gc = new GC(canv);
		Image image = new Image(canv.getDisplay(), canv.getSize().x,
				canv.getSize().y);
		gc.copyArea(image, 0, 0);
		ImageLoader loader = new ImageLoader();
		loader.data = new ImageData[] { image.getImageData() };
		loader.save(fileName, SWT.IMAGE_JPEG);
		gc.dispose();

	}
}
