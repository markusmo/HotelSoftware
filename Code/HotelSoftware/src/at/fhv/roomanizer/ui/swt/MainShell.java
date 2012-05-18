/**
 * Main Shell contains the key parts for the view
 * In this version the views are spread over the different 
 * tabs. These tabs are part of the Tabs _tab class and are called
 * tab items. The main view is Shell _shell, which calls the display
 * to use it as parent. All swt parts, also called widget need a parent.
 * All of the swt widget use the same syntax widget(parent, style) to be crated.
 * The most important rule of all swt widgets is that when a parent is disposed
 * all the children and subchildren of the parent will be disposed.
 * In this version the only tab is the reservation tab.
 * 
 * @author      Raf
 * 
 */

package at.fhv.roomanizer.ui.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;

import at.fhv.roomanizer.ui.swt.habitation.HabitationView;
import at.fhv.roomanizer.ui.swt.reservation.ReservationView;

public class MainShell{

	Shell _shell;
	Tabs _tab;
	Menu _menu;
	
	public MainShell() {
		/*
		 * Setting up the GUIController instance
		 * and the display for the shell
		 */
		GUIReservationController.getInstance();
		Display display = new Display();
		
		/*
		 * Setting up the main shell
		 * and register the display as parent
		 */
		_shell = new Shell(display);
		_shell.setText("Roomanizer");
		_shell.setLayout(new FillLayout());
		
	    
		//Setting up the main tab
		_tab = new Tabs(_shell, SWT.None);
		_shell.open();
		_shell.addListener (SWT.Resize, new Listener () {
		    public void handleEvent (Event e)
		    {
		       return;
		    }
		});
		
		//Menu
		initMenu();
		
		/*
		 * Will be called every time when shell
		 * needs a refresh. This loop is a blocking
		 * loop.
		 */
		_shell.pack();
		while (!_shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				
				display.sleep();
			}
		}
	}

	/**
	 * Initializes the menu
	 */
	private void initMenu() {
		_menu = new Menu(_shell, SWT.BAR);
		MenuItem generalItem = new MenuItem(_menu, SWT.CASCADE);
		generalItem.setText("General");
		
		Menu generalMenu = new Menu(_menu);
		generalItem.setMenu(generalMenu);
		MenuItem dayEndClosingItem = new MenuItem(generalMenu, SWT.NONE);
		dayEndClosingItem.setText("Day end closing");
		new MenuItem(generalMenu, SWT.SEPARATOR);
		MenuItem exitItem = new MenuItem(generalMenu, SWT.NONE);
		exitItem.setText("Exit");
		
		//Menu Listeners
		dayEndClosingItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event){
				GuiController.getInstance().dayEndClosing();
				MessageBox msgBox = new MessageBox(_shell);
				msgBox.setMessage("Day end closing successfully done.");
				msgBox.open();
			}
		});
		
		exitItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event){
				_shell.dispose();
			}
		});
		
		_shell.setMenuBar(_menu);
	}
	
	/**
	 * inner class Tabs is an extension of composite, 
	 * an base class for swt widgets
	 * this class holds the TabFolderMenu called CTabFolder tabMenu
	 * 
	 * @author Raf
	 */
	
	public class Tabs extends Composite {
		
		private Display _display;
		
		private CTabFolder _tabMenu;
		
		public Tabs(Composite parent, int style){
			super(parent, style);
			_display = Display.getDefault();
			this.setLayout(new FillLayout());
			initUI();
		}
		
		private void initUI(){
			createTabs();
		}

		private void createTabs() {
			/*
			 * Creating a tabmenu
			 */
			_tabMenu = new CTabFolder(this,SWT.None);
			_tabMenu.setSimple(false);
			
			_tabMenu.setSelectionBackground(new Color[] {
	        _display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND),
	        _display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND),
	        _display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND) }, new int[] { 80, 100 });

			/*
			 * Creating the tab items
			 * Composites are added to the tab items
			 * tabMenu is the parent for the Composites
			 */
			
			//The reservation view
			CTabItem reservationTab = new CTabItem(_tabMenu, SWT.NONE);
			reservationTab.setText("Reservation");
			ReservationView reservationView = new ReservationView(_tabMenu, SWT.NONE);
			reservationTab.setControl(reservationView);
			
			//The habitation view
			CTabItem habitationTab = new CTabItem(_tabMenu, SWT.NONE);
			habitationTab.setText("Habitation");
			HabitationView habitationView = new HabitationView(_tabMenu, SWT.NONE);
			habitationTab.setControl(habitationView);
		}
	}
	
	public static void main(String[] args){
		MainShell mainShell = new MainShell();
	}
	
}
