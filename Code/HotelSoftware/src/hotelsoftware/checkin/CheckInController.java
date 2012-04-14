/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.checkin;

/**
 *
 * @author Dunst
 */
public class CheckInController
{
    private static CheckInController controller = null;
    
    public static CheckInController getInstance()
    {
        if (controller == null)
        {
            controller = new CheckInController();
        }
        
        return controller;
    }
    
    private CheckInState state;
    
    private CheckInController()
    {
        state = new StartState();
    }
    
    /**
     * Ruft den nächsten Schritt auf
     */
    public abstract void nextStep();
    
    /**
     * Geht einen Schritt zurück
     */
    public abstract void previousStep();
    
    void setState(CheckInState state)
    {
        this.state = state;
    }
}
