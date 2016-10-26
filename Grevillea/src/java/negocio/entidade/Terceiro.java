/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.entidade;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "terceiro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Terceiro.findAll", query = "SELECT t FROM Terceiro t"),
    @NamedQuery(name = "Terceiro.findByIdterceiro", query = "SELECT t FROM Terceiro t WHERE t.idterceiro = :idterceiro"),
    @NamedQuery(name = "Terceiro.findByNome", query = "SELECT t FROM Terceiro t WHERE t.nome = :nome"),
    @NamedQuery(name = "Terceiro.findByTipo", query = "SELECT t FROM Terceiro t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "Terceiro.findByStatus", query = "SELECT t FROM Terceiro t WHERE t.status = :status")})
public class Terceiro implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "idterceiro", nullable = false)
    @SequenceGenerator(name="Terceiro_Generator", sequenceName="terceiro_sequence", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Terceiro_Generator")
    private Integer idterceiro;
    
    @Size(max = 2147483647)
    @Column(name = "nome")
    private String nome;
    @Column(name = "status")
    private Boolean status;
    
    @JoinColumn(name = "tipo", referencedColumnName = "idtipo", nullable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private TerceiroTipo tipo;
            
    public Terceiro() {
    }

    public Terceiro(Integer idterceiro) {
        this.idterceiro = idterceiro;
    }

    public Integer getIdterceiro() {
        return idterceiro;
    }

    public void setIdterceiro(Integer idterceiro) {
        this.idterceiro = idterceiro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TerceiroTipo getTipo() {
        return tipo;
    }

    public void setTipo(TerceiroTipo tipo) {
        this.tipo = tipo;
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
        hash += (idterceiro != null ? idterceiro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Terceiro)) {
            return false;
        }
        Terceiro other = (Terceiro) object;
        if ((this.idterceiro == null && other.idterceiro != null) || (this.idterceiro != null && !this.idterceiro.equals(other.idterceiro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.entidade.Terceiro[ idterceiro=" + idterceiro + " ]";
    }

}
