/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.fachada;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import negocio.entidade.Cliente;
import negocio.entidade.Colaborador;
import negocio.entidade.Decoracao;
import negocio.entidade.Evento;
import negocio.entidade.EventoColaborador;
import negocio.entidade.EventoTerceiro;
import negocio.entidade.Item;
import negocio.entidade.Terceiro;
import negocio.excecao.CampoUniqueException;
import negocio.persistecia.DecoracaoDAO;
import negocio.persistecia.EventoColaboradorDAO;
import negocio.persistecia.EventoDAO;
import negocio.persistecia.EventoTerceiroDAO;

/**
 *
 * @author pozenato
 */
@Stateless
public class EventoFachada {

    private static final String DATA_E_INFORMADA_JA_AGENDADA = "A Data Informada já foi Pré Agendada!";
    private static final String DATA_MENOR_QUE_HOJE = "A Data informada é menor que a data atual!";
    private static final String AGENDAMENTO_DEFINITIVO = "A Data Informa já foi Confirmada para outro Evento!";
    private static final String EXCLUSAO_RECUSADA = "Evento Confirmado! Exclusão não permitida!";
    private static final String ERRO_EXCLUSAO = "Ocorreu um erro durante o processo de Exclusão. Entre em contato com o Administrador!";
    
    /**
     * @return the DATA_E_INFORMADA_JA_AGENDADA
     */
    public static String getDATA_E_INFORMADA_JA_AGENDADA() {
        return DATA_E_INFORMADA_JA_AGENDADA;
    }

    public static String get_DATA_MENOR_QUE_HOJE() {
        return DATA_MENOR_QUE_HOJE;
    }

    public static String get_AGENDAMENTO_DEFINITIVO() {
        return AGENDAMENTO_DEFINITIVO;
    }

    /**
     * @return the EXCLUSAO_RECUSADA
     */
    public static String getEXCLUSAO_RECUSADA() {
        return EXCLUSAO_RECUSADA;
    }

    /**
     * @return the ERRO_EXCLUSAO
     */
    public static String getERRO_EXCLUSAO() {
        return ERRO_EXCLUSAO;
    }

    @EJB
    private EventoDAO eventoDAO;

    @EJB
    private EventoColaboradorDAO eventoColaboradorDAO;

    @EJB
    private EventoTerceiroDAO eventoTerceiroDAO;
    
    @EJB
    private DecoracaoDAO decoracaoDAO;

    public void Inserir(Evento evento) {
        eventoDAO.Inserir(evento);
    }

    public void Alterar(Evento evento) {
        eventoDAO.Alterar(evento);
    }

    public void Excluir(Evento evento) throws CampoUniqueException {
        if (evento.getAtivo() == 'C') {
            throw new CampoUniqueException(getEXCLUSAO_RECUSADA());
        } else {
            if (eventoColaboradorDAO.removerColaboradoresDoEvento(evento)
                    && eventoTerceiroDAO.removerTerceirosDoEvento(evento)) {
                eventoDAO.Excluir(evento);
            } else {
                throw new CampoUniqueException(getERRO_EXCLUSAO());
            }
        }
    }

    public List<Evento> Listar() {
        return eventoDAO.RecuperarTodos();
    }
    
    public List<Item> ListarDecoracaoEvento (Evento evento){
        return decoracaoDAO.BuscarItemPorEvento(evento);
    }
    
    public List<Item> ListarDecoracaoNaoPertenceEvento (Evento evento){
        return decoracaoDAO.BuscarItemQueNaoPertenceEvento(evento);
    }

    public void InserirColaborador(List<Colaborador> colaboradores, Evento evento) throws CampoUniqueException {
        if (eventoColaboradorDAO.removerColaboradoresDoEvento(evento)) {
            try {
                for (Colaborador colab : colaboradores) {   
                    EventoColaborador eventoColaborador = new EventoColaborador();
                    eventoColaborador.setIdcolaborador(colab.getIdcolaborador());
                    eventoColaborador.setIdevento(evento.getIdevento());
                    eventoColaboradorDAO.Inserir(eventoColaborador);
                }
            } catch (Exception e) {
                throw e;
            }
        } else {
            throw new CampoUniqueException(getERRO_EXCLUSAO());
        }
    }
    
    public void InserirTerceiros(List<Terceiro> terceiros, Evento evento) throws CampoUniqueException {
        if (eventoTerceiroDAO.removerTerceirosDoEvento(evento)) {
            try {
                for (Terceiro terceiro : terceiros) {   
                    EventoTerceiro eventoTerceiro = new EventoTerceiro();
                    eventoTerceiro.setIdterceiro(terceiro.getIdterceiro());
                    eventoTerceiro.setIdevento(evento.getIdevento());
                    eventoTerceiroDAO.Inserir(eventoTerceiro);
                }
            } catch (Exception e) {
                throw e;
            }
        } else {
            throw new CampoUniqueException(getERRO_EXCLUSAO());
        }
    }
    
    public void InserirDecoracao(List<Item> itens, Evento evento) throws CampoUniqueException {
        if (decoracaoDAO.removerDecoracaoDoEvento(evento)) {
            try {
                for (Item item : itens) {   
                    Decoracao decoracao = new Decoracao();
                    decoracao.setIditem(item.getIditem());
                    decoracao.setIdevento(evento.getIdevento());
                    decoracaoDAO.Inserir(decoracao);
                }
            } catch (Exception e) {
                throw e;
            }
        } else {
            throw new CampoUniqueException(getERRO_EXCLUSAO());
        }
    }

    public List<Evento> ListarConfirmados() {
        return eventoDAO.RecuperarConfirmados();
    }

    public Evento consultarClienteSchedule(Date data) {
        return eventoDAO.consultarClienteSchedule(data);
    }

    /**
     *
     * @throws CampoUniqueException
     */
    public void verificarSeDataMaiorQueHoje() throws CampoUniqueException {
        throw new CampoUniqueException(get_DATA_MENOR_QUE_HOJE());
    }

    public void verificarSeADataJaFoiCadastrada(Date data) throws CampoUniqueException {
        Evento evento = this.eventoDAO.verificarSeADataJaFoiCadastrada(data);
        if (evento != null) {
            if (evento.getAtivo() == 'C') {
                throw new CampoUniqueException(get_AGENDAMENTO_DEFINITIVO());
            }
            throw new CampoUniqueException(getDATA_E_INFORMADA_JA_AGENDADA());
        }

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

    /**
     * @return the decoracaoDAO
     */
    public DecoracaoDAO getDecoracaoDAO() {
        return decoracaoDAO;
    }

    /**
     * @param decoracaoDAO the decoracaoDAO to set
     */
    public void setDecoracaoDAO(DecoracaoDAO decoracaoDAO) {
        this.decoracaoDAO = decoracaoDAO;
    }

}
