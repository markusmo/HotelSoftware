package at.fhv.roomanizer.application;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import at.fhv.roomanizer.domain.person.Country;
import at.fhv.roomanizer.domain.person.ICountry;
import at.fhv.roomanizer.persistence.ManagerFactory;
import at.fhv.roomanizer.persistence.manager.PersonManager;

/**
 * Offer Controlling functions for the countries
 * @author phils
 */
public class CountryController {
	/*--------------------loading Data for the Check - In--------------------------*/
	/**
	 * Returns a list of all Countries in the Database
	 * @return a list of countries
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public List<ICountry> loadAllCountries() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException{
		PersonManager perManager = ManagerFactory.getPersonmanager();
		List<Country> Countries = perManager.getAllCountries();
		List<ICountry> allICountries = new ArrayList<ICountry>();
		for (ICountry IC : Countries) {
			allICountries.add(IC);
		}
		return allICountries;
	}
	
	/**
	 * Returns a country searched by Name
	 * @param countryName
	 * @return The country with the given name
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public ICountry loadCountryByName(String countryName) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException{
		PersonManager perManager = ManagerFactory.getPersonmanager();
		return perManager.getCountryByName(countryName);
	}
}
