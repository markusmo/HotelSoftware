/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.checkin;

import hotelsoftware.checkin.CheckInController;
import hotelsoftware.checkin.CouldNotSaveException;
import hotelsoftware.checkin.NoRoomsAvailableException;
import hotelsoftware.checkin.NoRoomsInCategoryAvailableException;
import hotelsoftware.model.domain.parties.data.AddressData;
import hotelsoftware.model.domain.parties.data.CountryData;
import hotelsoftware.model.domain.parties.data.GuestData;
import hotelsoftware.model.domain.reservation.data.ReservationData;
import hotelsoftware.model.domain.room.NoPriceDefinedException;
import hotelsoftware.model.domain.room.data.RoomCategoryData;
import hotelsoftware.model.domain.room.data.RoomData;
import hotelsoftware.model.domain.service.data.ExtraServiceData;
import hotelsoftware.model.domain.service.data.HabitationData;
import hotelsoftware.support.PermissionDeniedException;
import hotelsoftware.support.PermissionNotFoundException;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Dieser Controller ist eine Wrapper-Klasse für den Check-In-Controller, der noch zusaetzlich
 * eine Validierung der Eingaben vornimmt.
 *
 * @author Johannes
 */
public class CheckInGuiControler
{
    private JPanel contentpane;
    private JTabbedPane roomTabPane;
    private CheckInController cic;
    private Collection<RoomCategoryData> roomCategories;

    private static class CheckInGuiControllerHolder
    {
        private static final CheckInGuiControler INSTANCE;
        static 
        {
           // try
            {
                INSTANCE = new CheckInGuiControler();
            }
            /*
            catch (PermissionNotFoundException ex)
            {
                throw new ExceptionInInitializerError(ex);
            }
            catch (PermissionDeniedException ex)
            {
                throw new ExceptionInInitializerError(ex);
            }*/
        }
    }

    public static CheckInGuiControler getInstance() //throws PermissionNotFoundException, PermissionDeniedException
    {
        return CheckInGuiControllerHolder.INSTANCE;
    }
    
