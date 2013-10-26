/**
This file is part of LefG.

    LefG is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    LefG is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with LefG.  If not, see <http://www.gnu.org/licenses/>.
**/






/**
 * ADVANCED CLASSES AS FOLLOWS:
 * ME - Mercenary		CO - Commando
 * PT - Powertech		VA - Vanguard
 * SN - Sniper			GS - Gunslinger
 * OP - Operative       SC - Scoundrel
 * JU - Juggernaut		GU - Guardian
 * MA - Marauder        SE - Sentinel
 * AS - Assassin		SH - Shadow
 * SO - Sorcerer		SA - Sage
 * 
 */
package gui;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import src.ToonHandler;
import toon.Toon;

public class MainGui {
	Shell shell;
	final int rep = 0, imp = 2; // Index of rep and imp in String[][] advclass
	final int offset=1; // used for advclass
	MessageBox mb;
	ToonHandler th;
	Toon t;
	String[][] advclass = {
			{"Guardian", "Sentinel", "Sage", "Shadow", "Gunslinger", "Scoundrel", "Commando", "Vanguard"},
			{"GU", "SE", "SA", "SH", "GS", "SC", "CO", "VG"},
			{"Juggernaut", "Marauder", "Sorcerer", "Assassin", "Sniper", "Operative", "Mercenary", "Powertech"},
			{"JU", "MA", "SO", "AS", "SN", "OP", "ME", "PT" }
	};
	private Combo cmbToon;
	private Group grpFaction, grpServer, grpClass;
	private Table tblDbList;
	private Table tblQueue;
	private Text txtName;
	private Button[] btnClass = new Button[8];
	private Button rbPub, rbImp;
	private Text txtRName;
	private Text txtGear;
	private Text txtComment;
	
  
	public void run() {
		th = new ToonHandler();
		Display display = new Display();
		shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);
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
	
	    tabFolder.setSelection(0);
	    
	    TabItem tabaddToon = new TabItem(tabFolder, SWT.NONE);
	    tabaddToon.setText("Add Toon");
	    tabaddToon.setControl(gettabAddToonControl(tabFolder));
	    Composite composite = new Composite(tabFolder, SWT.NONE);
	    tabaddToon.setControl(composite);
	    composite.setLayout(null);
	    
	    txtName = new Text(composite, SWT.BORDER);
	    txtName.setBounds(185, 93, 205, 21);
	    
	    Label lblName = new Label(composite, SWT.NONE);
	    lblName.setBounds(113, 99, 55, 15);
	    lblName.setText("Name:");
	    
	    grpFaction = new Group(composite, SWT.NONE);
	    grpFaction.setText("Faction");
	    grpFaction.setBounds(113, 154, 118, 62);
	    
