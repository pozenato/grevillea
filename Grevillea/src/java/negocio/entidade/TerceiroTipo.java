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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pozenato
 */
@Entity
@Table(name = "terceiro_tipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TerceiroTipo.findAll", query = "SELECT t FROM TerceiroTipo t"),
    @NamedQuery(name = "TerceiroTipo.findByIdtipo", query = "SELECT t FROM TerceiroTipo t WHERE t.idtipo = :idtipo"),
    @NamedQuery(name = "TerceiroTipo.findByDescricao", query = "SELECT t FROM TerceiroTipo t WHERE t.descricao = :descricao")})
public class TerceiroTipo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtipo")
    private Integer idtipo;
    @Size(max = 2147483647)
    @Column(name = "descricao")
    private String descricao;
    
    @OneToOne(mappedBy = "tipo", fetch = FetchType.LAZY)
    private Terceiro terceiro;

    public TerceiroTipo() {
    }

    public TerceiroTipo(Integer idtipo) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipo != null ? idtipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TerceiroTipo)) {
            return false;
        }
        TerceiroTipo other = (TerceiroTipo) object;
        if ((this.idtipo == null && other.idtipo != null) || (this.idtipo != null && !this.idtipo.equals(other.idtipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.entidade.TerceiroTipo[ idtipo=" + idtipo + " ]";
    }

    /**
     * @return the terceiro
     */
    public Terceiro getTerceiro() {
        return terceiro;
    }

    /**
     * @param terceiro the terceiro to set
     */
    public void setTerceiro(Terceiro terceiro) {
        this.terceiro = terceiro;
    }
    
}
