/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.checkin;

import hotelsoftware.support.ServiceTypeNotFoundException;
import hotelsoftware.controller.login.LoginController;
import hotelsoftware.model.database.FailedToSaveToDatabaseException;
import hotelsoftware.model.domain.invoice.InvoiceItem;
import hotelsoftware.model.domain.parties.Address;
import hotelsoftware.model.domain.parties.Country;
import hotelsoftware.model.domain.parties.Guest;
import hotelsoftware.model.domain.parties.PartySaver;
import hotelsoftware.controller.data.parties.AddressData;
import hotelsoftware.controller.data.parties.CountryData;
import hotelsoftware.controller.data.parties.GuestData;
import hotelsoftware.model.domain.reservation.Reservation;
import hotelsoftware.model.domain.reservation.ReservationItem;
import hotelsoftware.support.NoPriceDefinedException;
import hotelsoftware.model.domain.room.Room;
import hotelsoftware.model.domain.room.RoomCategory;
import hotelsoftware.controller.data.room.RoomCategoryData;
import hotelsoftware.controller.data.room.RoomData;
import hotelsoftware.model.domain.service.*;
import hotelsoftware.controller.data.service.ExtraServiceData;
import hotelsoftware.util.HelperFunctions;
import hotelsoftware.util.HibernateUtil;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        context.setRoomSelections(new HashMap<Integer, RoomSelection>());
        Reservation r = context.getReservation();
        
        if (r != null)
        {
            for (ReservationItem data : context.getReservation().getReservationItems())
            {
                for (Integer i = 0; i < data.getAmount(); i++)
                {
                    context.getRoomSelections().put(i, new RoomSelection((RoomCategory) data.getReservedCategoryData(), new Room()));
                }
            }
        }
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
        GuestData guest = Guest.create(firstName, lastName, gender, birthday, (Address) address);

        return guest;
    }

    @Override
    public void assignRoom(int selectionIndex, GuestData guest)
    {
        context.getRoomSelections().get(selectionIndex).assignGuest((Guest) guest);
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
            context.getRoomSelections().put(context.increaseCounter(), new RoomSelection(RoomCategory.getCategoryByName(DEFAULT_CATEGORY), new Room()));
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
        context.getRoomSelections().remove(selectionIndex);
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
    public void changeRoom(int selectionIndex, RoomData room)
    {
        RoomSelection sel = context.getRoomSelections().get(selectionIndex);

        sel.setRoom((Room) room);
    }

    @Override
    public Collection<RoomCategoryData> getAllCategories()
    {
        return new HelperFunctions<RoomCategoryData, RoomCategory>().castCollectionUp(RoomCategory.getAllCategorys());
    }

    @Override
    public RoomData getRoomData(int selectionIndex) throws NoRoomsInCategoryAvailableException, NoRoomsAvailableException
    {
        RoomData d = context.getRoomSelections().get(selectionIndex).getRoom();
        if (d == null)
        {
            for (RoomCategory cat : RoomCategory.getAllCategorys())
            {
                Collection<Room> rooms = cat.getFreeRooms(context.getStartDate(), context.getEndDate());
                
                if (rooms.size() > 0)
                {
                    Room first = rooms.iterator().next();
                    context.getRoomSelections().put(selectionIndex, new RoomSelection(cat, first));
                    throw new NoRoomsInCategoryAvailableException(cat, first);
                }
            }
        }
        else
        {
            return d;
        }
        
        throw new NoRoomsAvailableException();
    }

    @Override
    public Collection<ExtraServiceData> getAllHabitationServices()
    {
        return new HelperFunctions<ExtraServiceData, ExtraService>().castCollectionUp(ExtraService.getAllHabitationServices());
    }

    @Override
    public void initKeys()
    {
        //Do Nothing
    }

    @Override
    public Collection<ExtraServiceData> getServices()
    {
        Collection<ExtraService> services = ExtraService.getAllExtraServices();

        return new HelperFunctions<ExtraServiceData, ExtraService>().castCollectionUp(services);
    }

    /**
     * Ändert die Informationen, betreffend des aktuellen Check In Vorgangs
     *
     * @param start Das neue Startdatum
     * @param end Das neue Enddatum
     * @param amount Die neue Anzahl an Personen
     */
    @Override
    public void changeInformation(Date start, Date end, int amount)
    {
        context.setStartDate(start);
        context.setEndDate(end);
        // TODO Set amount, nötig?
    }

    @Override
    public void saveData() throws NoPriceDefinedException, CouldNotSaveException
    {
        context.setHabitations(new LinkedList<Habitation>());
        LinkedList<Guest> guests = new LinkedList<Guest>();
        LinkedList<Address> addresses = new LinkedList<Address>();

        for (RoomSelection roomSel : context.getRoomSelections().values())
        {
            Habitation h = new Habitation();
            h.setStart(context.getStartDate());
            h.setEnd(context.getEndDate());
            h.setUsers(LoginController.getInstance().getCurrentUser());
            h.setPrice(roomSel.getRoom().getCategory().getPriceFor(context.getStartDate()));
            h.setRooms(roomSel.getRoom());
            h.setCreated(new Date());

            try
            {
                h.setServiceType(ServiceType.getTypeByName("Habitation"));
            }
            catch (ServiceTypeNotFoundException ex)
            {
                //Darf nie passieren, da Habitation in der Datenbank vorhanden sein muss
            }

            for (Guest g : roomSel.getGuests())
            {
                h.addGuests(g);
                g.addHabitation(h);
                guests.add(g);
                addresses.add(g.getAddress());
            }

            context.getHabitations().add(h);
        }

        try
        {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction ts = session.beginTransaction();
            ts.begin();
            ServiceSaver.getInstance().saveOrUpdate(session, new LinkedList(), context.getHabitations(), new LinkedList());
            PartySaver.getInstance().saveOrUpdate(session, addresses, new LinkedList(), new LinkedList(), new LinkedList(), guests);
            ts.commit();
        }
        catch (FailedToSaveToDatabaseException ex)
        {
            throw new CouldNotSaveException();
        }
        
        context.setState(new FinalState(context));
    }

    @Override
    void back()
    {
        context.clear();
        context.setState(new StartState(context));
    }
}
