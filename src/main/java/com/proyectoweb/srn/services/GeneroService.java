/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.services;

import com.proyectoweb.srn.modelo.SrnTblGenero;
import java.util.List;

/**
 *
 * @author TSI
 */
public interface GeneroService extends GenericService<SrnTblGenero, String> {

    /**
     *
     * @return @throws Exception
     */
    List<SrnTblGenero> buscarTodos() throws Exception;
}
