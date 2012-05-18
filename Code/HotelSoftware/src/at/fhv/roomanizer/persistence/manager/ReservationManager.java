package at.fhv.roomanizer.persistence.manager;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import at.fhv.roomanizer.domain.reservation.Reservation;
import at.fhv.roomanizer.persistence.ObjectConverter;
import at.fhv.roomanizer.persistence.entity.reservation.ReservationEntity;

/**
 * Reservationmanager is responsible for the whole Domain-Objects in the reservation package
 * @author Andreas Sinz <andreas.sinz@students.fhv.at>
 *
 */
public class ReservationManager extends Manager {
	
	/**
	 * Creates a new ReservationManager with a hibernate session
	 * @param session Hibernate-Session, which should be used to send/retrieve data
	 */
	private ReservationManager(Session session) {
		super(session);
	}
	
	/**
	 * Returns all reservations, which are stored in the database
	 * @return Returns a list of all reservations
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalArgumentException 
	 */
	public List<Reservation> getAllReservations() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Query reservationQuery = _session.createQuery("from ReservationEntity order by startDate");
		
		@SuppressWarnings("unchecked")
		List<ReservationEntity> tmpList = reservationQuery.list();
		
		List<Reservation> reservationList = new ArrayList<Reservation>();
		for(int i = 0; i < tmpList.size(); i++) {
			reservationList.add((Reservation) ObjectConverter.ConvertHibernateToDomain(tmpList.get(i), new HashMap<Object, Object>()));
		}
		return reservationList;
	}
	
	/**
	 * Returns a list of all reservations, which are booked behind the specified date
	 * @param date Date, which the reservation booking date is behind
	 * @return Returns a list of reservations, which are booked after the date
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalArgumentException 
	 */
	public List<Reservation> getFutureReservations(Date date) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Query reservationQuery = _session.createQuery("from ReservationEntity where startDate > :date order by startDate");
		reservationQuery.setDate("date", date);
		
		@SuppressWarnings("unchecked")
		List<ReservationEntity> tmpList = reservationQuery.list();
		
		List<Reservation> reservationList = new ArrayList<Reservation>();
		for(int i = 0; i < tmpList.size(); i++) {
			reservationList.add(((Reservation) ObjectConverter.ConvertHibernateToDomain(tmpList.get(i), new HashMap<Object, Object>())));
		}
		return reservationList;
	}
	
	/**
	 * Stores/Updates the given reservation in the database
	 * @param reservation, which will be stored/updated in the database
	 * @throws Exception
	 */
	public void saveReservation(Reservation reservation) throws Exception {
		Transaction tx = _session.beginTransaction();
		_session.merge(ObjectConverter.ConvertDomainToHibernate(reservation, new HashMap<Object, Object>()));
		_session.flush();
		tx.commit();
	}
	
	/**
	 * Returns a list of Reservations, which occur after the date and firstname and/or lastname matches with the given name
	 * @param date Date, which the reservation startDate is behind
	 * @param name Name of the Guest, who booked the Reservation
	 * @return A list of reservation associated with the given name
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public List<Reservation> getFutureReservationsByName(Date date, String name) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Query reservationQuery = _session.createQuery("from ReservationEntity where startDate > :date and (_guest._firstName || _guest._lastName) LIKE :guestname");
		reservationQuery.setDate("date", date);
		reservationQuery.setString("guestname", "%" + name + "%");
		
		@SuppressWarnings("unchecked")
		List<ReservationEntity> tmpList = reservationQuery.list();
		
		List<Reservation> reservationList = new ArrayList<Reservation>();
		for(int i = 0; i < tmpList.size(); i++) {
			reservationList.add(((Reservation) ObjectConverter.ConvertHibernateToDomain(tmpList.get(i), new HashMap<Object, Object>())));
		}
		return reservationList;
	}
	
	/**
	 * Returns a list of reservations, which start behind the date filtered by the reservationNumber
	 * @param date Date, which the reservation startDate is behind
	 * @param reservationNumber Reservationnumber, which the reservation list will be filtered for
	 * @return Returns a list of Reservations, which are filtered by the Reservationnumber
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public List<Reservation> getFutureReservationsByNumber(Date date, String reservationNumber) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Query reservationQuery = _session.createQuery("from ReservationEntity where startDate > :date and reservationNumber LIKE :number");
		reservationQuery.setDate("date", date);
		reservationQuery.setString("number", "%" + reservationNumber + "%");
		
		@SuppressWarnings("unchecked")
		List<ReservationEntity> tmpList = reservationQuery.list();
		
		List<Reservation> reservationList = new ArrayList<Reservation>();
		for(int i = 0; i < tmpList.size(); i++) {
			reservationList.add(((Reservation) ObjectConverter.ConvertHibernateToDomain(tmpList.get(i), new HashMap<Object, Object>())));
		}
		return reservationList;
	}
	
	
	
	
	/*
	 * Singleton stuff
	 */
	
	private static ReservationManager _instance;
	/**
	 * Returns the singleton-instance of the HabitationManager
	 * @param session Session, which should be used to retrieve/store data in the database
	 * @return
	 */
	public static ReservationManager getInstance(Session session) {
		if(_instance == null) {
			_instance = new ReservationManager(session);
		}
		
		return _instance;
	}
}
