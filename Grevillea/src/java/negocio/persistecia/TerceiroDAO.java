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
import negocio.entidade.Terceiro;

/**
 *
 * @author pozenato
 */
@Stateless
public class TerceiroDAO {
    

    @PersistenceContext
    private EntityManager em;

    public void Inserir(Terceiro terceiro) {
        em.persist(terceiro);
    }
    
    public List<Terceiro> RecuperarTodos(){
         return em.createQuery("select t from Terceiro as t order by t.idterceiro").getResultList();
    } 

    public void Alterar(Terceiro terceiro) {
        em.merge(terceiro);
    }

}
