/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.services;

import com.proyectoweb.srn.modelo.SrnTblEstado;
import java.util.List;

/**
 *
 * @author TSI
 */
public interface EstadoService extends GenericService<SrnTblEstado, String> {

    /**
     *
     * @return @throws Exception
     */
    List<SrnTblEstado> buscarTodos() throws Exception;
}
