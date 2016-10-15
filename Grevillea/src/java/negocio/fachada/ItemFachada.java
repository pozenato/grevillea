/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.fachada;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import negocio.entidade.Item;
import negocio.persistecia.ItemDAO;

/**
 *
 * @author pozenato
 */
@Stateless
public class ItemFachada {
    @EJB
    private ItemDAO itemDAO;

    public void Inserir(Item item){
        itemDAO.Inserir(item);
    }
    
    public void Alterar(Item item){
        itemDAO.Alterar(item);
    }
    
    public List<Item> Listar (){
        return itemDAO.RecuperarTodos();        
    }
}
