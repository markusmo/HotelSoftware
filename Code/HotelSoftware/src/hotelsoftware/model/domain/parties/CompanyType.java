/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.model.database.parties.DBCompanyType;
import hotelsoftware.model.domain.users.User;
import hotelsoftware.model.domain.users.UserFacade;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class CompanyType {

	private Integer id;
	private String typ;

	public static Collection<CompanyType> getAllTypes() {

		return PartyFacade.getInstance().getAllTypes();
	}

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

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}
}
