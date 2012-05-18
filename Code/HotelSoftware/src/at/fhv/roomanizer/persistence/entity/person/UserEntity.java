package at.fhv.roomanizer.persistence.entity.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name="users")
public class UserEntity {
	
	/**
	 * Id of the user
	 */
	@Id
	@Generated(GenerationTime.INSERT)
	@Column(name="id")
	private int _id;
	
	/**
	 * Username of the user
	 */
	@Column(name="username")
	private String _username;
	
	/**
	 * Password of the user
	 */
	@Column(name="password")
	private String _password;
	
	/**
	 * Defines, whether the user is active
	 */
	@Column(name="active")
	private boolean _active;

	/**
	 * Returns the id of the user
	 * @return id of the user
	 */
	public int getId() {
		return _id;
	}

	/**
	 * Sets the id of the user
	 * @param id of the user
	 */
	public void setId(int id) {
		this._id = id;
	}

	/**
	 * Returns the username of the user
	 * @return username of the user
	 */
	public String getUsername() {
		return _username;
	}

	/**
	 * Sets the username of the user
	 * @param username of the user
	 */
	public void setUsername(String username) {
		this._username = username;
	}

	/**
	 * Returns the password of the user
	 * @return password of the user
	 */
	public String getPassword() {
		return _password;
	}

	/**
	 * Sets the password of the user
	 * @param password of the user
	 */
	public void setPassword(String password) {
		this._password = password;
	}

	/**
	 * Returns a boolean, which defines whether the user is active
	 * @return active state of the user
	 */
	public boolean getActive() {
		return _active;
	}

	/**
	 * Sets the state of the user
	 * @param active State of the user
	 */
	public void setActive(boolean active) {
		this._active = active;
	}
}
