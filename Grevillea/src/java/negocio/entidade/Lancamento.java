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
@Table(name = "lancamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lancamento.findAll", query = "SELECT l FROM Lancamento l"),
    @NamedQuery(name = "Lancamento.findByIdlancamento", query = "SELECT l FROM Lancamento l WHERE l.idlancamento = :idlancamento"),
    @NamedQuery(name = "Lancamento.findByDatalancamento", query = "SELECT l FROM Lancamento l WHERE l.datalancamento = :datalancamento"),
    @NamedQuery(name = "Lancamento.findByDataprevisao", query = "SELECT l FROM Lancamento l WHERE l.dataprevisao = :dataprevisao"),
    @NamedQuery(name = "Lancamento.findByDatarecebimento", query = "SELECT l FROM Lancamento l WHERE l.datarecebimento = :datarecebimento"),
    @NamedQuery(name = "Lancamento.findByValorprevisto", query = "SELECT l FROM Lancamento l WHERE l.valorprevisto = :valorprevisto"),
    @NamedQuery(name = "Lancamento.findByValorrecebido", query = "SELECT l FROM Lancamento l WHERE l.valorrecebido = :valorrecebido"),
    @NamedQuery(name = "Lancamento.findByUsuariolancamento", query = "SELECT l FROM Lancamento l WHERE l.usuariolancamento = :usuariolancamento"),
    @NamedQuery(name = "Lancamento.findByUsuariorecebimento", query = "SELECT l FROM Lancamento l WHERE l.usuariorecebimento = :usuariorecebimento"),
    @NamedQuery(name = "Lancamento.findByStatus", query = "SELECT l FROM Lancamento l WHERE l.status = :status")})
public class Lancamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idlancamento")
    @SequenceGenerator(name="Lancamento_Generator", sequenceName="lancamento_sequence", allocationSize= 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="Lancamento_Generator")
    private Integer idlancamento;
    
    
    @Column(name = "datalancamento")
    @Temporal(TemporalType.DATE)
    private Date datalancamento;
    @Column(name = "dataprevisao")
    @Temporal(TemporalType.DATE)
    private Date dataprevisao;
    @Column(name = "datarecebimento")
    @Temporal(TemporalType.DATE)
    private Date datarecebimento;
    @Column(name = "valorprevisto", columnDefinition="Decimal(10,2)")
    private float valorprevisto;
    @Column(name = "valorrecebido", columnDefinition="Decimal(10,2)")
    private float valorrecebido;
    @Column(name = "status")
    private Boolean status;
    
    @JoinColumn(name = "usuariorecebimento", referencedColumnName = "id_usuario", nullable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuariorecebimento;
    
    @JoinColumn(name = "usuariolancamento", referencedColumnName = "id_usuario", nullable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuariolancamento;
    
    @JoinColumn(name = "idevento", referencedColumnName = "idevento", nullable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Evento evento;

    public Lancamento() {
    }

    public Lancamento(Integer idlancamento) {
        this.idlancamento = idlancamento;
    }

    public Integer getIdlancamento() {
        return idlancamento;
    }

    public void setIdlancamento(Integer idlancamento) {
        this.idlancamento = idlancamento;
    }

    public Date getDatalancamento() {
        return datalancamento;
    }

    public void setDatalancamento(Date datalancamento) {
        this.datalancamento = datalancamento;
    }

    public Date getDataprevisao() {
        return dataprevisao;
    }

    public void setDataprevisao(Date dataprevisao) {
        this.dataprevisao = dataprevisao;
    }

    public Date getDatarecebimento() {
        return datarecebimento;
    }

    public void setDatarecebimento(Date datarecebimento) {
        this.datarecebimento = datarecebimento;
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
        hash += (idlancamento != null ? idlancamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lancamento)) {
            return false;
        }
        Lancamento other = (Lancamento) object;
        if ((this.idlancamento == null && other.idlancamento != null) || (this.idlancamento != null && !this.idlancamento.equals(other.idlancamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.entidade.Lancamento[ idlancamento=" + idlancamento + " ]";
    }

    /**
     * @return the usuariorecebimento
     */
    public Usuario getUsuariorecebimento() {
        return usuariorecebimento;
    }

    /**
     * @param usuariorecebimento the usuariorecebimento to set
     */
    public void setUsuariorecebimento(Usuario usuariorecebimento) {
        this.usuariorecebimento = usuariorecebimento;
    }

    /**
     * @return the usuariolancamento
     */
    public Usuario getUsuariolancamento() {
        return usuariolancamento;
    }

    /**
     * @param usuariolancamento the usuariolancamento to set
     */
    public void setUsuariolancamento(Usuario usuariolancamento) {
        this.usuariolancamento = usuariolancamento;
    }

    /**
     * @return the evento
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     * @param evento the evento to set
     */
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    /**
     * @return the valorprevisto
     */
    public float getValorprevisto() {
        return valorprevisto;
    }

    /**
     * @param valorprevisto the valorprevisto to set
     */
    public void setValorprevisto(float valorprevisto) {
        this.valorprevisto = valorprevisto;
    }

    /**
     * @return the valorrecebido
     */
    public float getValorrecebido() {
        return valorrecebido;
    }

    /**
     * @param valorrecebido the valorrecebido to set
     */
    public void setValorrecebido(float valorrecebido) {
        this.valorrecebido = valorrecebido;
    }
    
}