    private CheckInGuiControler() //throws PermissionNotFoundException, PermissionDeniedException
    {
        try
        {
            cic = CheckInController.getInstance();
        }
        catch (PermissionNotFoundException ex)
        {
            ex.printStackTrace();
            Logger.getLogger(CheckInGuiControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (PermissionDeniedException ex)
        {
            ex.printStackTrace();
            Logger.getLogger(CheckInGuiControler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sucht nach den Parametern Reservierungen und gibt diese aus
     * @param firstName
     * Der Vorname, des Kunden, der reserviert hat
     * @param lastName
     * Der Nachname, des Kunden, der reserviet hat
     * @param companyName
     * Der Firmanname, des Kunden, der reserviert hat
     * @param reservationNumber
     * Die Reservierungsnummer, der Reservierung
     * @return 
     * Eine Liste von Reservierungen, die mit den Parametern übereinstimmen, mehr als eine Reservierung,
     * wenn die Paremeter mit mehr als einer Reservierung übereinstimmen.
     */
    public Collection<ReservationData> search(String firstName, String lastName, String companyName, String reservationNumber)
    {
        return cic.search(firstName, lastName, companyName, reservationNumber);
    }

    /**
     * Gibt alle Reservierungen aus
     *
     * @return
     * Alle Resservierungen, die verfuegbar sind
     */
    public Collection<ReservationData> getAllReservations()
    {
        return cic.getAllReservations();
    }

    /**
     * Gibt die Reservierung aus, die gerade ausgewählt ist
     * @return 
     * die ausgewählte Reservierung
     */
    public ReservationData getSelectedReservation()
    {
        return cic.getReservation();
    }

    /**
     * Ändert die ausgewählte Kategorie einer bestimmten Zimmerauswahl
     *
     * @param index Der index der sich zu verändernden Zimmerauwahl
     * @param c Die neue Kategorie
     * @return Eine Liste der belegbaren Zimmer der Kategorie
     */
    public Collection<RoomData> getFreeRoomsInCategory(int index, RoomCategoryData c)
    {
        return cic.changeRoomCategory(index, c);
    }

    /**
     * Gibt einen Gast aus
     *
     * @param fname
     * Der Nachname des Gasts
     * @param lname
     * Der Vorname des Gasts
     * @param street
     * Die Straße des Gasts
     * @param city
     * Der Wohnort des Gasts
     * @param zip
     * Die Postleitzahl des Gasts
     * @param email
     * Die Email des Gasts
     * @param phone
     * Die Telefonnummer des Gasts
     * @param fax
     * Die Faxnummer des Gasts
     * @param country
     * Das Ursprungsland des Gasts
     * @return
     * gibt einen Gast zurueck
     */
    public GuestData getGuest(String fname, String lname, String street, String city, String zip, String email, String phone, String fax, String country)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Gibt alle Extradienstleistungen aus
     *
     * @return
     * Alle verfuegbaren Dienstleistungen
     */
    public Collection<ExtraServiceData> getExtraservices()
    {
        return cic.getServices();
    }

    /**
     * Setzt die aktuelle Reservierung aus
     *
     * @param selectedReservation
     * Die Reservierung mit der gearbeitet werden soll
     */
    void setSelectedReservation(ReservationData selectedReservation)
    {
        cic.workWithReservation(selectedReservation);
    }

    /**
     * Erstellt eine zusätzliche Option um ein Zimmer zuzuweisen
     *
     * @return Der index der Zimmerauswahl
     */
    int addRoom()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Wählt ein anderes Zimmer aus
     *
     * @param selectionIndex Der index der Zimmerauswahl, bei der das Zimmer gewählt wird
     * @param roomNumber Die ausgewählte Zimmernummer
     */
    public void changeRoom(int selectionIndex, String roomNumber)
    {
        cic.changeRoom(selectionIndex, roomNumber);
    }

    /**
     * Wählt ein anderes Zimmer aus
     *
     * @param selectionIndex Der index der Zimmerauswahl, bei der das Zimmer gewählt wird
     * @param room Der ausgewaehlte Raum
     */
    public void changeRoom(int selectionIndex, RoomData room)
    {
        cic.changeRoom(selectionIndex, room);
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
        return cic.changeRoomCategory(selectionIndex, category);
    }

    /**
     * Gibt die Zimmerinformation zu einem jeweiligen Zimmer aus
     *
     * @param roomIndex
     * Der Index der Zimmer
     * @return
     * Die Zimmerinformationen des Zimmers
     */
    public RoomData getRoomData(int roomIndex) throws NoRoomsInCategoryAvailableException, NoRoomsAvailableException
    {
        return cic.getRoomData(roomIndex);
    }

    /**
     * Erstellt eine zusätzliche Option um ein Zimmer zuzuweisen
     *
     * @return Der index der Zimmerauswahl
     */
    public int addRoomSelection()
    {
        return cic.addRoomSelection();
    }
    /**
     * Gibt alle Verpflegunsarten aus
     *
     * @return
     * Die verfuebaren Verpflegunsarten aus
     */
    private Collection<ExtraServiceData> habitationServices;

    /**
     *
     * @return Gibt alle Extraservieces vom Typ Habitation zurück. (ZB Halbpension)
     */
    public Collection<ExtraServiceData> getAllHabitationServices()
    {
        if (habitationServices == null)
        {
            habitationServices = (Collection<ExtraServiceData>) cic.getAllHabitationServices();
        }
        return habitationServices;
    }

    /**
     * Gibt das Contentpane für den Mainframe aus.
     * @return
     * das Contentpane, des Mainframes
     */
    public JPanel getContentpane()
    {
        return contentpane;
    }

    /**
     * Setzt einmalig das Contentpane für den Mainframe
     * @param contentpane 
     * das Contentpane des Mainframes.
     */
    public void setContentpane(JPanel contentpane)
    {
        if (this.contentpane == null)
        {
            this.contentpane = contentpane;

        }
    }

    /**
     * Teilt einem Gast ein bestimmtes Zimmer zu
     *
     * @param selectionIndex der Index des Raumes der ausgewählt wurde
     * @param guest Der Gast, der zu diesem Zimmer zugeteilt werden soll
     */
    public void assignRoom(int selectionIndex, GuestData guest)
    {
        cic.assignRoom(selectionIndex, guest);
    }
    private Collection<CountryData> countries;

    public Collection<CountryData> getAllCountries()
    {
        if (countries == null)
        {
            countries = cic.getAllCountries();
        }
        return countries;
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
        cic.createWalkIn(start, end, amount);
    }

    /**
     * Startet einen Check In Vorgang für einen Walk In Gast
     *
     * @param days Die Aufenthaltsdauer in Tagen
     * @param amount Die Anzahl an Personen
     */
    public void createWalkIn(int days, int amount)
    {
        cic.createWalkIn(days, amount);
    }

    /**
     * Ändert die Periode, betreffend des aktuellen Check In Vorgangs
     *
     * @param start Das neue Startdatum
     * @param end Das neue Enddatum
     */
    public void changeInformation(Date start, Date end)
    {
        cic.changeInformation(start, end, 5);
    }

    /**
     * Gibt bei einem Check In Vorgang die Gäste zurück die einchecken wollen
     *
     * @return Der Gast der die Reservierung angelegt hat
     */
    public Collection<GuestData> getGuests()
    {
        return cic.getGuests();
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
        return cic.changeGuestData(guest, firstName, lastName, gender, birthday, address);
    }

    /**
     * Diese Methode legt einen neuen Gast an
     *
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
     * Das
     * <code>GuestData</code> Interface zum Gast, mit den Gettern fuer die Attribute
     */
    public GuestData addGuest(String firstName, String lastName, char gender, Date birthday,
            String street, String city, String zip, String email, String phone, String fax, CountryData country)
    {
        return cic.addGuest(firstName, lastName, gender, birthday, street, city, zip, email, phone, fax, country);
    }

    /**
     * Entfernt die angegebene Zimmerauswahl
     *
     * @param selectionIndex Der index der zu entfernenden Asuwahl
     */
    public void removeRoomSelection(int selectionIndex)
    {
        cic.removeRoomSelection(selectionIndex);
    }
    
    /**
     * Gibt alle Zimmerkategorien aus
     * @return 
     * alle Zimmerkategorien die es gibt.
     */
    public Collection<RoomCategoryData> getAllCategories()
    {
        if (roomCategories == null)
        {
            roomCategories = cic.getAllCategories();
        }
        return roomCategories;
    }

    /**
     * Programmiert die Zimmerschlüssel
     * (throw new NotImplementedException()...)
     */
    public void initKeys()
    {
        cic.initKeys();
    }

    /**
     * Gibt eine Liste der möglichen Extraleistungen aus
     *
     * @return Die Liste mit den Extrleistungen
     */
    public Collection<ExtraServiceData> getServices()
    {
        return cic.getServices();
    }

    public JTabbedPane getRoomTabPane()
    {
        return roomTabPane;
    }

    public void setRoomTabPane(JTabbedPane roomTabPane)
    {
        this.roomTabPane = roomTabPane;
    }

    int getGuestCounter()
    {
        return cic.getCounter();
    }

    /**
     * setzt den Checkinvorgang zurück
     */
    public void back()
    {
        cic.back();
    }
    
    void clear()
    {
        cic.clear();
    }

    public void createNewWalking()
    {
        cic.createNewWalkIn();
    }
    
    public void saveData() throws NoPriceDefinedException, CouldNotSaveException
    {
        cic.saveData();
    }
    
    public Collection<HabitationData> getHabitationsData()
    {
        return cic.getHabitationsData();
    }
    
  
}
