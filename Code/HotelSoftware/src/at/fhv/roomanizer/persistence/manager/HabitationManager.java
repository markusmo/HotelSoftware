package at.fhv.roomanizer.persistence.manager;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import at.fhv.roomanizer.application.PrePaymentController;
import at.fhv.roomanizer.domain.Habitation;
import at.fhv.roomanizer.domain.reservation.Reservation;
import at.fhv.roomanizer.persistence.ManagerFactory;
import at.fhv.roomanizer.persistence.ObjectConverter;
import at.fhv.roomanizer.persistence.entity.HabitationEntity;

/**
 * HabitationManager is responsible for the whole Domain-Objects in the persistence package
 *
 * @author Andreas Sinz <andreas.sinz@students.fhv.at>
 * @author Shady
 *
 */
public class HabitationManager extends Manager implements IHabitationManager
{
    /**
     * Creates a new Habitation manager with the specified Hibernate-session
     *
     * @param session Hibernate-session, which will be used for retrieving/saving data in the database
     */
    protected HabitationManager(Session session)
    {
        super(session);
    }

    /**
     * Returns all habitations stored in the database
     *
     * @return All habitations stored in the database
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @Override
    public List<Habitation> getAllHabitations() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Query habitationQuery = _session.createQuery("from HabitationEntity");

        @SuppressWarnings("unchecked")
        List<HabitationEntity> tmpList = habitationQuery.list();

        List<Habitation> habitationList = new ArrayList<Habitation>();
        for (HabitationEntity entity : tmpList)
        {
            habitationList.add((Habitation) ObjectConverter.ConvertHibernateToDomain(entity, new HashMap<Object, Object>()));
        }

        return habitationList;
    }

    /**
     * Returns all habitations which are/were active during the given date
     *
     * @return List<Habitation>
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @Override
    public List<Habitation> getHabitationsByDate(Date date) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Query habitationQuery = _session.createQuery("from HabitationEntity where :date between startDate and endDate order by startDate");
        habitationQuery.setDate("date", date);

        @SuppressWarnings("unchecked")
        List<HabitationEntity> tmpList = habitationQuery.list();

        List<Habitation> habitationList = new ArrayList<Habitation>();
        for (HabitationEntity entity : tmpList)
        {
            habitationList.add((Habitation) ObjectConverter.ConvertHibernateToDomain(entity, new HashMap<Object, Object>()));
        }

        return habitationList;
    }

    /**
     * Stores a habitation in the database
     *
     * @param habitation, which should be stored in the database
     */
    @Override
    public void saveHabitation(Habitation habitation) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Transaction tx = _session.beginTransaction();
        _session.merge(ObjectConverter.ConvertDomainToHibernate(habitation, new HashMap<Object, Object>()));
        _session.flush();
        tx.commit();
    }

    @Override
    public Habitation getHabitationById(int habitationID) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Query habitationQuery = _session.createQuery("from HabitationEntity where idServices = :id");
        habitationQuery.setInteger("id", habitationID);
        habitationQuery.setMaxResults(1);

        @SuppressWarnings("unchecked")
        List<HabitationEntity> tmpList = habitationQuery.list();

        if (tmpList.size() == 1)
        {
            return (Habitation) ObjectConverter.ConvertHibernateToDomain(tmpList.get(0), new HashMap<Object, Object>());
        }

        return null;
    }
    private static HabitationManager _instance;

    /**
     * Returns the singleton-instance of the HabitationManager
     *
     * @param session Session, which should be used to retrieve/store data in the database
     * @return
     */
    public static HabitationManager getInstance(Session session)
    {
        if (_instance == null)
        {
            _instance = new HabitationManager(session);
        }

        return _instance;
    }
/*
    public static void main(String[] args) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        List<Habitation> habitations = new ArrayList<Habitation>();
        HabitationManager habitationManager = ManagerFactory.getHabitationManager();

        habitations = habitationManager.getAllHabitations();



        if (habitations != null)
        {
            System.out.println("Im IF" + habitations.size());
            for (Habitation habitation : habitations)
            {
                System.out.println("DAS IST EINE HAB" + habitation.getId());
            }
        }
        else
        {
            System.out.println("gibt null zurück");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        try
        {
            Date d = sdf.parse("2012.5.7");

            PrePaymentController ppc = new PrePaymentController();
            ppc.savePrePayment(habitations.get(0).getId(), 154, d);
            ppc.savePrePayment(habitations.get(4).getId(), 145, d);

            ReservationManager rm = ManagerFactory.getReserveationManager();
            List<Reservation> reservationList = rm.getFutureReservations(d);
            System.out.println("Done..");
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }*/
}
