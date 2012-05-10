package hotelsoftware.model.database.service;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Diese Klasse ist die Superklasse, aller Services, die es gibt.
 * @author mohi
 */
@Entity
@Table(name = "services", catalog = "`roomanizer-dev`", schema = "")
@Inheritance(strategy = InheritanceType.JOINED)
@XmlRootElement
public abstract class DBService implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idServices", nullable = false)
    private Integer idServices;
    @JoinColumn(name = "idServiceTypes", referencedColumnName = "id", nullable=false)
    @ManyToOne(optional = false)
    private DBServiceType serviceType;

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

    public DBServiceType getServiceType()
    {
        return serviceType;
    }

    public void setServiceType(DBServiceType serviceType)
    {
        this.serviceType = serviceType;
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
