/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.services.impl;

import com.proyectoweb.srn.facade.GenericFacade;
import com.proyectoweb.srn.facade.SrnTblEstadoFacade;
import com.proyectoweb.srn.modelo.SrnTblEstado;
import com.proyectoweb.srn.services.SelectorEstadoService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service(SelectorEstadoServiceImpl.NAME_SERVICE)
public class SelectorEstadoServiceImpl extends GenericServiceImpl<SrnTblEstado, Integer> implements SelectorEstadoService {

    static final String NAME_SERVICE = "selectorEstadoService";

    @Inject
    private SrnTblEstadoFacade estadoFacade;

    @Override
    public GenericFacade<SrnTblEstado, Integer> getFacade() {
        return estadoFacade;
    }

    @Override
    public List<SrnTblEstado> findAll() {
        return estadoFacade.findAll();
    }

}
