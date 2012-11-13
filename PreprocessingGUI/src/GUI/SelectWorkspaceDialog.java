package GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

public class SelectWorkspaceDialog {
	private static FileDialog dialog = null;
	
	public static FileDialog getInstance() {
		if (dialog == null) {
			dialog = new FileDialog(MainWindow.shell, SWT.OPEN);
			dialog.setText("Select Workspace");
		}
		return dialog;
	}
}
