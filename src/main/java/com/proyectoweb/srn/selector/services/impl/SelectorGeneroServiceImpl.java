/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.selector.services.impl;

import com.proyectoweb.srn.facade.GenericFacade;
import com.proyectoweb.srn.facade.SrnTblGeneroFacade;
import com.proyectoweb.srn.modelo.SrnTblGenero;
import com.proyectoweb.srn.selector.services.SelectorGeneroService;
import com.proyectoweb.srn.services.impl.GenericServiceImpl;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service(SelectorGeneroServiceImpl.NAME_SERVICE)
public class SelectorGeneroServiceImpl extends GenericServiceImpl<SrnTblGenero, String> implements SelectorGeneroService {

    static final String NAME_SERVICE = "selectorGeneroService";

    @Inject
    private SrnTblGeneroFacade generoFacade;

    @Override
    public GenericFacade<SrnTblGenero, String> getFacade() {
        return generoFacade;
    }

    @Override
    public List<SrnTblGenero> findAll() {
        return generoFacade.findAll();
    }
    
}
