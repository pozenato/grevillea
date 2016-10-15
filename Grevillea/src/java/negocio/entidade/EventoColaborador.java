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
@Table(name = "evento_colaborador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EventoColaborador.findAll", query = "SELECT e FROM EventoColaborador e"),
    @NamedQuery(name = "EventoColaborador.findByIdevento", query = "SELECT e FROM EventoColaborador e WHERE e.idevento = :idevento"),
    @NamedQuery(name = "EventoColaborador.findByIdcolaborador", query = "SELECT e FROM EventoColaborador e WHERE e.idcolaborador = :idcolaborador"),
    @NamedQuery(name = "EventoColaborador.findByIdeventocolaborador", query = "SELECT e FROM EventoColaborador e WHERE e.ideventocolaborador = :ideventocolaborador")})
public class EventoColaborador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "idevento")
    private Integer idevento;
    @Column(name = "idcolaborador")
    private Integer idcolaborador;
    
    @Id
    @Basic(optional = false)
    @Column(name = "ideventocolaborador", nullable = false)
    @SequenceGenerator(name="EventoColaborador_Generator", sequenceName="evento_colaborador_sequence", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EventoColaborador_Generator")
    private Integer ideventocolaborador;
    

    public EventoColaborador() {
    }

    public EventoColaborador(Integer ideventocolaborador) {
        this.ideventocolaborador = ideventocolaborador;
    }

    public Integer getIdevento() {
        return idevento;
    }

    public void setIdevento(Integer idevento) {
        this.idevento = idevento;
    }

    public Integer getIdcolaborador() {
        return idcolaborador;
    }

    public void setIdcolaborador(Integer idcolaborador) {
        this.idcolaborador = idcolaborador;
    }

    public Integer getIdeventocolaborador() {
        return ideventocolaborador;
    }

    public void setIdeventocolaborador(Integer ideventocolaborador) {
        this.ideventocolaborador = ideventocolaborador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideventocolaborador != null ? ideventocolaborador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventoColaborador)) {
            return false;
        }
        EventoColaborador other = (EventoColaborador) object;
        if ((this.ideventocolaborador == null && other.ideventocolaborador != null) || (this.ideventocolaborador != null && !this.ideventocolaborador.equals(other.ideventocolaborador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.entidade.EventoColaborador[ ideventocolaborador=" + ideventocolaborador + " ]";
    }
    
}
