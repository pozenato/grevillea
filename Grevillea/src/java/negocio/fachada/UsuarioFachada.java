/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.fachada;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import negocio.entidade.Usuario;
import negocio.persistecia.UsuarioDAO;
import util.JavaMail;
/**
 *
 * @author pozenato
 */
@Stateless
public class UsuarioFachada {
    
    
    @EJB
    private UsuarioDAO usuarioDAO; 
    private JavaMail email = new JavaMail();

    public void Inserir(Usuario usuario){
        usuarioDAO.Inserir(usuario);
    }
    
    public void Alterar(Usuario usuario){
        usuarioDAO.Alterar(usuario);
    }
    
    public List<Usuario> Listar (){
        return usuarioDAO.RecuperarTodos();        
    }
    
    public Usuario logar (Usuario usuario){
       return usuarioDAO.logar(usuario);
    }
    
    public void recuperarSenha (Usuario usuario) throws Exception{
        this.getEmail().emailSenha(usuario);        
    }
    
    public void salvarSenhaGerada (Usuario usuario){
        usuario.setPrimeiroacesso(true);
        this.usuarioDAO.recuperarSenha(usuario);
    }
        
    public Usuario recuperarUsuario(Usuario usuario) {
        Usuario usuarioFa = this.usuarioDAO.recuperarUsuario(usuario);
        if (usuarioFa != null) {
            return usuarioFa;
        } else {
            this.addInfo();
            return null;
        }
    }
    
    public void addInfo() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuário informado não encontrado!", ""));
    }

    /**
     * @return the email
     */
    public JavaMail getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(JavaMail email) {
        this.email = email;
    }
}
