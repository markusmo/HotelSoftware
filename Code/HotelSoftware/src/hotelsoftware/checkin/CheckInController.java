/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.checkin;

import hotelsoftware.checkin.CheckInState.RoomSelection;
import hotelsoftware.gui.GuiController;
import hotelsoftware.gui.UseCaseController;
import hotelsoftware.login.LoginController;
import hotelsoftware.model.domain.parties.Address;
import hotelsoftware.model.domain.parties.Country;
import hotelsoftware.model.domain.parties.data.AddressData;
import hotelsoftware.model.domain.parties.data.CountryData;
import hotelsoftware.model.domain.parties.data.GuestData;
import hotelsoftware.model.domain.reservation.Reservation;
import hotelsoftware.model.domain.reservation.data.ReservationData;
import hotelsoftware.model.domain.reservation.data.ReservationItemData;
import hotelsoftware.model.domain.room.NoPriceDefinedException;
import hotelsoftware.model.domain.room.data.RoomCategoryData;
import hotelsoftware.model.domain.room.data.RoomData;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.model.domain.service.data.ExtraServiceData;
import hotelsoftware.model.domain.service.data.HabitationData;
import hotelsoftware.model.domain.users.Permission;
import hotelsoftware.support.PermissionNotFoundException;
import hotelsoftware.support.PermissionDeniedException;
import hotelsoftware.util.HelperFunctions;
import java.util.*;

/**
 *
 * @author Dunst
 */
public class CheckInController implements UseCaseController
{
    private static CheckInController controller = null;

    public static CheckInController getInstance() throws PermissionNotFoundException, PermissionDeniedException
    {
        Permission p = Permission.getPermissionByName("Check-In");
        
        if (LoginController.getInstance().getCurrentUser().hasPermission(p))
        {
            throw new PermissionDeniedException(p);
        }
        
        if (controller == null)
        {
            controller = new CheckInController();
            GuiController.getInstance().addUseCaseController(controller);
        }

        return controller;
    }
    private Date startDate;
    private Date endDate;
    private Reservation reservation;
    private Map<Integer, CheckInState.RoomSelection> roomSelections;
    private int counter;
    private Collection<ReservationItemData> reservationItems;
    private CheckInState state;
    private Collection<Habitation> habitations;

    public static CheckInController getController()
    {
        return controller;
    }

    public static void setController(CheckInController controller)
    {
        CheckInController.controller = controller;
    }

    private CheckInController()
    {
        state = new StartState(this);
    }

    void setState(CheckInState state)
    {
        this.state = state;
    }

    /**
     * Sucht nach einer Reservierung
     *
     * @param reservationNr Die bei der Reservierung erstelle Reservierungsnummer
     * @return Die zur Reservierungsnummer gehörende Reservierung
     */
    public ReservationData search(int reservationNr)
    {
        return state.search(reservationNr);
    }

    /**
     * Sucht anhand des Namen der Person die reserviert hat nach einer Reservierung
     *
     * @param firstName Der Vorname der Person
     * @param lastName Der Nachname der Person
     * @return Eine Liste mit allen zur Suche passenden Reservierungen
     */
    public Collection<ReservationData> search(String firstName, String lastName, String companyName, String reservationNumber)
    {
        return state.search(firstName, lastName, companyName, reservationNumber);
    }

    public Collection<ReservationData> searchApprox(String firstName, String lastName)
    {
        return state.searchApprox(firstName, lastName);
    }

    /**
     * Wählt eine Reservierung aus die abgearbeitet werden soll
     *
     * @param reservation Die Reservierung die abgearbeitet werden soll
     */
    public void workWithReservation(ReservationData reservation)
    {
        state.workWithReservation(reservation);
    }

    /**
     * Startet einen Check In Vorgang für einen Walk In Gast
     *
     * @param start Das Startdatum des Aufenthalts
     * @param end Das Enddatum des Aufenthalts
     * @param amount Die Anzahl an Personen
     */
    public void createWalkIn(Date start, Date end, int amount)
    {
        state.createWalkIn(start, end, amount);
    }

    /**
     * Startet einen Check In Vorgang für einen Walk In Gast
     *
     * @param days Die Aufenthaltsdauer in Tagen
     * @param amount Die Anzahl an Personen
     */
    public void createWalkIn(int days, int amount)
    {
        state.createWalkIn(days, amount);
    }

