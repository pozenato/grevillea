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
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import negocio.entidade.Evento;


/**
 *
 * @author pozenato
 */
@Stateless
public class EventoDAO {
    
    @PersistenceContext
    private EntityManager em;

    public void Inserir(Evento evento) {
        em.persist(evento);
    }
    
    public void Alterar(Evento evento) {
        em.merge(evento);
    }
    
    public void Excluir(Evento evento) {
        Evento eventoM = em.merge(evento);
        em.remove(eventoM);
    }
        
    public List<Evento> RecuperarTodos(){
         return em.createQuery("select e from Evento as e order by e.idevento").getResultList();
    } 
    
    public List<Evento> RecuperarEventoDetalhe(){
         return em.createQuery("select e from Evento as e WHERE e.ativo = 'C' or e.ativo = 'F' order by e.idevento desc").getResultList();
    } 
    
    public List<Evento> RecuperarConfirmados(){
         return em.createQuery("select e from Evento as e WHERE e.ativo = 'C' order by e.idevento desc").getResultList();
    } 
    
    public Evento verificarSeADataJaFoiCadastrada (Date data){
        try {
          Query query = em.createQuery("SELECT e FROM Evento as e WHERE e.iddata = :datapar");
            query.setParameter("datapar", data);
            return (Evento) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public Evento consultarClienteSchedule (Date data){
        try {
          Query query = em.createQuery("SELECT e FROM Evento as e WHERE e.iddata = :datapar");
            query.setParameter("datapar", data);
            return (Evento) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}