package hotelsoftware.model.domain.room;

import java.util.Collection;

/**
 * Die Fassadenklasse, die das Package Room verwaltet
 *
 * @author mohi
 */
public class RoomFacade
{

    private RoomFacade()
    {
    }

    public static RoomFacade getInstance()
    {
        return RoomFacadeHolder.INSTANCE;
    }

    private static class RoomFacadeHolder
    {

        private static final RoomFacade INSTANCE = new RoomFacade();
    }

    /**
     * Gibt ein Zimmer nach einer nummer aus
     *
     * @param number die Nummer des Zimmers
     * @return das Zimmer mit der gesuchten Nummer
     */
    public Room getRoomByNumber(String number)
    {
        return Room.getRoomByNumber(number);
    }

    /**
     * Alle Zimmer nach einer Kategorie
     *
     * @param cat Die Zimmerkategorie
     * @return Alle Zimmer nach dieser Kategorie
     */
    public Collection<IRoom> getRoomsByCategory(RoomCategory cat)
    {
        return Room.getRoomsByCategory(cat);
    }
}