    /**
     * Ändert die Informationen, betreffend des aktuellen Check In Vorgangs
     *
     * @param start Das neue Startdatum
     * @param end Das neue Enddatum
     * @param amount Die neue Anzahl an Personen
     */
    public void changeInformation(Date start, Date end, int amount)
    {
        state.changeInformation(start, end, amount);
    }

    /**
     * Gibt bei einem Check In Vorgang die Gäste zurück die einchecken wollen
     *
     * @return Der Gast der die Reservierung angelegt hat
     */
    public Collection<GuestData> getGuests()
    {
        return state.getGuests();
    }

    /**
     * Ändert die Daten eines ausgewählten Gastes
     *
     * @param guest Der Gast, dessen Daten geändert werden sollen
     * @param firstName Der Vorname des Gastes
     * @param lastName Der Nachname des Gastes
     * @param birthday Das Geburtsdatum des Gastes
     * @param address Die Adresse des Gastes
     * @return Der Gast mit den geänderten Daten
     */
    public GuestData changeGuestData(GuestData guest, String firstName, String lastName, char gender, Date birthday, AddressData address)
    {
        return state.changeGuestData(guest, firstName, lastName, gender, birthday, address);
    }

    /**
     * Legt einen neuen Gast an
     * @param firstName
     * Der Vorname des Gsstes
     * @param lastName
     * Der Nachname des Gastes
     * @param gender
     * Das Geschlecht des Gastes
     * @param birthday
     * Der Geburtstag des Gastes
     * @param street
     * Die Straße zu der Adresse des Gastes
     * @param city
     * Die Stadt zu der Adresse des Gastes
     * @param zip
     * Die Postleitzahl zu der Stadt in der Adresse des Gastes
     * @param email
     * Die Email-Adresse zu der Adresse des Gastes
     * @param phone
     * Der Telefonanschluss zu der Adresse des Gastes
     * @param fax
     * Der Faxanschluss zu der Adresse des Gastes
     * @param country
     * Das Land zu der Adresse des Gastes
     * @return 
     * Das <code>GuestData</code> Interface zum Gast, mit den Gettern fuer die Attribute
     */
    public GuestData addGuest(String firstName, String lastName, char gender, Date birthday,
            String street, String city, String zip, String email, String phone, String fax, CountryData country)
    {
        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        address.setZip(zip);
        address.setEmail(email);
        address.setPhone(phone);
        address.setFax(fax);
        address.setIdCountry((Country) country);

        return state.addGuest(firstName, lastName, gender, birthday, address);
    }

    /**
     * Teilt einem Gast ein bestimmtes Zimmer zu
     *
     * @param selectionIndex Der Index des Zimmers, dass ausgewaehlt wurde
     * @param guest Der Gast, der dem Zimmer zugeteilt wird
     */
    public void assignRoom(int selectionIndex, GuestData guest)
    {
        state.assignRoom(selectionIndex, guest);
    }

    public RoomData getRoomData(int selectionIndex)
    {
        return state.getRoomData(selectionIndex);
    }

    public Collection<CountryData> getAllCountries()
    {
        return state.getAllCountries();
    }

    /**
     * Erstellt eine zusätzliche Option um ein Zimmer zuzuweisen
     *
     * @return Der index der Zimmerauswahl
     */
    public int addRoomSelection()
    {
        return state.addRoomSelection();
    }

    /**
     * Entfernt die angegebene Zimmerauswahl
     *
     * @param selectionIndex Der index der zu entfernenden Asuwahl
     */
    public void removeRoomSelection(int selectionIndex)
    {
        state.removeRoomSelection(selectionIndex);
    }

    public Collection<RoomCategoryData> getAllCategories()
    {
        return state.getAllCategories();
    }

    /**
     * Ändert die ausgewählte Kategorie einer bestimmten Zimmerauswahl
     *
     * @param selectionIndex Der index der sich zu verändernden Zimmerauwahl
     * @param category Die neue Kategorie
     * @return Eine Liste der belegbaren Zimmer der Kategorie
     */
    public Collection<RoomData> changeRoomCategory(int selectionIndex, RoomCategoryData category)
    {
        return state.changeRoomCategory(selectionIndex, category);
    }

