package GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

public class SaveDialog {
	
	private static FileDialog dialog = null;
	
	public static FileDialog getInstance() {
		if (dialog == null) {
			dialog = new FileDialog(MainWindow.shell, SWT.SAVE);
			dialog.setText("Save Image");
		}
		return dialog;
	}
}
