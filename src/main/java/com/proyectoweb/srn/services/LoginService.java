/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.services;

import com.proyectoweb.srn.modelo.SrnTblUsuario;

/**
 *
 * @author TSI
 */
public interface LoginService extends GenericService<SrnTblUsuario, Integer> {

    SrnTblUsuario recuperarClave(String login, String email) throws Exception;
    
    SrnTblUsuario login(String login, String contrasena) throws Exception;

    boolean LoginControl(String login, String contrasena) throws Exception;
}
