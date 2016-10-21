/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentação.ManangedBean;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import negocio.entidade.Fluxocaixa;
import negocio.entidade.Fornecedor;
import negocio.entidade.Produto;
import negocio.fachada.FluxoFachada;
import negocio.fachada.FornecedorFachada;
import negocio.fachada.ProdutoFachada;
import org.primefaces.model.DualListModel;

/**
 *
 * @author pozenato
 */
@ManagedBean(name = "fluxoManagedBean")
@SessionScoped
public class FluxoManagedBean {

    private Fluxocaixa fluxo = new Fluxocaixa();
    private List<Fluxocaixa> Fluxos;
    private List<Fluxocaixa> FluxoFiltro;
    private List<Fluxocaixa> Despesas;
    private Integer idDoFornecedorSelecionado;
    private Integer idDoProdutoSelecionado;
    private Integer render;
    private Integer parcelas;
    private Date dataInit;
    private Date dataFim;
    private int qtdeDespesas;
    private double valorTotalDespesas;
    public ImpressaoManagedBean impressaoManagedBean = new ImpressaoManagedBean();

    @EJB
    private FluxoFachada fluxoFachada;

    @EJB
    private FornecedorFachada fornecedorFachada;

    @EJB
    private ProdutoFachada produtoFachada;

    public FluxoManagedBean() {
    }

    public String montarPaginaParaInsercao() {
        this.fluxo = new Fluxocaixa();
        this.render = 0;
        this.idDoFornecedorSelecionado = 0;
        this.idDoProdutoSelecionado = 0;
        this.parcelas = 0;
        this.recuperarFluxos();
        return "/Fluxo/InserirFluxo?faces-redirect=true";
    }
    
    public String montarPaginaParaPesquisarDespesa(){
        this.Despesas = new ArrayList();
        return "/Fluxo/ListarDespesas?faces-redirect=true";
    }
    
    public void ListarDespesasPorData(){
        this.Despesas = fluxoFachada.ListarDespesasPorData(this.getDataInit(), this.getDataFim());
        this.qtdeDespesas = Despesas.size();
        this.valorTotalDespesas = 0.0;
        for (Fluxocaixa fCaixa : Despesas){
            this.valorTotalDespesas += fCaixa.getValor();
        }
    }

    public List<SelectItem> getFornecedores() {
        List<Fornecedor> fornecedoresCadastrados = fornecedorFachada.Listar();

        List<SelectItem> fornecedores = new ArrayList<SelectItem>(fornecedoresCadastrados.size());

        for (Fornecedor fornecedor : fornecedoresCadastrados) {
            fornecedores.add(new SelectItem(fornecedor.getIdfornecedor(), fornecedor.getNomefornecedor()));
        }

        return fornecedores;
    }

    public List<SelectItem> getProdutos() {
        List<Produto> produtosCadastrados = produtoFachada.Listar();

        List<SelectItem> produtos = new ArrayList<SelectItem>(produtosCadastrados.size());

        for (Produto produto : produtosCadastrados) {
            produtos.add(new SelectItem(produto.getIdproduto(), produto.getNomeproduto()));
        }

        return produtos;
    }

    public String Inserir() {
        this.fluxo.setDatainsercao(new Date());
        if (render == 1) {
            this.fluxo.setTipo('D');
        } else if (render == 2) {
            this.fluxo.setTipo('I');
        } else {
            this.fluxo.setTipo('G');
        }
        this.fluxo.setStatus(Boolean.TRUE);
        fluxoFachada.Inserir(getFluxo(), idDoProdutoSelecionado, idDoFornecedorSelecionado);
        this.recuperarFluxos();
        return this.montarPaginaParaInsercao();
    }
    

    private void recuperarFluxos() {
        setFluxoFiltro(getFluxoFachada().Listar());
        this.setFluxos(getFluxoFachada().Listar());
    }

    public String listar() {
        this.recuperarFluxos();
        return "/Fluxo/ListarFluxos?faces-redirect=true";
    }
    
