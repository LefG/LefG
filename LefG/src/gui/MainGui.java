
package gui;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import src.ToonHandler;

public class MainGui {
	ToonHandler th;
	private Table tblDbList;
	private Table tblQueue;
  
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
    th = new ToonHandler();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }

  private void createContents(Shell shell) {
    // Create the containing tab folder
    final TabFolder tabFolder = new TabFolder(shell, SWT.NONE);

    // Create each tab and set its text, tool tip text,
    // image, and control
    TabItem one = new TabItem(tabFolder, SWT.NONE);
    one.setText("Main");
    one.setToolTipText("Main stats and controls");
    one.setControl(getTabOneControl(tabFolder));
    
    TabItem tabQueue = new TabItem(tabFolder, SWT.NONE);
    tabQueue.setText("Queue");
    tabQueue.setControl(gettabQueueControl(tabFolder));
     
    TabItem three = new TabItem(tabFolder, SWT.NONE);
    three.setText("Group");
    three.setToolTipText("This is tab three");
    three.setControl(getTabThreeControl(tabFolder));

    TabItem four = new TabItem(tabFolder, SWT.NONE);
    four.setText("Debugging");
    four.setToolTipText("General debugging controls");

    tabFolder.setSelection(0);
    

  }

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
  // QUEUE TAB CONTROL
  private Control gettabQueueControl(TabFolder tabFolder) {
	  Composite composite = new Composite(tabFolder, SWT.NONE);
	  composite.setToolTipText("");
	  composite.setLayout(null);
	  tblQueue = new Table(composite, SWT.BORDER);
      tblQueue.setHeaderVisible(true);
      tblQueue.setBounds(24, 10, 450, 150);
      
      TableColumn tblclmnName = new TableColumn(tblQueue, SWT.NONE);
      tblclmnName.setWidth(137);
      tblclmnName.setText("Name");
      
      TableColumn tblclmnClass = new TableColumn(tblQueue, SWT.NONE);
      tblclmnClass.setWidth(47);
      tblclmnClass.setText("Class");
      
      TableColumn tblclmnGear = new TableColumn(tblQueue, SWT.NONE);
      tblclmnGear.setWidth(54);
      tblclmnGear.setText("Gear");
      
      TableColumn tblclmnQueuedFor = new TableColumn(tblQueue, SWT.NONE);
      tblclmnQueuedFor.setWidth(246);
      tblclmnQueuedFor.setText("Queued For");
      
      Button btnQueue = new Button(composite, SWT.NONE);
      btnQueue.addSelectionListener(new SelectionAdapter() {
      	@Override
      	public void widgetSelected(SelectionEvent arg0) {
      	}
      });
      btnQueue.setBounds(318, 166, 75, 25);
      btnQueue.setText("Enter Queue ");
      
      Button btnLeaveQueue = new Button(composite, SWT.NONE);
      btnLeaveQueue.setBounds(399, 166, 75, 25);
      btnLeaveQueue.setText("Leave Queue");
      
      Combo cmbToon = new Combo(composite, SWT.READ_ONLY);
      cmbToon.setBounds(24, 168, 195, 25);
      for(int i=0; i<th.Toons.size(); i++){
    	  cmbToon.add(th.Toons.get(i).name);
      }
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
