package hotelsoftwareonline.controller;

/**
 *
 * @author Johannes
 */
public class ReservationController
{
    private static ReservationController controller = null;

    public static ReservationController getInstance()
    {
        if (controller == null)
        {
            controller = new ReservationController();
        }
        return controller;
    }
    
    
}
