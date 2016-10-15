/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pozenato
 */
@Entity
@Table(name = "decoracao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Decoracao.findAll", query = "SELECT d FROM Decoracao d"),
    @NamedQuery(name = "Decoracao.findByIddecoracao", query = "SELECT d FROM Decoracao d WHERE d.iddecoracao = :iddecoracao"),
    @NamedQuery(name = "Decoracao.findByIdevento", query = "SELECT d FROM Decoracao d WHERE d.idevento = :idevento"),
    @NamedQuery(name = "Decoracao.findByIditem", query = "SELECT d FROM Decoracao d WHERE d.iditem = :iditem")})
public class Decoracao implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "iddecoracao", nullable = false)
    @SequenceGenerator(name="Decoracao_Generator", sequenceName="decoracao_sequence", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Decoracao_Generator")
    private Integer iddecoracao;
    
    
    @Column(name = "idevento")
    private Integer idevento;
    @Column(name = "iditem")
    private Integer iditem;

    public Decoracao() {
    }

    public Decoracao(Integer iddecoracao) {
        this.iddecoracao = iddecoracao;
    }

    public Integer getIddecoracao() {
        return iddecoracao;
    }

    public void setIddecoracao(Integer iddecoracao) {
        this.iddecoracao = iddecoracao;
    }

    public Integer getIdevento() {
        return idevento;
    }

    public void setIdevento(Integer idevento) {
        this.idevento = idevento;
    }

    public Integer getIditem() {
        return iditem;
    }

    public void setIditem(Integer iditem) {
        this.iditem = iditem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddecoracao != null ? iddecoracao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Decoracao)) {
            return false;
        }
        Decoracao other = (Decoracao) object;
        if ((this.iddecoracao == null && other.iddecoracao != null) || (this.iddecoracao != null && !this.iddecoracao.equals(other.iddecoracao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.entidade.Decoracao[ iddecoracao=" + iddecoracao + " ]";
    }
    
}
