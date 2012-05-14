/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.controller.data.parties.PrivateCustomerData;

/**
 *
 * @author Kno
 */
public interface IPrivateCustomer extends ICustomer, PrivateCustomerData{

   
    String getFname();

    Character getGender();

    String getLname();

    String getName();

    void setFname(String fname);

    void setGender(Character gender);

    void setLname(String lname);

    String toString();
    
}