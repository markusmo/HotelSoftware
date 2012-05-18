package at.fhv.roomanizer.domain.person;

/**
 * Every Address is linked to a country
 * 
 * @author phils
 */
public class Country implements ICountry{
	/**
	 * ID of the country
	 */
	private int _id;
		
	/**
	 * Name of the country
	 */
	private String _name;
	

	/**
	 * The abbrevation of the country
	 */
	private String _nameShort;
		
	/*------------- GETTER AND SETTER -----------*/
	/**
	 * @see ICountry#getId()
	 */
	public int getId(){
		return _id;
	}
	/**
	 * set the id of the country
	 * @param id
	 */
	public void setId(int id){
		_id = id;
	}
	
	/**
	 * @see ICountry#getName()
	 */
	public String getName(){
		return _name;
	}
	
	/**
	 * Sets the name of the country
	 * @param name
	 */
	public void setName(String name){
		_name = name;
	}
	
	/**
	 * @see ICountry#getShortName()
	 */
	public String getShortName(){
		return _nameShort;
	}
	
	/**
	 * Set the abbrevation of the country
	 * @param nameShort
	 */
	public void setShortName(String nameShort){
		_nameShort = nameShort;
	}
}
