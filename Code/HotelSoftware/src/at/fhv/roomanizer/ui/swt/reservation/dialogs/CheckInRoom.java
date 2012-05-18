package at.fhv.roomanizer.ui.swt.reservation.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


public class CheckInRoom extends Composite implements SelectionListener {
	
	private Combo _roomCombo;
	private Combo _categoryCombo;
	
	private String[] _roomList;
	private String[] _categoryList;
	
	private Text _priceText;

	CheckInRoom(Composite parent, int style, String[] categoryItems, int selectedCategoryIndex) {
		super(parent, style);
		
		_categoryList = categoryItems;
		
		initUI(categoryItems, selectedCategoryIndex);
	}

	private void initUI(String[] categoryItems, int selectedCategoryIndex) {
		RowLayout layout = new RowLayout();
		layout.wrap = true;
		this.setLayout(layout);
		
		_categoryCombo = new Combo(this, SWT.READ_ONLY);
		_categoryCombo.setItems(categoryItems);
		_categoryCombo.select(selectedCategoryIndex);
		_categoryCombo.addSelectionListener(this);
		
		_roomCombo = new Combo(this, SWT.READ_ONLY);
		
		Label priceLabel = new Label(this, SWT.BORDER);
		priceLabel.setText("Preis:");
		
		_priceText = new Text(this, SWT.BORDER);
		_priceText.setText("100.0");
		
		
		this.setVisible(true);
	}
	
	/**
	 * Updates the item-list of the room-combo
	 * @param rooms RoomList, which will be set as the item-list of the room-combo
	 */
	public void setRoomList(String[] rooms) {
		_roomList = rooms;
		_roomCombo.setItems(_roomList);
		
		if(_roomList.length > 0) {
			_roomCombo.select(0);
		}
	}
	
	/**
	 * Sets the text of the price-textbox
	 * @param text Text, which will be set as the text of the price-textbox
	 */
	public void setPriceText(Double text) {
		this._priceText.setText(text.toString());
	}
	
	public String getPriceText() {
		return this._priceText.getText();
	}
	
	/**
	 * Returns the Name of the selected room
	 * @return Name of the selected room
	 */
	public String getSelectedRoom() {
		return _roomList[_roomCombo.getSelectionIndex()];
	}
	
	/**
	 * Returns the Name of the selected category
	 * @return Name of the selected category
	 */
	public String getSelectedCategory() {
		return _categoryList[_categoryCombo.getSelectionIndex()];
	}
	
	public void selectRoom(int index) {
		_roomCombo.select(index);
	}
	
	private List<SelectionListener> _selectionListener = new ArrayList<SelectionListener>();
	
	/**
	 * Adds the listener to the category-combo
	 * @param listener SelectionListener, which will be notified when the user chooses another category
	 */
	public void addCategoryListener(SelectionListener listener) {
		_selectionListener.add(listener);
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) { }

	@Override
	public void widgetSelected(SelectionEvent arg0) {
		Event e = new Event();
		e.data = arg0.data;
		e.display = arg0.display;
		e.doit = arg0.doit;
		e.type = SWT.Selection;
		e.widget = arg0.widget;
		e.item = arg0.item;
		CategorySelectionEvent cse = new CategorySelectionEvent(e, this);
		
		for(SelectionListener listener : _selectionListener) {
			listener.widgetSelected(cse);
		}
	}
}
