/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.persistecia;

/**
 *
 * @author pozenato
 */
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import negocio.entidade.Item;

/**
 *
 * @author pozenato
 */
@Stateless
public class ItemDAO {
    
    @PersistenceContext
    private EntityManager em;

    public void Inserir(Item item) {
        em.persist(item);
    }
    
    public void Alterar(Item item) {
        em.merge(item);
    }
    
    public List<Item> RecuperarTodos(){
         return em.createQuery("select i from Item as i order by i.iditem").getResultList();
    } 

}
