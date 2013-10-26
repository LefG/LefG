
package gui;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.Point;

/**
 * Creates a tabbed display with four tabs, and a few controls on each page
 */
public class MainGui {
	private Table tblDbList, tblQueueList;
  
  public void run() {
    Display display = new Display();
    final Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);
    shell.setSize(512, 512);
    shell.setMinimumSize(new Point(28, 28));
    shell.setBounds(0, 0, 512, 512);
    shell.setLayout(new FillLayout());
    shell.setText("LefG .1");
    createContents(shell);
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }

  /**
   * Creates the contents
   * 
   * @param shell the parent shell
   */
  private void createContents(Shell shell) {
    // Create the containing tab folder
    final TabFolder tabFolder = new TabFolder(shell, SWT.NONE);

    // Create each tab and set its text, tool tip text,
    // image, and control
    TabItem one = new TabItem(tabFolder, SWT.NONE);
    one.setText("Main");
    one.setToolTipText("Main stats and controls");
    one.setControl(getTabOneControl(tabFolder));
    

    
    TabItem tbtmQueues = new TabItem(tabFolder, SWT.NONE);
    tbtmQueues.setText("Queues");
    tbtmQueues.setControl(getTabTwoControl(tabFolder));
    Composite composite = new Composite(tabFolder, SWT.NONE);
    tbtmQueues.setControl(composite);

    TabItem three = new TabItem(tabFolder, SWT.NONE);
    three.setText("Group");
    three.setToolTipText("This is tab three");
    three.setControl(getTabThreeControl(tabFolder));

    TabItem four = new TabItem(tabFolder, SWT.NONE);
    four.setText("Debugging");
    four.setToolTipText("General debugging controls");

    tabFolder.setSelection(0);

  }

  /**
  
  /**
   * Gets the control for tab one
   * 
   * @param tabFolder the parent tab folder
   * @return Control
   */
  private Control getTabOneControl(TabFolder tabFolder) {
    // Create a composite and add four buttons to it
    Composite composite = new Composite(tabFolder, SWT.NONE);
    composite.setLayout(null);
    
    //Set up table on main
    tblDbList = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
    tblDbList.setHeaderVisible(true);
    tblDbList.setBounds(24, 10, 450, 400);
    
    TableColumn tblclmnName = new TableColumn(tblDbList, SWT.NONE);
    tblclmnName.setWidth(125);
    tblclmnName.setText("Name");
    
    TableColumn tblclmnClass = new TableColumn(tblDbList, SWT.NONE);
    tblclmnClass.setWidth(50);
    tblclmnClass.setText("Class");
    
    TableColumn tblclmnGear = new TableColumn(tblDbList, SWT.NONE);
    tblclmnGear.setWidth(100);
    tblclmnGear.setText("Gear");
    
    TableColumn tblclmnComment = new TableColumn(tblDbList, SWT.LEFT);
    tblclmnComment.setWidth(171);
    tblclmnComment.setText("Comments");
	
    return composite;
  }

  private Control getTabTwoControl(TabFolder tabFolder) {
	Composite composite = new Composite(tabFolder, SWT.NONE);
    composite.setLayout(null);
    
    //Set up table on main
    tblQueueList = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
    tblQueueList.setHeaderVisible(true);
    tblQueueList.setBounds(24, 10, 450, 400);
    
    TableColumn tblclmnName = new TableColumn(tblQueueList, SWT.NONE);
    tblclmnName.setWidth(125);
    tblclmnName.setText("Name");
    
    TableColumn tblclmnClass = new TableColumn(tblQueueList, SWT.NONE);
    tblclmnClass.setWidth(50);
    tblclmnClass.setText("Class");
    
    TableColumn tblclmnGear = new TableColumn(tblQueueList, SWT.NONE);
    tblclmnGear.setWidth(100);
    tblclmnGear.setText("Gear");
    
    TableColumn tblclmnComment = new TableColumn(tblQueueList, SWT.LEFT);
    tblclmnComment.setWidth(171);
    tblclmnComment.setText("Comments");
	
    return composite;
  }

  private Control getTabThreeControl(TabFolder tabFolder) {
    // Create some labels and text fields
    Composite composite = new Composite(tabFolder, SWT.NONE);
    composite.setLayout(new RowLayout());
    new Label(composite, SWT.LEFT).setText("Label One:");
    new Text(composite, SWT.BORDER);
    new Label(composite, SWT.RIGHT).setText("Label Two:");
    new Text(composite, SWT.BORDER);
    return composite;
  }

  public static void main(String[] args) {
    new MainGui().run();
  }
}
