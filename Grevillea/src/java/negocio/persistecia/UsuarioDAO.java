/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.persistecia;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import negocio.entidade.Usuario;

/**
 *
 * @author pozenato
 */
@Stateless
public class UsuarioDAO {
    

    @PersistenceContext
    private EntityManager em;

    public void Inserir(Usuario usuario) {
        em.persist(usuario);
    }
    
    public void Alterar(Usuario usuario) {
        em.merge(usuario);
    }
    
    public List<Usuario> RecuperarTodos(){
         return em.createQuery("select u from Usuario as u order by u.idUsuario").getResultList();
    } 
    
    public Usuario logar (Usuario usuarioRecuperado){
         try {
            Query query = em.createNamedQuery("Usuario.findByUsuario");
            String usuario = usuarioRecuperado.getUsuario();
            query.setParameter("usuario", usuario);
            Usuario usuarioRetorno = (Usuario) query.getSingleResult();

            if (usuarioRetorno != null &&  usuarioRecuperado.getSenha().equals(usuarioRetorno.getSenha())) {
                return usuarioRetorno;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    public Usuario recuperarUsuario (Usuario usuarioRecuperado){
         try {
            Query query = em.createNamedQuery("Usuario.findByUsuario");
            String usuario = usuarioRecuperado.getUsuario();
            query.setParameter("usuario", usuario);
            return (Usuario) query.getSingleResult();

        } catch (Exception e) {
            return null;
        }
    }
    
    public void recuperarSenha (Usuario usuario){
        em.merge(usuario);
    }

}