	    rbPub = new Button(grpFaction, SWT.RADIO);
	    rbPub.setSelection(true);
	    rbPub.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent arg0) {
	    		for(int i=0; i<btnClass.length; i++){
	    			btnClass[i].setText(advclass[rep][i]);
	    		}
	    	}
	    });
	    rbPub.setBounds(10, 20, 80, 16);
	    rbPub.setText("Republic");
	    
	    rbImp = new Button(grpFaction, SWT.RADIO);
	    rbImp.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent arg0) {
	    	    for(int i=0;i<btnClass.length; i++) {
	    	    	btnClass[i].setText(advclass[imp][i]);
	    	    }
	    	}
	    });
	    rbImp.setText("Empire");
	    rbImp.setBounds(10, 36, 80, 16);
	    
	    grpClass = new Group(composite, SWT.NONE);
	    grpClass.setText("Class");
	    grpClass.setBounds(248, 154, 142, 150);
	    
	// Place buttons in class group
	    int x = 10, y=21, w = 90, h = 16;
	    for(int i=0;i<btnClass.length; i++){
	    	btnClass[i] = new Button(grpClass, SWT.RADIO);
	    	btnClass[i].setBounds(x, y, w, h);
	    	btnClass[i].setText(advclass[rep][i]);
	    	y+=15;
	    }
	    btnClass[0].setSelection(true);
	    
	    
	 // Server group handling
	    grpServer = new Group(composite, SWT.NONE);
	    grpServer.setText("Server");
	    grpServer.setBounds(113, 222, 118, 82);
	    
	    Button btnAllServers = new Button(grpServer, SWT.RADIO);
	    btnAllServers.setSelection(true);
	    btnAllServers.setBounds(10, 20, 90, 16);
	    btnAllServers.setText("All Servers");
	    
	    Label lblRetypeName = new Label(composite, SWT.NONE);
	    lblRetypeName.setText("Retype Name:");
	    lblRetypeName.setBounds(75, 120, 94, 15);
	    
	    txtRName = new Text(composite, SWT.BORDER);
	    txtRName.setBounds(185, 120, 205, 21);
	    
	    txtGear = new Text(composite, SWT.BORDER);
	    txtGear.setBounds(185, 325, 76, 21);
	    
	    txtComment = new Text(composite, SWT.BORDER);
	    txtComment.setBounds(185, 354, 205, 21);
	    
	    Label lblGear = new Label(composite, SWT.NONE);
	    lblGear.setBounds(124, 328, 27, 15);
	    lblGear.setText("Gear:");
	    
	    Label lblComment = new Label(composite, SWT.NONE);
	    lblComment.setBounds(98, 357, 55, 15);
	    lblComment.setText("Comment:");
	    
	    // Buttons on Add Toon tab. Why are they here? Who know's. GUI programming is like petting a wet dog.
	    Button btnAddToon = new Button(composite, SWT.NONE);
	    btnAddToon.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent arg0) {
	    		addToon();
	    	}
	    });
	    btnAddToon.setBounds(335, 399, 55, 25);
	    btnAddToon.setText("Add");
	    
	    Button btnClear = new Button(composite, SWT.NONE);
	    btnClear.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent arg0) {
	    		clearToon();
	    	}
	    });
	    	
	    btnClear.setBounds(268, 399, 55, 25);
	    btnClear.setText("Clear");
	    // END buttons on Add Toon tab

  }
	// Executes global refresh of all items
	private void refresh(){
		queueCmbBox();
		clearToon();
  }
	// Links cmbToon in tab Queue to sh.Toons
	private void queueCmbBox(){
		String[] clear = new String[0];
		cmbToon.setItems(clear);
	    for(int i = 0; i<th.Toons.size(); i++){
	    	cmbToon.add(th.Toons.get(i).name);
	     }
	}
	private void clearToon() {
		 txtName.setText(""); 
		 txtRName.setText("");
		 btnClass[0].setSelection(true);
		 btnClass[0].setText(advclass[rep][0]);
		 for(int i=1;i<btnClass.length;i++)	{btnClass[i].setSelection(false); btnClass[i].setText(advclass[rep][i]);}
		 rbPub.setSelection(true);
		 rbImp.setSelection(false);
		 txtGear.setText("");
		 txtComment.setText("");
	}
	private void addToon() {
	  boolean flag = false; // raised if unsuccessful toon add attempt
	  t = new Toon();
	  int faction=rep;
		// Both text boxes must not be empty and must be equal
		if(txtName.getCharCount() > 0 && txtRName.getCharCount() > 0 &&
				txtName.getText().equals(txtRName.getText())) {
  		// Is the toon on the local list?
  		// If the toon is on the local list, we know it is on the db since we built local list from db
			if(th.hasToonLocal(txtName.getText())){
				new MessageBox(shell, SWT.ICON_WORKING | SWT.OK);
				mb = new MessageBox(shell, SWT.ICON_WORKING | SWT.OK);
				mb.setText("New Toon");
				mb.setMessage("Toon already on local list and in database");
				mb.open();
				flag = true;
			}else{
				// Get name
				t.name = txtName.getText();
				// Set faction
				faction = (rbPub.getSelection())?rep:imp;
				t.faction = faction;
				
				// Set advanced class
				for(int i=0; i<btnClass.length; i++){
					if (btnClass[i].getSelection()) {
						t.advclass = advclass[faction+offset][i];
					}
				}
				// Set gear
				if(txtGear.getCharCount()>1){
					t.gear = txtGear.getText();
				} else {
					System.out.println("Gear box empty");
					flag = true;
				}
				
				// Set comment
				t.comment = txtComment.getText();
			}
		// If initial naming conventions was not met, execute this else
		} else {
			System.out.println("Error with names");
			flag = true;
		}
		// Add toon to lists (and db if need be)
		// Recall the ToonHandler.addToon method adds to local list AND database if not already on
		if (!flag){
			int r = th.addToon(t);
			clearToon();
			refresh();
			
			if(r == th.FROM_DB){
				mb = new MessageBox(shell, SWT.ICON_WORKING | SWT.OK);
				mb.setText("New Toon");
				mb.setMessage("Add new toon to database success");
				mb.open();
			}
		}
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
	  
	  // TABLE CONTROLS
	  
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
      
      // END TABLE CONTROLS
      // BEGIN OTHER ELEMENTS
      
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
      
      cmbToon = new Combo(composite, SWT.READ_ONLY);
      cmbToon.setBounds(24, 168, 195, 25);
      queueCmbBox();
      return composite;
	}
  
	private Control gettabAddToonControl(TabFolder tabFolder){
		Composite composite = new Composite(tabFolder, SWT.NONE);
	    composite.setLayout(null);
	    
	    txtName = new Text(composite, SWT.BORDER);
	    txtName.setBounds(185, 93, 205, 21);
	    
	    Label lblName = new Label(composite, SWT.NONE);
	    lblName.setBounds(113, 99, 55, 15);
	    lblName.setText("Name:");
	    
	    grpFaction = new Group(composite, SWT.NONE);
	    grpFaction.setText("Faction");
	    grpFaction.setBounds(113, 154, 118, 62);
	    
	    rbPub = new Button(grpFaction, SWT.RADIO);
	    rbPub.setSelection(true);
	    rbPub.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent arg0) {
	    		for(int i=0; i<btnClass.length; i++){
	    			btnClass[i].setText(advclass[rep][i]);
	    		}
	    	}
	    });
	    rbPub.setBounds(10, 20, 80, 16);
	    rbPub.setText("Republic");
	    
	    rbImp = new Button(grpFaction, SWT.RADIO);
	    rbImp.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent arg0) {
	    	    for(int i=0;i<btnClass.length; i++) {
	    	    	btnClass[i].setText(advclass[imp][i]);
	    	    }
	    	}
	    });
	    rbImp.setText("Empire");
	    rbImp.setBounds(10, 36, 80, 16);
	    
	    grpClass = new Group(composite, SWT.NONE);
	    grpClass.setText("Class");
	    grpClass.setBounds(248, 154, 142, 150);
	    
	// Place buttons in class group
	    int x = 10, y=21, w = 90, h = 16;
	    for(int i=0;i<btnClass.length; i++){
	    	btnClass[i] = new Button(grpClass, SWT.RADIO);
	    	btnClass[i].setBounds(x, y, w, h);
	    	btnClass[i].setText(advclass[rep][i]);
	    	y+=15;
	    }
	    btnClass[0].setSelection(true);
	    
	    
	 // Server group handling
	    grpServer = new Group(composite, SWT.NONE);
	    grpServer.setText("Server");
	    grpServer.setBounds(113, 222, 118, 82);
	    
	    Button btnAllServers = new Button(grpServer, SWT.RADIO);
	    btnAllServers.setSelection(true);
	    btnAllServers.setBounds(10, 20, 90, 16);
	    btnAllServers.setText("All Servers");
	    
	    Label lblRetypeName = new Label(composite, SWT.NONE);
	    lblRetypeName.setText("Retype Name:");
	    lblRetypeName.setBounds(75, 120, 94, 15);
	    
	    txtRName = new Text(composite, SWT.BORDER);
	    txtRName.setBounds(185, 120, 205, 21);
	    
	    txtGear = new Text(composite, SWT.BORDER);
	    txtGear.setBounds(185, 325, 76, 21);
	    
	    txtComment = new Text(composite, SWT.BORDER);
	    txtComment.setBounds(185, 354, 205, 21);
	    
	    Label lblGear = new Label(composite, SWT.NONE);
	    lblGear.setBounds(124, 328, 27, 15);
	    lblGear.setText("Gear:");
	    
	    Label lblComment = new Label(composite, SWT.NONE);
	    lblComment.setBounds(98, 357, 55, 15);
	    lblComment.setText("Comment:");
	    

	    
	    Button btnAddToon = new Button(composite, SWT.NONE);
	    btnAddToon.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent arg0) {
	    		addToon();
	    	}
	    });
	    btnAddToon.setBounds(335, 399, 55, 25);
	    btnAddToon.setText("Add");
	    return composite;

  }
	
	private Control getTabThreeControl(TabFolder tabFolder) {
    // Create some labels and text fields
    Composite composite = new Composite(tabFolder, SWT.NONE);
    composite.setLayout(null);
    return composite;
  }

	public static void main(String[] args) {
		new MainGui().run();
	}
}
