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
import negocio.entidade.Produto;

/**
 *
 * @author pozenato
 */
@Stateless
public class ProdutoDAO {
    

    @PersistenceContext
    private EntityManager em;

    public void Inserir(Produto produto) {
        em.persist(produto);
    }
    
    public List<Produto> RecuperarTodos(){
         return em.createQuery("select p from Produto as p order by p.nomeproduto").getResultList();
    } 
    
    public Produto recuperarPorId(Integer id) {
        return em.find(Produto.class, id);
    }

    public void Alterar(Produto produto) {
        em.merge(produto);
    }

}
