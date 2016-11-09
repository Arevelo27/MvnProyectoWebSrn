/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.facade;

import com.proyectoweb.srn.modelo.SrnTblTipoDocumento;
import java.util.List;

/**
 *
 * @author TSI
 */
public interface SrnTblTipoDocumentoFacade extends GenericFacade<SrnTblTipoDocumento, Integer>{

    Integer findMaxId() throws Exception;
    
    List<SrnTblTipoDocumento> buscarTodos() throws Exception;
    
}
