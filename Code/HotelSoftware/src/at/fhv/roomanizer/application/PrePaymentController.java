package at.fhv.roomanizer.application;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import at.fhv.roomanizer.domain.Habitation;
import at.fhv.roomanizer.domain.invoice.InvoiceItem;
import at.fhv.roomanizer.domain.person.User;
import at.fhv.roomanizer.domain.service.Service;
import at.fhv.roomanizer.persistence.ManagerFactory;
import at.fhv.roomanizer.persistence.manager.HabitationManager;
import at.fhv.roomanizer.persistence.manager.InvoiceItemManager;
import at.fhv.roomanizer.persistence.manager.ServiceManager;

public class PrePaymentController
{
    public void savePrePayment(int habitationId, float amount, Date currentDate) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException
    {
        InvoiceItemManager iiManager = ManagerFactory.getInvoiceItemManager();
        ServiceManager serviceManager = ManagerFactory.getServiceManager();
        HabitationManager habManager = ManagerFactory.getHabitationManager();
        Habitation habitation = habManager.getHabitationById(habitationId);

        InvoiceItem prePaymentItem = new InvoiceItem();
        prePaymentItem.setAmount(1);
        prePaymentItem.setHabitation(habitation);
        prePaymentItem.setPrice(0 - amount);
        prePaymentItem.setCreated(currentDate);

        //TODO Workaround: Not much of a beauty ^^
        prePaymentItem.setUser((User) new UserController().loadFirstUser());

        Service service = new Service();
        service.setType(serviceManager.getTypeByName("PrePayment"));
        prePaymentItem.setService(service);
        serviceManager.saveService(service);

        iiManager.saveInvoiceItem(prePaymentItem);
    }
}
