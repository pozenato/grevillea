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
@Table(name = "evento_terceiro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EventoTerceiro.findAll", query = "SELECT e FROM EventoTerceiro e"),
    @NamedQuery(name = "EventoTerceiro.findByIdevento", query = "SELECT e FROM EventoTerceiro e WHERE e.idevento = :idevento"),
    @NamedQuery(name = "EventoTerceiro.findByIdterceiro", query = "SELECT e FROM EventoTerceiro e WHERE e.idterceiro = :idterceiro"),
    @NamedQuery(name = "EventoTerceiro.findByIdeventoterceiro", query = "SELECT e FROM EventoTerceiro e WHERE e.ideventoterceiro = :ideventoterceiro")})
public class EventoTerceiro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "idevento")
    private Integer idevento;
    @Column(name = "idterceiro")
    private Integer idterceiro;
    @Id
    @Basic(optional = false)
    @Column(name = "ideventoterceiro", nullable = false)
    @SequenceGenerator(name="EventoTerceiro_Generator", sequenceName="evento_terceiro_sequence", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EventoTerceiro_Generator")
    private Integer ideventoterceiro;

    public EventoTerceiro() {
    }

    public EventoTerceiro(Integer ideventoterceiro) {
        this.ideventoterceiro = ideventoterceiro;
    }

    public Integer getIdevento() {
        return idevento;
    }

    public void setIdevento(Integer idevento) {
        this.idevento = idevento;
    }

    public Integer getIdterceiro() {
        return idterceiro;
    }

    public void setIdterceiro(Integer idterceiro) {
        this.idterceiro = idterceiro;
    }

    public Integer getIdeventoterceiro() {
        return ideventoterceiro;
    }

    public void setIdeventoterceiro(Integer ideventoterceiro) {
        this.ideventoterceiro = ideventoterceiro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideventoterceiro != null ? ideventoterceiro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventoTerceiro)) {
            return false;
        }
        EventoTerceiro other = (EventoTerceiro) object;
        if ((this.ideventoterceiro == null && other.ideventoterceiro != null) || (this.ideventoterceiro != null && !this.ideventoterceiro.equals(other.ideventoterceiro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.entidade.EventoTerceiro[ ideventoterceiro=" + ideventoterceiro + " ]";
    }
    
}
