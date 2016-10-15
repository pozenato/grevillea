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
import negocio.entidade.Colaborador;
import negocio.entidade.Evento;
import negocio.entidade.EventoColaborador;


/**
 *
 * @author pozenato
 */
@Stateless
public class EventoColaboradorDAO {
    

    @PersistenceContext
    private EntityManager em;

    public void Inserir(EventoColaborador eventoColaborador) {
        em.persist(eventoColaborador);
        em.flush();
    }
    
    public List<EventoColaborador> RecuperarTodos(){
         return em.createQuery("SELECT e FROM EventoColaborador e ").getResultList();
    } 
        
    public List<Colaborador> BuscarColaboradorPorEvento (Evento evento) {
        try {
          Query query = em.createQuery("SELECT c FROM EventoColaborador ec, Colaborador c WHERE ec.idevento = :pEvento "
                                       + "and c.idcolaborador = ec.idcolaborador ORDER BY c.idcolaborador");
            query.setParameter("pEvento", evento.getIdevento());
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Colaborador> BuscarColaboradorQueNaoPertenceEvento (Evento evento) {
        try {
          Query query = em.createQuery("SELECT c FROM Colaborador c WHERE c.status = True and c.idcolaborador not in "
                                       + "(SELECT ec.idcolaborador From EventoColaborador ec WHERE ec.idevento = :pEvento)"
                                       + " ORDER BY c.idcolaborador");
            query.setParameter("pEvento", evento.getIdevento());
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    public Boolean removerColaboradoresDoEvento (Evento evento) {
        try {
            Query query = em.createQuery("delete from EventoColaborador c where c.idevento = :pEvento");
            query.setParameter("pEvento", evento.getIdevento());
            query.executeUpdate();
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
