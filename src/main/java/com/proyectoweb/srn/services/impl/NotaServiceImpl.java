/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.services.impl;

import com.proyectoweb.srn.facade.GenericFacade;
import com.proyectoweb.srn.facade.SrnTblNotaFacade;
import com.proyectoweb.srn.modelo.SrnTblNota;
import com.proyectoweb.srn.services.NotaService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;


@Service(RolServiceImpl.NAME_SERVICE)
public class NotaServiceImpl extends GenericServiceImpl<SrnTblNota, Integer> implements NotaService {

    static final String NAME_SERVICE = "notaService";
    
    @Inject
    private SrnTblNotaFacade notaFacade;
    
    @Override
    public GenericFacade<SrnTblNota, Integer> getFacade() {
        return notaFacade;
    }

    @Override
    public Integer findMaxId() throws Exception {
        return notaFacade.findMaxId();
    }

    @Override
    public List<SrnTblNota> buscarTodos() throws Exception {
        return notaFacade.buscarTodos();
    }

}
