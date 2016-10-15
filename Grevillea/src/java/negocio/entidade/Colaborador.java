/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.entidade;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pozenato
 */
@Entity
@Table(name = "colaborador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Colaborador.findAll", query = "SELECT c FROM Colaborador c"),
    @NamedQuery(name = "Colaborador.findByIdcolaborador", query = "SELECT c FROM Colaborador c WHERE c.idcolaborador = :idcolaborador"),
    @NamedQuery(name = "Colaborador.findByNome", query = "SELECT c FROM Colaborador c WHERE c.nome = :nome"),
    @NamedQuery(name = "Colaborador.findByTipo", query = "SELECT c FROM Colaborador c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "Colaborador.findByStatus", query = "SELECT c FROM Colaborador c WHERE c.status = :status")})
public class Colaborador implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "idcolaborador", nullable = false)
    @SequenceGenerator(name="Colaborador_Generator", sequenceName="colaborador_sequence", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Colaborador_Generator")
    private Integer idcolaborador;
    
    @Size(max = 2147483647)
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "status")
    private Boolean status;
    
    @JoinColumn(name = "tipo", referencedColumnName = "idtipo", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = true, fetch = FetchType.LAZY)
    private TipoColaborador tipo;
                   
    public Colaborador() {
    }

    public Colaborador(Integer idcolaborador) {
        this.idcolaborador = idcolaborador;
    }

    public Integer getIdcolaborador() {
        return idcolaborador;
    }

    public void setIdcolaborador(Integer idcolaborador) {
        this.idcolaborador = idcolaborador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

   

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcolaborador != null ? idcolaborador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Colaborador)) {
            return false;
        }
        Colaborador other = (Colaborador) object;
        if ((this.idcolaborador == null && other.idcolaborador != null) || (this.idcolaborador != null && !this.idcolaborador.equals(other.idcolaborador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.entidade.Colaborador[ idcolaborador=" + idcolaborador + " ]";
    }

    /**
     * @return the tipo
     */
    public TipoColaborador getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoColaborador tipo) {
        this.tipo = tipo;
    }
}
