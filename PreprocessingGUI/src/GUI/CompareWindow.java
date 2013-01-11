package GUI;

/**
 *  CompareWindow - Clasa care construieste fereastra pentru Compare. 
 *  Foloseste biblioteca SWT si este generata automat cu Window Builder.
 *  
 *  @author Alexandra Binca
 *  @version 1.0, 18 Nov 2012
 */
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import swing2swt.layout.BorderLayout;

public class CompareWindow {

	protected Shell shell;
	private Canvas canvasImage1;
	private Canvas canvasImage2;
	private String pathImage1 = "";
	private String pathImage2 = "";

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(800, 600);
		shell.setText("Compare Images");
		shell.setLayout(new BorderLayout(0, 0));

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(BorderLayout.SOUTH);
		composite.setLayout(new FormLayout());

		Button btnCompare = new Button(composite, SWT.NONE);
		FormData fd_btnCompare = new FormData();
		fd_btnCompare.left = new FormAttachment(0, 590);
		fd_btnCompare.bottom = new FormAttachment(100);
		btnCompare.setLayoutData(fd_btnCompare);
		btnCompare.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO
				ImagePlus imp1 = IJ.openImage(getImage1Path());
				ImagePlus imp2 = IJ.openImage(getImage2Path());
				ImageCalculator ic =  new ImageCalculator();
				ImagePlus imp3 = ic.run("Substract create", imp1, imp2);
				imp3.show();
			}
		});
		btnCompare.setText("Compare");

		Button btnClose = new Button(composite, SWT.NONE);
		fd_btnCompare.right = new FormAttachment(100, -120);
		FormData fd_btnClose = new FormData();
		fd_btnClose.bottom = new FormAttachment(btnCompare, 0, SWT.BOTTOM);
		fd_btnClose.left = new FormAttachment(btnCompare, 6);
		fd_btnClose.right = new FormAttachment(100, -26);
		btnClose.setLayoutData(fd_btnClose);
		btnClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.close();
			}
		});
		btnClose.setText("Close");

		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setLayoutData(BorderLayout.CENTER);

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new BorderLayout(0, 0));

		Composite composite_3 = new Composite(composite_1, SWT.NONE);
		composite_3.setLayoutData(BorderLayout.SOUTH);
		composite_3.setLayout(new FormLayout());

		Button btnLoadImage1 = new Button(composite_3, SWT.NONE);
		btnLoadImage1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				LoadDialog dialog = new LoadDialog(shell);
				dialog.open();
				pathImage1 = dialog.getPath();
				ImageLoader.loadImageInCanvas(pathImage1, canvasImage1);
			}
		});
		FormData fd_btnLoadImage1 = new FormData();
		fd_btnLoadImage1.top = new FormAttachment(0);
		fd_btnLoadImage1.left = new FormAttachment(0, 145);
		fd_btnLoadImage1.right = new FormAttachment(0, 242);
		btnLoadImage1.setLayoutData(fd_btnLoadImage1);
		btnLoadImage1.setText("Load Image");

		canvasImage1 = new Canvas(composite_1, SWT.NONE);
		canvasImage1.setLayoutData(BorderLayout.CENTER);

		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setLayout(new BorderLayout(0, 0));

		Composite composite_4 = new Composite(composite_2, SWT.NONE);
		composite_4.setLayoutData(BorderLayout.SOUTH);
		composite_4.setLayout(new FormLayout());

		Button btnLoadImage2 = new Button(composite_4, SWT.NONE);
		btnLoadImage2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				LoadDialog dialog = new LoadDialog(shell);
				dialog.open();
				pathImage2 = dialog.getPath();
				ImageLoader.loadImageInCanvas(pathImage2, canvasImage2);
			}
		});
		FormData fd_btnLoadImage2 = new FormData();
		fd_btnLoadImage2.bottom = new FormAttachment(100);
		fd_btnLoadImage2.top = new FormAttachment(0);
		fd_btnLoadImage2.right = new FormAttachment(100, -148);
		fd_btnLoadImage2.left = new FormAttachment(0, 162);
		btnLoadImage2.setLayoutData(fd_btnLoadImage2);
		btnLoadImage2.setText("Load Image");

		canvasImage2 = new Canvas(composite_2, SWT.NONE);
		canvasImage2.setLayoutData(BorderLayout.CENTER);
		sashForm.setWeights(new int[] { 1, 1 });
	}

	public String getImage1Path() {
		return pathImage1;
	}

	public String getImage2Path() {
		return pathImage2;
	}
}
