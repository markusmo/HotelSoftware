package hotelsoftware.model.domain.service;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.service.DBServiceType;
import hotelsoftware.model.domain.service.data.ServiceTypeData;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Diese Klasse bildet eine Serviceart ab, mit der das System arbeitet
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class ServiceType implements ServiceTypeData
{
    private String type;
    private Integer id;
    private BigDecimal taxRate;

    /**
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id)
    {
        if (this.id == null)
        {
            this.id = id;
        }
    }

    /**
     * Instanziert eine neue Serviceart (Essen, Getraenke, etc.)
     * @param name
     * Name des neuen Service
     * @return
     * eine neue Instanz
     */
    public static ServiceType createServiceType(String name)
    {
        return new ServiceType(name);
    }

    /**
     * Gibt alle Servicearten aus
     * @return 
     * Alle Servicearten, die vorhanden sind.
     */
    public static Set<ServiceType> getAllServiceTypes()
    {
        Set<DBServiceType> serviceType = DBServiceType.getAllServiceTypes();
        return (Set<ServiceType>) DynamicMapper.map(serviceType);
    }

    public ServiceType()
    {
    }

    private ServiceType(String type)
    {
        this.type = type;
    }

    /**
     * @return the type
     */
    @Override
    public String getName()
    {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setName(String type)
    {
        this.type = type;
    }
    
    public BigDecimal getTaxRate()
    {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate)
    {
        this.taxRate = taxRate;
    }
    
    public static ServiceType getTypeByName(String name) throws ServiceTypeNotFoundException
    {
        return ServiceFacade.getInstance().getServiceTypeByName(name);
    }
}
