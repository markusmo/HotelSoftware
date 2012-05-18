package at.fhv.roomanizer.persistence.entity.room;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 * Every room must be a member of a category. The category is asked
 * if we want to know if rooms are available. 
 * 
 * Categorys are the deciding
 * factor when deciding about pricing, but prices are stored in the Price
 * class and are depending of the actual season.
 * 
 * @author Shady
 * @author Andreas Sinz <andreas.sinz@students.fhv.at>
 */

@Entity
@Table(name="roomCategories")
public class CategoryEntity {

	/**
	 * ID of the category
	 */
	@Id
	@Generated(GenerationTime.INSERT)
	@Column(name="id")
	private int _id;
	
	/**
	 * Name of the category
	 */
	@Column(name="name")
	private String _name;
	
	/**
	 * Price list of the category
	 */
	@OneToMany(mappedBy="_category")
	private  List<PriceEntity> _price = new ArrayList<PriceEntity>();


	/**
	 * Rooms in this category
	 */
	@OneToMany(mappedBy="_category")
	private List<RoomEntity> _rooms = new ArrayList<RoomEntity>();
	
	/**
	 * Returns the ID
	 * @return the ID
	 */
	public int getId() {
		return _id;
	}

	/**
	 * Set the ID
	 * @param _id of the category
	 */
	public void setId(int _id) {
		this._id = _id;
	}

	/**
	 * Get the name of the category
	 * @return name of the category
	 */
	public String getName() {
		return _name;
	}

	/**
	 * Set the name of the category
	 * @param _name of the category
	 */
	public void setName(String _name) {
		this._name = _name;
	}

	/**
	 * Returns prices of the category
	 * @return List<Price> 
	 */
	public List<PriceEntity> getPrice() {
		return _price;
	}

	/**
	 * Set a list of prices for the category
	 * @param price List<price>
	 */
	public void setPrice(List<PriceEntity> price) {
		this._price = price;
	}
	
	/**
	 * Returns all of the rooms, which belong to this Category
	 * @return list of rooms, which belong to this category
	 */
	public List<RoomEntity> getRooms() {
		return _rooms;
	}

	/**
	 * Set the rooms, which belong to this Category
	 * @param rooms which belong to this Category
	 */
	public void setRooms(List<RoomEntity> rooms) {
		this._rooms = rooms;
	}
}
