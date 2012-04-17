/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.database.model;

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

/**
 *
 * @author mohi
 */
@Entity
@Table(name = "companies", catalog = "roomanizer", schema = "")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Companies.findAll", query = "SELECT c FROM Companies c"),
    @NamedQuery(name = "Companies.findById", query = "SELECT c FROM Companies c WHERE c.id = :id"),
    @NamedQuery(name = "Companies.findByName", query = "SELECT c FROM Companies c WHERE c.name = :name")
})
public class DBCompanies implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @JoinTable(name = "companiespersons", joinColumns =
    {
        @JoinColumn(name = "idCompanies", referencedColumnName = "id", nullable = false)
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "idPersons", referencedColumnName = "id", nullable = false)
    })
    @ManyToMany
    private Collection<DBPersons> personsCollection;
    @JoinColumn(name = "idCustomers", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBCustomers idCustomers;
    @JoinColumn(name = "idCompanyTypes", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBCompanytypes idCompanyTypes;

    public DBCompanies()
    {
    }

    public DBCompanies(Integer id)
    {
        this.id = id;
    }

    public DBCompanies(Integer id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @XmlTransient
    public Collection<DBPersons> getContactPersons()
    {
        return personsCollection;
    }

    public void setContactPersons(Collection<DBPersons> personsCollection)
    {
        this.personsCollection = personsCollection;
    }

    public DBCustomers getIdCustomers()
    {
        return idCustomers;
    }

    public void setIdCustomers(DBCustomers idCustomers)
    {
        this.idCustomers = idCustomers;
    }

    public DBCompanytypes getIdCompanyTypes()
    {
        return idCompanyTypes;
    }

    public void setIdCompanyTypes(DBCompanytypes idCompanyTypes)
    {
        this.idCompanyTypes = idCompanyTypes;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof DBCompanies))
        {
            return false;
        }
        DBCompanies other = (DBCompanies) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Companies[ id=" + id + " ]";
    }
    
}
