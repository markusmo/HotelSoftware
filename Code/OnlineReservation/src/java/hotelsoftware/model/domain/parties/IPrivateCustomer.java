/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

/**
 *
 * @author Kno
 */
public interface IPrivateCustomer extends ICustomer{

   
    String getFname();

    Character getGender();

    String getLname();

    String getName();

    void setFname(String fname);

    void setGender(Character gender);

    void setLname(String lname);
    
}
