/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import java.math.BigDecimal;

/**
 *
 * @author Kno
 */
public interface IRoomCategoryPrice {

    BigDecimal getPrice();

    BigDecimal getPriceMin();

    IRoomCategory getRoomcategories();

    IRoomCategoryPricePK getRoomcategorypricesPK();

    ISeason getSeasons();

    void setPrice(BigDecimal price);

    void setPriceMin(BigDecimal priceMin);

    void setRoomcategories(IRoomCategory roomcategories);

    void setRoomcategorypricesPK(IRoomCategoryPricePK roomcategorypricesPK);

    void setSeasons(ISeason seasons);
    
}
