/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.services.impl;

import com.proyectoweb.srn.facade.GenericFacade;
import com.proyectoweb.srn.facade.SrnTblTipoDocumentoFacade;
import com.proyectoweb.srn.modelo.SrnTblTipoDocumento;
import com.proyectoweb.srn.services.SelectorTipoDocService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;


@Service(SelectorTipoDocServiceImpl.NAME_SERVICE)
public class SelectorTipoDocServiceImpl extends GenericServiceImpl<SrnTblTipoDocumento, Integer> implements SelectorTipoDocService {

    static final String NAME_SERVICE = "selectorTipoDocServiceImpl";

    @Inject
    private SrnTblTipoDocumentoFacade tipoDocumentoFacade;

    @Override
    public GenericFacade<SrnTblTipoDocumento, Integer> getFacade() {
        return tipoDocumentoFacade;
    }

    @Override
    public List<SrnTblTipoDocumento> findAll() {
        return tipoDocumentoFacade.findAll();
    }

}
