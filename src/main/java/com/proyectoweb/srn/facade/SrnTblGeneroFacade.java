/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.facade;

import com.proyectoweb.srn.modelo.SrnTblGenero;
import java.util.List;

/**
 *
 * @author TSI
 */
public interface SrnTblGeneroFacade extends GenericFacade<SrnTblGenero, String> {

    List<SrnTblGenero> buscarTodos() throws Exception;
}
