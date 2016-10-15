/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.entidade;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "tipo_colaborador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoColaborador.findAll", query = "SELECT t FROM TipoColaborador t"),
    @NamedQuery(name = "TipoColaborador.findByIdtipo", query = "SELECT t FROM TipoColaborador t WHERE t.idtipo = :idtipo"),
    @NamedQuery(name = "TipoColaborador.findByDescricao", query = "SELECT t FROM TipoColaborador t WHERE t.descricao = :descricao"),
    @NamedQuery(name = "TipoColaborador.findByValor", query = "SELECT t FROM TipoColaborador t WHERE t.valor = :valor")})
public class TipoColaborador implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "idtipo", nullable = false)
    @SequenceGenerator(name="TipoColaborador_Generator", sequenceName="tipo_colaborador_sequence", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TipoColaborador_Generator")
    private Integer idtipo;
    
    @Size(max = 2147483647)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "valor")
    private BigInteger valor;
    
    @OneToOne(mappedBy = "tipo", fetch = FetchType.LAZY)
    private Colaborador colaborador;

    public TipoColaborador() {
    }

    public TipoColaborador(Integer idtipo) {
        this.idtipo = idtipo;
    }

    public Integer getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(Integer idtipo) {
        this.idtipo = idtipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigInteger getValor() {
        return valor;
    }

    public void setValor(BigInteger valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipo != null ? idtipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoColaborador)) {
            return false;
        }
        TipoColaborador other = (TipoColaborador) object;
        if ((this.idtipo == null && other.idtipo != null) || (this.idtipo != null && !this.idtipo.equals(other.idtipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.entidade.TipoColaborador[ idtipo=" + idtipo + " ]";
    }

    /**
     * @return the colaborador
     */
    public Colaborador getColaborador() {
        return colaborador;
    }

    /**
     * @param colaborador the colaborador to set
     */
    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }
    
}
