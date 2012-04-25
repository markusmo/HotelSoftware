/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.checkin;

import hotelsoftware.model.domain.invoice.InvoiceItem;
import hotelsoftware.model.domain.parties.Address;
import hotelsoftware.model.domain.parties.Country;
import hotelsoftware.model.domain.parties.Guest;
import hotelsoftware.model.domain.parties.data.AddressData;
import hotelsoftware.model.domain.parties.data.CountryData;
import hotelsoftware.model.domain.parties.data.GuestData;
import hotelsoftware.model.domain.reservation.ReservationItem;
import hotelsoftware.model.domain.reservation.data.ReservationItemData;
import hotelsoftware.model.domain.room.Room;
import hotelsoftware.model.domain.room.RoomCategory;
import hotelsoftware.model.domain.room.data.RoomCategoryData;
import hotelsoftware.model.domain.room.data.RoomData;
import hotelsoftware.model.domain.service.ExtraService;
import hotelsoftware.model.domain.service.data.ExtraServiceData;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;
import java.util.Date;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Diese Klasse ist abstrakt und beinhaltet alle Methoden fuer Reservations und Walk-Ins Check-In.
 *
 * @author Dunst
 */
public abstract class ChangeDataState extends CheckInState
{
    public ChangeDataState(CheckInController context)
    {
        super(context);
    }

    @Override
    public GuestData changeGuestData(GuestData guest, String firstName, String lastName, char gender, Date birthday, AddressData address)
    {
        Guest g = (Guest) guest;
        g.setFname(firstName);
        g.setLname(lastName);
        g.setGender(gender);
        g.setBirthday(birthday);
        g.setAddress((Address) address);

        return g;
    }

    @Override
    public GuestData addGuest(String firstName, String lastName, char gender, Date birthday, AddressData address)
    {
        GuestData guest = Guest.create(lastName, lastName, gender, birthday, null);

        //TODO save somehow
        System.out.println("@ChangeDataState in addGuest TODO!!!");
        return guest;
    }

    @Override
    public void assignRoom(GuestData guest, RoomData room)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int addRoomSelection()
    {
        if (context.getRoomCategoryArray().length > context.getCounter())
        {
            context.getRoomSelections().put(context.increaseCounter(), new RoomSelection(context.getRoomCategoryArray()[context.getCounter() - 1], new Room()));
        }
        else
        {
            context.getRoomSelections().put(context.increaseCounter(), new RoomSelection(context.getRoomCategoryArray()[context.getRoomCategoryArray().length - 1], new Room()));
        }
        return context.getCounter() - 1;
    }

    @Override
    public Collection<CountryData> getAllCountries()
    {
        return new HelperFunctions<CountryData, Country>().castCollectionUp(Country.getAllCountries());
    }

    @Override
    public void removeRoomSelection(int selectionIndex)
    {
        throw new IllegalStateException();
    }

    @Override
    public Collection<RoomData> changeRoomCategory(int selectionIndex, RoomCategoryData category)
    {
        RoomCategory cat = (RoomCategory) category;

        return new HelperFunctions<RoomData, Room>().castCollectionUp(cat.getFreeRooms(context.getStartDate(), context.getEndDate()));
    }

    @Override
    public void changeRoom(int selectionIndex, String roomNumber)
    {
        RoomSelection sel = context.getRoomSelections().get(selectionIndex);
        Room room = Room.getRoomByNumber(roomNumber);

        sel.setRoom(room);
    }

    @Override
    public Collection<RoomCategoryData> getAllCategories()
    {
        return new HelperFunctions<RoomCategoryData, RoomCategory>().castCollectionUp(RoomCategory.getAllCategorys());
    }

    @Override
    public RoomData getRoomData(int selectionIndex)
    {
        return context.getRoomSelections().get(selectionIndex).getRoom();
    }

    @Override
    public Collection<ExtraServiceData> getAllHabitationServices()
    {
        return new HelperFunctions<ExtraServiceData, ExtraService>().castCollectionUp(ExtraService.getAllHabitationServices());
    }

    @Override
    public void initKeys()
    {
        throw new NotImplementedException();
    }

    @Override
    public Collection<ExtraServiceData> getServices()
    {
        Collection<ExtraService> services = ExtraService.getAllExtraServices();

        return new HelperFunctions<ExtraServiceData, ExtraService>().castCollectionUp(services);
    }

    @Override
    public void selectServices(Collection<ExtraServiceData> services)
    {
        for (ExtraServiceData entry : services)
        {
            InvoiceItem item = InvoiceItem.createInvoiceItem((ExtraService) entry, 1, context.getHabitation());
            context.getHabitation().addInvoiceItems(item);
        }
    }

    public void saveData()
    {
    }
}
