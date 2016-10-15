/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.entidade;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pozenato
 */
@Entity
@Table(name = "evento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e"),
    @NamedQuery(name = "Evento.findByIdevento", query = "SELECT e FROM Evento e WHERE e.idevento = :idevento"),
    @NamedQuery(name = "Evento.findByObservacao", query = "SELECT e FROM Evento e WHERE e.observacao = :observacao"),
    @NamedQuery(name = "Evento.findByNomeconjuge", query = "SELECT e FROM Evento e WHERE e.nomeconjuge = :nomeconjuge"),
    @NamedQuery(name = "Evento.findByTelefoneconjuge", query = "SELECT e FROM Evento e WHERE e.telefoneconjuge = :telefoneconjuge"),
    @NamedQuery(name = "Evento.findByEmailconjuge", query = "SELECT e FROM Evento e WHERE e.emailconjuge = :emailconjuge"),
    @NamedQuery(name = "Evento.findByContato", query = "SELECT e FROM Evento e WHERE e.contato = :contato"),
    @NamedQuery(name = "Evento.findByAtivo", query = "SELECT e FROM Evento e WHERE e.ativo = :ativo"),
    @NamedQuery(name = "Evento.findByDatacriacao", query = "SELECT e FROM Evento e WHERE e.datacriacao = :datacriacao"),
    @NamedQuery(name = "Evento.findByIddata", query = "SELECT e FROM Evento e WHERE e.iddata = :iddata"),
    @NamedQuery(name = "Evento.findByValor", query = "SELECT e FROM Evento e WHERE e.valor = :valor"),
    @NamedQuery(name = "Evento.findByNomeaniversariante", query = "SELECT e FROM Evento e WHERE e.nomeaniversariante = :nomeaniversariante"),
    @NamedQuery(name = "Evento.findByEmpresa", query = "SELECT e FROM Evento e WHERE e.empresa = :empresa"),
    @NamedQuery(name = "Evento.findByEscola", query = "SELECT e FROM Evento e WHERE e.escola = :escola"),
    @NamedQuery(name = "Evento.findByInstituicao", query = "SELECT e FROM Evento e WHERE e.instituicao = :instituicao")})
public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "idevento", nullable = false)
    @SequenceGenerator(name="Evento_Generator", sequenceName="evento_sequence", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Evento_Generator")
    private Integer idevento;
    
    @Size(max = 2147483647)
    @Column(name = "observacao")
    private String observacao;
    @Size(max = 2147483647)
    @Column(name = "nomeconjuge")
    private String nomeconjuge;
    @Size(max = 2147483647)
    @Column(name = "telefoneconjuge")
    private String telefoneconjuge;
    @Size(max = 2147483647)
    @Column(name = "emailconjuge")
    private String emailconjuge;
    @Size(max = 2147483647)
    @Column(name = "contato")
    private String contato;
    @Column(name = "ativo")
    private Character ativo;
    @Column(name = "datacriacao")
    @Temporal(TemporalType.DATE)
    private Date datacriacao;
    @Column(name = "iddata")
    @Temporal(TemporalType.DATE)
    private Date iddata;
    @Column(name = "valor")
    private float valor;
    @Size(max = 2147483647)
    @Column(name = "nomeaniversariante")
    private String nomeaniversariante;
    @Size(max = 2147483647)
    @Column(name = "empresa")
    private String empresa;
    @Size(max = 2147483647)
    @Column(name = "escola")
    private String escola;
    @Size(max = 2147483647)
    @Column(name = "instituicao")
    private String instituicao;
    
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente", nullable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente cliente;
    
    @JoinColumn(name = "idtipo", referencedColumnName = "idtipo", nullable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Tipo tipo;
    
    @JoinColumn(name = "idusuario", referencedColumnName = "id_usuario", nullable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;
    
    @OneToOne(mappedBy = "evento", fetch = FetchType.LAZY)
    private Lancamento lancamento;
        
    public Evento() {
    }

    public Evento(Integer idevento) {
        this.idevento = idevento;
    }

    public Integer getIdevento() {
        return idevento;
    }

    public void setIdevento(Integer idevento) {
        this.idevento = idevento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getNomeconjuge() {
        return nomeconjuge;
    }

    public void setNomeconjuge(String nomeconjuge) {
        this.nomeconjuge = nomeconjuge;
    }

    public String getTelefoneconjuge() {
        return telefoneconjuge;
    }

    public void setTelefoneconjuge(String telefoneconjuge) {
        this.telefoneconjuge = telefoneconjuge;
    }

    public String getEmailconjuge() {
        return emailconjuge;
    }

    public void setEmailconjuge(String emailconjuge) {
        this.emailconjuge = emailconjuge;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Character getAtivo() {
        return ativo;
    }

    public void setAtivo(Character ativo) {
        this.ativo = ativo;
    }

    public Date getDatacriacao() {
        return datacriacao;
    }

    public void setDatacriacao(Date datacriacao) {
        this.datacriacao = datacriacao;
    }

    public Date getIddata() {
        return iddata;
    }

    public void setIddata(Date iddata) {
        this.iddata = iddata;
    }

    public String getNomeaniversariante() {
        return nomeaniversariante;
    }

    public void setNomeaniversariante(String nomeaniversariante) {
        this.nomeaniversariante = nomeaniversariante;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idevento != null ? idevento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.idevento == null && other.idevento != null) || (this.idevento != null && !this.idevento.equals(other.idevento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.entidade.Evento[ idevento=" + idevento + " ]";
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the tipo
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the lancamento
     */
    public Lancamento getLancamento() {
        return lancamento;
    }

    /**
     * @param lancamento the lancamento to set
     */
    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
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

}
