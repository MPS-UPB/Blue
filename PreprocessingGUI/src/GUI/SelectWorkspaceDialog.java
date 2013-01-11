package GUI;

/**
 *  SelectWorkspaceDialog - Clasa care se ocupa de dialogul pentru workspace.
 *  
 *  @author Alexandra Binca
 *  @version 1.1, 18 Nov 2012
 */
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;

import Main.MainClass;

public class SelectWorkspaceDialog {

	DirectoryDialog dialog;

	public SelectWorkspaceDialog(Shell shell) {
		dialog = new DirectoryDialog(shell, SWT.OPEN);
		dialog.setText("Select Workspace");
		dialog.setFilterPath(MainClass.getWorkspacePath());
	}

	public void apply() {
		String path = dialog.open();
		if (path != null) {
			MainClass.setWorkspacePath(path);
		}
	}
}
