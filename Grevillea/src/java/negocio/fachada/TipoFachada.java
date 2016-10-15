/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.fachada;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import negocio.entidade.Tipo;
import negocio.persistecia.FluxoDAO;
import negocio.persistecia.FornecedorDAO;
import negocio.persistecia.ProdutoDAO;
import negocio.persistecia.TipoDAO;

/**
 *
 * @author pozenato
 */
@Stateless
public class TipoFachada {

    private Tipo tipo = new Tipo();
    private String nomeCorrigido = new String();
    @EJB
    private TipoDAO tipoDAO;
    
    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public void Inserir(Tipo tipo) {;
        tipoDAO.Inserir(tipo);
    }

    public List<Tipo> Listar() {
        return tipoDAO.RecuperarTodos();
    }   
    
    public Tipo listarPorId (int id){
        return tipoDAO.RecuperarPorID(id);
    }
      
    /**
     * @return the nomeCorrigido
     */
    public String getNomeCorrigido() {
        return nomeCorrigido;
    }

    /**
     * @param nomeCorrigido the nomeCorrigido to set
     */
    public void setNomeCorrigido(String nomeCorrigido) {
        this.nomeCorrigido = nomeCorrigido;
    }

}
