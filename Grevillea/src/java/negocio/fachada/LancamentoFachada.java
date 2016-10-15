/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.fachada;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import negocio.entidade.Evento;
import negocio.entidade.Lancamento;
import negocio.persistecia.LancamentoDAO;

/**
 *
 * @author pozenato
 */
@Stateless
public class LancamentoFachada {
    @EJB
    private LancamentoDAO lancamentoDAO;

    public void Inserir(Lancamento lancamento){
        lancamentoDAO.Inserir(lancamento);
    }
    
    public List<Lancamento> Listar (){
        return lancamentoDAO.RecuperarTodos();        
    }
    
    public List<Lancamento> ListarParaPagamento (Evento evento) {
        return lancamentoDAO.RecuperarPorIdEvento(evento);
    }

    public void ConfirmarPagamento(Lancamento lancamento) {
        lancamentoDAO.ConfirmarPagamento(lancamento);
    }
    
    public List<Lancamento> ListarRecebimentoPorData(Date dataInit, Date dateFim) {
        return this.lancamentoDAO.ListarPagamentoPorData(dataInit, dateFim);
    }
    
}
