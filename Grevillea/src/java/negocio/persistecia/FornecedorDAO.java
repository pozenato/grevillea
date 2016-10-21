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
import negocio.entidade.Fornecedor;


/**
 *
 * @author pozenato
 */
@Stateless
public class FornecedorDAO {
    

    @PersistenceContext
    private EntityManager em;

    public void Inserir(Fornecedor fornecedor) {
        em.persist(fornecedor);
    }
    
    public List<Fornecedor> RecuperarTodos(){
         return em.createQuery("select f from Fornecedor as f order by f.nomefornecedor").getResultList();
    } 
    
    public List<Fornecedor> buscaFornecedorPorNome(Fornecedor fornecedor) {
        try {
          Query query = em.createQuery("SELECT f FROM Fornecedor f WHERE UPPER(f.nomefornecedor) LIKE :keyword ORDER BY f.nomefornecedor");
            query.setParameter("keyword", "%" + fornecedor.getNomefornecedor().toUpperCase() + "%");
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    public Fornecedor recuperarPorId(Integer id) {
        return em.find(Fornecedor.class, id);
    }

    public void Alterar(Fornecedor fornecedor) {
        em.merge(fornecedor);
    }

}
