package hotelsoftware.model.domain.parties;

import hotelsoftware.controller.data.parties.CompanyTypeData;
import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.invoice.DBPaymentMethod;
import hotelsoftware.model.database.parties.DBCompanyType;
import hotelsoftware.model.domain.invoice.PaymentMethod;

import java.util.Collection;
import java.util.Set;

/**
 * Klasse die die Typen einer Firma enthält. Mithife dieser Klasse unterscheiden sich Firmen von Reisebüros.
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class CompanyType implements ICompanyType
{
    private Integer id;
    private String typ;

    public CompanyType()
    {
    }
    
    /**
     * Instanziert einen neuen Firmen-Typ (z.B. GMBH, Reisebuero, etc.)
     * @param typ
     * Der Typ der Firma
     * @return 
     * Eine neue Instanz des Firmentyps
     */
    public static ICompanyType create(String typ)
    {
        return new CompanyType(typ);
    }

    private CompanyType(String typ)
    {
        this.typ = typ;
    }

    @Override
    public Integer getId()
    {
        return id;
    }

    @Override
    public void setId(Integer id)
    {
        if (this.id == null)
        {
            this.id = id;
        }
    }

    @Override
    public void setName(String typ)
    {
        this.typ = typ;
    }

    @Override
    public String getName()
    {
        return typ;
    }

    /**
     * gibt alle Typen zurück
     *
     * @return Kollektion aus Firmentypen
     */
    public static Set<ICompanyType> getAllTypes()
    {
        PartyFacade facade = PartyFacade.getInstance();
        Set<ICompanyType> dbct = facade.getAllTypes();
        return (Set<ICompanyType>) DynamicMapper.mapCollection(dbct);
    }
}
