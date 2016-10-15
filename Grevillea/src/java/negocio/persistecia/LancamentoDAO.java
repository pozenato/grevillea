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
import negocio.entidade.Lancamento;

/**
 *
 * @author pozenato
 */
@Stateless
public class LancamentoDAO {
    
    @PersistenceContext
    private EntityManager em;

    public void Inserir(Lancamento lancamento) {
        em.persist(lancamento);
    }
    
    public List<Lancamento> RecuperarTodos(){
         return em.createQuery("select l from Lancamento as l order by l.idlancamento").getResultList();
    } 
    
     public List<Lancamento> RecuperarPorIdEvento(Evento evento){
         Query query = em.createQuery("SELECT l FROM Lancamento as l WHERE l.evento = :id ORDER BY l.idlancamento");
            query.setParameter("id", evento);
            return query.getResultList();
    } 
     
    public List<Lancamento> ListarPagamentoPorData(Date dataInit, Date dateFim) {
         try {
          Query query = em.createQuery("SELECT l FROM Lancamento l WHERE l.datarecebimento BETWEEN :dataInit AND :dataFim ORDER BY l.datarecebimento");
            query.setParameter("dataInit" , dataInit);
            query.setParameter("dataFim", dateFim);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public void ConfirmarPagamento(Lancamento lancamento) {
        em.merge(lancamento);
    }

}