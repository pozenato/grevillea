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
import negocio.entidade.Fornecedor;
import negocio.fachada.FornecedorFachada;
import org.primefaces.model.DualListModel;


/**
 *
 * @author pozenato
 */
@ManagedBean(name = "fornecedorManagedBean")
@RequestScoped
public class FornecedorManagedBean {

    private Fornecedor fornecedor = new Fornecedor();
    private List<Fornecedor> Fornecedores;
    private List<Fornecedor> FornecedorFiltro;
    public ImpressaoManagedBean impressaoManagedBean = new ImpressaoManagedBean();

    @EJB
    private FornecedorFachada fornecedorFachada;

    public FornecedorManagedBean() {
    }

    public String montarPaginaParaInsercao() {
        this.setFornecedor(new Fornecedor());
        this.recuperarFornecedores();
        return "/Fornecedor/InserirFornecedor?faces-redirect=true";
    }

    public String Inserir() {
        this.fornecedor.setDatacadastro(new Date());
        fornecedorFachada.Inserir(getFornecedor());
        this.recuperarFornecedores();
        return "/Fornecedor/ListarFornecedores?faces-redirect=true";
    }
    

    private void recuperarFornecedores() {
        setFornecedorFiltro(getFornecedorFachada().Listar());
        this.setFornecedores(getFornecedorFachada().Listar());
    }

    public String listar() {
        this.recuperarFornecedores();
        return "/Fornecedor/ListarFornecedores?faces-redirect=true";
    }

   

    /**
     * @return the Fornecedores
     */
    public List<Fornecedor> getFornecedores() {
        this.recuperarFornecedores();
        return Fornecedores;
    }

    /**
     * @param Fornecedores the Fornecedores to set
     */
    public void setFornecedores(List<Fornecedor> Fornecedores) {
        this.Fornecedores = Fornecedores;
    }

    /**
     * @return the FornecedorFiltro
     */
    public List<Fornecedor> getFornecedorFiltro() {
        return FornecedorFiltro;
    }

    /**
     * @param FornecedorFiltro the FornecedorFiltro to set
     */
    public void setFornecedorFiltro(List<Fornecedor> FornecedorFiltro) {
        this.FornecedorFiltro = FornecedorFiltro;
    }

    /**
     * @return the fornecedorFachada
     */
    public FornecedorFachada getFornecedorFachada() {
        return fornecedorFachada;
    }

    /**
     * @param fornecedorFachada the fornecedorFachada to set
     */
    public void setFornecedorFachada(FornecedorFachada fornecedorFachada) {
        this.fornecedorFachada = fornecedorFachada;
    }
    
     public void imprimePDF(Object document) throws IOException, BadElementException, DocumentException {
        impressaoManagedBean.preProcessPDF(document, "Lista de Clientes");
    }
     
     public void imprimeXLS(Object document){
        impressaoManagedBean.gerarXLS(document);
    }

    /**
     * @return the fornecedor
     */
    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    /**
     * @param fornecedor the fornecedor to set
     */
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

}

