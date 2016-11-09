/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.services.impl;

import com.proyectoweb.srn.facade.GenericFacade;
import com.proyectoweb.srn.facade.SrnTblTipoDocumentoFacade;
import com.proyectoweb.srn.modelo.SrnTblTipoDocumento;
import com.proyectoweb.srn.services.TipoDocService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service(TipoDocServiceImpl.NAME_SERVICE)
public class TipoDocServiceImpl extends GenericServiceImpl<SrnTblTipoDocumento, Integer> implements TipoDocService {

    static final String NAME_SERVICE = "tipoDocService";

    @Inject
    private SrnTblTipoDocumentoFacade tipoDocumentoFacade;


    @Override
    public GenericFacade<SrnTblTipoDocumento, Integer> getFacade() {
        return tipoDocumentoFacade;
    }
    
    @Override
    public Integer findMaxId() throws Exception {
        return tipoDocumentoFacade.findMaxId();
    }

    @Override
    public List<SrnTblTipoDocumento> buscarTodos() throws Exception {
        return tipoDocumentoFacade.buscarTodos();
    }
    
}
