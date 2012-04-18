/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.invoice;

import hotelsoftware.model.domain.invoice.InvoiceitemsPK;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.model.domain.service.HabitationData;
import hotelsoftware.model.domain.service.Service;
import hotelsoftware.model.domain.service.ServiceData;
import hotelsoftware.model.domain.users.User;
import hotelsoftware.model.domain.users.UserData;
import java.util.Date;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface InvoiceItemData
{

    int getAmount();

    Date getCreated();

    HabitationData getHabitationData();

    ServiceData getServiceData();

    /**
     * Gibt den Preis für eine Rechungsposition aus.
     * @return
     */
    double getTotalPrice();

    UserData getUserData();
    
}
