package at.fhv.roomanizer.ui.swt.reservation;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import at.fhv.roomanizer.application.CountryController;
import at.fhv.roomanizer.domain.person.ICountry;
import at.fhv.roomanizer.domain.reservation.IReservation;
import at.fhv.roomanizer.ui.swt.GUIReservationController;

public class ReservationDetailView extends Composite {

	Composite _parent;
	Shell _shell;

	private Text _personID;
	private Text _fNameText;
	private Text _telText;
	private Text _streetText;
	private Text _zipText;
	private Text _townText;
	private Text _lNameText;
	DateTime _arrivalDateForm;
	private DateTime _leavingDateForm;
	private Text _numberText;
	private Text _emailText;
	private Text _faxText;
	private Text _streetNrText;

	private Combo _countryCombo;

	public Text get_presonID() {
		return _personID;
	}

	public Combo get_countryCombo(){
		return _countryCombo;
	}
	
	public Text get_fNameText() {
		return _fNameText;
	}

	public Text get_telText() {
		return _telText;
	}

	public Text get_streetText() {
		return _streetText;
	}

	public Text get_zipText() {
		return _zipText;
	}

	public Text get_townText() {
		return _townText;
	}

	public Text get_lNameText() {
		return _lNameText;
	}

	public DateTime get_arrivalDateForm() {
		return _arrivalDateForm;
	}

	public DateTime get_leavingDateForm() {
		return _leavingDateForm;
	}

	public Text get_numberText() {
		return _numberText;
	}

	public Text get_emailText() {
		return _emailText;
	}

	public Text get_faxText() {
		return _faxText;
	}
	
	public Text get_streetNrText() {
		return _streetNrText;
	}

	public ReservationDetailView(Composite parent, int style) {
		super(parent, style);
		_shell = parent.getShell();

		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.makeColumnsEqualWidth = false;

		this.setLayout(layout);

		initUI();

		GUIReservationController.set_detailView(this);
	}

	private void initUI() {
		GridData gridData = new GridData();

		//PersonID
		Label pId = new Label(this, SWT.NONE);
		pId.setText("Person ID:");
		_personID = new Text(this, SWT.BORDER);
		_personID.setEditable(false);
		
		// ReservationID
		Label id = new Label(this, SWT.NONE);
		id.setText("Reservation ID:");
		_numberText = new Text(this, SWT.BORDER);
		GridData data = new GridData(120, SWT.DEFAULT);
		_numberText.setLayoutData(data);

		// Vorname
		Label fName = new Label(this, SWT.NONE);
		fName.setText("First name:");
		_fNameText = new Text(this, SWT.BORDER);
		data = new GridData(120, SWT.DEFAULT);
		_fNameText.setLayoutData(data);

		// Nachname
		Label lName = new Label(this, SWT.NONE);
		lName.setText("Last name:");
		_lNameText = new Text(this, SWT.BORDER);
		_lNameText.setLayoutData(data);

		// Ort
		Label town = new Label(this, SWT.NONE);
		town.setText("Town:");
		_townText = new Text(this, SWT.BORDER);
		_townText.setLayoutData(data);

		// Zip code
		Label zip = new Label(this, SWT.NONE);
		zip.setText("Zip:");
		_zipText = new Text(this, SWT.BORDER);
		data = new GridData(40, SWT.DEFAULT);
		_zipText.setLayoutData(data);
		
		// Country
		Label country = new Label(this, SWT.NONE);
		country.setText("Country:");
		_countryCombo = new Combo(this, SWT.BORDER |SWT.READ_ONLY);
		//_countryText = new Text(this, SWT.BORDER);
		data = new GridData(120, SWT.DEFAULT);
		//_countryText.setLayoutData(data);
		_countryCombo.setLayoutData(data);

		// email
		Label email = new Label(this, SWT.NONE);
		email.setText("Email:");
		_emailText = new Text(this, SWT.BORDER);
		data = new GridData(120, SWT.DEFAULT);
		_emailText.setLayoutData(data);

		// Strasse
		Label street = new Label(this, SWT.NONE);
		street.setText("Street:");
		_streetText = new Text(this, SWT.BORDER);
		data = new GridData(120, SWT.DEFAULT);
		_streetText.setLayoutData(data);

		// Tel
		Label tel = new Label(this, SWT.NONE);
		tel.setText("Phone:");
		_telText = new Text(this, SWT.BORDER);
		data = new GridData(120, SWT.DEFAULT);
		_telText.setLayoutData(data);

		// Fax
		Label fax = new Label(this, SWT.NONE);
		fax.setText("Fax:");
		_faxText = new Text(this, SWT.BORDER);
		_faxText.setLayoutData(data);

		// Arrival
		Label arrivalDate = new Label(this, SWT.NONE);
		arrivalDate.setText("Arrival Date.:");
		_arrivalDateForm = new DateTime(this, SWT.BORDER | SWT.DROP_DOWN);

		// Leaving
		Label leavingDate = new Label(this, SWT.NONE);
		leavingDate.setText("Leaving Date.:");
		_leavingDateForm = new DateTime(this, SWT.BORDER | SWT.DROP_DOWN);
		
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = false;
		gridData.grabExcessVerticalSpace = false;
		gridData.horizontalSpan = 2;
		
		// AutoFill
		Button autoFill = new Button(this, SWT.PUSH);
		autoFill.setText("Choose Guest");
		autoFill.setLayoutData(gridData);
		autoFill.setEnabled(true);
		GUIReservationController.set_autoFill(autoFill);
		GUIReservationController.addAutoDialogFillListener(autoFill);

		// Save
		Button save = new Button(this, SWT.PUSH);
		save.setText("Update Reservation");
		save.setLayoutData(gridData);
		save.setEnabled(false);
		GUIReservationController.set_update(save);
		GUIReservationController.addUpdateListener(save);
	}
	
