/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.fhv.roomanizer.persistence.manager;

import at.fhv.roomanizer.domain.room.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dunst
 */
public interface IRoomManager
{

    /**
     * Returns a list of all categories from the database
     *
     * @return List of all categories
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    List<Category> getAllCategories() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException;

    /**
     * Returns a list of all rooms from the database
     *
     * @return List of all rooms, which are stored in the database
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    List<Room> getAllRooms() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException;

    /**
     * Returns the singleton-instance of the HabitationManager
     *
     * @param session Session, which should be used to retrieve/store data in the database
     * @return
     */
    List<Status> getAllStatus() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException;

    /**
     * Returns a Category specified by its name
     *
     * @param name Name of the Category, which should be retrieved
     * @return
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    Category getCategoryByName(String name) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException;

    /**
     * Returns the price of the given category at the given time
     *
     * @param category Category of the room
     * @param date Date, at which we want to know the price
     * @return Returns a Price object with the given category at the given time
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    Price getCategoryPriceByDate(Category category, Date date) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException;

    /**
     * Get the room status of the given room at the given date
     *
     * @param room Room, which we want to know the status from
     * @param date Date, at which we want to know the status from
     * @return Returns the status, which the room has at the given date
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    RoomStatus getRoomStatusByDate(Room room, Date date) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException;

    /**
     * Returns all rooms, which belongs to a specific category
     *
     * @param category, which the rooms should belong to
     * @return
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    List<Room> getRoomsByCategory(Category category) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException;

    Season getSeasonByDate(Date date) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException;

    Status getStatusByName(String name) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException;

    /**
     * Returns the season, which belongs to the given date
     *
     * @param date Date, which we want to know the Season for
     * @return
     * Saves a new status of a room
     * @param status Status, which will be stored in the database
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    void saveRoomStatus(RoomStatus status) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException;
    
}
