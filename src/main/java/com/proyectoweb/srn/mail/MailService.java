/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.mail;

/**
 *
 * @author TSI
 */
public interface MailService {

    boolean send(String to, String subject, String text);

}
