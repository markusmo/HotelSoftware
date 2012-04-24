/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.checkin;

/**
 * Dieser Status ist dazu da um Walk-In und Reservations-Check-Ins zu abstrahieren.
 * @author Dunst
 */
public class ChangeWalkInDataState extends ChangeDataState
{
    public ChangeWalkInDataState(CheckInController context)
    {
        super(context);
    }
}
