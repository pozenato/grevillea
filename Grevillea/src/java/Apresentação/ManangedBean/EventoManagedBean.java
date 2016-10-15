/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentação.ManangedBean;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import negocio.entidade.Cliente;
import negocio.entidade.Colaborador;
import negocio.entidade.Evento;
import negocio.entidade.Item;
import negocio.entidade.Tipo;
import negocio.entidade.Lancamento;
import negocio.entidade.Terceiro;
import negocio.entidade.Usuario;
import negocio.excecao.CampoUniqueException;
import negocio.excecao.MensagemUtility;
import negocio.fachada.ClienteFachada;
import negocio.fachada.ColaboradorFachada;
import negocio.fachada.EventoFachada;
import negocio.fachada.ItemFachada;
import negocio.fachada.LancamentoFachada;
import negocio.fachada.TerceiroFachada;
import negocio.fachada.TipoFachada;
import negocio.fachada.UsuarioFachada;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author pozenato
 */
@ManagedBean(name = "eventoManagedBean")
@SessionScoped
public class EventoManagedBean implements java.io.Serializable {
    public static final String PROP_DATAFIM = "PROP_DATAFIM";

    private Evento evento = new Evento();
    private Lancamento lancamento = new Lancamento();
    private List<Cliente> clientes;
    private List<Cliente> clienteFiltro;
    private List<Evento> eventos = new ArrayList<>();
    private List<Evento> eventoFiltro = new ArrayList<>();
    private List<Evento> eventosConfirmados = new ArrayList<>();
    private List<Evento> eventoFiltroConfirmados = new ArrayList<>();
    private List<Lancamento> lancamentos = new ArrayList<>();
    private List<Lancamento> lancamentoFiltro = new ArrayList<>();
    private List<Colaborador> colaboradores = new ArrayList<>();
    private List<Colaborador> colaboradorFiltro = new ArrayList<>();
    private List<Colaborador> colaboradorFiltroAdd = new ArrayList<>();
    private List<Colaborador> colaboradoresAdd = new ArrayList<>();
    private List<Terceiro> terceiroFiltro = new ArrayList<>();
    private List<Terceiro> terceiroFiltroAdd = new ArrayList<>();
    private List<Terceiro> terceiros = new ArrayList<>();
    private List<Terceiro> terceirosAdd = new ArrayList<>();
    private List<Tipo> tipos = new ArrayList<>();
    private List<Item> itens = new ArrayList<>();
    private List<Item> itensFiltro = new ArrayList<>();
    private List<Item> itensFiltroADD = new ArrayList<>();
    private List<Item> itensAdd = new ArrayList<>();
    private Item item;
    private Date dataInit;
    private Date dataFim;
    private List<Lancamento> recebimentos;
    private boolean isAddColaborador = false;
    private boolean isAddTerceiro = false;
    private boolean isAddDecoracao = false;
    private int qteLancamento;
    public ImpressaoManagedBean impressaoManagedBean = new ImpressaoManagedBean();
    private int rendercliente;
    private int render;
    private boolean confirmaPagamento = false;
    private Cliente cliente = new Cliente();
    private Cliente clienteRecuperado = new Cliente();
    private Evento eventoSc = new Evento();
    private boolean ativo = false;
    private Usuario usuariologado;
    private Terceiro terceiro;
    private Tipo tipo = new Tipo();
    private Evento eventoPagamento = new Evento();
    private Colaborador colaborador = new Colaborador();
    private double valorTotalRecebimento;
    private double valorRecebidoEvento;
    private ScheduleModel eventModel;

    private ScheduleModel lazyEventModel;

    private ScheduleEvent event = new DefaultScheduleEvent();

    @EJB
    private ItemFachada itemFachada;

    @EJB
    private EventoFachada eventoFachada;

    @EJB
    private LancamentoFachada lancamentoFachada;

    @EJB
    private TipoFachada tipoFachada;

    @EJB
    private UsuarioFachada usuarioFachada;

    @EJB
    private ClienteFachada clienteFachada;

