/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao.validacao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Bruno Pozenato
 */
@FacesValidator("validaSenha")
public class ValidaSenha {  
      
    /** Creates a new instance of EncriptaSenha */  
    public ValidaSenha() {          
    }  
      
     public static String encripta (String senha) {  
         try    {  
             MessageDigest digest = MessageDigest.getInstance("MD5");  
             digest.update(senha.getBytes());  
             BASE64Encoder encoder = new BASE64Encoder ();   
             return encoder.encode(digest.digest());   
         }catch (NoSuchAlgorithmException ns) {  
             ns.printStackTrace ();  
             return senha;  
         }  
     }      
      
} 