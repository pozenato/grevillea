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
import negocio.entidade.Decoracao;
import negocio.entidade.Evento;
import negocio.entidade.Item;


/**
 *
 * @author pozenato
 */
@Stateless
public class DecoracaoDAO {
    

    @PersistenceContext
    private EntityManager em;

    public void Inserir(Decoracao decoracao) {
        em.persist(decoracao);
        em.flush();
    }
    
    public List<Decoracao> RecuperarTodos(){
         return em.createQuery("SELECT d FROM Decoracao d ").getResultList();
    } 
        
    public List<Item> BuscarItemPorEvento (Evento evento) {
        try {
          Query query = em.createQuery("SELECT i FROM Decoracao d, Item i WHERE d.idevento = :pEvento "
                                       + "and i.iditem = d.iditem ORDER BY i.iditem");
            query.setParameter("pEvento", evento.getIdevento());
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Item> BuscarItemQueNaoPertenceEvento (Evento evento) {
        try {
          Query query = em.createQuery("SELECT i FROM Item i WHERE i.iditem not in "
                                       + "(SELECT d.iditem From Decoracao d WHERE d.idevento = :pEvento)"
                                       + " AND i.status = True ORDER BY i.iditem");
            query.setParameter("pEvento", evento.getIdevento());
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    public Boolean removerDecoracaoDoEvento (Evento evento) {
        try {
            Query query = em.createQuery("delete from Decoracao d where d.idevento = :pEvento");
            query.setParameter("pEvento", evento.getIdevento());
            query.executeUpdate();
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
