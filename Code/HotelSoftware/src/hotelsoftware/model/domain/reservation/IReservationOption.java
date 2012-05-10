package hotelsoftware.model.domain.reservation;

/**
 *Dieses Interface enthällt die Methoden der Klasse ReservationOption, welche dort benötigt werden.
 * @author Kno
 */
public interface IReservationOption {

    /**
     * Diese Methode wird benötigt um das Ablaufdatum zu verlängern
     *
     * @param days The amount of days to extend
     */
    void extendExpiration(int days);

    Boolean isFulfilled();
    
}
