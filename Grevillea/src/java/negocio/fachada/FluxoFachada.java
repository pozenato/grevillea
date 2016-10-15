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
import negocio.entidade.Fluxocaixa;
import negocio.entidade.Fornecedor;
import negocio.entidade.Produto;
import negocio.persistecia.FluxoDAO;
import negocio.persistecia.FornecedorDAO;
import negocio.persistecia.ProdutoDAO;

/**
 *
 * @author pozenato
 */
@Stateless
public class FluxoFachada {

    private Fluxocaixa fluxo = new Fluxocaixa();
    private String nomeCorrigido = new String();
    @EJB
    private FluxoDAO fluxoDAO;
    
    @EJB
    private ProdutoDAO produtoDAO;
    
    @EJB
    private FornecedorDAO fornecedorDAO;

    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public void Inserir(Fluxocaixa fluxo, Integer idDoProduto, Integer idDoFornecedor) {
        fluxo.setProduto(this.recuperarProdutoPorId(idDoProduto));
        fluxo.setFornecedor(this.recuperarFornecedorPorID(idDoFornecedor));
        fluxoDAO.Inserir(fluxo);
    }

    public List<Fluxocaixa> Listar() {
        return fluxoDAO.RecuperarTodos();
    }
    
    public Produto recuperarProdutoPorId(Integer idDoProduto) {
        return getProdutoDAO().recuperarPorId(idDoProduto);
    }
    
    public Fornecedor recuperarFornecedorPorID(Integer idDoFornecedor){
        return getFornecedorDAO().recuperarPorId(idDoFornecedor);
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
     * @return the fluxo
     */
    public Fluxocaixa getFluxo() {
        return fluxo;
    }

    /**
     * @param fluxo the fluxo to set
     */
    public void setFluxo(Fluxocaixa fluxo) {
        this.fluxo = fluxo;
    }

    /**
     * @return the produtoDAO
     */
    public ProdutoDAO getProdutoDAO() {
        return produtoDAO;
    }

    /**
     * @param produtoDAO the produtoDAO to set
     */
    public void setProdutoDAO(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    /**
     * @return the fornecedorDAO
     */
    public FornecedorDAO getFornecedorDAO() {
        return fornecedorDAO;
    }

    /**
     * @param fornecedorDAO the fornecedorDAO to set
     */
    public void setFornecedorDAO(FornecedorDAO fornecedorDAO) {
        this.fornecedorDAO = fornecedorDAO;
    }

    public List<Fluxocaixa> ListarDespesasPorData(Date dataInit, Date dateFim) {
        return this.fluxoDAO.ListarDespesasPorData(dataInit, dateFim);
    }
}
