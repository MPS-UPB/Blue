package GUI;

/**
 *  MainWindow - Clasa care construieste fereastra principala. 
 *  Foloseste biblioteca SWT si este generata automat cu Window Builder.
 *  
 *  @author Alexandra Binca
 *  @version 1.1, 18 Nov 2012
 */

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import swing2swt.layout.BorderLayout;

public class MainWindow {

	private Shell shell;
	private Canvas originalImageCanv;
	private Canvas preprocessedImageCanv;
	private List executablesList;

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
		shell.setText("Preprocessing Image");
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
				LoadDialog dialog = new LoadDialog(shell, originalImageCanv);
				dialog.open();
			}
		});
		mntmLoadImage.setText("Load Image");

		MenuItem mntmSaveImage = new MenuItem(menuBar, SWT.NONE);
		mntmSaveImage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				SaveDialog dialog = new SaveDialog(shell, preprocessedImageCanv);
				dialog.open();
			}
		});
		mntmSaveImage.setText("Save Image");

		MenuItem mntmImportExecutables = new MenuItem(menuBar, SWT.CASCADE);
		mntmImportExecutables.setText("Import Executables");

		Menu menu_imports = new Menu(mntmImportExecutables);
		mntmImportExecutables.setMenu(menu_imports);

		MenuItem mntmImportXsd = new MenuItem(menu_imports, SWT.NONE);
		mntmImportXsd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				ImportDialog dialog = new ImportDialog(shell, "Select XSD",
						"*.xsd");
				dialog.open();
			}
		});
		mntmImportXsd.setText("Import XSD");

		MenuItem mntmImportExe = new MenuItem(menu_imports, SWT.NONE);
		mntmImportExe.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				ImportDialog dialog = new ImportDialog(shell, "Select EXE",
						"*.exe");
				dialog.open();
			}
		});
		mntmImportExe.setText("Import EXE");

		MenuItem mntmSelectWorkspace = new MenuItem(menuBar, SWT.NONE);
		mntmSelectWorkspace.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				SelectWorkspaceDialog dialog = new SelectWorkspaceDialog(shell);
				dialog.apply();
			}
		});
		mntmSelectWorkspace.setText("Select Workspace");

		MenuItem mntmQuit = new MenuItem(menuBar, SWT.NONE);
		mntmQuit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				System.exit(0);
			}
		});
		mntmQuit.setText("Quit");

		MenuItem mntmHelp = new MenuItem(menu, SWT.CASCADE);
		mntmHelp.setText("Help");

		Menu menu_help = new Menu(mntmHelp);
		mntmHelp.setMenu(menu_help);

		MenuItem mntmHelpContents = new MenuItem(menu_help, SWT.NONE);
		mntmHelpContents.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				MessageBox dlog = new MessageBox(shell, SWT.ICON_INFORMATION
						| SWT.OK);
				dlog.setText("Help");
				dlog.setMessage("Citeste manualul!");
				dlog.open();
			}
		});
		mntmHelpContents.setText("Help Contents");

		MenuItem mntmAbout = new MenuItem(menu_help, SWT.NONE);
		mntmAbout.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				MessageBox dlog = new MessageBox(shell, SWT.ICON_INFORMATION
						| SWT.OK);
				dlog.setText("About");
				dlog.setMessage("Managemantul Proiectelor Software"
						+ System.getProperty("line.separator")
						+ "Preprocessing Graphical User Interface"
						+ System.getProperty("line.separator")
						+ "Echipa BLUE - 2012");
				dlog.open();
			}
		});
		mntmAbout.setText("About");

		Group originalImage = new Group(shell, SWT.NONE);
		originalImage.setLayout(new BorderLayout(0, 0));
		FormData fd_originalImage = new FormData();
		fd_originalImage.bottom = new FormAttachment(100, -19);
		fd_originalImage.right = new FormAttachment(42);
		fd_originalImage.top = new FormAttachment(0, 5);
		fd_originalImage.left = new FormAttachment(0, 5);
		originalImage.setLayoutData(fd_originalImage);

		Group executables = new Group(shell, SWT.NONE);
		FormData fd_executables = new FormData();
		fd_executables.left = new FormAttachment(originalImage, 6);

		originalImageCanv = new Canvas(originalImage, SWT.NONE);

		originalImageCanv.setLayoutData(BorderLayout.CENTER);

		Composite loadComposite = new Composite(originalImage, SWT.NONE);
		loadComposite.setLayoutData(BorderLayout.SOUTH);
		loadComposite.setLayout(new FormLayout());

		Button btnLoadImage = new Button(loadComposite, SWT.NONE);
		btnLoadImage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				LoadDialog dialog = new LoadDialog(shell, originalImageCanv);
				dialog.open();
			}
		});
		FormData fd_btnLoadImage = new FormData();
		fd_btnLoadImage.top = new FormAttachment(0);
		fd_btnLoadImage.left = new FormAttachment(0, 111);
		fd_btnLoadImage.bottom = new FormAttachment(100, -5);
		fd_btnLoadImage.right = new FormAttachment(100, -127);
		btnLoadImage.setLayoutData(fd_btnLoadImage);
		btnLoadImage.setSize(324, 27);
		btnLoadImage.setText("Load Image");
		executables.setLayout(new BorderLayout(0, 0));
		fd_executables.bottom = new FormAttachment(0, 229);
		fd_executables.top = new FormAttachment(0, 5);
		executables.setLayoutData(fd_executables);

		Group preprocessedImage = new Group(shell, SWT.NONE);
		fd_executables.right = new FormAttachment(preprocessedImage, -6);

		executablesList = new List(executables, SWT.BORDER | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.MULTI);
		executablesList.setItems(new String[] {});

		Menu menu_executables = new Menu(executablesList);
		executablesList.setMenu(menu_executables);

		MenuItem mntmRefresh = new MenuItem(menu_executables, SWT.NONE);
		mntmRefresh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Actions actions = new Actions(originalImageCanv,
						preprocessedImageCanv, executablesList);
				actions.refresh();
			}
		});

		mntmRefresh.setText("Refresh");

		Composite composite = new Composite(executables, SWT.NONE);
		composite.setLayoutData(BorderLayout.SOUTH);
		composite.setLayout(new FormLayout());

		Button btnApply = new Button(composite, SWT.NONE);
		btnApply.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Actions actions = new Actions(originalImageCanv,
						preprocessedImageCanv, executablesList);
				actions.apply();
			}
		});
		FormData fd_btnApply = new FormData();
		fd_btnApply.right = new FormAttachment(100, -3);
		fd_btnApply.top = new FormAttachment(0, 3);
		fd_btnApply.left = new FormAttachment(0, 3);
		btnApply.setLayoutData(fd_btnApply);
		btnApply.setText("Apply");

		Button btnCompare = new Button(composite, SWT.NONE);
		btnCompare.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				CompareWindow cmpWindow = new CompareWindow();
				cmpWindow.open();
			}
		});

		FormData fd_btnCompare = new FormData();
		fd_btnCompare.right = new FormAttachment(100, -4);
		fd_btnCompare.top = new FormAttachment(0, 33);
		fd_btnCompare.left = new FormAttachment(0, 3);
		btnCompare.setLayoutData(fd_btnCompare);
		btnCompare.setText("Compare");
		preprocessedImage.setLayout(new BorderLayout(0, 0));
		FormData fd_preprocessedImage = new FormData();
		fd_preprocessedImage.bottom = new FormAttachment(100, -19);
		fd_preprocessedImage.right = new FormAttachment(100, -7);
		fd_preprocessedImage.top = new FormAttachment(0, 5);
		fd_preprocessedImage.left = new FormAttachment(56);
		preprocessedImage.setLayoutData(fd_preprocessedImage);

		preprocessedImageCanv = new Canvas(preprocessedImage, SWT.NONE);

		preprocessedImageCanv.setLayoutData(BorderLayout.CENTER);

		Composite saveComposite = new Composite(preprocessedImage, SWT.NONE);
		saveComposite.setLayoutData(BorderLayout.SOUTH);
		saveComposite.setLayout(new FormLayout());

		Button btnSaveImage = new Button(saveComposite, SWT.NONE);
		btnSaveImage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				SaveDialog dialog = new SaveDialog(shell, preprocessedImageCanv);
				dialog.open();
			}
		});
		FormData fd_btnSaveImage = new FormData();
		fd_btnSaveImage.top = new FormAttachment(0);
		fd_btnSaveImage.left = new FormAttachment(0, 123);
		fd_btnSaveImage.bottom = new FormAttachment(100, -5);
		fd_btnSaveImage.right = new FormAttachment(100, -129);
		btnSaveImage.setLayoutData(fd_btnSaveImage);
		btnSaveImage.setSize(339, 27);
		btnSaveImage.setText("Save Image");

	}
}