	/**
	 * Fills the country Combobox with all countries of the db
	 * @param countryName
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public void fillCountryComboText(String countryName) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException{
		//load all countries
		CountryController cc = new CountryController();
		List<ICountry> allCountries = cc.loadAllCountries();
		String[] allCountryNames = new String[allCountries.size()];
		//select the country of the address
		int selectedCountry = 0;
		//fill the array
		for (int i = 0; i < allCountries.size(); i++) {
			allCountryNames[i] = allCountries.get(i).getName();
			if (allCountryNames[i].equals(countryName)) {
				selectedCountry = i;
			}
		}
		_countryCombo.setItems(allCountryNames);
		_countryCombo.select(selectedCountry);
	}

	@SuppressWarnings("deprecation")
	public void fillForm(IReservation reservation) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {

		//11 form fields to set
		_personID.setText(reservation.getIGuest().getId()+"");
		_numberText.setText(reservation.getNumber().toString());
		_fNameText.setText(reservation.getIGuest().getFirstName());
		_lNameText.setText(reservation.getIGuest().getLastName());
		_townText.setText(reservation.getIGuest().getIAddress().getCity());
		_zipText.setText(reservation.getIGuest().getIAddress().getZip());
		
		fillCountryComboText(reservation.getIGuest().getIAddress().getICountry().getName());
		_emailText.setText(reservation.getIGuest().getIAddress().getEmail());
		_streetText.setText(reservation.getIGuest().getIAddress().getStreet());
		_telText.setText(reservation.getIGuest().getIAddress().getPhone());
		_faxText.setText(reservation.getIGuest().getIAddress().getFax());

		int year = reservation.getStart().getYear();
		int month = reservation.getStart().getMonth();
		
		String DATE_FORMAT = "dd.MM.yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		
		//To get the day split the date
		String dateString = sdf.format(reservation.getStart());
		System.out.print(dateString);
		String[] dateStringArray = dateString.split("\\.");
		dateString = dateStringArray[0];
		Integer day = Integer.parseInt(dateStringArray[0]);
		_arrivalDateForm.setDate(1900+year, month, day);

		year = reservation.getEnd().getYear();
	    month = reservation.getEnd().getMonth();
		
	    //To get the day split the date
	    dateString = sdf.format(reservation.getEnd());
		dateStringArray = dateString.split("\\.");
		dateString = dateStringArray[0];
		day = Integer.parseInt(dateStringArray[0]);
		_leavingDateForm.setDate(1900+year, month, day);
	}

}
