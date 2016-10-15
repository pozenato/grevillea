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
@Table(name = "produto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p"),
    @NamedQuery(name = "Produto.findByIdproduto", query = "SELECT p FROM Produto p WHERE p.idproduto = :idproduto"),
    @NamedQuery(name = "Produto.findByNomeproduto", query = "SELECT p FROM Produto p WHERE p.nomeproduto = :nomeproduto"),
    @NamedQuery(name = "Produto.findByMarcaproduto", query = "SELECT p FROM Produto p WHERE p.marcaproduto = :marcaproduto")})
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idproduto")
    @SequenceGenerator(name="Produto_Generator", sequenceName="produto_sequence", allocationSize= 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="Produto_Generator")
    private Integer idproduto;
    @Size(max = 2147483647)
    @Column(name = "nomeproduto")
    private String nomeproduto;
    @Size(max = 2147483647)
    @Column(name = "marcaproduto")
    private String marcaproduto;
    
    @OneToOne(mappedBy = "produto", fetch = FetchType.LAZY)
    private Fluxocaixa fluxo;

    public Produto() {
    }

    public Produto(Integer idproduto) {
        this.idproduto = idproduto;
    }

    public Integer getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Integer idproduto) {
        this.idproduto = idproduto;
    }

    public String getNomeproduto() {
        return nomeproduto;
    }

    public void setNomeproduto(String nomeproduto) {
        this.nomeproduto = nomeproduto;
    }

    public String getMarcaproduto() {
        return marcaproduto;
    }

    public void setMarcaproduto(String marcaproduto) {
        this.marcaproduto = marcaproduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproduto != null ? idproduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.idproduto == null && other.idproduto != null) || (this.idproduto != null && !this.idproduto.equals(other.idproduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.entidade.Produto[ idproduto=" + idproduto + " ]";
    }

    /**
     * @return the fluxo
     */
    public Fluxocaixa getFluxo() {
        return fluxo;
    }

    /**
     * @param fluxo the fluxo to set
     */
    public void setFluxo(Fluxocaixa fluxo) {
        this.fluxo = fluxo;
    }
    
}
