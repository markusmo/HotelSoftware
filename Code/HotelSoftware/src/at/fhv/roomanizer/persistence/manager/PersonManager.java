package at.fhv.roomanizer.persistence.manager;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import at.fhv.roomanizer.domain.person.Address;
import at.fhv.roomanizer.domain.person.Country;
import at.fhv.roomanizer.domain.person.Guest;
import at.fhv.roomanizer.domain.person.User;
import at.fhv.roomanizer.persistence.ObjectConverter;
import at.fhv.roomanizer.persistence.entity.person.CountryEntity;
import at.fhv.roomanizer.persistence.entity.person.GuestEntity;
import at.fhv.roomanizer.persistence.entity.person.UserEntity;

/**
 * PersonManager is responsible for the whole Domain-Objects in the person package
 * @author Andreas Sinz <andreas.sinz@students.fhv.at>
 *
 */
public class PersonManager extends Manager {
	
	private PersonManager(Session session) {
		super(session);
	}
	
	/**
	 * Returns a list of users from the database
	 * @return List of users, which are stored in the database
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public List<User> getUserList() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Query userQuery = _session.createQuery("from UserEntity");
		
		@SuppressWarnings("unchecked")
		List<UserEntity> tmpList = userQuery.list();
		
		List<User> userList = new ArrayList<User>();
		for(UserEntity entity : tmpList) {
			userList.add((User) ObjectConverter.ConvertHibernateToDomain(entity, new HashMap<Object, Object>()));
		}
		
		return userList;
	}
	
	/**
	 * Returns a list of guests from the database
	 * @return List of guests, which are stored a the database
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public List<Guest> getGuestList() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Query guestQuery = _session.createQuery("from GuestEntity");
		
		@SuppressWarnings("unchecked")
		List<GuestEntity> tmpList = guestQuery.list();
		
		List<Guest> guestList = new ArrayList<Guest>();
		for(GuestEntity entity : tmpList) {
			guestList.add((Guest) ObjectConverter.ConvertHibernateToDomain(entity, new HashMap<Object, Object>()));
		}
		
		return guestList;
	}
	
	/**
	 * Stores/Updates the given guest in the database
	 * @param guest, which will be stored/updated in the database
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SecurityException
	 * @throws RollbackException
	 * @throws HeuristicMixedException
	 * @throws HeuristicRollbackException
	 * @throws SystemException
	 */
	public void saveGuest(Guest guest) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException  {
		Transaction tx = _session.beginTransaction();
		_session.merge(ObjectConverter.ConvertDomainToHibernate(guest, new HashMap<Object, Object>()));
		tx.commit();
	}
	
	/**
	 * Stores/Updates the given address in the database
	 * @param address, which will be stored/updated in the database
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void saveAddress(Address address) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException  {
		Transaction tx = _session.beginTransaction();
		_session.merge(ObjectConverter.ConvertDomainToHibernate(address, new HashMap<Object, Object>()));
		tx.commit();
	}
	
	public List<Country> getAllCountries() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Query countryQuery = _session.createQuery("from CountryEntity");
		
		@SuppressWarnings("unchecked")
		List<CountryEntity> tmpList = countryQuery.list();
		
		List<Country> countryList = new ArrayList<Country>();
		for(CountryEntity entity : tmpList) {
			countryList.add((Country) ObjectConverter.ConvertHibernateToDomain(entity, new HashMap<Object, Object>()));
		}
		
		return countryList;
	}
	
	public Country getCountryByName(String name) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Query countryQuery = _session.createQuery("from CountryEntity where name like :countryName");
		countryQuery.setMaxResults(1);
		countryQuery.setString("countryName", name);
		@SuppressWarnings("unchecked")
		List<CountryEntity> tmpList = countryQuery.list();
		
		if(tmpList.size() == 1) {
			return (Country) ObjectConverter.ConvertHibernateToDomain(tmpList.get(0), new HashMap<Object, Object>());
		}
		
		return null;
	}
	
	

	//Singleton-stuff
	private static PersonManager _instance;
	/**
	 * Returns the singleton-instance of the PersonManager
	 * @param session Session, which should be used to retrieve/store data in the database
	 * @return
	 */
	public static PersonManager getInstance(Session session) {
		if(_instance == null) {
			_instance = new PersonManager(session);
		}
		
		return _instance;
	}
}
