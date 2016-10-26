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
import negocio.entidade.TerceiroTipo;


/**
 *
 * @author pozenato
 */
@Stateless
public class TipoTerceiroDAO {    

    @PersistenceContext
    private EntityManager em;

    public void Inserir(TerceiroTipo terceiroTipo) {
        em.persist(terceiroTipo);
    }
    
    public List<TerceiroTipo> RecuperarTodos(){
         return em.createQuery("SELECT t FROM TerceiroTipo t ").getResultList();
    } 
    
    public TerceiroTipo RecuperarPorID(int id){
         Query query = em.createQuery("SELECT t FROM TerceiroTipo t WHERE t.idtipo = :id ORDER BY t.idtipo");
            query.setParameter("id", id);
            return (TerceiroTipo) query.getSingleResult();
    }
       
    public void Alterar(TerceiroTipo terceiroTipo) {
        em.merge(terceiroTipo);
    }

}
