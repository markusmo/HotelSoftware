/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.checkin;

import hotelsoftware.model.domain.parties.AddressData;
import hotelsoftware.model.domain.parties.GuestData;
import hotelsoftware.model.domain.reservation.ReservationData;
import hotelsoftware.model.domain.room.CategoryData;
import hotelsoftware.model.domain.room.RoomData;
import hotelsoftware.model.domain.service.ExtraServiceData;
import java.util.*;

/**
 *
 * @author Dunst
 */
public class CheckInController
{
    private static class CheckInControllerHolder
    {
        private static final CheckInController INSTANCE = new CheckInController();
    }
    
    public static CheckInController getInstance()
    {
        return CheckInControllerHolder.INSTANCE;
    }
    
    private CheckInState state;
    
    private CheckInController()
    {
    }
    
    public void start()
    {
        state = new StartState(null);
    }
    
    void setState(CheckInState state)
    {
        this.state = state;
    }
    
    /**
     * Sucht nach einer Reservierung
     * @param reservationNr Die bei der Reservierung erstelle Reservierungsnummer
     * @return Die zur Reservierungsnummer gehörende Reservierung 
     */
    public ReservationData search(int reservationNr)
    {
        return state.search(reservationNr);
    }
    
    /**
     * Sucht anhand des Namen der Person die reserviert hat nach einer Reservierung
     * @param firstName Der Vorname der Person
     * @param lastName Der Nachname der Person
     * @return Eine Liste mit allen zur Suche passenden Reservierungen
     */
    public Collection<ReservationData> search(String firstName, String lastName)
    {
        return state.search(firstName, lastName);
    }
    
    /**
     * Wählt eine Reservierung aus die abgearbeitet werden soll
     * @param reservation Die Reservierung die abgearbeitet werden soll
     */
    public void workWithReservation(ReservationData reservation)
    {
        state.workWithReservation(reservation);
    }
    
    /**
     * Startet einen Check In Vorgang für einen Walk In Gast
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
     * @param days Die Aufenthaltsdauer in Tagen
     * @param amount Die Anzahl an Personen
     */
    public void createWalkIn(int days, int amount)
    {
        state.createWalkIn(days, amount);
    }
    
    /**
     * Ändert die Informationen, betreffend des aktuellen Check In Vorgangs
     * @param start Das neue Startdatum
     * @param end Das neue Enddatum
     * @param amount Die neue Anzahl an Personen
     */
    public void changeInformation(Date start, Date end, int amount)
    {
        state.changeInformation(start, end, amount);
    }
    
    /**
     * Bibt bei einem Check In Vorgang die Gäste zurück die einchecken wollen
     * @return Der Gast der die Reservierung angelegt hat
     */
    public GuestData getGuests()
    {
        return state.getGuest();
    }
    
    /**
     * Ändert die Daten eines ausgewählten Gastes
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
     * @param firstName Der Vorname des Gastes
     * @param lastName Der Nachname des Gastes
     * @param birthday Das Geburtsdatum des Gastes
     * @param address Die Adresse des Gastes
     * @return Der neu erstellte Gast
     */
    public GuestData addGuest(String firstName, String lastName, char gender, Date birthday, AddressData address)
    {
        return state.addGuest(firstName, lastName, gender, birthday, address);
    }
    
    /**
     * Teilt einem Gast ein bestimmtes Zimmer zu
     * @param guest Der Gast der zugeteilt werden soll
     * @param room Das Zimmer das dem Gast zugeteilt wird
     */
    public void assignRoom(GuestData guest, RoomData room)
    {
        state.assignRoom(guest, room);
    }
    
    public RoomData getRoomData(int selectionIndex)
    {
        return state.getRoomData(selectionIndex);
    }
    
    /**
     * Erstellt eine zusätzliche Option um ein Zimmer zuzuweisen
     * @return Der index der Zimmerauswahl
     */
    public int addRoomSelection()
    {
        return state.addRoomSelection();
    }
        
    /**
     * Entfernt die angegebene Zimmerauswahl
     * @param selectionIndex Der index der zu entfernenden Asuwahl
     */
    public void removeRoomSelection(int selectionIndex)
    {
        state.removeRoomSelection(selectionIndex);
    }
    
    public Collection<CategoryData> getAllCategories()
    {
        return state.getAllCategories();
    }
    
    /**
     * Ändert die ausgewählte Kategorie einer bestimmten Zimmerauswahl
     * @param selectionIndex Der index der sich zu verändernden Zimmerauwahl
     * @param category Die neue Kategorie
     * @return Eine Liste der belegbaren Zimmer der Kategorie
     */
    public Collection<RoomData> changeRoomCategory(int selectionIndex, CategoryData category)
    {
        return state.changeRoomCategory(selectionIndex, category);
    }
    
    /**
     * Rählt ein anderes Zimmer aus
     * @param selectionIndex Der index der Zimmerauswahl, bei der das Zimmer gewählt wird
     * @param roomNumber Die ausgewählte Zimmernummer
     */
    public void changeRoom(int selectionIndex, String roomNumber)
    {
        state.changeRoom(selectionIndex, roomNumber);
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
     * @return Die Liste mit den Extrleistungen
     */
    public Collection<ExtraServiceData> getServices()
    {
        return state.getServices();
    }
    
    /**
     * Gibt an welche Extraleistungen wie oft ausgewählt wurden
     * @param services Eine Map, bestehend aus gebuchten Extrleistungen und deren Anzahl
     */
    public void selectServices(Collection<ExtraServiceData> services)
    {
        state.selectServices(services);
    }

    public Collection<ReservationData> getAllReservations()
    {
        return state.getAllReservations();
    }
}
