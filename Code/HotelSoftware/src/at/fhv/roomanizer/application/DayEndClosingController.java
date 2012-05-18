package at.fhv.roomanizer.application;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import at.fhv.roomanizer.domain.Habitation;
import at.fhv.roomanizer.domain.invoice.InvoiceItem;
import at.fhv.roomanizer.domain.person.User;
import at.fhv.roomanizer.domain.room.RoomStatus;
import at.fhv.roomanizer.domain.room.Status;
import at.fhv.roomanizer.persistence.ManagerFactory;
import at.fhv.roomanizer.persistence.manager.*;
import hotelsoftware.adapter.RoomStatusAdapter;
import hotelsoftware.adapter.UserControllerAdapter;

public class DayEndClosingController
{
    public void runDayEndClosing(Date currentDate) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException
    {
        IHabitationManager habitationManager = ManagerFactory.getHabitationManager();
        IInvoiceItemManager invoiceManager = ManagerFactory.getInvoiceItemManager();
        IRoomManager roomManager = ManagerFactory.getRoomManager();

        List<Habitation> habitationList = habitationManager.getHabitationsByDate(currentDate);

        for (Habitation habitation : habitationList)
        {
            InvoiceItem roomInvoice = invoiceManager.getHabitationInvoiceItem(habitation);

            if (roomInvoice != null)
            {
                //Increase the amount of days, which the room has been paired with the habitation
                roomInvoice.setAmount(roomInvoice.getAmount() + 1);
            }
            else
            {
                roomInvoice = new InvoiceItem();
                roomInvoice.setAmount(1);
                roomInvoice.setCreated(currentDate);
                roomInvoice.setHabitation(habitation);
                roomInvoice.setPrice(habitation.getPrice());
                roomInvoice.setService(habitation);

                //TODO Workaround: Not much of a beauty ^^
                roomInvoice.setUser((User) new UserControllerAdapter().loadFirstUser());
            }

            invoiceManager.saveInvoiceItem(roomInvoice);

            //Save the room status per day-end closing
            RoomStatus roomStatus = new RoomStatusAdapter(new hotelsoftware.model.domain.room.RoomsRoomStatus());
            roomStatus.setRoom(habitation.getRoom());

            Status occupiedDirtyStatus = roomManager.getStatusByName("Occupied - Dirty");
            roomStatus.setStatus(occupiedDirtyStatus);

            roomStatus.setStart(currentDate);
            roomStatus.setEnd(currentDate);

            roomManager.saveRoomStatus(roomStatus);

            //TODO Add all extra-services???

        }
    }
}
