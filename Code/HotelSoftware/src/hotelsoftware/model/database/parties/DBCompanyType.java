/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.parties;

import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * 
 * @author mohi
 */
@Entity
@Table(name = "companytypes", catalog = "roomanizer", schema = "", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Companytypes.findAll", query = "SELECT c FROM Companytypes c"),
		@NamedQuery(name = "Companytypes.findById", query = "SELECT c FROM Companytypes c WHERE c.id = :id"),
		@NamedQuery(name = "Companytypes.findByName", query = "SELECT c FROM Companytypes c WHERE c.name = :name") })
public class DBCompanyType implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCompanyTypes")
    private Collection<DBCompany> dBCompanyCollection;
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Basic(optional = false)
	@Column(name = "name", nullable = false, length = 255)
	private String name;

	public DBCompanyType() {
	}

	public DBCompanyType(Integer id) {
		this.id = id;
	}

	public DBCompanyType(Integer id, String name) {
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
		if (!(object instanceof DBCompanyType)) {
			return false;
		}
		DBCompanyType other = (DBCompanyType) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "hotelsoftware.database.model.Companytypes[ id=" + id + " ]";
	}

	public static List<DBCompanyType> getAllTypes() throws HibernateException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction ts = session.beginTransaction();
		ts.begin();
		Criteria criteria = session.createCriteria(DBCompanyType.class);
		List<DBCompanyType> retList = criteria.list();
		session.close();

		return retList;
	}

    @XmlTransient
    public Collection<DBCompany> getDBCompanyCollection()
    {
        return dBCompanyCollection;
    }

    public void setDBCompanyCollection(Collection<DBCompany> dBCompanyCollection)
    {
        this.dBCompanyCollection = dBCompanyCollection;
    }

}
