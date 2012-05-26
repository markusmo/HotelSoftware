package hotelsoftware.model.domain.parties;

import hotelsoftware.model.database.manager.PartyManager;
import java.util.Collection;

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
    public static Collection<ICompanyType> getAllTypes()
    {
        PartyManager facade = PartyManager.getInstance();
        return facade.getAllTypes();
    }
    
    
    @Override
    public String toString()
    {
        return typ;
    }
           
}
