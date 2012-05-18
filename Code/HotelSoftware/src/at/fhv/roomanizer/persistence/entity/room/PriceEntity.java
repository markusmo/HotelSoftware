package at.fhv.roomanizer.persistence.entity.room;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * Represents the price of a specific category in a specific season
 * 
 * @author Daniel Rotter <daniel.rotter@students.fhv.at>
 * @author Andreas Sinz <andreas.sinz@students.fhv.at>
 */
@Entity
@Table(name="roomCategoryPrices")
public class PriceEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The standard price for the given season
	 */
	@Column(name="price")
	private double _price;
	/**
	 * The minimun price for the given season
	 */
	@Column(name="priceMin")
	private double _priceMin;
	/**
	 * The season, in which this price is valid
	 */
	
	/**
	 * Reference to the Season, which the price belongs to
	 */
	@Id
	@OneToOne
	@JoinColumn(name="idSeasons")
	private SeasonEntity _season;
	
	/**
	 * Reference to the Category, which the price belongs to
	 */
	@Id
	@ManyToOne
	@JoinColumn(name="idRoomCategories")
	private CategoryEntity _category;
	
	/**
	 * Sets the price
	 * @param price The price
	 */
	public void setPrice(double price){
		_price = price;
	}
	
	/**
	 * Returns the price
	 * @return The price
	 */
	public double getPrice(){
		return _price;
	}
	
	/**
	 * Sets the minimum price
	 * @param priceMin The minimum price
	 */
	public void setPriceMin(double priceMin){
		_priceMin = priceMin;
	}
	
	/**
	 * Returns the minimum price
	 * @return The minimum price
	 */
	public double getPriceMin(){
		return _priceMin;
	}
	
	/**
	 * Sets the season of the price
	 * @param season The season of the price
	 */
	public void setSeason(SeasonEntity season){
		_season = season;
	}
	
	/**
	 * Returns the season of the price
	 * @return The season of the price
	 */
	public SeasonEntity getSeason(){
		return _season;
	}
	
	/**
	 * Sets the category of the price
	 * @param category of the price
	 */
	public void setCategory(CategoryEntity category) {
		this._category = category;
	}
	
	/**
	 * Returns the Category of the price 
	 * @return Category of the price
	 */
	public CategoryEntity getCategory() {
		return this._category;
	}
}
