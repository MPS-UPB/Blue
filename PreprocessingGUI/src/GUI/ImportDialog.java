package GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

public class ImportDialog {
	private static FileDialog dialog1 = null;
	
	public static FileDialog getInstanceXSD() {
		if (dialog1 == null) {
			dialog1 = new FileDialog(MainWindow.shell, SWT.OPEN);
			dialog1.setText("Select XSD");
		}
		return dialog1;
	}
	
	private static FileDialog dialog2 = null;
	
	public static FileDialog getInstanceXML() {
		if (dialog2 == null) {
			dialog2 = new FileDialog(MainWindow.shell, SWT.OPEN);
			dialog2.setText("Select XML");
		}
		return dialog2;
	}
}
