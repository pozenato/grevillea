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
import negocio.entidade.TerceiroTipo;
import negocio.entidade.TipoColaborador;
import negocio.persistecia.TipoTerceiroDAO;
/**
 *
 * @author pozenato
 */
@Stateless
public class TipoTerceiroFachada {

    private TerceiroTipo tipoTerceiro = new TerceiroTipo();
    
    @EJB
    private TipoTerceiroDAO tipoTerceiroDAO;
    
    public void Inserir(TerceiroTipo terceiroTipo) {;
        getTipoTerceiroDAO().Inserir(terceiroTipo);
    }

    public List<TerceiroTipo> Listar() {
        return getTipoTerceiroDAO().RecuperarTodos();
    }   
    
    public TerceiroTipo listarPorId (int id){
        return getTipoTerceiroDAO().RecuperarPorID(id);
    }

    /**
     * @return the tipoTerceiro
     */
    public TerceiroTipo getTipoTerceiro() {
        return tipoTerceiro;
    }

    /**
     * @param tipoTerceiro the tipoTerceiro to set
     */
    public void setTipoTerceiro(TerceiroTipo tipoTerceiro) {
        this.tipoTerceiro = tipoTerceiro;
    }

    /**
     * @return the tipoTerceiroDAO
     */
    public TipoTerceiroDAO getTipoTerceiroDAO() {
        return tipoTerceiroDAO;
    }

    /**
     * @param tipoTerceiroDAO the tipoTerceiroDAO to set
     */
    public void setTipoTerceiroDAO(TipoTerceiroDAO tipoTerceiroDAO) {
        this.tipoTerceiroDAO = tipoTerceiroDAO;
    }
   

}