    public void imprimeDespesasPDF(Object document) throws IOException, BadElementException, DocumentException {
        impressaoManagedBean.preProcessPDF(document, "Lista de Despesas");
    }
     
     public void imprimeXLS(Object document){
        impressaoManagedBean.gerarXLS(document);
    }

    /**
     * @return the Fluxos
     */
    public List<Fluxocaixa> getFluxos() {
        this.recuperarFluxos();
        return Fluxos;
    }

    /**
     * @param Fluxos the Fluxos to set
     */
    public void setFluxos(List<Fluxocaixa> Fluxos) {
        this.Fluxos = Fluxos;
    }

    /**
     * @return the FluxoFiltro
     */
    public List<Fluxocaixa> getFluxoFiltro() {
        return FluxoFiltro;
    }

    /**
     * @param FluxoFiltro the FluxoFiltro to set
     */
    public void setFluxoFiltro(List<Fluxocaixa> FluxoFiltro) {
        this.FluxoFiltro = FluxoFiltro;
    }

    /**
     * @return the fluxoFachada
     */
    public FluxoFachada getFluxoFachada() {
        return fluxoFachada;
    }

    /**
     * @param fluxoFachada the fluxoFachada to set
     */
    public void setFornecedorFachada(FluxoFachada fluxoFachada) {
        this.fluxoFachada = fluxoFachada;
    }

    public void imprimePDF(Object document) throws IOException, BadElementException, DocumentException {
        impressaoManagedBean.preProcessPDF(document, "Lista de Clientes");
    }

    /**
     * @return the fluxo
     */
    public Fluxocaixa getFluxo() {
        return fluxo;
    }

    /**
     * @param fluxo the fluxo to set
     */
    public void setFluxocaixa(Fluxocaixa fluxo) {
        this.fluxo = fluxo;
    }

    /**
     * @return the idDoFornecedorSelecionado
     */
    public Integer getIdDoFornecedorSelecionado() {
        return idDoFornecedorSelecionado;
    }

    /**
     * @param idDoFornecedorSelecionado the idDoFornecedorSelecionado to set
     */
    public void setIdDoFornecedorSelecionado(Integer idDoFornecedorSelecionado) {
        this.idDoFornecedorSelecionado = idDoFornecedorSelecionado;
    }

    /**
     * @return the idDoProdutoSelecionado
     */
    public Integer getIdDoProdutoSelecionado() {
        return idDoProdutoSelecionado;
    }

    /**
     * @param idDoProdutoSelecionado the idDoProdutoSelecionado to set
     */
    public void setIdDoProdutoSelecionado(Integer idDoProdutoSelecionado) {
        this.idDoProdutoSelecionado = idDoProdutoSelecionado;
    }

    /**
     * @return the render
     */
    public Integer getRender() {
        return render;
    }

    /**
     * @param render the render to set
     */
    public void setRender(Integer render) {
        this.render = render;
    }

    /**
     * @return the parcelas
     */
    public Integer getParcelas() {
        return parcelas;
    }

    /**
     * @param parcelas the parcelas to set
     */
    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
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
     * @param dataFim the dataFim to set
     */
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @return the Despesas
     */
    public List<Fluxocaixa> getDespesas() {
        return Despesas;
    }

    /**
     * @param Despesas the Despesas to set
     */
    public void setDespesas(List<Fluxocaixa> Despesas) {
        this.Despesas = Despesas;
    }

    /**
     * @return the qtdeDespesas
     */
    public int getQtdeDespesas() {
        return qtdeDespesas;
    }

    /**
     * @param qtdeDespesas the qtdeDespesas to set
     */
    public void setQtdeDespesas(int qtdeDespesas) {
        this.qtdeDespesas = qtdeDespesas;
    }

    /**
     * @return the valorTotalDespesas
     */
    public double getValorTotalDespesas() {
        return valorTotalDespesas;
    }

    /**
     * @param valorTotalDespesas the valorTotalDespesas to set
     */
    public void setValorTotalDespesas(double valorTotalDespesas) {
        this.valorTotalDespesas = valorTotalDespesas;
    }

}
