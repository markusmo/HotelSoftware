/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.checkin;

import hotelsoftware.model.database.manager.ServiceManager;
import hotelsoftware.model.database.manager.RoomManager;
import hotelsoftware.model.database.manager.PartyManager;
import hotelsoftware.controller.data.parties.AddressData;
import hotelsoftware.controller.data.parties.CountryData;
import hotelsoftware.controller.data.parties.GuestData;
import hotelsoftware.controller.data.room.RoomCategoryData;
import hotelsoftware.controller.data.room.RoomData;
import hotelsoftware.controller.data.service.ExtraServiceData;
import hotelsoftware.controller.login.LoginController;
import hotelsoftware.gui.checkin.CheckInGuiControler;
import hotelsoftware.model.domain.invoice.IInvoiceItem;
import hotelsoftware.model.domain.invoice.InvoiceItem;
import hotelsoftware.model.database.manager.InvoiceManager;
import hotelsoftware.model.domain.parties.*;
import hotelsoftware.model.domain.reservation.IReservationItem;
import hotelsoftware.model.domain.reservation.Reservation;
import hotelsoftware.model.domain.room.*;
import hotelsoftware.model.domain.service.*;
import hotelsoftware.support.NoPriceDefinedException;
import hotelsoftware.support.ServiceTypeNotFoundException;
import hotelsoftware.util.HelperFunctions;
import hotelsoftware.util.HibernateUtil;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            for (IReservationItem data : context.getReservation().getReservationItems())
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
        Guest guest = new Guest();
        guest.setFname(firstName);
        guest.setLname(lastName);
        guest.setBirthday(birthday);
        guest.setAddress((Address) address);

        context.getAddedGuests().add(guest);
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
        if (context.getCountries() == null)
        {
            context.setCountries(HelperFunctions.castCollectionUp(Country.getAllCountries(), CountryData.class, ICountry.class));
        }
        return context.getCountries();
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

        return new HelperFunctions<RoomData, IRoom>().castCollectionUp(cat.getFreeRooms(context.getStartDate(), context.getEndDate()));
    }

    @Override
    public void changeRoom(int selectionIndex, String roomNumber)
    {
        RoomSelection sel = context.getRoomSelections().get(selectionIndex);
        IRoom room = Room.getRoomByNumber(roomNumber);

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
        if (context.getCategories() == null)
        {
            context.setCategories(HelperFunctions.castCollectionUp(RoomCategory.getAllCategorys(), RoomCategoryData.class, IRoomCategory.class));
        }

        return context.getCategories();
    }

    @Override
    public RoomData getRoomData(int selectionIndex) throws NoRoomsInCategoryAvailableException, NoRoomsAvailableException
    {
        RoomData d = context.getRoomSelections().get(selectionIndex).getRoom();
        if (d == null)
        {
            for (IRoomCategory cat : RoomCategory.getAllCategorys())
            {
                Collection<IRoom> rooms = cat.getFreeRooms(context.getStartDate(), context.getEndDate());

                if (rooms.size() > 0)
                {
                    IRoom first = rooms.iterator().next();
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
    public Collection<ExtraServiceData> getAllBoardCategoriesServices()
    {
        if (context.getHabitationServices() == null)
        {
            context.setHabitationServices(HelperFunctions.castCollectionUp(ExtraService.getAllBoardCategoriesServices(), ExtraServiceData.class, IExtraService.class));
        }

        return context.getHabitationServices();
    }

    @Override
    public void initKeys()
    {
        //Do Nothing
    }

    @Override
    public Collection<ExtraServiceData> getServices()
    {
        Collection<IExtraService> services = ExtraService.getAllExtraServices();

        return new HelperFunctions<ExtraServiceData, IExtraService>().castCollectionUp(services);
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
    }

    @Override
    public void saveData() throws NoPriceDefinedException, CouldNotSaveException
    {
        context.setHabitations(new LinkedList<IHabitation>());
        LinkedList<IGuest> guests = new LinkedList<IGuest>();
        LinkedList<IAddress> addresses = new LinkedList<IAddress>();
        LinkedList<IInvoiceItem> items = new LinkedList<IInvoiceItem>();
        LinkedList<IRoomRoomStatus> status = new LinkedList<IRoomRoomStatus>();

        int i = 0;
        for (RoomSelection roomSel : context.getRoomSelections().values())
        {
            Habitation h = new Habitation();
            h.setStart(context.getStartDate());
            h.setEnd(context.getEndDate());
            h.setUsers(LoginController.getInstance().getCurrentUser());
            h.setPrice(roomSel.getRoom().getCategory().getPriceFor(context.getStartDate()));
            h.setRooms(roomSel.getRoom());
            h.setCreated(new Date());
            h.setHabitationNumber(HelperFunctions.getNewContinousNumber(Habitation.class, i++));

            try
            {
                h.setServiceType(ServiceType.getTypeByName("Habitation"));
            }
            catch (ServiceTypeNotFoundException ex)
            {
                //Darf nie passieren, da Habitation in der Datenbank vorhanden sein muss
                Logger.getLogger(CheckInGuiControler.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (IGuest g : roomSel.getGuests())
            {
                h.addGuests(g);
                g.addHabitation(h);
                guests.add(g);
                addresses.add(g.getAddress());
            }

            context.getHabitations().add(h);

            InvoiceItem item = new InvoiceItem();
            item.setAmount(1);
            item.setCreated(new Date());
            item.setHabitation(h);
            item.setPrice(h.getPrice());
            item.setService(h);
            item.setUser(LoginController.getInstance().getCurrentUser());

            items.add(item);

            RoomsRoomStatus rrs = new RoomsRoomStatus();
            rrs.setRoom(h.getRooms());
            rrs.setStart(new Date());
            rrs.setRoomstatus(RoomStatus.getRoomStatusByName("Occupied - Clean"));

            status.add(rrs);
        }

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();

        for (IHabitation hab : context.getHabitations())
        {
            ServiceManager.getInstance().saveHabitation(hab, session);
        }

        for (IGuest guest : guests)
        {
            PartyManager.getInstance().saveParty(guest, session);
        }

        InvoiceManager.getInstance().saveInvoiceItems(items, session);

        for (IRoomRoomStatus s : status)
        {
            RoomManager.getInstance().saveRoomsRoomStatus(s, session);
        }
        ts.commit();

        context.setState(new FinalState(context));
    }

    @Override
    void back()
    {
        context.clear();
        context.setState(new StartState(context));
    }
}