    /**
     * Wählt ein anderes Zimmer aus
     *
     * @param selectionIndex Der index der Zimmerauswahl, bei der das Zimmer gewählt wird
     * @param roomNumber Die ausgewählte Zimmernummer
     */
    public void changeRoom(int selectionIndex, String roomNumber)
    {
        state.changeRoom(selectionIndex, roomNumber);
    }
    
    /**
     * Wählt ein anderes Zimmer aus
     *
     * @param selectionIndex Der index der Zimmerauswahl, bei der das Zimmer gewählt wird
     * @param room Das ausgewählte Zimmer
     */
    public void changeRoom(int selectionIndex, RoomData room)
    {
        state.changeRoom(selectionIndex, room);
    }

    /**
     * Programmiert die Zimmerschlüssel
     * (throw new NotImplementedException()...)
     */
    public void initKeys()
    {
        state.initKeys();
    }

    /**
     * Gibt eine Liste der möglichen Extraleistungen aus
     *
     * @return Die Liste mit den Extrleistungen
     */
    public Collection<ExtraServiceData> getServices()
    {
        return state.getServices();
    }

    /**
     * Gibt alle Reservierungen aus.
     *
     * @return
     * Alle Reservierungen ,die verfuegbar sind.
     */
    public Collection<ReservationData> getAllReservations()
    {
        return state.getAllReservations();
    }

    /**
     * Gibt alle Verpflegunsarten aus
     *
     * @return
     * Die verfuegbaren Verpflegunsarten
     */
    public Collection<ExtraServiceData> getAllHabitationServices()
    {
        return state.getAllHabitationServices();
    }
    
    public void saveData() throws NoPriceDefinedException, CouldNotSaveException
    {
        state.saveData();
    }
    
    public void back()
    {
        state.back();
    }
    
    public Collection<HabitationData> getHabitationsData()
    {
        return new HelperFunctions<HabitationData, Habitation>().castCollectionUp(state.getHabitationsOverview());
    }
    
    Collection<Habitation> getHabitations()
    {
        return habitations;
    }
    
    void clear()
    {
        startDate = null;
        endDate = null;
        reservation = null;
        roomSelections = null;
        counter = 0;
        reservationItems = null;
        state = null;
        habitations = null;
    }
    
    /**
     ************************************************ Getter und Setter für die States**************************************************************
     */
    public Date getEndDate()
    {
        return endDate;
    }

    void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public Reservation getReservation()
    {
        return reservation;
    }

    void setReservation(Reservation reservation)
    {
        this.reservation = reservation;
    }

    public Collection<ReservationItemData> getReservationItems()
    {
        return reservationItems;
    }

    void setReservationItems(Collection<ReservationItemData> reservationItems)
    {
        this.reservationItems = reservationItems;
    }

    public RoomCategoryData[] getRoomCategoryArray()
    {
        List<RoomCategoryData> categories = new ArrayList<RoomCategoryData>();
        if (reservation != null)
        {
            for (ReservationItemData data : reservation.getReservationItemCollectionData())
            {
                for (int i = 0; i < data.getAmount(); i++)
                {
                    categories.add(data.getReservedCategoryData());
                }
            }
            return categories.toArray(new RoomCategoryData[0]);
        }
        else return new RoomCategoryData[0];
    }

    Map<Integer, RoomSelection> getRoomSelections()
    {
        return roomSelections;
    }

    void setRoomSelections(Map<Integer, RoomSelection> roomSelections)
    {
        this.roomSelections = roomSelections;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    /**
     * Gibt eine Id für eine Raumauswahl aus
     *
     * @return
     * Die Id für die Raumauswahl
     */
    public int getCounter()
    {
        return counter;
    }

    void setCounter(int i)
    {
        this.counter = i;
    }

    int increaseCounter()
    {
        return counter++;
    }

    public boolean isInSwitchingState()
    {
       return state instanceof StartState;
    }

    public void createNewWalkIn()
    {
       state.createNewWalkIn();
    }

    void setHabitations(Collection<Habitation> habitations)
    {
        this.habitations = habitations;
    }
}
