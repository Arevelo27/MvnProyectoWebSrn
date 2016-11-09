/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.services.impl;

import com.proyectoweb.srn.facade.GenericFacade;
import com.proyectoweb.srn.facade.SrnTblEstadoFacade;
import com.proyectoweb.srn.modelo.SrnTblEstado;
import com.proyectoweb.srn.services.EstadoService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service(EstadoServiceImpl.NAME_SERVICE)
public class EstadoServiceImpl extends GenericServiceImpl<SrnTblEstado, String> implements EstadoService {

    static final String NAME_SERVICE = "estadoService";

    @Inject
    private SrnTblEstadoFacade estadoFacade;

    @Override
    public GenericFacade<SrnTblEstado, String> getFacade() {
        return estadoFacade;
    }

    @Override
    public List<SrnTblEstado> buscarTodos() throws Exception {
        return estadoFacade.buscarTodos();
    }

}
