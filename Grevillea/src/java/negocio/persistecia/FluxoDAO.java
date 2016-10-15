/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.persistecia;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import negocio.entidade.Fluxocaixa;


/**
 *
 * @author pozenato
 */
@Stateless
public class FluxoDAO {
    

    @PersistenceContext
    private EntityManager em;

    public void Inserir(Fluxocaixa fluxo) {
        em.persist(fluxo);
    }
    
    public List<Fluxocaixa> RecuperarTodos(){
         return em.createQuery("SELECT f FROM Fluxocaixa f ").getResultList();
    } 
    
    /*
    public List<Fornecedor> buscaFornecedorPorNome(Fornecedor fornecedor) {
        try {
          Query query = em.createQuery("SELECT f FROM Fornecedor f WHERE UPPER(f.nomefornecedor) LIKE :keyword ORDER BY f.nomefornecedor");
            query.setParameter("keyword", "%" + fornecedor.getNomefornecedor().toUpperCase() + "%");
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    } */

    public List<Fluxocaixa> ListarDespesasPorData(Date dataInit, Date dateFim) {
         try {
          Query query = em.createQuery("SELECT f FROM Fluxocaixa f WHERE f.datadespesa BETWEEN :dataInit AND :dataFim ORDER BY f.datadespesa");
            query.setParameter("dataInit" , dataInit);
            query.setParameter("dataFim", dateFim);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
