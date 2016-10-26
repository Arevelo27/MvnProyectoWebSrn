/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.mail.impl;

//import org.apache.commons.lang.text.StrBuilder;
import com.proyectoweb.srn.utilidades.FacesUtils;
import com.proyectoweb.srn.mail.MailService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
//import org.apache.commons.lang.text.StrBuilder;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service(MailServiceImpl.NAME_SERVICE)
public class MailServiceImpl implements MailService, Serializable {

    static final String NAME_SERVICE = "mailService";

    private static final long serialVersionUID = 1L;

    private JavaMailSenderImpl mailSender;
    
    Properties mail = new Properties();
    Properties props = new Properties();

    private InputStream entrada = null;

    private String username;
    private String password;
    private boolean enviado;

    @Override
    @Scheduled(fixedDelay = 10000000)
    public boolean send(String to, String subject, String text) {
        String usuario = subject;
        String clave = text;
        enviado = true;
        try {
            entrada = new FileInputStream(FacesUtils.getPath() + "/resources/" + "mail.properties");

            // cargamos el archivo de propiedades
            mail.load(entrada);

            /**
             * Obtenemos las propiedades y las imprimimos Parâmetros de conexão
             * com servidor Gmail
             */
            props.put(mail.getProperty("mail.smtp.host"), mail.getProperty("mail.vlr.gmail.com"));
            props.put(mail.getProperty("mail.socketfactory.port"), mail.getProperty("mail.vlr.socketfactory.port"));
            props.put(mail.getProperty("mail.socketfactory.class"), mail.getProperty("mail.vlr.sslsocketfactory"));
            props.put(mail.getProperty("mail.smtp.auth"), mail.getProperty("mail.vlr.auth"));
            props.put(mail.getProperty("mail.smtp.port"), mail.getProperty("mail.vlr.port"));

            username = mail.getProperty("mail.username");
            password = mail.getProperty("mail.password");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            enviado = false;
        } catch (IOException ex) {
            Logger.getLogger(MailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            enviado = false;
        } finally {
            if (entrada != null) {
                try {
                    entrada.close();
                } catch (IOException e) {
                    enviado = true;
                    e.printStackTrace();
                }
            }
        }

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        /**
         * Ativa Debug para session
         */
        session.setDebug(true);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username)); //Remetente
            //Destinatário(s)
            Address[] toUser = InternetAddress.parse(to);
            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Cambio de clave (SRN)...");//Assunto
            message.setText("!!Cordial saludo " + usuario + ". \n\n"
                    + " Tu clave se ha cambiado temporalmente <" + clave + ">"
                    + " \n"
                    + " \n"
                    + " Este correo es enviado por el sistema de correos de SRN"
                    + " \n"
                    + " \n"
                    + " Saludos...");
            /**
             * Método para enviar a mensages creados
             */
            Transport.send(message);

            System.out.println("Feito!!!");
//            return true;
        } catch (MessagingException e) {
            enviado = false;
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("Error MAIL: " + e.getMessage());
            enviado = false;
        }
        return enviado;
    }

}
