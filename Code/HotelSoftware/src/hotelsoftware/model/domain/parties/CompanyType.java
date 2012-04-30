package hotelsoftware.model.domain.parties;

import hotelsoftware.model.domain.parties.data.CompanyTypeData;
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
public class CompanyType implements CompanyTypeData
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
    public CompanyType create(String typ)
    {
        return new CompanyType(typ);
    }

    private CompanyType(String typ)
    {
        this.typ = typ;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        if (this.id == null)
        {
            this.id = id;
        }
    }

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
    public static Set<CompanyType> getAllTypes()
    {
        Set<DBCompanyType> dbct = DBCompanyType.getAllTypes();
        return (Set<CompanyType>) DynamicMapper.map(dbct);
    }
}
