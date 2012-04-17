/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.service;

import hotelsoftware.database.model.DBExtraservices;
import hotelsoftware.util.DynamicMapper;
import java.util.Collection;

/**
 *
 * @author Tobias
 */
public class ExtraServiceFacade {
    
    private ExtraServiceFacade() {
    }
    
    public static ExtraServiceFacade getInstance() {
        return ExtraServiceFacadeHolder.INSTANCE;
    }
    
    private static class ExtraServiceFacadeHolder {

        private static final ExtraServiceFacade INSTANCE = new ExtraServiceFacade();
    }
    
    public Collection<ExtraService> getAllExtraServices(){
        return DynamicMapper.mapCollection(DBExtraservices.getExtraServices(), ExtraService.class);
    }
    
    public ExtraService getExtraServiceByName(String name){
        
        DBExtraservices p = DBExtraservices.getServiceByName(name);
        
        if (p == null){
            
            throw new ServiceNotFoundException();
            
        }
        return DynamicMapper.map(p, ExtraService.class);
    }
}
