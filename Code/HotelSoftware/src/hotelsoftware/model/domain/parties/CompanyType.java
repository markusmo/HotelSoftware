/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.invoice.DBPaymentMethod;
import hotelsoftware.model.database.parties.DBCompanyType;
import hotelsoftware.model.domain.invoice.PaymentMethod;

import java.util.Collection;
import java.util.Set;

/**
 * Klasse die die Typen einer Firma enthällt. Mithife dieser Klasse unterscheiden sich Firmen von Reisebüros.
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 * @author Hubert
 */
public class CompanyType implements CompanyTypeData
{
    private Integer id;
    private String typ;

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
        if (id == null)
        {
            this.id = id;
        }
    }

    public void setTyp(String typ)
    {
        this.typ = typ;
    }

    @Override
    public String getTyp()
    {
        return typ;
    }

    /**
     * gibt alle Typen zurück
     *
     * @return Kollektion aus Firmentypen
     */
    @SuppressWarnings("unchecked")
    public static Set<CompanyType> getAllTypes()
    {
        Set<DBCompanyType> dbct = DBCompanyType.getAllTypes();
        return (Set<CompanyType>) DynamicMapper.map(dbct);
    }
}
