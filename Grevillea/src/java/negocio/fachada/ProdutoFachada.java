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
import negocio.entidade.Produto;
import negocio.persistecia.ProdutoDAO;

/**
 *
 * @author pozenato
 */
@Stateless
public class ProdutoFachada {
    @EJB
    private ProdutoDAO produtoDAO;

    public void Inserir(Produto produto){        
        produtoDAO.Inserir(produto);
    }
    
    public List<Produto> Listar (){
        return produtoDAO.RecuperarTodos();        
    }   
    
}
