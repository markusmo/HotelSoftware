/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.checkin;

import hotelsoftware.checkin.CheckInController;
import hotelsoftware.checkin.CheckInState;
import hotelsoftware.model.domain.parties.Address;
import hotelsoftware.model.domain.parties.Country;
import hotelsoftware.model.domain.parties.data.AddressData;
import hotelsoftware.model.domain.parties.data.CountryData;
import hotelsoftware.model.domain.parties.data.GuestData;
import hotelsoftware.model.domain.reservation.Reservation;
import hotelsoftware.model.domain.reservation.data.ReservationData;
import hotelsoftware.model.domain.reservation.data.ReservationItemData;
import hotelsoftware.model.domain.room.data.RoomCategoryData;
import hotelsoftware.model.domain.room.data.RoomData;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.model.domain.service.data.ExtraServiceData;
import java.text.SimpleDateFormat;
import java.util.*;
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
    private ReservationData selectedReservation;

    int getCounter()
    {
        return CheckInController.getInstance().getCounter();
    }

    private static class CheckInGuiControllerHolder
    {
        private static final CheckInGuiControler INSTANCE = new CheckInGuiControler();
    }

    public static CheckInGuiControler getInstance()
    {
        return CheckInGuiControllerHolder.INSTANCE;
    }

    /**
     * Diese Methode gibt ein Set von Reservierungen aus, welches es aus dem Check-In-Controller durch die dort implementierte Suche bekommt.
     *
     * @param fname
     * Der Vorname nach dem gesucht wird
     * @param lname
     * Der Nachname nach dem gesucht wird
     * @param reservationNumber
     * Die Reservierungsnummer nach der gesucht wird
     * @return
     * Ein Set aus Reservierungen, die eindeutig sind.
     * @throws InvalidInputException
     * Falls die Validierung fehlschlaegt, wird ein Fehler geworfen
     */
    public Collection<ReservationData> searchReservations(String fname, String lname, String reservationNumber) throws InvalidInputException
    {
        Collection<ReservationData> res = new LinkedHashSet<ReservationData>();
        try
        {
            CheckInController cic = CheckInController.getInstance();

            if (reservationNumber.length() > 0)
            {
                ReservationData reservation = cic.search(Integer.parseInt(reservationNumber));

                if (reservation != null)
                {
                    res.add(reservation);
                    //return dafuq;
                }
            }
            if (res.isEmpty())
            {
                res.addAll(CheckInController.getInstance().searchApprox(fname, lname));
            }

            return res;
        }
        catch (NumberFormatException ex)
        {
            throw new InvalidInputException(reservationNumber);
        }
    }

    /**
     * Gibt alle Reservierungen aus
     *
     * @return
     * Alle Resservierungen, die verfuegbar sind
     */
    public Collection<ReservationData> getAllReservations()
    {
        return CheckInController.getInstance().getAllReservations();
    }

    public ReservationData getSelectedReservation()
    {
        return selectedReservation;
    }

    /**
     * Gibt alle Zimmerkategorieen
     *
     * @return
     * Alle verfuegbaren Zimmerkategeorieen
     */
    public Collection<RoomCategoryData> getCategories()
    {
        return CheckInController.getInstance().getAllCategories();
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
        return CheckInController.getInstance().changeRoomCategory(index, c);
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
        return CheckInController.getInstance().getServices();
    }

    /**
     * Setzt die aktuelle Reservierung aus
     *
     * @param selectedReservation
     * Die Reservierung mit der gearbeitet werden soll
     */
    void setSelectedReservation(ReservationData selectedReservation)
    {
        this.selectedReservation = selectedReservation;
        CheckInController.getInstance().workWithReservation(selectedReservation);
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
        CheckInController.getInstance().changeRoom(selectionIndex, roomNumber);
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
        return CheckInController.getInstance().changeRoomCategory(selectionIndex, category);
    }

    /**
     * Gibt die Zimmerinformation zu einem jeweiligen Zimmer aus
     *
     * @param roomIndex
     * Der Index der Zimmer
     * @return
     * Die Zimmerinformationen des Zimmers
     */
    public RoomData getRoomData(int roomIndex)
    {
        return CheckInController.getInstance().getRoomData(roomIndex);
    }

    /**
     * Erstellt eine zusätzliche Option um ein Zimmer zuzuweisen
     *
     * @return Der index der Zimmerauswahl
     */
    public int addRoomSelection()
    {
        return CheckInController.getInstance().addRoomSelection();
    }

    /**
     * Gibt alle Verpflegunsarten aus
     *
     * @return
     * Die verfuebaren Verpflegunsarten aus
     */
    public List<ExtraServiceData> getAllHabitationServices()
    {
        return (List<ExtraServiceData>) CheckInController.getInstance().getAllHabitationServices();
    }

    public JPanel getContentpane()
    {
        return contentpane;
    }

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
     * @param guest Der Gast der zugeteilt werden soll
     * @param room Das Zimmer das dem Gast zugeteilt wird
     */
    public void assignRoom(GuestData guest, RoomData room)
    {
        CheckInController.getInstance().assignRoom(guest, room);
    }

    public Collection<CountryData> getAllCountries()
    {
        return CheckInController.getInstance().getAllCountries();
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
        CheckInController.getInstance().createWalkIn(start, end, amount);
    }

    /**
     * Startet einen Check In Vorgang für einen Walk In Gast
     *
     * @param days Die Aufenthaltsdauer in Tagen
     * @param amount Die Anzahl an Personen
     */
    public void createWalkIn(int days, int amount)
    {
        CheckInController.getInstance().createWalkIn(days, amount);
    }

    /**
     * Ändert die Informationen, betreffend des aktuellen Check In Vorgangs
     *
     * @param start Das neue Startdatum
     * @param end Das neue Enddatum
     * @param amount Die neue Anzahl an Personen
     */
    public void changeInformation(Date start, Date end)
    {
        CheckInController.getInstance().changeInformation(start, end, 5);
    }

    /**
     * Bibt bei einem Check In Vorgang die Gäste zurück die einchecken wollen
     *
     * @return Der Gast der die Reservierung angelegt hat
     */
    public GuestData getGuests()
    {
        return CheckInController.getInstance().getGuests();
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
        return CheckInController.getInstance().changeGuestData(guest, firstName, lastName, gender, birthday, address);
    }

    /**
     * Legt einen neuen Gast an
     *
     * @param firstName Der Vorname des Gastes
     * @param lastName Der Nachname des Gastes
     * @param birthday Das Geburtsdatum des Gastes
     * @param address Die Adresse des Gastes
     * @return Der neu erstellte Gast
     */
    public GuestData addGuest(String firstName, String lastName, char gender, Date birthday,
            String street, String city, String zip, String email, String phone, String fax, CountryData country)
    {
        return CheckInController.getInstance().addGuest(firstName, lastName, gender, birthday, street, city, zip, email, phone, fax, country);
    }

    /**
     * Entfernt die angegebene Zimmerauswahl
     *
     * @param selectionIndex Der index der zu entfernenden Asuwahl
     */
    public void removeRoomSelection(int selectionIndex)
    {
        CheckInController.getInstance().removeRoomSelection(selectionIndex);
    }

    public Collection<RoomCategoryData> getAllCategories()
    {
        return CheckInController.getInstance().getAllCategories();
    }

    /**
     * Programmiert die Zimmerschlüssel
     * (throw new NotImplementedException()...)
     */
    public void initKeys()
    {
        CheckInController.getInstance().initKeys();
    }

    /**
     * Gibt eine Liste der möglichen Extraleistungen aus
     *
     * @return Die Liste mit den Extrleistungen
     */
    public Collection<ExtraServiceData> getServices()
    {
        return CheckInController.getInstance().getServices();
    }

    /**
     * Gibt an welche Extraleistungen wie oft ausgewählt wurden
     *
     * @param services Eine Map, bestehend aus gebuchten Extrleistungen und deren Anzahl
     */
    public void selectServices(Collection<ExtraServiceData> services)
    {
        CheckInController.getInstance().selectServices(services);
    }

    public JTabbedPane getRoomTabPane()
    {
        return roomTabPane;
    }

    public void setRoomTabPane(JTabbedPane roomTabPane)
    {
        this.roomTabPane = roomTabPane;
    }
}
