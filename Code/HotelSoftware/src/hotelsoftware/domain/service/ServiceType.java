/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.service;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class ServiceType
{
    private String type;

    private ServiceType(String type){
        this.type = type;
    }
    
    /**
     * @return the type
     */
    
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
    public void createServiceType(String name){
        ServiceType(name);
    }
}
