package hotelsoftware.model.domain.service;

import hotelsoftware.model.database.manager.ServiceManager;
import hotelsoftware.support.ServiceTypeNotFoundException;
import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.service.DBServiceType;
import hotelsoftware.controller.data.service.ServiceTypeData;
import hotelsoftware.util.HibernateUtil;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Diese Klasse bildet eine Serviceart ab, mit der das System arbeitet
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class ServiceType implements IServiceType
{
    private String type;
    private Integer id;
    private BigDecimal taxRate;

    /**
     * @return the id
     */
    @Override
    public Integer getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    @Override
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
    public static IServiceType createServiceType(String name)
    {
        return new ServiceType(name);
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
    @Override
    public void setName(String type)
    {
        this.type = type;
    }
    
    @Override
    public BigDecimal getTaxRate()
    {
        return taxRate;
    }

    @Override
    public void setTaxRate(BigDecimal taxRate)
    {
        this.taxRate = taxRate;
    }
    /**
     * sucht nach einem Typ mithilfe eines Namens
     * @param name
     * @return
     * Der Servicetyp mit dem angegebenen Namen
     * @throws ServiceTypeNotFoundException 
     */
    public static IServiceType getTypeByName(String name) throws ServiceTypeNotFoundException
    {
        return ServiceManager.getInstance().getServiceTypeByName(name);
    }
    
    /**
     * Gibt alle Servicetypen aus
     *
     * @return
     * Alle Servicetypen, die verfuegbar sind
     */
    public static Set<IServiceType> getAllServiceTypes()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Collection<DBServiceType> serviceType = session.createCriteria(DBServiceType.class).list();

        return (Set<IServiceType>)DynamicMapper.mapCollection(serviceType);
    }
}
