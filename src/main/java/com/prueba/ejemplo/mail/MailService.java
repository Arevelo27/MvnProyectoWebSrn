/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.ejemplo.mail;

/**
 *
 * @author TSI
 */
public interface MailService {

    void send(String to, String subject, String text);

}
