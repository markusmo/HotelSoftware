package hotelsoftware.model.domain.invoice.data;

import hotelsoftware.model.domain.service.data.HabitationData;
import hotelsoftware.model.domain.service.data.ServiceData;
import hotelsoftware.model.domain.users.data.UserData;
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
