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

    public List<Lancamento> RecuperarTodos() {
        return em.createQuery("select l from Lancamento as l order by l.idlancamento").getResultList();
    }

    public List<Lancamento> RecuperarPorIdEvento(Evento evento) {
        Query query = em.createQuery("SELECT l FROM Lancamento as l WHERE l.evento = :id ORDER BY l.idlancamento");
        query.setParameter("id", evento);
        return query.getResultList();
    }

    public List<Lancamento> ListarPagamentoPorData(Date dataInit, Date dateFim) {
        try {
            Query query = em.createQuery("SELECT l FROM Lancamento l WHERE l.datarecebimento BETWEEN :dataInit AND :dataFim ORDER BY l.datarecebimento");
            query.setParameter("dataInit", dataInit);
            query.setParameter("dataFim", dateFim);
            return query.getResultList();
        } catch (Exception e) {
            throw e;
        }
    }

    public void ConfirmarPagamento(Lancamento lancamento) {
        em.merge(lancamento);
    }

    public List<Lancamento> ListarPagamentoPrevistoPorData(Date dataInit, Date dataFim) {
        try {
            Query query = em.createQuery("SELECT l FROM Lancamento l WHERE l.dataprevisao BETWEEN :dataInit AND :dataFim AND l.usuariorecebimento = null ORDER BY l.idlancamento ASC");
            query.setParameter("dataInit", dataInit);
            query.setParameter("dataFim", dataFim);
            return query.getResultList();
        } catch (Exception e) {
            throw e;
        }
    }

    public Boolean removerLancamentoDoEvento(Evento evento) {
        try {
            Query query = em.createQuery("delete from Lancamento l where l.evento = :pEvento");
            query.setParameter("pEvento", evento);
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
