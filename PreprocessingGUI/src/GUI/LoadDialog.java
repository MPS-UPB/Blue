package GUI;

/**
 *  LoadDialog - Clasa care se ocupa de dialogurile de Load. 
 *  
 *  @author Alexandra Binca
 *  @version 1.1, 18 Nov 2012
 */

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class LoadDialog extends AbstractFileDialog {

	public LoadDialog(Shell shell) {
		dialog = new FileDialog(shell, SWT.OPEN);
		dialog.setText("Load Image");
		dialog.setFilterExtensions(new String[] { "*.jpg", "*.bmp", "*.*" });
	}

	@Override
	public void apply() {
	}

	public String getPath() {
		return fileName;
	}
}
