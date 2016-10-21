/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentação.ManangedBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import negocio.entidade.Usuario;
import negocio.fachada.UsuarioFachada;
import org.primefaces.model.DualListModel;
import apresentacao.validacao.ValidaSenha;
import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import util.gerarSenha;

/**
 *
 * @author pozenato
 */
@ManagedBean(name = "usuarioManagedBean")
@SessionScoped
public class UsuarioManagedBean {

    private Usuario usuario = new Usuario();
    private List<Usuario> Usuarios;
    private List<Usuario> UsuarioFiltro;
    private String mensagemErro;
    public String senhavalidada = new String();
    public String senhagerada = new String();
    public ImpressaoManagedBean impressaoManagedBean = new ImpressaoManagedBean();
    private String password;
    private String idUsuario;
    private Usuario usuarioLogado = new Usuario();
    public gerarSenha senha = new gerarSenha();
    private boolean logou = false;
    private HtmlInputText usuarioInput = new HtmlInputText();
    private HtmlInputText senhaInput = new HtmlInputText();

    @EJB
    private UsuarioFachada usuarioFachada;

    public UsuarioManagedBean() {
    }

    public String montarPaginaParaInsercao() {
        this.setUsuario(new Usuario());
        this.recuperarUsuarios();
        return "/Usuario/InserirUsuario?faces-redirect=true";
    }

    public String montarPaginaParaAlteracao() {
        return "/Usuario/AlterarUsuario?faces-redirect=true";
    }

    public String Inserir() {
        senhavalidada = ValidaSenha.encripta(this.usuario.getSenha());
        this.usuario.setSenha(senhavalidada);
        this.usuario.setPrimeiroacesso(true);
        this.usuario.setTipouser('U');
        getUsuarioFachada().Inserir(getUsuario());
        this.recuperarUsuarios();
        return "/Usuario/ListarUsuarios?faces-redirect=true";
    }

    public String Alterar() {
        if (this.usuarioLogado.getTipouser() == 'A' || this.usuarioLogado.equals(this.usuario)) {
            senhavalidada = ValidaSenha.encripta(this.usuario.getSenha());
            this.usuario.setSenha(senhavalidada);
            this.usuario.setPrimeiroacesso(false);
            getUsuarioFachada().Alterar(getUsuario());
            this.recuperarUsuarios();
            return "/Usuario/ListarUsuarios?faces-redirect=true";
        } else {
            this.erroAlterar();
            return null;
        }
    }

    public String alterarSenha() {
        senhavalidada = ValidaSenha.encripta(this.usuarioLogado.getSenha());
        this.usuarioLogado.setSenha(senhavalidada);
        this.usuarioLogado.setPrimeiroacesso(false);
        getUsuarioFachada().Alterar(this.usuarioLogado);
        return "/homeAdmin";
    }

    private void recuperarUsuarios() {
        setUsuarioFiltro(getUsuarioFachada().Listar());
        this.setUsuarios(getUsuarioFachada().Listar());
    }

    public String logar() {
        this.logou = false;
        this.getUsuario().setUsuario(idUsuario);
        this.getUsuario().setSenha(ValidaSenha.encripta(password));
        usuarioLogado = usuarioFachada.logar(this.getUsuario());
        if (usuarioLogado != null) {
            this.logou = true;
            if (usuarioLogado.getPrimeiroacesso() == false) {
                return "/homeAdmin";
            } else {
                return "/Usuario/AlterarSenha?faces-redirect=true";
            }
        } else {
            this.erroLogar();
            return null;
        }
    }
    
    public void deslogar(){
        this.setLogou(false);
    }

    public void verificaLogin() throws IOException {
        if (!isLogou()) {
            FacesContext.getCurrentInstance().getExternalContext().redirect(".././faces/homePage.xhtml");
        }
    }

    public String listar() {
        this.recuperarUsuarios();
        return "/Usuario/ListarUsuarios?faces-redirect=true";
    }

