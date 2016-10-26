/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.ejemplo.mail.impl;

//import org.apache.commons.lang.text.StrBuilder;
import com.proyectoweb.srn.utilidades.UtilidadesSeguridad;
import com.prueba.ejemplo.mail.MailService;
import java.io.File;
import java.io.Serializable;
import java.util.Properties;
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

    @Override
    @Scheduled(fixedDelay = 10000000)
    public void send(String to, String subject, String text) {
        String usuario = "buscar_usuario_en_la_bd";
        String clave = "clave_temporal";

        Properties props = new Properties();
        /**
         * Parâmetros de conexão com servidor Gmail
         */
        props.put(UtilidadesSeguridad.SMTP_HOST, UtilidadesSeguridad.VLR_GMAIL_COM);
        props.put(UtilidadesSeguridad.SOCKETFACTORY_PORT, UtilidadesSeguridad.VLR_SOCKETFACTORY_PORT);
        props.put(UtilidadesSeguridad.SOCKETFACTORY_CLASS, UtilidadesSeguridad.VLR_SSLSOCKETFACTORY);
        props.put(UtilidadesSeguridad.SMTP_AUTH, UtilidadesSeguridad.VLR_AUTH);
        props.put(UtilidadesSeguridad.SMTP_PORT, UtilidadesSeguridad.VLR_PORT);

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("jteam2708@gmail.com", "Ahdaynhl");
                    }
                });
        /**
         * Ativa Debug para session
         */
        session.setDebug(true);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("jteam2708@gmail.com")); //Remetente
            //Destinatário(s)
            Address[] toUser = InternetAddress.parse(to);
            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(subject);//Assunto
            message.setText("!!Hola " + usuario + " \n\n"
                    + " Tu clave se ha cambiado temporalmente <"+clave+">"
                    + " \n"
                    + " \n"
                    + " Este correo es enviado por el sistema de correos de SRN");
            /**
             * Método para enviar a mensages creados
             */
            Transport.send(message);
            System.out.println("Feito!!!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("Error MAIL: " + e.getMessage());
        }
    }

}
