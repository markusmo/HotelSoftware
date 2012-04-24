/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.service;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mohi
 */
@Entity
@Table(name = "services", catalog = "roomanizer", schema = "")
@Inheritance(strategy = InheritanceType.JOINED)
@XmlRootElement
public class DBService implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idServices", nullable = false)
    private Integer idServices;
    @JoinColumn(name = "idServiceTypes", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBServiceType idServiceTypes;

    public DBService()
    {
    }

    public DBService(Integer id)
    {
        this.idServices = id;
    }

    public Integer getIdServices()
    {
        return idServices;
    }

    public void setIdServices(Integer id)
    {
        this.idServices = id;
    }

    public DBServiceType getIdServiceTypes()
    {
        return idServiceTypes;
    }

    public void setIdServiceTypes(DBServiceType idServiceTypes)
    {
        this.idServiceTypes = idServiceTypes;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idServices != null ? idServices.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DBService))
        {
            return false;
        }
        DBService other = (DBService) object;
        if ((this.idServices == null && other.idServices != null) || (this.idServices != null && !this.idServices.equals(other.idServices)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Services[ id=" + idServices + " ]";
    }
}
