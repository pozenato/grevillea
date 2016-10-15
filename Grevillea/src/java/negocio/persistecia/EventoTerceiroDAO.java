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
import negocio.entidade.Terceiro;
import negocio.entidade.Evento;
import negocio.entidade.EventoTerceiro;


/**
 *
 * @author pozenato
 */
@Stateless
public class EventoTerceiroDAO {
    

    @PersistenceContext
    private EntityManager em;

    public void Inserir(EventoTerceiro eventoTerceiro) {
        em.persist(eventoTerceiro);
        em.flush();
    }
    
    public List<EventoTerceiro> RecuperarTodos(){
         return em.createQuery("SELECT e FROM EventoTerceiro e").getResultList();
    } 
        
    public List<Terceiro> BuscarTerceirosPorEvento (Evento evento) {
        try {
          Query query = em.createQuery("SELECT t FROM Terceiro t, EventoTerceiro et "
                                       + "WHERE t.idterceiro = et.idterceiro "
                                       + "AND et.idevento = :pEvento ORDER BY t.idterceiro");
            query.setParameter("pEvento", evento.getIdevento());
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
     public List<Terceiro> BuscarTerceirosNaoPertenceEvento (Evento evento) {
        try {
          Query query = em.createQuery("SELECT t FROM Terceiro t WHERE t.idterceiro not in "
                                       + "(SELECT et.idterceiro From EventoTerceiro et WHERE et.idevento = :pEvento)"
                                       + "AND t.status = True ORDER BY t.idterceiro");
            query.setParameter("pEvento", evento.getIdevento());
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    public Boolean removerTerceirosDoEvento (Evento evento) {
        try {
            Query query = em.createQuery("delete from EventoTerceiro e where e.idevento = :pEvento");
            query.setParameter("pEvento", evento.getIdevento());
            query.executeUpdate();
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
