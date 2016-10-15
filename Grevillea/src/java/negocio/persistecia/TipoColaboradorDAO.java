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
import negocio.entidade.TipoColaborador;


/**
 *
 * @author pozenato
 */
@Stateless
public class TipoColaboradorDAO {
    

    @PersistenceContext
    private EntityManager em;

    public void Inserir(TipoColaborador tipoColaborador) {
        em.persist(tipoColaborador);
    }
    
    public List<TipoColaborador> RecuperarTodos(){
         return em.createQuery("SELECT t FROM TipoColaborador t ").getResultList();
    } 
    
    public TipoColaborador RecuperarPorID(int id){
         Query query = em.createQuery("SELECT t FROM TipoColaborador t WHERE t.idtipo = :id ORDER BY t.idtipo");
            query.setParameter("id", id);
            return (TipoColaborador) query.getSingleResult();
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

}
