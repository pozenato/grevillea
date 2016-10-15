/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.entidade;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pozenato
 */
@Entity
@Table(name = "fluxocaixa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fluxocaixa.findAll", query = "SELECT f FROM Fluxocaixa f"),
    @NamedQuery(name = "Fluxocaixa.findByIdfluxo", query = "SELECT f FROM Fluxocaixa f WHERE f.idfluxo = :idfluxo"),
    @NamedQuery(name = "Fluxocaixa.findByDatainsercao", query = "SELECT f FROM Fluxocaixa f WHERE f.datainsercao = :datainsercao"),
    @NamedQuery(name = "Fluxocaixa.findByTipo", query = "SELECT f FROM Fluxocaixa f WHERE f.tipo = :tipo"),
    @NamedQuery(name = "Fluxocaixa.findByValor", query = "SELECT f FROM Fluxocaixa f WHERE f.valor = :valor"),
    @NamedQuery(name = "Fluxocaixa.findByStatus", query = "SELECT f FROM Fluxocaixa f WHERE f.status = :status")})
public class Fluxocaixa implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "idfluxo", nullable = false)
    @SequenceGenerator(name="Fluxo_Generator", sequenceName="fluxo_sequence", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Fluxo_Generator")
    private Integer idfluxo;
    
    
    @Column(name = "datainsercao")
    @Temporal(TemporalType.DATE)
    private Date datainsercao;
    @Column(name = "tipo")
    private Character tipo;
    @Column(name="valor", columnDefinition="Decimal(10,2)")
    private float valor;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "datadespesa")
    @Temporal(TemporalType.DATE)
    private Date datadespesa;
    
    @JoinColumn(name = "idfornecedor", referencedColumnName = "idfornecedor", nullable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Fornecedor fornecedor;
    
    @JoinColumn(name = "idproduto", referencedColumnName = "idproduto", nullable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Produto produto;

    public Fluxocaixa() {
    }

    public Fluxocaixa(Integer idfluxo) {
        this.idfluxo = idfluxo;
    }

    public Integer getIdfluxo() {
        return idfluxo;
    }

    public void setIdfluxo(Integer idfluxo) {
        this.idfluxo = idfluxo;
    }

    public Date getDatainsercao() {
        return datainsercao;
    }

    public void setDatainsercao(Date datainsercao) {
        this.datainsercao = datainsercao;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
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
        hash += (idfluxo != null ? idfluxo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fluxocaixa)) {
            return false;
        }
        Fluxocaixa other = (Fluxocaixa) object;
        if ((this.idfluxo == null && other.idfluxo != null) || (this.idfluxo != null && !this.idfluxo.equals(other.idfluxo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.entidade.Fluxocaixa[ idfluxo=" + idfluxo + " ]";
    }

    /**
     * @return the fornecedor
     */
    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    /**
     * @param fornecedor the fornecedor to set
     */
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    /**
     * @return the produto
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    /**
     * @return the valor
     */
    public float getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(float valor) {
        this.valor = valor;
    }

    /**
     * @return the datadespesa
     */
    public Date getDatadespesa() {
        return datadespesa;
    }

    /**
     * @param datadespesa the datadespesa to set
     */
    public void setDatadespesa(Date datadespesa) {
        this.datadespesa = datadespesa;
    }
    
}
