/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Diese Klasse bildet die verschiedenen Servicetypen (Essen, Getraenke, Aufenthalte, ...) mit Steuern auf die Datenbank ab.
 *
 * @author mohi
 */
@Entity
@Table(name = "servicetypes", catalog = "`roomanizer-dev`", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "name"
    })
})
@XmlRootElement
public class DBServiceType implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Basic(optional = false)
    @Column(name = "taxRate", nullable = false, precision = 5, scale = 2)
    private BigDecimal taxRate;
    @OneToMany(mappedBy = "serviceType")
    private Set<DBService> dBServiceCollection;

    public DBServiceType()
    {
    }

    public DBServiceType(Integer id)
    {
        this.id = id;
    }

    public DBServiceType(Integer id, String name, BigDecimal taxRate)
    {
        this.id = id;
        this.name = name;
        this.taxRate = taxRate;
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

    public BigDecimal getTaxRate()
    {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate)
    {
        this.taxRate = taxRate;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Servicetypes[ id=" + id + " ]";
    }    

    @XmlTransient
    public Set<DBService> getDBServiceCollection()
    {
        return dBServiceCollection;
    }

    public void setDBServiceCollection(Set<DBService> dBServiceCollection)
    {
        this.dBServiceCollection = dBServiceCollection;
    }
}
