package GUI;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.layout.GridLayout;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MainWindow {

	protected static Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
		shell.setText("SWT Application");
		shell.setLayout(new FormLayout());
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("File");
		
		Menu menuBar = new Menu(mntmFile);
		mntmFile.setMenu(menuBar);
		
		MenuItem mntmLoadImage = new MenuItem(menuBar, SWT.NONE);
		mntmLoadImage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				LoadDialog.getInstance().open();
			}
		});
		mntmLoadImage.setText("Load image");
		
		MenuItem mntmSaveImage = new MenuItem(menuBar, SWT.NONE);
		mntmSaveImage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				SaveDialog.getInstance().open();
			}
		});
		mntmSaveImage.setText("Save image");
		
		MenuItem mntmImportExecutables = new MenuItem(menuBar, SWT.NONE);
		mntmImportExecutables.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				ImportDialog.getInstanceXML().open();
				ImportDialog.getInstanceXSD().open();
			}
		});
		mntmImportExecutables.setText("Import executables");
		
		MenuItem mntmSelectWorkspace = new MenuItem(menuBar, SWT.NONE);
		mntmSelectWorkspace.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				SelectWorkspaceDialog.getInstance().open();
			}
		});
		mntmSelectWorkspace.setText("Select workspace");
		
		MenuItem mntmQuit = new MenuItem(menuBar, SWT.NONE);
		mntmQuit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				System.exit(0);
			}
		});
		mntmQuit.setText("Quit");
		
		Group originalImage = new Group(shell, SWT.NONE);
		originalImage.setToolTipText("Original image loaded from a file.");
		originalImage.setLayout(new BorderLayout(0, 0));
		FormData fd_originalImage = new FormData();
		fd_originalImage.bottom = new FormAttachment(100, -19);
		fd_originalImage.right = new FormAttachment(42);
		fd_originalImage.top = new FormAttachment(0, 5);
		fd_originalImage.left = new FormAttachment(0, 5);
		originalImage.setLayoutData(fd_originalImage);
	
		Group executables = new Group(shell, SWT.NONE);
		executables.setToolTipText("Select one or more executables to apply to image.");
		FormData fd_executables = new FormData();
		fd_executables.left = new FormAttachment(originalImage, 6);
		
		Button btnLoadImage = new Button(originalImage, SWT.NONE);
		btnLoadImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				LoadDialog.getInstance().open();
			}
		});
		btnLoadImage.setLayoutData(BorderLayout.SOUTH);
		btnLoadImage.setText("Load image");
		
		Canvas originalImageCanv = new Canvas(originalImage, SWT.NONE);
		originalImageCanv.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		originalImageCanv.setLayoutData(BorderLayout.CENTER);
		executables.setLayout(new BorderLayout(0, 0));
		fd_executables.bottom = new FormAttachment(0, 229);
		fd_executables.top = new FormAttachment(0, 5);
		executables.setLayoutData(fd_executables);
		
		Group preprocessedImage = new Group(shell, SWT.NONE);
		preprocessedImage.setToolTipText("The result of applying selected executbles on the image.");
		fd_executables.right = new FormAttachment(preprocessedImage, -6);
		
		List list = new List(executables, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI);
		list.setItems(new String[] {"Rotate", "Crop", "????"});
		
		Button btnApply = new Button(executables, SWT.NONE);
		btnApply.setLayoutData(BorderLayout.SOUTH);
		btnApply.setText("Apply");
		preprocessedImage.setLayout(new BorderLayout(0, 0));
		FormData fd_preprocessedImage = new FormData();
		fd_preprocessedImage.bottom = new FormAttachment(100, -19);
		fd_preprocessedImage.right = new FormAttachment(100, -7);
		fd_preprocessedImage.top = new FormAttachment(0, 5);
		fd_preprocessedImage.left = new FormAttachment(56);
		preprocessedImage.setLayoutData(fd_preprocessedImage);
		
		Button btnSaveImage = new Button(preprocessedImage, SWT.NONE);
		btnSaveImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				SaveDialog.getInstance().open();
			}
		});
		btnSaveImage.setLayoutData(BorderLayout.SOUTH);
		btnSaveImage.setText("Save image");
		
		Canvas preprocessedImageCanv = new Canvas(preprocessedImage, SWT.NONE);
		preprocessedImageCanv.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		preprocessedImageCanv.setLayoutData(BorderLayout.CENTER);

	}
}
