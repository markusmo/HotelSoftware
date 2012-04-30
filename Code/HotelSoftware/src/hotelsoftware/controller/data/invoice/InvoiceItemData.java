package hotelsoftware.controller.data.invoice;

import hotelsoftware.controller.data.service.HabitationData;
import hotelsoftware.controller.data.service.ServiceData;
import hotelsoftware.controller.data.users.UserData;
import java.util.Date;

/**
 * Dieses Interface wird an die GUI weitergereicht.
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface InvoiceItemData
{

    Integer getAmount();

    Date getCreated();

    HabitationData getHabitationData();

    ServiceData getServiceData();

    /**
     * Gibt den Preis einer Rechungsposition aus
     * @return 
     * Preis der Position*Amount
     */
    double getTotalPrice();

    UserData getUserData();
    
}