    public String recuperarSenha() throws Exception {
        this.getUsuario().setUsuario(idUsuario);
        if (this.getUsuario() != null) {
            senhagerada = senha.gerarSenhaAleatoria();
            Usuario usuarioSenha = this.usuarioFachada.recuperarUsuario(this.usuario);
            if (usuarioSenha != null) {
                usuarioSenha.setSenha(senhagerada);
                this.usuarioFachada.recuperarSenha(usuarioSenha);
                usuarioSenha.setSenha(ValidaSenha.encripta(senhagerada));
                this.usuarioFachada.salvarSenhaGerada(usuarioSenha);
                this.addInfo();
            } else {
                return null;
            }

        } else {
            this.erroRecuperar();
            return null;
        }
        return null;
    }

    public void addInfo() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "A senha foi enviada para o email informado!", ""));
    }

    public void erroRecuperar() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Para recuperar a senha é necessário informar o usuário!", ""));
    }

    public void erroLogar() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Autenticar no Sistema, Favor confir acessos!", ""));
    }
    
    public void erroAlterar() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alteração permitida apenas para seu usuário!", ""));
    }

    public void pesquisar() throws Exception {
        usuarioInput.processUpdates(FacesContext.getCurrentInstance());
        System.out.println("NOME " + usuarioInput.getSubmittedValue());
        usuarioInput.setValue(usuarioInput.getSubmittedValue());
        idUsuario = usuarioInput.getSubmittedValue().toString();
        this.recuperarSenha();
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
     * @return the Uusuarios
     */
    public List<Usuario> getUsuarios() {
        this.recuperarUsuarios();
        return Usuarios;
    }

    /**
     * @param Usuarios the Usuarios to set
     */
    public void setUsuarios(List<Usuario> Usuarios) {
        this.Usuarios = Usuarios;
    }

    /**
     * @return the UsuarioFiltro
     */
    public List<Usuario> getUsuarioFiltro() {
        return UsuarioFiltro;
    }

    /**
     * @param UsuarioFiltro the UsuarioFiltro to set
     */
    public void setUsuarioFiltro(List<Usuario> UsuarioFiltro) {
        this.UsuarioFiltro = UsuarioFiltro;
    }

    /**
     * @return the itemFachada
     */
    public UsuarioFachada getUsuarioFachada() {
        return usuarioFachada;
    }

    /**
     * @param usuarioFachada the usuarioFachada to set
     */
    public void setUsuarioFachada(UsuarioFachada usuarioFachada) {
        this.usuarioFachada = usuarioFachada;
    }

    public void imprimePDF(Object document) throws IOException, BadElementException, DocumentException {
        impressaoManagedBean.preProcessPDF(document, "Lista de Usuários");
    }

    public void imprimeXLS(Object document) {
        impressaoManagedBean.gerarXLS(document);
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the idUsuario
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the mensagemErro
     */
    public String getMensagemErro() {
        return mensagemErro;
    }

    /**
     * @param mensagemErro the mensagemErro to set
     */
    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    /**
     * @return the logou
     */
    public boolean isLogou() {
        return logou;
    }

    /**
     * @param logou the logou to set
     */
    public void setLogou(boolean logou) {
        this.logou = logou;
    }

    /**
     * @return the senha
     */
    public gerarSenha getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(gerarSenha senha) {
        this.senha = senha;
    }

    /**
     * @return the usuarioLogado
     */
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    /**
     * @param usuarioLogado the usuarioLogado to set
     */
    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    /**
     * @return the usuarioInput
     */
    public HtmlInputText getUsuarioInput() {
        return usuarioInput;
    }

    /**
     * @param usuarioInput the usuarioInput to set
     */
    public void setUsuarioInput(HtmlInputText usuarioInput) {
        this.usuarioInput = usuarioInput;
    }

    /**
     * @return the senhaInput
     */
    public HtmlInputText getSenhaInput() {
        return senhaInput;
    }

    /**
     * @param senhaInput the senhaInput to set
     */
    public void setSenhaInput(HtmlInputText senhaInput) {
        this.senhaInput = senhaInput;
    }

}
