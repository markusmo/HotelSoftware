/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.service;

/**
 *
 * @author Kno
 */
public interface IExtraService extends IService{

    String getName();

    @Override
    String getServiceName();

    void setName(String name);
    
}
