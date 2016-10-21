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
import negocio.entidade.TipoColaborador;
import negocio.persistecia.TipoColaboradorDAO;
/**
 *
 * @author pozenato
 */
@Stateless
public class TipoColaboradorFachada {

    private TipoColaborador tipoColaborador = new TipoColaborador();
    private String nomeCorrigido = new String();
    @EJB
    private TipoColaboradorDAO tipoColaboradorDAO;
    
    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public void Inserir(TipoColaborador tipoColaborador) {;
        tipoColaboradorDAO.Inserir(tipoColaborador);
    }

    public List<TipoColaborador> Listar() {
        return tipoColaboradorDAO.RecuperarTodos();
    }   
    
    public TipoColaborador listarPorId (int id){
        return tipoColaboradorDAO.RecuperarPorID(id);
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

    /**
     * @return the tipoColaborador
     */
    public TipoColaborador getTipoColaborador() {
        return tipoColaborador;
    }

    /**
     * @param tipoColaborador the tipoColaborador to set
     */
    public void setTipoColaborador(TipoColaborador tipoColaborador) {
        this.tipoColaborador = tipoColaborador;
    }

    /**
     * @return the tipoColaboradorDAO
     */
    public TipoColaboradorDAO getTipoColaboradorDAO() {
        return tipoColaboradorDAO;
    }

    /**
     * @param tipoColaboradorDAO the tipoColaboradorDAO to set
     */
    public void setTipoColaboradorDAO(TipoColaboradorDAO tipoColaboradorDAO) {
        this.tipoColaboradorDAO = tipoColaboradorDAO;
    }

    public void Alterar(TipoColaborador tipoColaborador) {
        tipoColaboradorDAO.Alterar(tipoColaborador);
    }

}
