/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.parties;

import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Dies ist die Klasse für die Tabelle "companies". Hier werden alle Firmen gespeichert.
 * @author mohi
 */
@Entity
@Table(name = "companies", catalog = "roomanizer", schema = "")
@XmlRootElement
//@NamedQueries({
//		@NamedQuery(name = "Companies.findAll", query = "SELECT c FROM Companies c"),
//		@NamedQuery(name = "Companies.findById", query = "SELECT c FROM Companies c WHERE c.id = :id"),
//		@NamedQuery(name = "Companies.findByName", query = "SELECT c FROM Companies c WHERE c.name = :name") 
//})
public class DBCompany implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Basic(optional = false)
	@Column(name = "name", nullable = false, length = 255)
	private String name;
	@JoinTable(name = "companiespersons", joinColumns = { @JoinColumn(name = "idCompanies", referencedColumnName = "id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "idPersons", referencedColumnName = "id", nullable = false) })
	@ManyToMany
	private Collection<DBPerson> personsCollection;
	@JoinColumn(name = "idCustomers", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private DBCustomer idCustomers;
	@JoinColumn(name = "idCompanyTypes", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private DBCompanyType idCompanyTypes;

	public DBCompany() {
	}

	public DBCompany(Integer id) {
		this.id = id;
	}

	public DBCompany(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlTransient
	public Collection<DBPerson> getContactPersons() {
		return personsCollection;
	}

	public void setContactPersons(Collection<DBPerson> personsCollection) {
		this.personsCollection = personsCollection;
	}

	public DBCustomer getIdCustomers() {
		return idCustomers;
	}

	public void setIdCustomers(DBCustomer idCustomers) {
		this.idCustomers = idCustomers;
	}

	public DBCompanyType getIdCompanyTypes() {
		return idCompanyTypes;
	}

	public void setIdCompanyTypes(DBCompanyType idCompanyTypes) {
		this.idCompanyTypes = idCompanyTypes;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof DBCompany)) {
			return false;
		}
		DBCompany other = (DBCompany) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "hotelsoftware.database.model.Companies[ id=" + id + " ]";
	}

	public static DBCompany getCompanyByName(String name)
			throws HibernateException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction ts = session.beginTransaction();
		ts.begin();
		Criteria criteria = session.createCriteria(DBCompany.class).add(Restrictions.eq(
                "name", name));
		return (DBCompany) criteria.uniqueResult();
	}

}
