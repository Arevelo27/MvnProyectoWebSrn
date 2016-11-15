/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.facade;

import com.proyectoweb.srn.modelo.SrnTblUsuario;
import java.util.List;

/**
 *
 * @author TSI
 */
public interface SrnTblUsuarioFacade extends GenericFacade<SrnTblUsuario, Integer> {

    Integer findMaxId() throws Exception;

    SrnTblUsuario findDocument(long document) throws Exception;

    SrnTblUsuario recuperarClave(String login, String email) throws Exception;

    SrnTblUsuario login(String login, String contrasena) throws Exception;

    boolean LoginControl(String login, String contrasena) throws Exception;

    List<SrnTblUsuario> findByCriterio(String criterio);
}
