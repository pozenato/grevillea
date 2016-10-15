/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.excecao;

/**
 *
 * @author pozenato
 */
public class CampoUniqueException extends Exception {
    
    public CampoUniqueException(String mensagem) {
        super(mensagem);
    }
} 
