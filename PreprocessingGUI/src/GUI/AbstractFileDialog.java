package GUI;

import org.eclipse.swt.widgets.FileDialog;

/**
 * AbstractFileDialog - Clasa abstracta pentru dialogurile de fisiere.
 * 
 * @author Alexandra Binca
 * @version 1.0, 18 Nov 2012
 */

public abstract class AbstractFileDialog {

	protected static FileDialog dialog = null;
	protected String fileName;

	/**
	 * Metoda abstracta care realizeaza actiunea ce se vrea in urma folosirii
	 * dialogului.
	 */
	public abstract void apply();

	/**
	 * Deschide dialogul si realizeaza actiunile specifice fiecarui tip de
	 * dialog.
	 * 
	 * @return
	 */
	public void open() {
		fileName = dialog.open();
		if (fileName != null) {
			apply();
		}
	}
}
