package at.fhv.roomanizer.ui.swt.habitation;

import java.text.SimpleDateFormat;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import at.fhv.roomanizer.domain.IHabitation;
import at.fhv.roomanizer.ui.swt.GuiController;

/**
 * Lists habitations with additional information in a table
 * 
 * @author Daniel Rotter <daniel.rotter@students.fhv.at>
 */
public class HabitationTable extends Composite {
	
	private Table _table;
	private final String[] _columns = {"Roomnumber", "Firstname", "Lastname", "Arrival", "Leaving"};
	
	private Text _roomNumber;
	private Text _firstName;
	private Text _lastName;
	private Text _town;
	private Text _zip;
	private Combo _country;
	private Text _email;
	private Text _street;
	private Text _phone;
	private Text _fax;
	private DateTime _arrival;
	private DateTime _leaving;

	public HabitationTable(Composite parent, int style) {
		super(parent, style);
		initUI();
	}

	private void initUI() {
		//Table
		RowLayout layout = new RowLayout();
		layout.type = SWT.HORIZONTAL;
		layout.fill = true;
		setLayout(layout);
		
		_table = new Table(this, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL);
		_table.setLinesVisible(true);
		_table.setHeaderVisible(true);
		_table.setSize(600, 400);
		
		for(String s : _columns){
			TableColumn column = new TableColumn(_table, SWT.NONE);
			column.setText(s);
			column.pack();
		}
		
		//Detail view
		Composite detail = new Composite(this, SWT.NONE);
		GridLayout detailLayout = new GridLayout();
		detailLayout.numColumns = 2;
		detailLayout.makeColumnsEqualWidth = true;
		detail.setLayout(detailLayout);
		
		GridData data = new GridData(120, SWT.DEFAULT);
		
		Label l;
		
		l = new Label(detail, SWT.NONE);
		l.setText("Roomnumber:");
		_roomNumber = new Text(detail, SWT.BORDER);
		_roomNumber.setLayoutData(data);
		
		l = new Label(detail, SWT.NONE);
		l.setText("First name:");
		_firstName = new Text(detail, SWT.BORDER);
		_firstName.setLayoutData(data);
		
		l = new Label(detail, SWT.NONE);
		l.setText("Last name:");
		_lastName = new Text(detail, SWT.BORDER);
		_lastName.setLayoutData(data);
		
		l = new Label(detail, SWT.NONE);
		l.setText("Town:");
		_town = new Text(detail, SWT.BORDER);
		_town.setLayoutData(data);
		
		l = new Label(detail, SWT.NONE);
		l.setText("Zip:");
		_zip = new Text(detail, SWT.BORDER);
		_zip.setLayoutData(data);
		
		l = new Label(detail, SWT.NONE);
		l.setText("Country:");
		_country = new Combo(detail, SWT.NONE | SWT.READ_ONLY);
		_country.setLayoutData(data);
		
		l = new Label(detail, SWT.NONE);
		l.setText("Email:");
		_email = new Text(detail, SWT.BORDER);
		_email.setLayoutData(data);
		
		l = new Label(detail, SWT.NONE);
		l.setText("Street:");
		_street = new Text(detail, SWT.BORDER);
		_street.setLayoutData(data);
		
		l = new Label(detail, SWT.NONE);
		l.setText("Phone:");
		_phone = new Text(detail, SWT.BORDER);
		_phone.setLayoutData(data);
		
		l = new Label(detail, SWT.NONE);
		l.setText("Fax:");
		_fax = new Text(detail, SWT.BORDER);
		_fax.setLayoutData(data);
		
		l = new Label(detail, SWT.NONE);
		l.setText("Arrival Date:");
		_arrival = new DateTime(detail, SWT.BORDER | SWT.DROP_DOWN);
		_arrival.setLayoutData(data);
		
		l = new Label(detail, SWT.NONE);
		l.setText("Leaving Date:");
		_leaving = new DateTime(detail, SWT.BORDER | SWT.DROP_DOWN);
		_leaving.setLayoutData(data);
		
		//Load data for table
		GuiController.getInstance().initHabitationTable(this);
	}
	
	public void paint(List<IHabitation> habitationList){
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		for(IHabitation hab : habitationList){
			TableItem item = new TableItem(_table, SWT.NONE);
			item.setText(0, hab.getRoom().getNumber());
			item.setText(1, hab.getGuests().get(0).getFirstName());
			item.setText(2, hab.getGuests().get(0).getLastName());
			item.setText(3, sdf.format(hab.getStart()));
			item.setText(4, sdf.format(hab.getEnd()));
			item.setData(hab);
		}
		
		for(TableColumn tc : _table.getColumns()){
			tc.pack();
		}
	}
	
	public Table getTable(){
		return _table;
	}
}
