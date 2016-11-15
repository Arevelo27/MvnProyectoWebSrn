/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.services;

import com.proyectoweb.srn.modelo.SrnTblUsuario;
import java.util.List;

/**
 *
 * @author TSI
 */
public interface UsuarioService extends GenericService<SrnTblUsuario, Integer> {

    Integer findMaxId() throws Exception;
    SrnTblUsuario findDocument(long document) throws Exception;
    List<SrnTblUsuario> findByCriterio(String criterio);
}
