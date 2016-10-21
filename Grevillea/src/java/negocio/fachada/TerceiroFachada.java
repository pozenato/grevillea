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
import negocio.entidade.Evento;
import negocio.entidade.Terceiro;
import negocio.persistecia.EventoTerceiroDAO;
import negocio.persistecia.TerceiroDAO;

/**
 *
 * @author pozenato
 */
@Stateless
public class TerceiroFachada {
    @EJB
    private TerceiroDAO terceiroDAO;
    
    @EJB
    private EventoTerceiroDAO eventoTerceiroDAO;

    public void Inserir(Terceiro terceiro){
        terceiroDAO.Inserir(terceiro);
    }
    
    public void Alterar (Terceiro terceiro){
        terceiroDAO.Alterar(terceiro);
    }
            
    
    public List<Terceiro> Listar (){
        return terceiroDAO.RecuperarTodos();        
    }
    
    public List<Terceiro> ListarNaoEvento (Evento evento){
        return eventoTerceiroDAO.BuscarTerceirosNaoPertenceEvento(evento);
    }
    
    public List<Terceiro> ListarTerceirosEvento (Evento evento){
        return eventoTerceiroDAO.BuscarTerceirosPorEvento(evento);
    }

    /**
     * @return the eventoTerceiroDAO
     */
    public EventoTerceiroDAO getEventoTerceiroDAO() {
        return eventoTerceiroDAO;
    }

    /**
     * @param eventoTerceiroDAO the eventoTerceiroDAO to set
     */
    public void setEventoTerceiroDAO(EventoTerceiroDAO eventoTerceiroDAO) {
        this.eventoTerceiroDAO = eventoTerceiroDAO;
    }

    public void ExcluirPorEvento(Evento evento) {
        this.getEventoTerceiroDAO().removerTerceirosDoEvento(evento);
    }
}