    @EJB
    private ColaboradorFachada colaboradorFachada;

    @EJB
    private TerceiroFachada terceiroFachada;

    @ManagedProperty(value = "#{usuarioManagedBean}")
    private UsuarioManagedBean usuarioBean;
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);

    public EventoManagedBean() {
    }

    public void init() {
        eventModel = new DefaultScheduleModel();
        this.eventos = new ArrayList<>();
        this.setEventos(eventoFachada.Listar());
        for (Evento e : eventos) {
            String tipoevento = tipoFachada.listarPorId(e.getTipo().getIdtipo()).getNometipo();
            eventModel.addEvent(new DefaultScheduleEvent(tipoevento, e.getIddata(), e.getDatacriacao(), true));
        }
    }

    public void addColaboradorEvento() {
        colaboradoresAdd.add(this.getColaborador());
        colaboradorFiltroAdd.add(this.getColaborador());
        colaboradores.remove(this.getColaborador());
        colaboradorFiltro.remove(this.getColaborador());
    }

    public void removeColaboradorEvento() {
        colaboradoresAdd.remove(this.getColaborador());
        colaboradorFiltroAdd.remove(this.getColaborador());
        colaboradores.add(this.getColaborador());
        colaboradorFiltro.add(this.getColaborador());
    }

    public void addTerceitoEvento() {
        terceirosAdd.add(this.getTerceiro());
        terceiroFiltroAdd.add(this.getTerceiro());
        terceiros.remove(this.getTerceiro());
        terceiroFiltro.remove(this.getTerceiro());
    }

    public void removeTerceiroEvento() {
        terceirosAdd.remove(this.getTerceiro());
        terceiroFiltroAdd.remove(this.getTerceiro());
        terceiros.add(this.getTerceiro());
        terceiroFiltro.add(this.getTerceiro());
    }

    public void addItemEvento() {
        itensAdd.add(this.getItem());
        itensFiltroADD.add(this.getItem());
        itens.remove(this.getItem());
        itensFiltro.remove(this.getItem());
    }

    public void removeItemEvento() {
        itensAdd.remove(this.getItem());
        itensFiltroADD.remove(this.getItem());
        itens.add(this.getItem());
        itensFiltro.add(this.getItem());
    }

    public String montarPaginaParaInsercao() {
        this.setEvento(new Evento());
        return "/Evento/ConfirmarData?faces-redirect=true";
    }

    public String montaPaginaParaListarAlteracao() {
        this.recuperarEventos();
        return "/Evento/ListarEventos?faces-redirect=true";
    }

    public String montaPaginaParaAlteracao() {
        return "/Evento/AlterarEvento?faces-redirect=true";
    }

    public String montaPaginaParaDetalhes() {
        this.recuperarLancamentos(evento);
        this.recuperarColaboradoresAdd(evento);
        this.recuperarTerceirosAdd(evento);
        this.recuperarColaboradores(evento);
        this.recuperarTerceiros(evento);
        this.recuperarDecoracao(evento);
        this.recuperarDecoracaoAdd(evento);
        this.isAddColaborador = false;
        this.isAddTerceiro = false;
        this.setIsAddDecoracao(false);
        return "/Evento/EventoDetalhes?faces-redirect=true";
    }

    public String montarPaginaParaPagamento() {
        this.lancamento = new Lancamento();
        return "/Evento/CadastrarPagamento?faces-redirect=true";
    }

    public String montaPaginaParaListarConfirmados() {
        this.recuperarEventosConfirmados();
        return "/Evento/ListarEventosDetalhes?faces-redirect=true";
    }

    public String montarPaginaParaCadastrarPagamento() {
        this.recuperarEventosConfirmados();
        return "/Evento/ListarEventosPagamento?faces-redirect=true";
    }

    public String Inserir() {
        this.getEvento().setDatacriacao(new Date());
        this.getEvento().setCliente(clienteRecuperado);
        this.getEvento().setAtivo('A');
        this.tipo.setIdtipo(render);
        this.getEvento().setTipo(tipo);
        this.getEvento().setUsuario(recuperarUsuarioCadastro());
        eventoFachada.Inserir(this.getEvento());
        return "/Evento/ListarEventos?faces-redirect=true";
    }

    public String SalvarColaboradorEvento() throws CampoUniqueException {
        this.getEventoFachada().InserirColaborador(colaboradoresAdd, evento);
        this.popularColaborador();
        return this.montaPaginaParaDetalhes();
    }

    public String SalvarTerceiroEvento() throws CampoUniqueException {
        this.getEventoFachada().InserirTerceiros(terceirosAdd, evento);
        this.popularTerceiro();
        return this.montaPaginaParaDetalhes();
    }

    public String SalvarDecoracao() throws CampoUniqueException {
        this.getEventoFachada().InserirDecoracao(itensAdd, evento);
        this.popularTerceiro();
        return this.montaPaginaParaDetalhes();
    }

    public String InserirPagamento() {
        this.getLancamento().setEvento(eventoPagamento);
        this.getLancamento().setDatalancamento(new Date());
        this.getLancamento().setUsuariolancamento(recuperarUsuarioCadastro());
        this.getLancamento().setStatus(true);
        if (confirmaPagamento) {
            this.getLancamento().setDatarecebimento(new Date());
            this.getLancamento().setUsuariorecebimento(recuperarUsuarioCadastro());
            this.getLancamento().setValorrecebido(this.getLancamento().getValorprevisto());
        }
        lancamentoFachada.Inserir(this.getLancamento());
        this.setEvento(eventoPagamento);
        return this.montaPaginaParaDetalhes();
    }

    public String ConfirmarRecebimento() {
        this.getLancamento().setDatarecebimento(new Date());
        this.getLancamento().setUsuariorecebimento(recuperarUsuarioCadastro());
        this.getLancamento().setValorrecebido(this.getLancamento().getValorprevisto());
        lancamentoFachada.ConfirmarPagamento(this.getLancamento());
        this.recuperarLancamentos(evento);
        return this.montaPaginaParaDetalhes();
    }

    public String Alterar() {
        this.getEvento().setAtivo('C');
        eventoFachada.Alterar(this.getEvento());
        return "/Evento/ListarEventos?faces-redirect=true";
    }

    public String Excluir() {
        try {
            eventoFachada.Excluir(this.getEvento());
            this.recuperarEventos();
            return "/Evento/ListarEventos?faces-redirect=true";
        } catch (Exception e) {
            MensagemUtility.adicionarMensagemDeErro("formDetalhes", e.getMessage());
            return "";
        }
    }

    private Usuario recuperarUsuarioCadastro() {
        return usuarioFachada.recuperarUsuario(usuarioBean.getUsuario());
    }

    private void recuperarClientes() {
        this.setClientes(clienteFachada.Listar());
    }

    private void recuperarColaboradoresAdd(Evento evento) {
        setColaboradoresAdd(colaboradorFachada.ListarColaboradoresEvento(evento));
        setColaboradorFiltroAdd(colaboradorFachada.ListarColaboradoresEvento(evento));
    }

    private void recuperarTerceirosAdd(Evento evento) {
        setTerceirosAdd(terceiroFachada.ListarTerceirosEvento(evento));
        setTerceiroFiltroAdd(terceiroFachada.ListarTerceirosEvento(evento));
    }

    private void recuperarDecoracaoAdd(Evento evento) {
        setItensAdd(eventoFachada.ListarDecoracaoEvento(evento));
        setItensFiltroADD(eventoFachada.ListarDecoracaoEvento(evento));
    }

    private void recuperarTerceiros(Evento evento) {
        setTerceiros(terceiroFachada.ListarNaoEvento(evento));
        setTerceiroFiltro(terceiroFachada.ListarNaoEvento(evento));
    }

    private void recuperarColaboradores(Evento evento) {
        setColaboradores(colaboradorFachada.ListarNaoEvento(evento));
        setColaboradorFiltro(colaboradorFachada.ListarNaoEvento(evento));
    }

    private void recuperarDecoracao(Evento evento) {
        setItens(eventoFachada.ListarDecoracaoNaoPertenceEvento(evento));
        setItensFiltro(eventoFachada.ListarDecoracaoNaoPertenceEvento(evento));
    }

    private void recuperarTipos() {
        this.setTipos(tipoFachada.Listar());
    }

    private void recuperarLancamentos(Evento evento) {
        this.setLancamentos(lancamentoFachada.ListarParaPagamento(evento));
        this.setLancamentoFiltro(lancamentoFachada.ListarParaPagamento(evento));
        double valorRecebido = 0.0;
        for (Lancamento lancEvenco : lancamentos){
            valorRecebido += lancEvenco.getValorrecebido();
        }
        this.setValorRecebidoEvento(valorRecebido);
    }

    private void recuperarEventos() {
        this.setEventos(eventoFachada.Listar());
        this.setEventoFiltro(eventoFachada.Listar());
    }

    private void recuperarEventosConfirmados() {
        this.setEventosConfirmados(eventoFachada.ListarConfirmados());
        this.setEventoFiltroConfirmados(eventoFachada.ListarConfirmados());
    }

    public String montarPaginaParaAdicionarColaborador() {
        return "/Colaborador/InserirColaboradorEvento?faces-redirect=true";
    }

    public void popularColaborador() {
        this.isAddColaborador = !this.isAddColaborador;
    }

    public void popularTerceiro() {
        this.isAddTerceiro = !this.isAddTerceiro;
    }

    public void popularDecoracao() {
        this.setIsAddDecoracao(!this.isIsAddDecoracao());
    }

    public String montarPaginaParaCadastro() {
        try {
            Date hoje = new Date();
            DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String datahoje = formato.format(hoje);
            String datatela = formato.format(this.getEvento().getIddata());
            if (datahoje.equals(datatela) || this.getEvento().getIddata().after(hoje)) {
                try {
                    this.eventoFachada.verificarSeADataJaFoiCadastrada(this.getEvento().getIddata());
                    this.clientes = new ArrayList<>();
                    this.clienteFiltro = new ArrayList<>();
                    this.clienteRecuperado = new Cliente();
                    this.rendercliente = 0;
                    this.render = 0;
                    this.recuperarTipos();
                    return "/Evento/CadastrarEvento?face's-redirect=true";
                } catch (Exception e) {
                    MensagemUtility.adicionarMensagemDeErro("formDetalhes", e.getMessage());
                    return "";
                }
            }
            this.eventoFachada.verificarSeDataMaiorQueHoje();
        } catch (Exception e) {
            MensagemUtility.adicionarMensagemDeErro("formDetalhes", e.getMessage());
            return "";
        }
        return null;
    }

    public String listar() {
        this.recuperarEventos();
        return "/Evento/ListarEventos?faces-redirect=true";
    }

    public String montarPaginaParaInsercaoCliente() {
        this.clienteRecuperado = new Cliente();
        return "/Cliente/InserirClienteEvento?faces-redirect=true";
    }

    public String montarPaginaParaConfirmarRecebimento() {
        return "/Evento/ConfirmarPagamento?faces-redirect=true";
    }

    public void consultarClientePorNome(Cliente cliente) {
        this.clientes = this.clienteFachada.recuperarCliente(cliente);
    }

    public String confirmarCadastroCliente() {
        this.clienteFachada.Inserir(this.clienteRecuperado);
        this.rendercliente = 1;
        return "/Evento/CadastrarEvento?face's-redirect=true";
    }

    public void consultarClienteEvento() {
        this.consultarClientePorNome(this.getCliente());
        this.rendercliente = 3;
    }

    public String montarPaginaParaListar() {
        this.init();
        return "/Evento/ConsultaEvento?faces-redirect=true";
    }
    
    public String montarPaginaParaPesquisarPagamento(){
        return "/Fluxo/ListarRecebimentos?faces-redirect=true";
    }
    
    public void ListarRecebimentoPorData(){
        this.recebimentos = lancamentoFachada.ListarRecebimentoPorData(this.getDataInit(), this.getDataFim());
        this.qteLancamento = recebimentos.size();
        valorTotalRecebimento = 0.0;
        for (Lancamento lanc : recebimentos){
            valorTotalRecebimento += lanc.getValorrecebido();
        }
    }

    public List<SelectItem> getTipoItens() {
        recuperarTipos();
        List<SelectItem> lista = new ArrayList<SelectItem>();

        for (Tipo t : tipos) {
            lista.add(new SelectItem(t.getIdtipo(), t.getNometipo()));
        }

        return lista;
    }

    /**
     * @return the cliente
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     * @param evento the item to set
     */
    public void setCliente(Evento evento) {
        this.setEvento(evento);
    }

    public void selecionarCliente() {
        this.getClienteRecuperado();
        this.rendercliente = 1;
    }

    /**
     * @return the clienteFachada
     */
    public EventoFachada getEventoFachada() {
        return eventoFachada;
    }

    /**
     * @param eventoFachada the clienteFachada to set
     */
    public void setClienteFachada(EventoFachada eventoFachada) {
        this.eventoFachada = eventoFachada;
    }

    public void imprimePDF(Object document) throws IOException, BadElementException, DocumentException {
        impressaoManagedBean.preProcessPDF(document, "Lista de Eventos");
    }
    
    public void imprimeRecebimentoPDF(Object document) throws IOException, BadElementException, DocumentException {
        impressaoManagedBean.preProcessPDF(document, "Lista de Recebimentos");
    }

    public void imprimeXLS(Object document) {
        impressaoManagedBean.gerarXLS(document);
    }

    /**
     * @return the rendercliente
     */
    public int getRendercliente() {
        return rendercliente;
    }

    /**
     * @param rendercliente the rendercliente to set
     */
    public void setRendercliente(int rendercliente) {
        this.rendercliente = rendercliente;
    }

    /**
     * @return the clientes
     */
    public List<Cliente> getClientes() {
        return clientes;
    }

    /**
     * @param clientes the clientes to set
     */
    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    /**
     * @return the clienteFiltro
     */
    public List<Cliente> getClienteFiltro() {
        return clienteFiltro;
    }

    /**
     * @param clienteFiltro the clienteFiltro to set
     */
    public void setClienteFiltro(List<Cliente> clienteFiltro) {
        this.clienteFiltro = clienteFiltro;
    }

    /**
     * @return the clienteFachada
     */
    public ClienteFachada getClienteFachada() {
        return clienteFachada;
    }

    /**
     * @param clienteFachada the clienteFachada to set
     */
    public void setClienteFachada(ClienteFachada clienteFachada) {
        this.clienteFachada = clienteFachada;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the clienteRecuperado
     */
    public Cliente getClienteRecuperado() {
        return clienteRecuperado;
    }

    /**
     * @param clienteRecuperado the clienteRecuperado to set
     */
    public void setClienteRecuperado(Cliente clienteRecuperado) {
        this.clienteRecuperado = clienteRecuperado;
    }

    /**
     * @return the render
     */
    public int getRender() {
        return render;
    }

    /**
     * @param render the render to set
     */
    public void setRender(int render) {
        this.render = render;
    }

    /**
     * @return the eventos
     */
    public List<Evento> getEventos() {
        this.recuperarEventos();
        return eventos;
    }

    /**
     * @param eventos the eventos to set
     */
    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    /**
     * @return the eventoFiltro
     */
    public List<Evento> getEventoFiltro() {
        return eventoFiltro;
    }

    /**
     * @param eventoFiltro the eventoFiltro to set
     */
    public void setEventoFiltro(List<Evento> eventoFiltro) {
        this.eventoFiltro = eventoFiltro;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
        this.eventoSc = this.eventoFachada.consultarClienteSchedule(event.getStartDate());
        setAtivo(eventoSc.getAtivo() != 'A');
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    /**
     * @return the eventoSc
     */
    public Evento getEventoSc() {
        return eventoSc;
    }

    /**
     * @param eventoSc the eventoSc to set
     */
    public void setEventoSc(Evento eventoSc) {
        this.eventoSc = eventoSc;
    }

    /**
     * @return the ativo
     */
    public boolean isAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    /**
     * @return the usuarioBean
     */
    public UsuarioManagedBean getUsuarioBean() {
        return usuarioBean;
    }

    /**
     * @param usuarioBean the usuarioBean to set
     */
    public void setUsuarioBean(UsuarioManagedBean usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

    /**
     * @return the usuariologado
     */
    public Usuario getUsuariologado() {
        return usuariologado;
    }

    /**
     * @param usuariologado the usuariologado to set
     */
    public void setUsuariologado(Usuario usuariologado) {
        this.usuariologado = usuariologado;
    }

    /**
     * @return the usuarioFachada
     */
    public UsuarioFachada getUsuarioFachada() {
        return usuarioFachada;
    }

    /**
     * @param usuarioFachada the usuarioFachada to set
     */
    public void setUsuarioFachada(UsuarioFachada usuarioFachada) {
        this.usuarioFachada = usuarioFachada;
    }

    /**
     * @param evento the evento to set
     */
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    /**
     * @return the eventosConfirmados
     */
    public List<Evento> getEventosConfirmados() {
        return eventosConfirmados;
    }

    /**
     * @param eventosConfirmados the eventosConfirmados to set
     */
    public void setEventosConfirmados(List<Evento> eventosConfirmados) {
        this.eventosConfirmados = eventosConfirmados;
    }

    /**
     * @return the eventoFiltroConfirmados
     */
    public List<Evento> getEventoFiltroConfirmados() {
        return eventoFiltroConfirmados;
    }

    /**
     * @param eventoFiltroConfirmados the eventoFiltroConfirmados to set
     */
    public void setEventoFiltroConfirmados(List<Evento> eventoFiltroConfirmados) {
        this.eventoFiltroConfirmados = eventoFiltroConfirmados;
    }

    /**
     * @return the tipo
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the tipos
     */
    public List<Tipo> getTipos() {
        return tipos;
    }

    /**
     * @param tipos the tipos to set
     */
    public void setTipos(List<Tipo> tipos) {
        this.tipos = tipos;
    }

    /**
     * @return the lancamento
     */
    public Lancamento getLancamento() {
        return lancamento;
    }

    /**
     * @param lancamento the lancamento to set
     */
    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
    }

    /**
     * @return the confirmaPagamento
     */
    public boolean isConfirmaPagamento() {
        return confirmaPagamento;
    }

    /**
     * @param confirmaPagamento the confirmaPagamento to set
     */
    public void setConfirmaPagamento(boolean confirmaPagamento) {
        this.confirmaPagamento = confirmaPagamento;
    }

    /**
     * @return the lancamentos
     */
    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    /**
     * @param lancamentos the lancamentos to set
     */
    public void setLancamentos(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }

    /**
     * @return the lancamentoFiltro
     */
    public List<Lancamento> getLancamentoFiltro() {
        return lancamentoFiltro;
    }

    /**
     * @param lancamentoFiltro the lancamentoFiltro to set
     */
    public void setLancamentoFiltro(List<Lancamento> lancamentoFiltro) {
        this.lancamentoFiltro = lancamentoFiltro;
    }

    /**
     * @return the eventoPagamento
     */
    public Evento getEventoPagamento() {
        return eventoPagamento;
    }

    /**
     * @param eventoPagamento the eventoPagamento to set
     */
    public void setEventoPagamento(Evento eventoPagamento) {
        this.eventoPagamento = eventoPagamento;
    }

    /**
     * @return the colaboradorFachada
     */
    public ColaboradorFachada getColaboradorFachada() {
        return colaboradorFachada;
    }

    /**
     * @param colaboradorFachada the colaboradorFachada to set
     */
    public void setColaboradorFachada(ColaboradorFachada colaboradorFachada) {
        this.colaboradorFachada = colaboradorFachada;
    }

    /**
     * @return the colaboradores
     */
    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    /**
     * @param colaboradores the colaboradores to set
     */
    public void setColaboradores(List<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }

    /**
     * @return the colaboradorFiltro
     */
    public List<Colaborador> getColaboradorFiltro() {
        return colaboradorFiltro;
    }

    /**
     * @param colaboradorFiltro the colaboradorFiltro to set
     */
    public void setColaboradorFiltro(List<Colaborador> colaboradorFiltro) {
        this.colaboradorFiltro = colaboradorFiltro;
    }

    /**
     * @return the terceiroFachada
     */
    public TerceiroFachada getTerceiroFachada() {
        return terceiroFachada;
    }

    /**
     * @param terceiroFachada the terceiroFachada to set
     */
    public void setTerceiroFachada(TerceiroFachada terceiroFachada) {
        this.terceiroFachada = terceiroFachada;
    }

    /**
     * @return the terceiroFiltro
     */
    public List<Terceiro> getTerceiroFiltro() {
        return terceiroFiltro;
    }

    /**
     * @param terceiroFiltro the terceiroFiltro to set
     */
    public void setTerceiroFiltro(List<Terceiro> terceiroFiltro) {
        this.terceiroFiltro = terceiroFiltro;
    }

    /**
     * @return the terceiros
     */
    public List<Terceiro> getTerceiros() {
        return terceiros;
    }

    /**
     * @param terceiros the terceiros to set
     */
    public void setTerceiros(List<Terceiro> terceiros) {
        this.terceiros = terceiros;
    }

    /**
     * @return the colaboradoresAdd
     */
    public List<Colaborador> getColaboradoresAdd() {
        return colaboradoresAdd;
    }

    /**
     * @param colaboradoresAdd the colaboradoresAdd to set
     */
    public void setColaboradoresAdd(List<Colaborador> colaboradoresAdd) {
        this.colaboradoresAdd = colaboradoresAdd;
    }

    /**
     * @return the colaborador
     */
    public Colaborador getColaborador() {
        return colaborador;
    }

    /**
     * @param colaborador the colaborador to set
     */
    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    /**
     * @return the terceiro
     */
    public Terceiro getTerceiro() {
        return terceiro;
    }

    /**
     * @param terceiro the terceiro to set
     */
    public void setTerceiro(Terceiro terceiro) {
        this.terceiro = terceiro;
    }

    /**
     * @return the terceirosAdd
     */
    public List<Terceiro> getTerceirosAdd() {
        return terceirosAdd;
    }

    /**
     * @param terceirosAdd the terceirosAdd to set
     */
    public void setTerceirosAdd(List<Terceiro> terceirosAdd) {
        this.terceirosAdd = terceirosAdd;
    }

    /**
     * @return the itens
     */
    public List<Item> getItens() {
        return itens;
    }

    /**
     * @param itens the itens to set
     */
    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    /**
     * @return the itensFiltro
     */
    public List<Item> getItensFiltro() {
        return itensFiltro;
    }

    /**
     * @param itensFiltro the itensFiltro to set
     */
    public void setItensFiltro(List<Item> itensFiltro) {
        this.itensFiltro = itensFiltro;
    }

    /**
     * @return the itensAdd
     */
    public List<Item> getItensAdd() {
        return itensAdd;
    }

    /**
     * @param itensAdd the itensAdd to set
     */
    public void setItensAdd(List<Item> itensAdd) {
        this.itensAdd = itensAdd;
    }

    /**
     * @return the itemFachada
     */
    public ItemFachada getItemFachada() {
        return itemFachada;
    }

    /**
     * @param itemFachada the itemFachada to set
     */
    public void setItemFachada(ItemFachada itemFachada) {
        this.itemFachada = itemFachada;
    }

    /**
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * @return the isAddColaborador
     */
    public boolean isIsAddColaborador() {
        return isAddColaborador;
    }

    /**
     * @param isAddColaborador the isAddColaborador to set
     */
    public void setIsAddColaborador(boolean isAddColaborador) {
        this.isAddColaborador = isAddColaborador;
    }

    /**
     * @return the colaboradorFiltroAdd
     */
    public List<Colaborador> getColaboradorFiltroAdd() {
        return colaboradorFiltroAdd;
    }

    /**
     * @param colaboradorFiltroAdd the colaboradorFiltroAdd to set
     */
    public void setColaboradorFiltroAdd(List<Colaborador> colaboradorFiltroAdd) {
        this.colaboradorFiltroAdd = colaboradorFiltroAdd;
    }

    /**
     * @return the terceiroFiltroAdd
     */
    public List<Terceiro> getTerceiroFiltroAdd() {
        return terceiroFiltroAdd;
    }

    /**
     * @param terceiroFiltroAdd the terceiroFiltroAdd to set
     */
    public void setTerceiroFiltroAdd(List<Terceiro> terceiroFiltroAdd) {
        this.terceiroFiltroAdd = terceiroFiltroAdd;
    }

    /**
     * @return the isAddTerceiro
     */
    public boolean isIsAddTerceiro() {
        return isAddTerceiro;
    }

    /**
     * @param isAddTerceiro the isAddTerceiro to set
     */
    public void setIsAddTerceiro(boolean isAddTerceiro) {
        this.isAddTerceiro = isAddTerceiro;
    }

    /**
     * @return the itensFiltroADD
     */
    public List<Item> getItensFiltroADD() {
        return itensFiltroADD;
    }

    /**
     * @param itensFiltroADD the itensFiltroADD to set
     */
    public void setItensFiltroADD(List<Item> itensFiltroADD) {
        this.itensFiltroADD = itensFiltroADD;
    }

    /**
     * @return the isAddDecoracao
     */
    public boolean isIsAddDecoracao() {
        return isAddDecoracao;
    }

    /**
     * @param isAddDecoracao the isAddDecoracao to set
     */
    public void setIsAddDecoracao(boolean isAddDecoracao) {
        this.isAddDecoracao = isAddDecoracao;
    }

    /**
     * @return the dataInit
     */
    public Date getDataInit() {
        return dataInit;
    }

    /**
     * @param dataInit the dataInit to set
     */
    public void setDataInit(Date dataInit) {
        this.dataInit = dataInit;
    }

    /**
     * @return the dataFim
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * @return the recebimentos
     */
    public List<Lancamento> getRecebimentos() {
        return recebimentos;
    }

    /**
     * @param recebimentos the recebimentos to set
     */
    public void setRecebimentos(List<Lancamento> recebimentos) {
        this.recebimentos = recebimentos;
    }

    /**
     * @param dataFim the dataFim to set
     */
    public void setDataFim(Date dataFim) {
        java.util.Date oldDataFim = this.dataFim;
        this.dataFim = dataFim;
        propertyChangeSupport.firePropertyChange(PROP_DATAFIM, oldDataFim, dataFim);
    }

    /**
     * @return the qteLancamento
     */
    public int getTotalLancamento() {
        return qteLancamento;
    }

    /**
     * @param totalLancamento the qteLancamento to set
     */
    public void setTotalLancamento(int totalLancamento) {
        this.qteLancamento = totalLancamento;
    }

    /**
     * @return the valorTotalRecebimento
     */
    public double getValorTotalRecebimento() {
        return valorTotalRecebimento;
    }

    /**
     * @param valorTotalRecebimento the valorTotalRecebimento to set
     */
    public void setValorTotalRecebimento(double valorTotalRecebimento) {
        this.valorTotalRecebimento = valorTotalRecebimento;
    }

    /**
     * @return the valorRecebidoEvento
     */
    public double getValorRecebidoEvento() {
        return valorRecebidoEvento;
    }

    /**
     * @param valorRecebidoEvento the valorRecebidoEvento to set
     */
    public void setValorRecebidoEvento(double valorRecebidoEvento) {
        this.valorRecebidoEvento = valorRecebidoEvento;
    }

}
