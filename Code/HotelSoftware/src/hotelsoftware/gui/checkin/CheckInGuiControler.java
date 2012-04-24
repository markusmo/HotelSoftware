/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.checkin;

import hotelsoftware.checkin.CheckInController;
import hotelsoftware.model.domain.parties.GuestData;
import hotelsoftware.model.domain.reservation.ReservationData;
import hotelsoftware.model.domain.room.data.RoomCategoryData;
import hotelsoftware.model.domain.room.data.RoomData;
import hotelsoftware.model.domain.service.data.ExtraServiceData;
import java.util.*;
import javax.swing.JPanel;

/**
 * Dieser Controller ist eine Wrapper-Klasse für den Check-In-Controller, der noch zusaetzlich
 * eine Validierung der Eingaben vornimmt.
 * @author Johannes
 */
public class CheckInGuiControler
{
    private JPanel contentpane;

    int getCounter()
    {
        return CheckInController.getInstance().getCounter();
    }

    private static class CheckInGuiControllerHolder
    {
        private static final CheckInGuiControler INSTANCE = new CheckInGuiControler();
    }
    private ReservationData selectedReservation;

    public static CheckInGuiControler getInstance()
    {
        return CheckInGuiControllerHolder.INSTANCE;
    }

    /**
     * Diese Methode gibt ein Set von Reservierungen aus, welches es aus dem Check-In-Controller durch die dort implementierte Suche bekommt.
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
            // cic.start();
            if (reservationNumber.length() > 0)
            {
                ReservationData reservation = cic.search(Integer.parseInt(reservationNumber));

                if (reservation != null)
                {
                    res.add(reservation);
                    //return dafuq;
                }
            }
            res.addAll(CheckInController.getInstance().searchApprox(fname, lname));
            //dafuq.addAll(CheckInController.getInstance().search(fname, lname));

            return res;
        }
        catch (NumberFormatException ex)
        {
            throw new InvalidInputException(reservationNumber);
        }
    }

    /**
     * Gibt alle Reservierungen aus
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
     * @return 
     * Alle verfuegbaren Zimmerkategeorieen
     */
    public Collection<RoomCategoryData> getCategories()
   {
        return CheckInController.getInstance().getAllCategories();
    }
     /**
     * Ändert die ausgewählte Kategorie einer bestimmten Zimmerauswahl
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
     * @return
     * Alle verfuegbaren Dienstleistungen
     */
    public Collection<ExtraServiceData> getExtraservices()

    {
        return CheckInController.getInstance().getServices();
    }

    /**
     * Setzt die aktuelle Reservierung aus
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
}
