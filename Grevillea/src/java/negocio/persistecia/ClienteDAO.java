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
import negocio.entidade.Cliente;


/**
 *
 * @author pozenato
 */
@Stateless
public class ClienteDAO {
    

    @PersistenceContext
    private EntityManager em;

    public void Inserir(Cliente cliente) {
        em.persist(cliente);
    }
    
    public List<Cliente> RecuperarTodos(){
         return em.createQuery("select c from Cliente as c ").getResultList();
    } 
    
    public List<Cliente> buscaClientePorNome(Cliente cliente) {
        try {
          Query query = em.createQuery("SELECT c FROM Cliente c WHERE UPPER(c.nome) LIKE :keyword ORDER BY c.nome");
            query.setParameter("keyword", "%" + cliente.getNome().toUpperCase() + "%");
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public void Alterar(Cliente cliente) {
        em.merge(cliente);
    }

}
