/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.services.impl;

import com.proyectoweb.srn.facade.GenericFacade;
import com.proyectoweb.srn.facade.SrnTblGeneroFacade;
import com.proyectoweb.srn.modelo.SrnTblGenero;
import com.proyectoweb.srn.services.GeneroService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service(GeneroServiceImpl.NAME_SERVICE)
public class GeneroServiceImpl extends GenericServiceImpl<SrnTblGenero, String> implements GeneroService {

    static final String NAME_SERVICE = "generoService";

    @Inject
    private SrnTblGeneroFacade generoFacade;
    
    @Override
    public GenericFacade<SrnTblGenero, String> getFacade() {
        return generoFacade;
    }

    @Override
    public List<SrnTblGenero> buscarTodos() throws Exception {
        return generoFacade.buscarTodos();
    }

}
