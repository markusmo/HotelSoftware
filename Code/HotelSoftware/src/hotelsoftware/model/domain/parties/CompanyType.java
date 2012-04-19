/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.invoice.DBPaymentmethod;
import hotelsoftware.model.database.parties.DBCompanyType;
import hotelsoftware.model.domain.invoice.PaymentMethod;

import java.util.Collection;

/**
 * 
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class CompanyType implements CompanyTypeData {

	private Integer id;
	private String typ;

	public CompanyType create(String typ) {
		return new CompanyType(typ);
	}

	private CompanyType(String typ) {
		this.typ = typ;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		if (id == null)
			this.id = id;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

    @Override
        public String getTyp()
        {
            return typ;
        }
        
        @SuppressWarnings("unchecked")
		public static Collection<CompanyType> getAllTypes()
        {
        	  Collection<DBCompanyType> dbct = DBCompanyType.getAllTypes();
              return (Collection<CompanyType>)DynamicMapper.map(dbct);
        }
        
        
}
