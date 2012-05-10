/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation;

/**
 *
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
