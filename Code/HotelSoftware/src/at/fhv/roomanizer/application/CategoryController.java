/**
 * 
 */
package at.fhv.roomanizer.application;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.fhv.roomanizer.application.exceptions.NotEnoughRoomsException;
import at.fhv.roomanizer.application.exceptions.NotEnoughRoomsInCategory;
import at.fhv.roomanizer.domain.room.Category;
import at.fhv.roomanizer.domain.room.ICategory;
import at.fhv.roomanizer.domain.room.IPrice;
import at.fhv.roomanizer.domain.room.IRoom;
import at.fhv.roomanizer.domain.room.Room;
import at.fhv.roomanizer.domain.room.Status;
import at.fhv.roomanizer.persistence.ManagerFactory;
import at.fhv.roomanizer.persistence.manager.IRoomManager;
import at.fhv.roomanizer.persistence.manager.RoomManager;

/**
 * Offers the controlling functions for categories and rooms
 * 
 * @author phils
 *
 */
public class CategoryController {

	/*--------------------loading Data for category and rooms--------------------------*/
	
	/**
	 * Returns a list of all categories
	 * @return a list of categories
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public List<ICategory> loadCategories() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException{
		IRoomManager roomMan = ManagerFactory.getRoomManager();
		List<Category> allCategories = roomMan.getAllCategories();
		List<ICategory> allICategories = new ArrayList<ICategory>();
		for (ICategory ic : allCategories) {
			allICategories.add(ic);
		}
		return allICategories;
	} 
	
	/**
	 * loading categories for change
	 * @return all categories
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private List<Category> loadAllCategies() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException{
		IRoomManager roomMan = ManagerFactory.getRoomManager();
		return roomMan.getAllCategories();
	}
	
	/**
	 * Returns a ICategory searched by name
	 * @param name
	 * @return a ICategory
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public ICategory loadCategoryByName(String name) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException{
		for (ICategory ic : loadCategories()) {
			if (ic.getName().equals(name)) {
				return ic;
			}
		}
		return null;
	}
	
	/**
	 * Returns a list of all Rooms
	 * 
	 * @return a list of all Rooms
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public List<IRoom> loadRooms() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {

		IRoomManager roomManager = ManagerFactory.getRoomManager();
		List<Room> allRooms = roomManager.getAllRooms();
		List<IRoom> allIRooms = new ArrayList<IRoom>();
		for (Room r : allRooms) {
			allIRooms.add((IRoom) r);
		}
		return allIRooms;
	}
	
	/**
	 * Returns an Room searched via roomnumber
	 * @param roomnumber
	 * @return an iRoom
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public IRoom getRoom(String roomnumber) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
		List<IRoom> allRooms = loadRooms();
		for (IRoom iR : allRooms) {
			if (iR.getNumber().equals(roomnumber)) {
				return iR;
			}
		}
		return null;
	}
	
	/**
	 * Retuns a list of available Rooms in an category with an specified amount
	 * 
	 * @param start
	 * @param end
	 * @param categoryName
	 * @param amount
	 * @return a list of available Rooms in an category with an specified amount
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws NotEnoughRoomsInCategory 
	 * @throws NotEnoughRoomsException 
	 * @throws Exception 
	 */
	public List<IRoom> availableRooms(Date start, Date end,
			String categoryName, int amount) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NotEnoughRoomsInCategory, NotEnoughRoomsException {
		// loading Category from db
		IRoomManager roomManager = ManagerFactory.getRoomManager();


		Category curCategory = roomManager.getCategoryByName(categoryName);

		List<IRoom> avRooms = new ArrayList<IRoom>();
		List<Room> tmpList = curCategory.checkAvailability(start, end, amount);
		if(tmpList == null) {
			return null;
		}
		
		for (IRoom iR : tmpList) {
			avRooms.add(iR);
		}
		if (avRooms.size() < amount) {
			List<Category> allCategories = loadAllCategies();
			List<IRoom> allRooms = new ArrayList<IRoom>();
			for (Category C : allCategories) {
				for (IRoom iR : C.checkAvailability(start, end, amount)) {
					allRooms.add(iR);
				}
			}
			if (allRooms.size() < amount) {
				throw new NotEnoughRoomsException("There are not enough rooms!");	
			}
		}
		return avRooms;
	}
	
	/**
	 * Returns a list with all possible states of a room
	 * @return a list with all possible states of a room
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalArgumentException 
	 */
	public List<Status> loadAllStates() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
		// loading sates from db
		IRoomManager roomManager = ManagerFactory.getRoomManager();

		List<Status> allStatesList = roomManager.getAllStatus();
		
		return allStatesList;
	}
	
	/**
	 * Returns a specific state searched by name
	 * @param name
	 * @return a specific state
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalArgumentException 
	 */
	public Status loadStates(String name) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException{
		List<Status> allStates = loadAllStates();
		for (Status s : allStates) {
			if (s.getName().equals(name)) {
				return s;
			}
		}
		return null;
	}
	
	/**
	 * Returns a price for a category
	 * @param date
	 * @param category
	 * @return a price
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public IPrice loadPriceForSeason(Date date, String category) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException{
		Category tempCategory = (Category) loadCategoryByName(category);
		IRoomManager rM = ManagerFactory.getRoomManager();
		IPrice price = rM.getCategoryPriceByDate(tempCategory, date); 
		return price;
	}

}
