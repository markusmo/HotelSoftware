package at.fhv.roomanizer.persistence.manager;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import at.fhv.roomanizer.domain.service.Service;
import at.fhv.roomanizer.domain.service.Type;
import at.fhv.roomanizer.persistence.ObjectConverter;
import at.fhv.roomanizer.persistence.entity.service.TypeEntity;

public class ServiceManager extends Manager {

	protected ServiceManager(Session session) {
		super(session);
	}
	
	/**
	 * Returns all Service-Types from the database
	 * @return All Service-Types which are stored in the database
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public List<Type> getAllTypes() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Query typeQuery = _session.createQuery("from TypeEntity");
		
		@SuppressWarnings("unchecked")
		List<TypeEntity> tmpList = typeQuery.list();
		
		List<Type> typeList = new ArrayList<Type>();
		for(TypeEntity entity : tmpList) {
			typeList.add((Type) ObjectConverter.ConvertHibernateToDomain(entity, new HashMap<Object, Object>()));
		}
		
		return typeList;
	}
	
	/**
	 * Returns the type, associated with the given name
	 * @param typeName Name of the type
	 * @return Returns the type, which is associated with the given name
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public Type getTypeByName(String typeName) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Query query = _session.createQuery("from TypeEntity where name = :typeName");
		query.setString("typeName", typeName);
		query.setMaxResults(1);
		
		@SuppressWarnings("unchecked")
		List<TypeEntity> tmpList = query.list();
		
		if(tmpList.size() == 1) {
			return (Type) ObjectConverter.ConvertHibernateToDomain(tmpList.get(0), new HashMap<Object, Object>());
		}
		
		return null;
		
	}
	
	public void saveService(Service service) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Transaction tx = _session.beginTransaction();
		
		Object tmp = ObjectConverter.ConvertDomainToHibernate(service, new HashMap<Object, Object>());
		_session.merge(tmp);
		_session.flush();
		tx.commit();
	}
	
	/*
	 * Singleton stuff
	 */
	
	private static ServiceManager _instance;
	public static ServiceManager getInstance(Session session) {
		if(_instance == null) {
			_instance = new ServiceManager(session);
		}
		
		return _instance;
	}
}
