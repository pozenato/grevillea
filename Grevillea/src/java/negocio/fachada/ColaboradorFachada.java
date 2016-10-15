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
import negocio.entidade.Colaborador;
import negocio.entidade.Evento;
import negocio.persistecia.ColaboradorDAO;
import negocio.persistecia.EventoColaboradorDAO;

/**
 *
 * @author pozenato
 */
@Stateless
public class ColaboradorFachada {
    @EJB
    private ColaboradorDAO colaboradorDAO;
    
    @EJB
    private EventoColaboradorDAO eventoColaboradorDAO;

    public void Inserir(Colaborador colaborador){
        colaboradorDAO.Inserir(colaborador);
    }
    
    public void Alterar (Colaborador colaborador){
        colaboradorDAO.Alterar(colaborador);
    }
    
    public List<Colaborador> Listar (){
        return colaboradorDAO.RecuperarTodos();        
    }
    
    public List<Colaborador> ListarNaoEvento (Evento evento){
        return eventoColaboradorDAO.BuscarColaboradorQueNaoPertenceEvento(evento);        
    }
    
    public List<Colaborador> ListarColaboradoresEvento (Evento evento) {
        return eventoColaboradorDAO.BuscarColaboradorPorEvento(evento);
    }

    /**
     * @return the eventoColaboradorDAO
     */
    public EventoColaboradorDAO getEventoColaboradorDAO() {
        return eventoColaboradorDAO;
    }

    /**
     * @param eventoColaboradorDAO the eventoColaboradorDAO to set
     */
    public void setEventoColaboradorDAO(EventoColaboradorDAO eventoColaboradorDAO) {
        this.eventoColaboradorDAO = eventoColaboradorDAO;
    }
}
