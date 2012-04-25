package hotelsoftware.checkin;

import hotelsoftware.model.domain.parties.data.AddressData;
import hotelsoftware.model.domain.parties.data.CountryData;
import hotelsoftware.model.domain.parties.data.GuestData;
import hotelsoftware.model.domain.reservation.data.ReservationData;
import hotelsoftware.model.domain.room.Room;
import hotelsoftware.model.domain.room.RoomCategory;
import hotelsoftware.model.domain.room.data.RoomCategoryData;
import hotelsoftware.model.domain.room.data.RoomData;
import hotelsoftware.model.domain.service.data.ExtraServiceData;
import java.util.Collection;
import java.util.Date;


/**
 * Diese Klasse ist die abstrakte Klasse, die alle Status und dessen Funktionen abbilded.
 * Keine dieser Methoden ist implementiert, fuer jeden Status muss die jeweilige Funktionalitaet implementiert werden.
 * 
 * @author Dunst
 */
public abstract class CheckInState
{
    protected CheckInController context;
    
    public CheckInState(CheckInController context)
    {
        this.context = context;
    }

    /**
     * Sucht nach einer Reservierung
     *
     * @param reservationNr Die bei der Reservierung erstelle Reservierungsnummer
     * @return Die zur Reservierungsnummer gehörende Reservierung
     */
    public ReservationData search(int reservationNr)
    {
        throw new IllegalStateException();
    }

    /**
     * Sucht anhand des Namen der Person die reserviert hat nach einer Reservierung
     *
     * @param firstName Der Vorname der Person
     * @param lastName Der Nachname der Person
     * @return Eine Liste mit allen zur Suche passenden Reservierungen
     */
    public Collection<ReservationData> search(String firstName, String lastName)
    {
        throw new IllegalStateException();
    }

    /**
     * Wählt eine Reservierung aus die abgearbeitet werden soll
     *
     * @param reservation Die Reservierung die abgearbeitet werden soll
     */
    public void workWithReservation(ReservationData reservation)
    {
        throw new IllegalStateException();
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
        throw new IllegalStateException();
    }

    /**
     * Startet einen Check In Vorgang für einen Walk In Gast
     *
     * @param days Die Aufenthaltsdauer in Tagen
     * @param amount Die Anzahl an Personen
     */
    public void createWalkIn(int days, int amount)
    {
        throw new IllegalStateException();
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
        throw new IllegalStateException();
    }

    /**
     * Bibt bei einem Check In Vorgang die Gäste zurück die einchecken wollen
     *
     * @return Alle bereits erfassten Gäste die einchecken
     */
    public GuestData getGuest()
    {
        throw new IllegalStateException();
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
        throw new IllegalStateException();
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
    public GuestData addGuest(String firstName, String lastName, char gender, Date birthday, AddressData address)
    {
        throw new IllegalStateException();
    }

    /**
     * Teilt einem Gast ein bestimmtes Zimmer zu
     *
     * @param guest Der Gast der zugeteilt werden soll
     * @param room Das Zimmer das dem Gast zugeteilt wird
     */
    public void assignRoom(GuestData guest, RoomData room)
    {
        throw new IllegalStateException();
    }

    /**
     * Erstellt eine zusätzliche Option um ein Zimmer zuzuweisen
     *
     * @return Der index der Zimmerauswahl
     */
    public int addRoomSelection()
    {
        throw new IllegalStateException();
    }

    /**
     * Entfernt die angegebene Zimmerauswahl
     *
     * @param selectionIndex Der index der zu entfernenden Asuwahl
     */
    public void removeRoomSelection(int selectionIndex)
    {
        throw new IllegalStateException();
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
        throw new IllegalStateException();
    }

    /**
     * Wählt ein anderes Zimmer aus
     *
     * @param selectionIndex Der index der Zimmerauswahl, bei der das Zimmer gewählt wird
     * @param roomNumber Die ausgewählte Zimmernummer
     */
    public void changeRoom(int selectionIndex, String roomNumber)
    {
        throw new IllegalStateException();
    }

    /**
     * Programmiert die Zimmerschlüssel
     * (throw new NotImplementedException()...)
     */
    public void initKeys()
    {
        throw new IllegalStateException();
    }

    /**
     * Gibt eine Liste der möglichen Extraleistungen aus
     *
     * @return Die Liste mit den Extrleistungen
     */
    public Collection<ExtraServiceData> getServices()
    {
        throw new IllegalStateException();
    }

    /**
     * Gibt an welche Extraleistungen wie oft ausgewählt wurden
     *
     * @param services Eine Map, bestehend aus gebuchten Extrleistungen und deren Anzahl
     */
    public void selectServices(Collection<ExtraServiceData> services)
    {
        throw new IllegalStateException();
    }

    public Collection<RoomCategoryData> getAllCategories()
    {
        throw new IllegalStateException();
    }

    public Collection<ReservationData> getAllReservations()
    {
        throw new IllegalStateException();
    }

    public RoomData getRoomData(int selectionIndex)
    {
        throw new IllegalStateException();
    }

    public Collection<ReservationData> searchApprox(String firstName, String lastName)
    {
        throw new IllegalStateException();
    }

    public Collection<ExtraServiceData> getAllHabitationServices()
    {
        throw new IllegalStateException();
    }

    public Collection<CountryData> getAllCountries()
    {
        throw new IllegalStateException();
    }

    protected class RoomSelection
    {
        private RoomCategoryData category;
        private Room room;

        public RoomSelection(RoomCategoryData category, Room room)
        {
            this.category = category;
            this.room = room;
            this.room.setCategory((RoomCategory)category);
        }

        public RoomCategoryData getCategory()
        {
            return category;
        }

        public void setCategory(RoomCategory category)
        {
            this.category = (RoomCategoryData) category;
        }

        public Room getRoom()
        {
            return room;
        }

        public void setRoom(Room room)
        {
            this.room = room;
        }
    }
}
