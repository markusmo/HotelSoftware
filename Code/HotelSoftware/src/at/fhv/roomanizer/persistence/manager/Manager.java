package at.fhv.roomanizer.persistence.manager;

import org.hibernate.Session;
/**
 * Abstract Manager-class, which all Hibernate-Managers inherit from
 * @author Andreas Sinz <andreas.sinz@students.fhv.at>
 *
 */
public abstract class Manager {
	protected Session _session;
	
	/**
	 * Initializes a new Manager with the specified hibernate session
	 * @param session Hibernate-session, which will be used to retrieve/store data a the database
	 */
	protected Manager(Session session) {
		this._session = session;
	}
}
