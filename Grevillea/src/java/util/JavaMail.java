/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import negocio.entidade.Usuario;

/**
 *
 * @author pozenato
 */
public class JavaMail {

    public void enviarEmail(String emailDest, String emailCopia,
            String assunto, String corpo) throws Exception {

        Properties props = new Properties();
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("noreply.grevillea@gmail.com", "gre.villea");
                    }
                });
      session.setDebug(true);
        try {
            //Variaveis
            String d_email = "noreply.grevillea@gmail.com",
                    d_password = "gre.villea",
                    d_host = "smtp.gmail.com",
                    d_port = "465";
 
            //Modo debug para verificar os passos do envio
            props.put("mail.debug", "true");
             
            //Servidor SMTP
            props.put("mail.host", d_host);
             
            //Porta
            props.put("mail.smtp.port", d_port);
             
            //Necessario autenticacao
            props.put("mail.smtp.auth", "true");
             
            //Liga o SSL
            props.put("mail.smtp.ssl.enable", "true");
 
            //Pega a sessao com usuario e senha
            MimeMessage msg = new MimeMessage(session);
             
            //Coloca O corpo do titulo
            msg.setText(corpo);
             
            //Coloca o assunto
            msg.setSubject(assunto);
             
            //Coloca quem enviou
            msg.setFrom(new InternetAddress(d_email));
             
            //Coloca para quem sera enviado
            msg.addRecipient(Message.RecipientType.TO,  new InternetAddress(emailDest));
            
            if(!emailCopia.isEmpty()){
                msg.addRecipient(Message.RecipientType.CC, new InternetAddress(emailCopia));
            }
            //Envia a mensagem
            Transport.send(msg);
 
        } catch (MessagingException mex) {
            System.out.println("Falha no envio, exception: " + mex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void emailSenha (Usuario usuario) throws Exception{
                
        StringBuilder corpo = new StringBuilder();
        corpo.append("Caro(a):").append(usuario.getNomeusuario());
        corpo.append("\n");
        corpo.append("\n");
        corpo.append("Foi feita uma solicitação de alteração de senha para o seu usuário");
        corpo.append("\n");
        corpo.append("Segue abaixo a nova senha:");
        corpo.append("\n");
        corpo.append("\n").append(usuario.getSenha());
        corpo.append("\n");
        corpo.append("\n");
        corpo.append("Ao ingressar no sistema com a nova senha será solicitado a alteração de sua senha");
        corpo.append("\n");
        corpo.append("\n");
        corpo.append("\n");
        corpo.append("Esta é uma mensagem automática. Favor não responder a mesma.");
        corpo.append("\n");
        corpo.append("Em caso de dúvida, favor entrar em contato com o administrador do Sistema");
        
        JavaMail mail = new JavaMail();
        mail.enviarEmail(usuario.getEmail(),"", "Recuperar Senha - Grevillea", corpo.toString());
        
    }
    
    
}
