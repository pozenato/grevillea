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
import negocio.entidade.Fornecedor;
import negocio.persistecia.FornecedorDAO;

/**
 *
 * @author pozenato
 */
@Stateless
public class FornecedorFachada {

    private Fornecedor fornecedor = new Fornecedor();
    private String nomeCorrigido = new String();
    @EJB
    private FornecedorDAO fornecedorDAO;

    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public void Inserir(Fornecedor fornecedor) {
        nomeCorrigido = removerAcentos(fornecedor.getNomefornecedor().toUpperCase());
        fornecedor.setNomefornecedor(nomeCorrigido);
        fornecedorDAO.Inserir(fornecedor);
    }

    public List<Fornecedor> Listar() {
        return fornecedorDAO.RecuperarTodos();
    }

    public List<Fornecedor> recuperarFornecedor(Fornecedor fornecedor) {
        return fornecedorDAO.buscaFornecedorPorNome(fornecedor);         
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

    public void Alterar(Fornecedor fornecedor) {
        fornecedorDAO.Alterar(fornecedor);
    }
}
