package hotelsoftware.checkin;

import hotelsoftware.domain.parties.Address;
import hotelsoftware.domain.parties.Guest;
import hotelsoftware.domain.reservation.Reservation;
import hotelsoftware.domain.room.Category;
import hotelsoftware.domain.room.Room;
import hotelsoftware.domain.service.ExtraService;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dunst
 */
public interface CheckInState
{
    /**
     * Startet einen CheckIn Vorgang
     */
    void start();
    
    /**
     * Sucht nach einer Reservierung
     * @param reservationNr Die bei der Reservierung erstelle Reservierungsnummer
     * @return Die zur Reservierungsnummer gehörende Reservierung 
     */
    Reservation search(int reservationNr);
    
    /**
     * Sucht anhand des Namen der Person die reserviert hat nach einer Reservierung
     * @param firstName Der Vorname der Person
     * @param lastName Der Nachname der Person
     * @return Eine Liste mit allen zur Suche passenden Reservierungen
     */
    List<Reservation> search(String firstName, String lastName);
    
    /**
     * Startet einen Check In Vorgang für einen Walk In Gast
     * @param start Das Startdatum des Aufenthalts
     * @param end Das Enddatum des Aufenthalts
     * @param amount Die Anzahl an Personen
     */
    void createWalkIn(Date start, Date end, int amount);
    
    /**
     * Startet einen Check In Vorgang für einen Walk In Gast
     * @param days Die Aufenthaltsdauer in Tagen
     * @param amount Die Anzahl an Personen
     */
    void createWalkIn(int days, int amount);
    
    /**
     * Ändert die Informationen, betreffend des aktuellen Check In Vorgangs
     * @param start Das neue Startdatum
     * @param end Das neue Enddatum
     * @param amount Die neue Anzahl an Personen
     */
    void changeInformation(Date start, Date end, int amount);
    
    /**
     * Bibt bei einem Check In Vorgang die Gäste zurück die einchecken wollen
     * @return Alle bereits erfassten Gäste die einchecken
     */
    List<Guest> getGuests();
    
    /**
     * Ändert die Daten eines ausgewählten Gastes
     * @param guest Der Gast, dessen Daten geändert werden sollen
     * @param firstName Der Vorname des Gastes
     * @param lastName Der Nachname des Gastes
     * @param birthday Das Geburtsdatum des Gastes
     * @param address Die Adresse des Gastes
     * @return Der Gast mit den geänderten Daten
     */
    Guest chanceGuestData(Guest guest, String firstName, String lastName, Date birthday, Address address);
    
    /**
     * Legt einen neuen Gast an
     * @param firstName Der Vorname des Gastes
     * @param lastName Der Nachname des Gastes
     * @param birthday Das Geburtsdatum des Gastes
     * @param address Die Adresse des Gastes
     * @return Der neu erstellte Gast
     */
    Guest addGuest(String firstName, String lastName, Date birthday, Address address);
    
    /**
     * Teilt einem Gast ein bestimmtes Zimmer zu
     * @param guest Der Gast der zugeteilt werden soll
     * @param room Das Zimmer das dem Gast zugeteilt wird
     */
    void assignRoom(Guest guest, Room room);
    
    /**
     * Erstellt eine zusätzliche Option um ein Zimmer zuzuweisen
     * @return Der index der Zimmerauswahl
     */
    int addRoomSelection();
    
    //Eine Liste aus Kategorien aus denen ein Zimmer ausgewählt werden kann
    
    /**
     * Entfernt die angegebene Zimmerauswahl
     * @param selectionIndex Der index der zu entfernenden Asuwahl
     */
    void removeRoomSelection(int selectionIndex);
    
    /**
     * Ändert die ausgewählte Kategorie einer bestimmten Zimmerauswahl
     * @param selectionIndex Der index der sich zu verändernden Zimmerauwahl
     * @param category Die neue Kategorie
     * @return Eine Liste der belegbaren Zimmer der Kategorie
     */
    List<Room> changeRoomCategory(int selectionIndex, Category category);
    
    /**
     * Rählt ein anderes Zimmer aus
     * @param selectionIndex Der index der Zimmerauswahl, bei der das Zimmer gewählt wird
     * @param roomNumber Die ausgewählte Zimmernummer
     */
    void changeRoom(int selectionIndex, int roomNumber);

    /**
     * Programmiert die Zimmerschlüssel
     * (throw new NotImplementedException()...)
     */
    void initKeys();
    
    /**
     * Gibt eine Liste der möglichen Extraleistungen aus
     * @return Die Liste mit den Extrleistungen
     */
    List<ExtraService> getServices();
    
    /**
     * Gibt an welche Extraleistungen wie oft ausgewählt wurden
     * @param services Eine Map, bestehend aus gebuchten Extrleistungen und deren Anzahl
     */
    void selectServices(Map<ExtraService, Integer> services);
    
    /**
     * Ruft den nächsten Schritt auf
     */
    void nextStep();
    
    /**
     * Geht einen Schritt zurück
     */
    void previousStep();
}
