/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

/**
 *
 * @author Kno
 */
public interface ICountry{

    Integer getId();

    String getName();

    String getNameShort();


    void setId(Integer id);

    void setName(String name);

    void setNameShort(String nameShort);    
}
