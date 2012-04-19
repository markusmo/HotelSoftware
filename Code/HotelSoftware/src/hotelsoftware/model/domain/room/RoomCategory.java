/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.parties.DBCompanyType;
import hotelsoftware.model.database.room.DBRoom;
import hotelsoftware.model.domain.parties.CompanyType;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * 
 * @author Lins Christian (christian.lins87@gmail.com)
 */
<<<<<<< HEAD:Code/HotelSoftware/src/hotelsoftware/model/domain/room/Category.java
public class Category implements CategoryData {
	private Integer id;
	private String name;
	private BigDecimal price;
	private BigDecimal minPrice;
	private int bedAmount;

	public void setId(Integer id) {
		if (id == null)
			this.id = id;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public int getBedAmount() {
		return bedAmount;
	}
=======
public class RoomCategory implements CategoryData
{
    private String name;
    private BigDecimal price;
    private BigDecimal minPrice;
    private int bedAmount;
>>>>>>> 78813edac85f434f28a193314799cd7ac88d0518:Code/HotelSoftware/src/hotelsoftware/model/domain/room/RoomCategory.java

	public void setBedAmount(int bedAmount) {
		this.bedAmount = bedAmount;
	}

	@Override
	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Category create(String name, BigDecimal price, BigDecimal minprice,
			int bedAmound) {
		return new Category(name, price, minprice, bedAmount);
	}

	private Category(String name, BigDecimal price, BigDecimal minprice,
			int bedAmount) {
		this.name = name;
		this.price = price;
		this.minPrice = minprice;
		this.bedAmount = bedAmount;
	}
	public static Category getCategoryByName(String name)
	{
	Category c = (Category) DBRoomCategory.getCategoryByName(name);	
	return (Category)DynamicMapper.map(c);
	}
	public static Collection<Category> getAllCategorys()
	{
        	  Collection<DBRoomCategory> dbc = DBRoomCategory.getAllCategorys();
              return (Collection<Category>)DynamicMapper.map(dbc);
	}
	public Collection<Category> getFreeRooms(start, ende)
	{
	Collection<Category> dbc = DBRoomCategory.getFreeRooms(this, start, ende);
	return (Collection<Category>)DynamicMapper.map(dbc);
	}
	public Collection<Room> getAllRooms()
	{
		Collection<Room> dbr = DBRoom.getAllRooms(this);
		return (Collection<Room>)DynamicMapper.map(dbr);
	}
}
