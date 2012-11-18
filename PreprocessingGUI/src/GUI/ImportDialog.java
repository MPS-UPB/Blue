package GUI;

/**
 *  ImportDialog - Clasa care se ocupa de dialogul de import de fisiere. 
 *  
 *  @author Alexandra Binca
 *  @version 1.1, 18 Nov 2012
 */

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class ImportDialog extends AbstractFileDialog {

	String path;
	Shell shell;

	public ImportDialog(Shell shell, String title, String filter) {
		this.shell = shell;
		dialog = new FileDialog(shell, SWT.OPEN | SWT.MULTI);
		dialog.setText(title);
		dialog.setFilterExtensions(new String[] { filter });
		path = MainClass.getWorkspacePath();
	}

	@Override
	public void apply() {
		for (String fName : dialog.getFileNames()) {
			File source = new File(dialog.getFilterPath() + "/" + fName);
			File destination = new File(path + "/" + source.getName());
			if (destination.exists()) {
				MessageBox dlog = new MessageBox(shell, SWT.ICON_WARNING
						| SWT.YES | SWT.NO);
				dlog.setText("File exists");
				dlog.setMessage("File " + fName
						+ " already exists.Do you want to overwrite?");
				int status = dlog.open();
				if (status == SWT.NO) {
					continue;
				}
			} else {
				try {
					FileUtils.copyFile(source, destination);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
