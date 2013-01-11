package GUI;

/**
 *  SaveDialog - Clasa care se ocupa de dialogul de Save.
 *  
 *  @author Alexandra Binca
 *  @version 1.1, 18 Nov 2012
 */
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class SaveDialog extends AbstractFileDialog {

	private String processedImagePath;

	public SaveDialog(Shell shell, String processedImagePath) {
		dialog = new FileDialog(shell, SWT.SAVE);
		dialog.setText("Save Image");
		this.processedImagePath = processedImagePath;

	}

	@Override
	public void apply() {
		try {
			FileUtils
					.copyFile(new File(processedImagePath), new File(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
